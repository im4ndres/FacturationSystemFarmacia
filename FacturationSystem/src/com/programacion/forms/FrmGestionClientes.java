package com.programacion.forms;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import com.programacion.database.Conexion;
import com.programacion.modelo.Cliente;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class FrmGestionClientes extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablaClientes;
	private DefaultTableModel model;
	private JTextField txtModNombre;
	private JTextField txtModApellido;
	private JTextField txtModCedula;
	private JTextField txtModCelular;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmGestionClientes frame = new FrmGestionClientes();
					frame.setVisible(true);
					frame.setBackground(new Color(0, 0, 0, 0));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmGestionClientes() {
		getContentPane().setBackground(new Color(0, 0, 0, 0));
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);
		
		JPanel panelTabla = new JPanel();
		panelTabla.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelTabla.setBounds(50, 109, 796, 174);
		getContentPane().add(panelTabla);
		panelTabla.setLayout(null);
		
		/**
		 * 
		 * Establecemos la cabecera de la tabla
		 * 
		 **/
		String[] cabecera = {"id_cliente", "nombre", "apellido", "cedula", "celular"};
		model = new DefaultTableModel(cabecera, 0);
		tablaClientes = new JTable(model);
		tablaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaClientes.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(tablaClientes);
		scrollPane.setBounds(0, 0, 796, 174);
		panelTabla.add(scrollPane);
		
		JPanel panelDisplay = new JPanel();
		panelDisplay.setBackground(UIManager.getColor("CheckBox.background"));
		panelDisplay.setBounds(0, 0, 900, 600);
		getContentPane().add(panelDisplay);
		panelDisplay.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Gestionar Clientes");
		lblNewLabel.setBounds(354, 43, 191, 25);
		panelDisplay.add(lblNewLabel);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		JPanel panelModCampos = new JPanel();
		panelModCampos.setBackground(Color.GRAY);
		panelModCampos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelModCampos.setBounds(30, 301, 840, 145);
		panelDisplay.add(panelModCampos);
		panelModCampos.setLayout(null);
		
		txtModNombre = new JTextField();
		txtModNombre.setBorder(new TitledBorder(null, "Nombre:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtModNombre.setBounds(147, 16, 200, 45);
		panelModCampos.add(txtModNombre);
		txtModNombre.setColumns(10);
		
		txtModApellido = new JTextField();
		txtModApellido.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Apellido:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtModApellido.setBounds(399, 16, 200, 45);
		panelModCampos.add(txtModApellido);
		txtModApellido.setColumns(10);
		
		txtModCedula = new JTextField();
		txtModCedula.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "C\u00E9dula:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtModCedula.setBounds(399, 81, 200, 45);
		panelModCampos.add(txtModCedula);
		txtModCedula.setColumns(10);
		
		txtModCelular = new JTextField();
		txtModCelular.setColumns(10);
		txtModCelular.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Celular:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtModCelular.setBounds(147, 81, 200, 45);
		panelModCampos.add(txtModCelular);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(30, 483, 150, 50); 
		panelDisplay.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(200, 483, 150, 50);
		panelDisplay.add(btnEliminar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(724, 483, 120, 50);
		panelDisplay.add(btnSalir);
		
		cargarDatos();
		
		btnModificar.addActionListener(e -> {
			try {
				modificarCliente();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		
		btnEliminar.addActionListener(e -> {
			try {
				eliminarCliente();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		
		tablaClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = tablaClientes.getSelectedRow();
				
				if (fila != -1) 
				{
					txtModNombre.setText(model.getValueAt(fila, 1).toString());
					txtModApellido.setText(model.getValueAt(fila, 2).toString());
					txtModCedula.setText(model.getValueAt(fila, 3).toString());
					txtModCelular.setText(model.getValueAt(fila, 4).toString());
				}
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
		List<Cliente> lista = new ArrayList<Cliente>();
		
		Connection c = Conexion.conectar();
		String sql = "SELECT id_cliente, nombre, apellido, cedula, celular FROM CLIENTES";
		Statement st;
		
		try {
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next())
			{
				Cliente cl = new Cliente();
				cl.setId_cliente(rs.getInt("id_cliente"));
				cl.setNombre(rs.getString("nombre"));
				cl.setApellido(rs.getString("apellido"));
				cl.setCedula(rs.getString("cedula"));
				cl.setCelular(rs.getString("celular"));
				lista.add(cl);
			}
			
			model.setRowCount(0);
			
			for (Cliente cliente : lista) 
			{
				Object[] row = { cliente.getId_cliente(), cliente.getNombre(), cliente.getApellido(), cliente.getCedula(), cliente.getCelular() };
				model.addRow(row);
			}
		} catch (SQLException e) {
			System.out.println("Problemas al cargar la tabla de clientes: " + e);
		}
	}
	
	/**
	 * 
	 * Método para modificar un cliente
	 * 
	 **/
	private void modificarCliente() 
	{
		try {
			int fila = tablaClientes.getSelectedRow();
			
			if (fila == -1) 
			{
				JOptionPane.showMessageDialog(null, "Seleccione un registro para modificar.");
				return;
			}
			
			int id_cliente = Integer.parseInt(model.getValueAt(fila, 0).toString());

			String nombre = txtModNombre.getText();
			String apellido = txtModApellido.getText();
			String cedula = txtModCedula.getText();
			String celular = txtModCelular.getText();

			if (apellido.isEmpty() || cedula.isEmpty() || celular.isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Los campos con (*) no pueden estar vacíos.");
				if (apellido.isEmpty())
				{
					txtModApellido.setBackground(Color.red);
				}
				else
				{
					if (cedula.isEmpty())
					{
						txtModCedula.setBackground(Color.red);
					}
					else
					{
						if (celular.isEmpty())
						{
							txtModCelular.setBackground(Color.red);
						}
					}
				}
				return;
			}
			
			Connection c = Conexion.conectar();
			String sql = "UPDATE CLIENTES SET nombre = ?, apellido = ?, cedula = ?, celular = ? WHERE id_cliente = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, nombre);
			pst.setString(2, apellido);
			pst.setString(3, cedula);
			pst.setString(4, celular);
			pst.setInt(5, id_cliente);

			int filaModificar = pst.executeUpdate();
			System.out.println(pst.toString());

			if (filaModificar > 0) 
			{
				JOptionPane.showMessageDialog(this, "Cliente modificado exitosamente.");
				cargarDatos();
			}
		} catch (SQLException e) {
			System.out.println("Problemas al modificar: " + e);
		}
	}
	
	/**
	 * 
	 * Método para eliminar clientes
	 * 
	 **/
	private void eliminarCliente() 
	{
		try {
			int selectedRow = tablaClientes.getSelectedRow();
			if (selectedRow == -1) 
			{
				JOptionPane.showMessageDialog(this, "Seleccione un cliente para eliminar.");
				return;
			}

			int id_cliente = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
			
			Connection c = Conexion.conectar();
			String sql = "DELETE FROM CLIENTES WHERE id_cliente = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, id_cliente);
			int filaModificar = pst.executeUpdate();
			System.out.println(pst.toString());
			if (filaModificar > 0) 
			{
				JOptionPane.showMessageDialog(this, "Cliente eliminado exitosamente.");
				cargarDatos();
			}
		} catch (SQLException e) {
			System.out.println("Problemas al eliminar: " + e);
		}
	}
}
