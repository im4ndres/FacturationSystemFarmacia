package com.programacion.forms;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import com.programacion.database.Conexion;
import com.programacion.modelo.Categoria;
import com.programacion.modelo.Producto;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;

public class FrmGestionProductos extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablaProductos;
	private DefaultTableModel model;
	private JTextField txtModNombre;
	private JTextField txtModStock;
	private JTextField txtModPrecio;
	private JComboBox comboModCategoria;
	private JTextField txtDescripcion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmGestionProductos frame = new FrmGestionProductos();
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
	public FrmGestionProductos() {
		setClosable(true);
		setIconifiable(true);
		getContentPane().setBackground(new Color(0, 0, 0, 0));
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(50, 109, 796, 174);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		/**
		 * 
		 * Establecemos la cabecera de la tabla
		 * 
		 **/
		String[] cabecera = {"id_producto", "id_categoria", "nombre", "stock", "precio", "descripcion"};
		model = new DefaultTableModel(cabecera, 0);
		tablaProductos = new JTable(model);
		tablaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaProductos.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(tablaProductos);
		scrollPane.setBounds(0, 0, 796, 174);
		panel.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("CheckBox.background"));
		panel_1.setBounds(0, 0, 900, 550);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(30, 480, 150, 50);
		panel_1.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(259, 480, 150, 50);
		panel_1.add(btnEliminar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(730, 480, 120, 50);
		panel_1.add(btnSalir);
		
		JLabel lblNewLabel = new JLabel("Gestionar Productos");
		lblNewLabel.setBounds(345, 24, 210, 25);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(30, 301, 840, 135);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		txtModNombre = new JTextField();
		txtModNombre.setBorder(new TitledBorder(null, "Nombre:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtModNombre.setBounds(30, 14, 200, 50);
		panel_2.add(txtModNombre);
		txtModNombre.setColumns(10);
		
		txtModStock = new JTextField();
		txtModStock.setBorder(new TitledBorder(null, "Stock:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtModStock.setBounds(30, 76, 200, 50);
		panel_2.add(txtModStock);
		txtModStock.setColumns(10);
		
		txtModPrecio = new JTextField();
		txtModPrecio.setBorder(new TitledBorder(null, "Precio:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtModPrecio.setBounds(320, 14, 200, 50);
		panel_2.add(txtModPrecio);
		txtModPrecio.setColumns(10);
		
		comboModCategoria = new JComboBox();
		comboModCategoria.setFont(new Font("Arial", Font.PLAIN, 15));
		comboModCategoria.setBounds(320, 76, 200, 50);
		panel_2.add(comboModCategoria);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Descripci\u00F3n:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtDescripcion.setBounds(600, 14, 200, 50);
		panel_2.add(txtDescripcion);
		
		cargarDatos();
		
		btnModificar.addActionListener(e -> {
			try {
				modificarProducto();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		
		btnEliminar.addActionListener(e -> {
			try {
				eliminarProducto();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		
		cargarCategorias();
		
		tablaProductos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = tablaProductos.getSelectedRow();
				
				if (fila != -1) 
				{
					txtModNombre.setText(model.getValueAt(fila, 2).toString());
					txtModStock.setText(model.getValueAt(fila, 3).toString());
					txtModPrecio.setText(model.getValueAt(fila, 4).toString());
					comboModCategoria.setSelectedItem(obtenerCategoria(Integer.parseInt(model.getValueAt(fila, 1).toString())));
					txtDescripcion.setText(model.getValueAt(fila, 5).toString());
				}
			}
		});
	}
	
	/**
	 * 
	 * Método para cargar las categorías al comboBox
	 * 
	 **/
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
				comboModCategoria.addItem(rs.getString(1));
			}
			c.close();
		} catch (SQLException e) {
			System.out.println("Problemas al cargar las categorías: " + e);
		}
	}
	
	/**
	 * 
	 * Método para cargar los datos de Workbench a tabla
	 * 
	 **/
	public void cargarDatos()
	{
		List<Producto> lista = new ArrayList<Producto>();
		
		Connection c = Conexion.conectar();
		String sql = "SELECT id_producto, id_categoria, nombre, stock, precio, descripcion FROM PRODUCTOS";
		Statement st;
		
		try {
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next())
			{
				Producto prod = new Producto();
				prod.setId_producto(rs.getInt("id_producto"));
				prod.setId_categoria(rs.getInt("id_categoria"));
				prod.setNombre(rs.getString("nombre"));
				prod.setStock(rs.getInt("stock"));
				prod.setPrecio(rs.getDouble("precio"));
				prod.setDescripcion(rs.getString("descripcion"));
				lista.add(prod);
			}
			
			model.setRowCount(0);
			
			for (Producto producto : lista) 
			{
				Object[] row = { producto.getId_producto(), producto.getId_categoria(), producto.getNombre(), producto.getStock(), producto.getPrecio(), producto.getDescripcion() };
				model.addRow(row);
			}
		} catch (SQLException e) {
			System.out.println("Problemas al cargar la tabla de categorías: " + e);
		}
	}
	
	/**
	 * 
	 * Método para modificar un producto
	 * 
	 **/
	private void modificarProducto() 
	{
		try {
			int fila = tablaProductos.getSelectedRow();
			
			if (fila == -1) 
			{
				JOptionPane.showMessageDialog(null, "Seleccione un registro para modificar.");
				return;
			}
			
			int id_producto = Integer.parseInt(model.getValueAt(fila, 0).toString());

			String nombre = txtModNombre.getText();
			int stock = Integer.parseInt(txtModStock.getText());
			double precio = Double.parseDouble(txtModPrecio.getText());
			String descripcion = txtDescripcion.getText();
			String categoria = comboModCategoria.getSelectedItem().toString();
			int id_categoria = obtenerIdCategoria(categoria);

			Connection c = Conexion.conectar();
			String sql = "UPDATE PRODUCTOS SET nombre = ?, stock = ?, precio = ?, id_categoria = ?, descripcion = ? WHERE id_producto = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, nombre);
			pst.setInt(2, stock);
			pst.setDouble(3, precio);
			pst.setInt(4, id_categoria);
			pst.setString(5, descripcion);
			pst.setInt(6, id_producto);

			int filaModificar = pst.executeUpdate();
			System.out.println(pst.toString());

			if (filaModificar > 0) 
			{
				JOptionPane.showMessageDialog(this, "Producto modificado exitosamente.");
				cargarDatos();
			}
		} catch (SQLException e) {
			System.out.println("Problemas al modificar: " + e);
		}
	}
	
	private int obtenerIdCategoria(String descripcion) 
	{
		Connection c = Conexion.conectar();
		String sql = "SELECT id_categoria FROM CATEGORIAS WHERE descripcion = ?";
		
		try {
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, descripcion);
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) 
			{
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Problemas al obtener ID de la categoría: " + e);
		}
		return -1; 
	}

	private String obtenerCategoria(int idCategoria) 
	{
		Connection c = Conexion.conectar();
		String sql = "SELECT descripcion FROM CATEGORIAS WHERE id_categoria = ?";
		
		try {
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, idCategoria);
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) 
			{
				return rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("Problemas al obtener la descripción de la categoría: " + e);
		}
		return null;
	}
	
	/**
	 * 
	 * Método para eliminar una categorías
	 * 
	 **/
	private void eliminarProducto() 
	{
		try {
			int selectedRow = tablaProductos.getSelectedRow();
			if (selectedRow == -1) 
			{
				JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar.");
				return;
			}

			int id_producto = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
			
			Connection c = Conexion.conectar();
			String sql = "DELETE FROM PRODUCTOS WHERE id_producto = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, id_producto);
			int rowsAffected = pst.executeUpdate();
			System.out.println(pst.toString());
			if (rowsAffected > 0) 
			{
				JOptionPane.showMessageDialog(this, "Producto eliminado exitosamente.");
				cargarDatos();
			}
		} catch (SQLException e) {
			System.out.println("Problemas al eliminar: " + e);
		}
	}
}
