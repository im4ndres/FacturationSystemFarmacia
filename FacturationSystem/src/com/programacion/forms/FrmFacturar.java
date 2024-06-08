package com.programacion.forms;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import com.programacion.control.Control_RegistrarVenta;
import com.programacion.control.VentaPDF;
import com.programacion.database.Conexion;
import com.programacion.modelo.Detalles;
import com.programacion.modelo.Factura;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmFacturar extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCI;
	private JTextField txtCantidad;
	private JTextField txtSubtotal;
	public static JTextField txtTotal;
	private JTextField txtEfectivo;
	private JTextField txtCambio;
	private JTable tablaFacturar;
	public static DefaultTableModel model;
	private JComboBox comboProducto;
	private JComboBox comboClientes;
	public JScrollPane scrollPane;

	private int id_producto = 0;
	private String nombre = "";
	private int cantidadProductostock = 0;
	private double precio_unitario = 0.0;
	
	int obtenerIdProducto = 0;
	int obtenerIdCliente = 0;
	
	// cantidad de producto a comprar
	private int cantidad = 0;
	// cantidad por precio
	private double subtotal = 0.0;
	private double total_pagar = 0.0;
	
	// variables para calculos
	private double subtotalGeneral = 0.0;
	private double totalPagarGeneral = 0.0;
	
	
	// id del detalle de venta
	private int id_detallesAux = 1;
	
	// lista para detalles
	ArrayList<Detalles> listaProductos = new ArrayList<Detalles>();
	private Detalles producto;
	
	private int id_cliente = 0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmFacturar frame = new FrmFacturar();
					frame.setBackground(new Color(0, 0, 0, 0));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public FrmFacturar() {
		setIconifiable(true);
		setClosable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		PanelRound panelDisplay = new PanelRound();
		panelDisplay.setRoundTopRight(20);
		panelDisplay.setRoundTopLeft(20);
		panelDisplay.setRoundBottomRight(20);
		panelDisplay.setRoundBottomLeft(20);
		panelDisplay.setBounds(0, 0, 800, 554);
		contentPane.add(panelDisplay);
		panelDisplay.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Facturación");
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblTitulo.setBounds(310, 18, 180, 27);
		panelDisplay.add(lblTitulo);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblCliente.setBounds(53, 81, 60, 16);
		panelDisplay.add(lblCliente);
		
		comboClientes = new JComboBox();
		comboClientes.setFont(new Font("SansSerif", Font.PLAIN, 15));
		comboClientes.addItem("Seleccione cliente:");
		comboClientes.setBounds(125, 78, 200, 27);
		panelDisplay.add(comboClientes);
		
		txtCI = new JTextField();
		txtCI.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtCI.setBounds(433, 76, 200, 26);
		panelDisplay.add(txtCI);
		txtCI.setColumns(10);
		
		JLabel lblCi = new JLabel("CI:");
		lblCi.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblCi.setBounds(401, 81, 20, 16);
		panelDisplay.add(lblCi);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String buscarCliente = txtCI.getText().trim();
				Connection c = Conexion.conectar();
				String sql = "SELECT nombre, apellido FROM CLIENTES WHERE cedula = '" + buscarCliente + "'";
				Statement st;
				
				try {
					st = c.createStatement();
					ResultSet rs = st.executeQuery(sql);
					
					if (rs.next())
					{
						comboClientes.setSelectedItem(rs.getString("nombre") + " " + rs.getString("apellido"));
					}
					else
					{
						comboClientes.setSelectedItem("Seleccione cliente:");
						JOptionPane.showMessageDialog(null, "Cédula del cliente incorrecta o no se encuentra en registrado");
					}
					txtCI.setText("");
					c.close();
				} catch (Exception ex) {
					System.out.println("Error al buscar cliente: " + ex);
				}
			}
		});
		btnBuscar.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnBuscar.setBounds(645, 76, 100, 29);
		panelDisplay.add(btnBuscar);
		
		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblProducto.setBounds(39, 128, 74, 16);
		panelDisplay.add(lblProducto);
		
		comboProducto = new JComboBox();
		comboProducto.setFont(new Font("SansSerif", Font.PLAIN, 15));
		comboProducto.addItem("Seleccione producto:");
		comboProducto.setBounds(125, 124, 200, 27);
		panelDisplay.add(comboProducto);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblCantidad.setBounds(347, 126, 74, 16);
		panelDisplay.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtCantidad.setBounds(433, 122, 200, 26);
		panelDisplay.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		JButton btnAnadir = new JButton("Añadir");
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String combo = comboProducto.getSelectedItem().toString();
				
				// seleccion del producto
				if (combo.equalsIgnoreCase("Seleccione producto:"))
				{
					JOptionPane.showMessageDialog(null, "Seleccione un producto por favor.");
				}
				else
				{
					// ingresar cantidad
					if (!txtCantidad.getText().isEmpty())
					{
						// control de caracteres numericos
						boolean validar = validando(txtCantidad.getText());
						if (validar == true)
						{
							// cantidad > 0
							if (Integer.parseInt(txtCantidad.getText()) > 0)
							{
								cantidad = Integer.parseInt(txtCantidad.getText());
								// metodo para obtener datos de producto
								datosProducto();
								// la cantidad de productos seleccionados no debe ser mayor al stock de BD
								if (cantidad <= cantidadProductostock)
								{
									subtotal = precio_unitario * cantidad;
									total_pagar = subtotal;
									
									// redondeo decimal
									subtotal = (double) Math.round(subtotal * 100) / 100;
									total_pagar = (double) Math.round(subtotal * 100) / 100;
									
									// creación de un nuevo producto
									producto = new Detalles(id_detallesAux, 
											1, 
											id_producto, 
											nombre, 
											Integer.parseInt(txtCantidad.getText()), 
											precio_unitario, 
											subtotal, 
											total_pagar
									);
									
									// Añadir a la lista
									listaProductos.add(producto);
									JOptionPane.showMessageDialog(null, "Producto agregado.");
									id_detallesAux ++;
									
									txtCantidad.setText("");
										
									cargarTabla();
									
									// Total pagar - metodo
									calcularTotalPagar();
								}
								else
								{
									JOptionPane.showMessageDialog(null, "La cantidad es mayor al stock.");
								}
							}
							else
							{
								JOptionPane.showMessageDialog(null, "La cantidad no puede ser cero, ni números negativos.");
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "En cantidad no se permiten caracteres no numéricos.");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Ingrese la cantidad de productos por favor.");
					}
				}
			}
		});
		
		btnAnadir.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnAnadir.setBounds(645, 122, 100, 29);
		panelDisplay.add(btnAnadir);
		
		JPanel panelTabla = new JPanel();
		panelTabla.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelTabla.setBounds(50, 182, 700, 200);
		panelDisplay.add(panelTabla);
		panelTabla.setLayout(null);
		
		/**
		 * 
		 * Establecemos la cabecera de la tabla
		 * 
		 **/
		String[] cabecera = {"N˚", "Nombre", "Cantidad", "P. Unitario", "Subtotal", "Total Pagar", "Acción"};
		model = new DefaultTableModel(cabecera, 0);
		tablaFacturar = new JTable(model);
		tablaFacturar.addMouseListener(new MouseAdapter() {
			int idArray = 0;
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila_point = tablaFacturar.rowAtPoint(e.getPoint());
				int columna_point = 0;
				
				if (fila_point > -1)
				{
					idArray = (int) model.getValueAt(fila_point, columna_point);
				}
				
				int opcion = JOptionPane.showConfirmDialog(null, "¿Desea eleminar el producto?");
				
				switch (opcion)
				{
					case 0:
						listaProductos.remove(idArray - 1);
						calcularTotalPagar();
						cargarTabla();
						break;
					case 1:
						break;
					default:
						break;
				}
			}
		});
		tablaFacturar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaFacturar.setFillsViewportHeight(true);
		
		scrollPane = new JScrollPane(tablaFacturar);
		scrollPane.setBounds(0, 0, 700, 200);
		panelTabla.add(scrollPane);
		
		JLabel lblSubtotal = new JLabel("Subtotal:");
		lblSubtotal.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblSubtotal.setBounds(53, 399, 69, 16);
		panelDisplay.add(lblSubtotal);
		
		txtSubtotal = new JTextField();
		txtSubtotal.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtSubtotal.setBounds(134, 394, 200, 26);
		panelDisplay.add(txtSubtotal);
		txtSubtotal.setColumns(10);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblTotal.setBounds(78, 436, 44, 16);
		panelDisplay.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtTotal.setColumns(10);
		txtTotal.setBounds(134, 432, 200, 26);
		panelDisplay.add(txtTotal);
		
		JLabel lblEfectivo = new JLabel("Efectivo:");
		lblEfectivo.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblEfectivo.setBounds(53, 474, 69, 16);
		panelDisplay.add(lblEfectivo);
		
		txtEfectivo = new JTextField();
		txtEfectivo.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtEfectivo.setColumns(10);
		txtEfectivo.setBounds(134, 469, 200, 26);
		panelDisplay.add(txtEfectivo);
		
		JLabel lblCambio = new JLabel("Cambio:");
		lblCambio.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblCambio.setBounds(53, 514, 69, 16);
		panelDisplay.add(lblCambio);
		
		txtCambio = new JTextField();
		txtCambio.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtCambio.setColumns(10);
		txtCambio.setBounds(134, 509, 200, 26);
		panelDisplay.add(txtCambio);
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtEfectivo.getText().isEmpty())
				{
					boolean validar = validandoDouble(txtEfectivo.getText());
					// validar solo numeros double
					if (validar == true)
					{
						// validar efectivo > 0
						double efectivo = Double.parseDouble(txtEfectivo.getText().trim());
						double tp = Double.parseDouble(txtTotal.getText().trim());
						
						if (efectivo < tp)
						{
							JOptionPane.showMessageDialog(null, "El monto efectivo es insuficiente.");
						}
						else
						{
							double cambio = (efectivo - tp);
							double cambioRedondeado = (double) Math.round(cambio * 100d) / 100;
							String camb = String.valueOf(cambioRedondeado);
							txtCambio.setText(camb); 
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "No se admiten caracteres no numéricos.");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Ingrese dinero en efectivo para calcular el cambio.");
				}
				
			}
		});
		btnCalcular.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnCalcular.setBounds(337, 509, 117, 29);
		panelDisplay.add(btnCalcular);
		
		JButton btnGenerarFactura = new JButton("Generar factura");
		btnGenerarFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Factura factura = new Factura();
				Detalles detalles = new Detalles();
				Control_RegistrarVenta control_RegistrarVenta = new Control_RegistrarVenta();
				
				String fechaActual = "";
				Date date = new Date();
				fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(date);
				
				if (!comboClientes.getSelectedItem().equals("Seleccione cliente:"))
				{
					if (listaProductos.size() > 0)
					{
						// obtener el id_cliente
						obtenerIdCliente();
						// resgitrar factura
						factura.setId_factura(0);
						factura.setId_cliente(id_cliente);
						factura.setValor_pagar(Double.parseDouble(txtTotal.getText()));
						factura.setFecha_emision(fechaActual);
						
						if (control_RegistrarVenta.guardar(factura))
						{
							JOptionPane.showMessageDialog(null, "Factura registrada.");
							
							// generar factura
							VentaPDF facturapdf = new VentaPDF();
							facturapdf.datosCliente(id_cliente);
							facturapdf.generarFactura();
							
							//guardar detalle
							for (Detalles elemento : listaProductos)
							{
								detalles.setId_detalle(0);
								detalles.setId_factura(0);
								detalles.setId_producto(elemento.getId_producto());
								detalles.setCantidad(elemento.getCantidad());
								detalles.setPrecio_unitario(elemento.getPrecio_unitario());
								detalles.setSubtotal(elemento.getSubtotal());
								detalles.setTotal_pagar(elemento.getTotal_pagar());
								
								if (control_RegistrarVenta.guardarDetalle(detalles))
								{
									System.out.println("Detalle de venta registrado");
									
									txtSubtotal.setText("0.0");
									txtTotal.setText("0.0");
									txtEfectivo.setText("0.0");
									txtCambio.setText("0.0");
									id_detallesAux = 1;
									
									cargarClientes();
									
									restarAlStock(elemento.getId_producto(), elemento.getCantidad());
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Error al guardar detalle.");
								}
							}
							// vaciar lista
							listaProductos.clear();
							cargarTabla();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Error al guardar factura.");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Por favor, seleccione un producto.");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Por favor, seleccione un cliente.");
				}
			}
		});
		btnGenerarFactura.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnGenerarFactura.setBounds(433, 399, 200, 70);
		panelDisplay.add(btnGenerarFactura);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnSalir.setBounds(613, 498, 150, 50);
		panelDisplay.add(btnSalir);
		
		cargarClientes();
		
		cargarProductos();
	}
	/*
	 * 
	 * Cargar clientes en comboClientes
	 * 
	 * */
	public void cargarClientes()
	{
		Connection c = Conexion.conectar();
		String sql = "SELECT * FROM CLIENTES";
		Statement st;
		
		try {
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next())
			{
				comboClientes.addItem(rs.getString("nombre") + " " + rs.getString("apellido"));
			}
			c.close();
		} catch (SQLException e) {
			System.out.println("Problemas al cargar los clientes: " + e);
		}
	}
	
	/*
	 * 
	 * Para cargar el id_cliente
	 * 
	 * */
	public int cargarIdCliente()
	{
		String sql = "SELECT nombre, apellido FROM CLIENTES WHERE nombre = '" + this.comboProducto.getSelectedItem() + "' AND apellido = '" + this.comboClientes.getSelectedItem() + "'";
		Statement st;
		try {
			Connection c = Conexion.conectar();
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next())
			{
				obtenerIdCliente = rs.getInt("id_cliente");
			}
		} catch (Exception e) {
			System.out.println("Problemas al obtener el ID cliente");
		}
		return obtenerIdCliente;
	}
	
	
	/*
	 * 
	 * Cargar productos en comboProductos
	 * 
	 * */
	public void cargarProductos()
	{
		Connection c = Conexion.conectar();
		String sql = "SELECT nombre FROM PRODUCTOS";
		Statement st;
		
		try {
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next())
			{
				comboProducto.addItem(rs.getString(1));
			}
			c.close();
		} catch (SQLException e) {
			System.out.println("Problemas al cargar los productos: " + e);
		}
	}
	
	/*
	 * 
	 * Para cargar el id_producto
	 * 
	 * */
	public int cargarIdProducto()
	{
		String sql = "SELECT * FROM PRODUCTOS WHERE nombre = '" + this.comboProducto.getSelectedItem() + "'";
		Statement st;
		try {
			Connection c = Conexion.conectar();
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next())
			{
				obtenerIdProducto = rs.getInt("id_producto");
			}
			c.close();
		} catch (Exception e) {
			System.out.println("Problemas al obtener el ID producto");
		}
		return obtenerIdProducto;
	}
	
	/*
	 * 
	 * Validacion de caracteres no numericos
	 * 
	 * */
	private boolean validando(String valor)
	{
		try {
			int num = Integer.parseInt(valor);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	/*
	 * 
	 * Validacion de caracteres no numericos (double)
	 * 
	 * */
	private boolean validandoDouble(String valor)
	{
		try {
			double num = Double.parseDouble(valor);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	/*
	 * 
	 * Para mostrar datos del producto
	 * 
	 * */
	private void datosProducto()
	{
		try {
			String sql = "SELECT * FROM PRODUCTOS WHERE nombre = '" + comboProducto.getSelectedItem() + "'";
			Connection c = Conexion.conectar();
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next())
			{
				id_producto = rs.getInt("id_producto");
				nombre = rs.getString("nombre");
				cantidadProductostock = rs.getInt("stock");
				precio_unitario = rs.getDouble("precio");
				
			}
		} catch (SQLException e) {
			System.out.println("Problemas al obtener datos del producto: " + e);
		}
	}
	
	/*
	 * 
	 * Cargar tabla de facturacion
	 * 
	 * */
	public void cargarTabla()
	{
		model.setRowCount(listaProductos.size());
		for (int i = 0; i < listaProductos.size(); i ++)
		{
			model.setValueAt(i + 1, i, 0);
			model.setValueAt(listaProductos.get(i).getNombre(), i, 1);
			model.setValueAt(listaProductos.get(i).getCantidad(), i, 2);
			model.setValueAt(listaProductos.get(i).getPrecio_unitario(), i, 3);
			model.setValueAt(listaProductos.get(i).getSubtotal(), i, 4);
			model.setValueAt(listaProductos.get(i).getTotal_pagar(), i, 5);
			model.setValueAt("Eliminar", i, 6);
		}
	}
	
	// metodo total pagar
	private void calcularTotalPagar()
	{
		subtotalGeneral = 0;
		totalPagarGeneral = 0;
		
		for (Detalles d : listaProductos)
		{
			subtotalGeneral += d.getSubtotal();
			totalPagarGeneral += d.getTotal_pagar();
		}
		
		subtotalGeneral = (double) Math.round(subtotalGeneral * 100) / 100;
		totalPagarGeneral = (double) Math.round(totalPagarGeneral * 100) / 100;
		
		// presentancion en sus txt
		txtSubtotal.setText(String.valueOf(subtotalGeneral));
		txtTotal.setText(String.valueOf(totalPagarGeneral));
	}
	
	// obtener id_cliente
	private void obtenerIdCliente() 
	{
		try {
			String sql = "SELECT * FROM CLIENTES WHERE CONCAT(nombre, ' ',apellido) = '" + comboClientes.getSelectedItem() + "'";
			Connection c = Conexion.conectar();
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next())
			{
				id_cliente = rs.getInt("id_cliente");
			}
		} catch (SQLException e) {
			System.out.println("Error al obtener el id_cliente: " + e);
		}
	}
	
	// resyar lo vendido al stock
	private void restarAlStock(int id_producto, int cantidad)
	{
		int stockProductos = 0;
		try {
			Connection c = Conexion.conectar();
			String sql = "SELECT id_producto, stock FROM PRODUCTOS WHERE id_producto = '" + id_producto + "'";
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next())
			{
				stockProductos = rs.getInt("stock");
			}
			c.close();
		} catch (SQLException e) {
			System.out.println("Error al restar cantidad 1: " + e);
		}
		
		try {
			Connection c = Conexion.conectar();
			PreparedStatement consulta = c.prepareStatement("UPDATE PRODUCTOS SET stock = ? WHERE id_producto = '" + id_producto + "'");
			int cantidadNueva = stockProductos - cantidad;
			consulta.setInt(1, cantidadNueva);
			
			if (consulta.executeUpdate() > 0)
			{
				System.out.println("Restado con éxito");
			}
			c.close();
		} catch (SQLException e) {
			System.out.println("Error al restar cantidad 2: " + e);
		}
	}
}
