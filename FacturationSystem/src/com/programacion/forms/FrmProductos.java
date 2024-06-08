package com.programacion.forms;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.programacion.control.ControlProducto;
import com.programacion.database.Conexion;
import com.programacion.modelo.Categoria;
import com.programacion.modelo.Producto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class FrmProductos extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtCandidad;
	private JTextField txtPrecio;
	private JTextField txtDescripcion;
	private JComboBox comboCategoria;
	
	int obtenerIdCategoria = 0;
	private JButton btnSalir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmProductos frame = new FrmProductos();
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
	public FrmProductos() {
		setIconifiable(true);
		setClosable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 400, 400);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Nuevo producto");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitulo.setBounds(124, 6, 152, 24);
		panel.add(lblTitulo);
		
		txtNombre = new JTextField();
		txtNombre.setBorder(new TitledBorder(null, "Nombre:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 15));
		txtNombre.setBounds(50, 42, 300, 45);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtCandidad = new JTextField();
		txtCandidad.setBorder(new TitledBorder(null, "Cantidad:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtCandidad.setFont(new Font("Arial", Font.PLAIN, 15));
		txtCandidad.setColumns(10);
		txtCandidad.setBounds(50, 102, 300, 45);
		panel.add(txtCandidad);
		
		txtPrecio = new JTextField();
		txtPrecio.setBorder(new TitledBorder(null, "Precio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtPrecio.setFont(new Font("Arial", Font.PLAIN, 15));
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(50, 159, 300, 45);
		panel.add(txtPrecio);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Descripci\u00F3n:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtDescripcion.setFont(new Font("Arial", Font.PLAIN, 15));
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(50, 216, 300, 45);
		panel.add(txtDescripcion);
		
		JLabel lblCategora = new JLabel("Categoría:");
		lblCategora.setFont(new Font("Arial", Font.BOLD, 15));
		lblCategora.setBounds(50, 273, 73, 24);
		panel.add(lblCategora);
		
		comboCategoria = new JComboBox();
		comboCategoria.setFont(new Font("Arial", Font.PLAIN, 15));
		comboCategoria.setModel(new DefaultComboBoxModel(new String[] {"Seleccione categoría:"}));
		comboCategoria.setBounds(139, 273, 200, 27);
		panel.add(comboCategoria);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto producto = new Producto();
				ControlProducto controlProducto = new ControlProducto();
				String categoria = "";
				categoria = comboCategoria.getSelectedItem().toString().trim();
				
				if (txtNombre.getText().equals("") || txtCandidad.getText().equals("") || txtPrecio.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Los campos con (*) son obligatorios, por favor llénelos");
					txtNombre.setBackground(Color.red);
					txtCandidad.setBackground(Color.red);
					txtPrecio.setBackground(Color.red);
				}
				else
				{
					if (!controlProducto.evitarRepetidos(txtNombre.getText().trim()))
					{
						if (categoria.equalsIgnoreCase("Seleccione categoría:"))
						{
							JOptionPane.showMessageDialog(null, "Seleccione una categoría por favor");
						}
						else
						{
							try {
								producto.setNombre(txtNombre.getText().trim());
								producto.setStock(Integer.parseInt(txtCandidad.getText().trim()));
								String precioTxt = "";
								double precio = 0.0;
								precioTxt = txtPrecio.getText().trim();
								boolean aux = false;
								
								for (int i = 0; i < precioTxt.length(); i++)
								{
									if (precioTxt.charAt(i) == ',')
									{
										String precioNuevo = precioTxt.replace(",", ".");
										precio = Double.parseDouble(precioNuevo);
										aux = true;
									}
								}
								
								if (aux == true)
								{
									producto.setPrecio(precio);
								}
								else
								{
									precio = Double.parseDouble(precioTxt);
									producto.setPrecio(precio);
								}
								
								producto.setDescripcion(txtDescripcion.getText().trim());
								
								cargarIdCategoria();
								producto.setId_categoria(obtenerIdCategoria);
								
								if (controlProducto.guardar(producto))
								{
									JOptionPane.showMessageDialog(null, "Registro guardado");
									txtNombre.setBackground(Color.green);
									txtCandidad.setBackground(Color.green);
									txtPrecio.setBackground(Color.green);
									txtDescripcion.setBackground(Color.green);
									
									limpiar();
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Problemas al guardar");
								}
							} catch (HeadlessException | NumberFormatException ex) {
								System.out.println("Problemas en: " + ex);
							}
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "El producto ya existe");
					}
				}
			}
		});
		btnGuardar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnGuardar.setBounds(33, 309, 150, 40);
		panel.add(btnGuardar);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setFont(new Font("Arial", Font.PLAIN, 15));
		btnSalir.setBounds(200, 308, 150, 40);
		panel.add(btnSalir);
		
		cargarCategorias();
	}
	
	/*
	 * 
	 * Para cargar las categorías
	 * 
	 * */
	public void cargarCategorias()
	{
		Connection c = Conexion.conectar();
		String sql = "SELECT descripcion FROM CATEGORIAS";
		Statement st;
		
		try {
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next())
			{
				comboCategoria.addItem(rs.getString(1));
			}
			c.close();
		} catch (SQLException e) {
			System.out.println("Problemas al cargar las categorías: " + e);
		}
	}
	
	/*
	 * 
	 * Para cargar el id_categoria
	 * 
	 * */
	public int cargarIdCategoria()
	{
		String sql = "SELECT * FROM CATEGORIAS WHERE descripcion = '" + this.comboCategoria.getSelectedItem() + "'";
		Statement st;
		try {
			Connection c = Conexion.conectar();
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next())
			{
				obtenerIdCategoria = rs.getInt("id_categoria");
			}
		} catch (Exception e) {
			System.out.println("Problemas al obtener el ID categoría");
		}
		return obtenerIdCategoria;
	}
	/*
	 * 
	 * Para limpiar los txt
	 * 
	 * */
	public void limpiar()
	{
		txtNombre.setText("");
		txtCandidad.setText("");
		txtPrecio.setText("");
		txtDescripcion.setText("");
	}
}
