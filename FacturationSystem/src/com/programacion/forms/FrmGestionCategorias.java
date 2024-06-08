package com.programacion.forms;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import com.programacion.database.Conexion;
import com.programacion.modelo.Categoria;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmGestionCategorias extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTable tablaCategorias;
	private DefaultTableModel model;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmGestionCategorias frame = new FrmGestionCategorias();
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
	public FrmGestionCategorias() {
		setIconifiable(true);
		setClosable(true);
		getContentPane().setBackground(new Color(0, 0, 0, 0));
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(50, 109, 500, 174);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		/**
		 * 
		 * Establecemos la cabecera de la tabla
		 * 
		 **/
		String[] cabecera = {"id_categoria", "descripcion"};
		model = new DefaultTableModel(cabecera, 0);
		tablaCategorias = new JTable(model);
		tablaCategorias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaCategorias.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(tablaCategorias);
		scrollPane.setBounds(0, 0, 500, 174);
		panel.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("CheckBox.background"));
		panel_1.setBounds(0, 0, 600, 400);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(51, 295, 150, 50);
		panel_1.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(225, 295, 150, 50);
		panel_1.add(btnEliminar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(403, 295, 150, 50);
		panel_1.add(btnSalir);
		
		JLabel lblNewLabel = new JLabel("Gestionar Categorías");
		lblNewLabel.setBounds(191, 43, 218, 25);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		cargarDatos();
		
		btnModificar.addActionListener(e -> {
			try {
				modificarCategoria();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		
		btnEliminar.addActionListener(e -> {
			try {
				eliminarCategoria();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}
	
	/**
	 * 
	 * Método para cargar los datos de Workbench a tabla
	 * 
	 **/
	public void cargarDatos()
	{
		List<Categoria> lista = new ArrayList<Categoria>();
		
		Connection c = Conexion.conectar();
		String sql = "SELECT id_categoria, descripcion FROM CATEGORIAS";
		Statement st;
		
		try {
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next())
			{
				Categoria a = new Categoria();
				a.setId_categoria(rs.getInt("id_categoria"));
				a.setDescripcion(rs.getString("descripcion"));
				lista.add(a);
			}
			
			model.setRowCount(0);
			
			for (Categoria categoria : lista) 
			{
				Object[] row = { categoria.getId_categoria(), categoria.getDescripcion() };
				model.addRow(row);
			}
		} catch (SQLException e) {
			System.out.println("Problemas al cargar la tabla de categorías: " + e);
		}
	}
	
	/**
	 * 
	 * Método para modificar el nombre de las categorías
	 * 
	 **/
	private void modificarCategoria() 
	{
		try {
			int fila = tablaCategorias.getSelectedRow();
			if (fila == -1) 
			{
				JOptionPane.showMessageDialog(this, "Seleccione un registro para modificar.");
				return;
			}
			int id_categoria = Integer.parseInt(model.getValueAt(fila, 0).toString());

			String descripcion = JOptionPane.showInputDialog("Ingrese el nuevo nombre de la categoría:");
			if (descripcion == null) 
			{
				return;
			}

			Connection c = Conexion.conectar();
			String sql = "UPDATE CATEGORIAS SET descripcion = ? WHERE id_categoria = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, descripcion);
			pst.setInt(2, id_categoria);
			int rowsAffected = pst.executeUpdate();
			System.out.println(pst.toString());

			if (rowsAffected > 0) 
			{
				JOptionPane.showMessageDialog(this, "Categoría modificada exitosamente.");
				cargarDatos();
			}
		} catch (SQLException e) {
			System.out.println("Problemas al modificar: " + e);
		}
	}
	
	/**
	 * 
	 * Método para eliminar una categorías
	 * 
	 **/
	private void eliminarCategoria() 
	{
		try {
			int selectedRow = tablaCategorias.getSelectedRow();
			if (selectedRow == -1) 
			{
				JOptionPane.showMessageDialog(this, "Seleccione una categoría para eliminar.");
				return;
			}

			int id_categoria = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
			
			Connection c = Conexion.conectar();
			String sql = "DELETE FROM CATEGORIAS WHERE id_categoria = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, id_categoria);
			int rowsAffected = pst.executeUpdate();
			System.out.println(pst.toString());
			if (rowsAffected > 0) 
			{
				JOptionPane.showMessageDialog(this, "Categoría eliminada exitosamente.");
				cargarDatos();
			}
		} catch (SQLException e) {
			System.out.println("Problemas al eliminar: " + e);
		}
	}
}
