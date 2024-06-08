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
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import com.programacion.database.Conexion;
import com.programacion.modelo.Usuario;

import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class FrmGestionUsuarios extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablaUsuarios;
	private DefaultTableModel model;
	private JTextField txtModNombre;
	private JTextField txtModApellido;
	private JTextField txtModUsuario;
	private JTextField txtModCelular;
	private JTextField txtModClave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmGestionUsuarios frame = new FrmGestionUsuarios();
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
	public FrmGestionUsuarios() {
		setIconifiable(true);
		setClosable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
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
		String[] cabecera = {"id_usuario", "nombre", "apellido", "usuario", "clave", "celular"};
		model = new DefaultTableModel(cabecera, 0);
		tablaUsuarios = new JTable(model);
		tablaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaUsuarios.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
		scrollPane.setBounds(0, 0, 796, 174);
		panelTabla.add(scrollPane);
		
		PanelRound panelDisplay = new PanelRound();
		panelDisplay.setBackground(UIManager.getColor("CheckBox.background"));
		panelDisplay.setRoundTopRight(20);
		panelDisplay.setRoundTopLeft(20);
		panelDisplay.setRoundBottomRight(20);
		panelDisplay.setRoundBottomLeft(20);
		panelDisplay.setBounds(0, 0, 900, 300);
		getContentPane().add(panelDisplay);
		panelDisplay.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Gestionar Usuarios");
		lblNewLabel.setBounds(351, 43, 198, 25);
		panelDisplay.add(lblNewLabel);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		JPanel panelModCampos = new JPanel();
		panelModCampos.setBackground(Color.WHITE);
		panelModCampos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelModCampos.setBounds(30, 301, 840, 128);
		panelDisplay.add(panelModCampos);
		panelModCampos.setLayout(null);
		
		txtModNombre = new JTextField();
		txtModNombre.setBorder(new TitledBorder(null, "Nombre:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtModNombre.setBounds(24, 18, 200, 40);
		panelModCampos.add(txtModNombre);
		txtModNombre.setColumns(10);
		
		txtModApellido = new JTextField();
		txtModApellido.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Apellido:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtModApellido.setBounds(24, 73, 200, 40);
		panelModCampos.add(txtModApellido);
		txtModApellido.setColumns(10);
		
		txtModUsuario = new JTextField();
		txtModUsuario.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Usuario:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtModUsuario.setBounds(320, 18, 200, 40);
		panelModCampos.add(txtModUsuario);
		txtModUsuario.setColumns(10);
		
		txtModCelular = new JTextField();
		txtModCelular.setColumns(10);
		txtModCelular.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Celular:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtModCelular.setBounds(613, 18, 200, 40);
		panelModCampos.add(txtModCelular);
		
		txtModClave = new JTextField();
		txtModClave.setColumns(10);
		txtModClave.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Contrase\u00F1a:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtModClave.setBounds(320, 73, 200, 40);
		panelModCampos.add(txtModClave);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(28, 441, 120, 50);
		panelDisplay.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(177, 441, 120, 50);
		panelDisplay.add(btnEliminar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(735, 498, 120, 50);
		panelDisplay.add(btnSalir);
		
		cargarDatos();
		
		btnModificar.addActionListener(e -> {
			try {
				modificarUsuario();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		
		btnEliminar.addActionListener(e -> {
			try {
				eliminarUsuario();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		
		tablaUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = tablaUsuarios.getSelectedRow();
				
				if (fila != -1) 
				{
					txtModNombre.setText(model.getValueAt(fila, 1).toString());
					txtModApellido.setText(model.getValueAt(fila, 2).toString());
					txtModUsuario.setText(model.getValueAt(fila, 3).toString());
					txtModClave.setText(model.getValueAt(fila, 4).toString());
					txtModCelular.setText(model.getValueAt(fila, 5).toString());
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
		List<Usuario> lista = new ArrayList<Usuario>();
		
		Connection c = Conexion.conectar();
		String sql = "SELECT id_usuario, nombre, apellido, usuario, clave, celular FROM USUARIOS";
		Statement st;
		
		try {
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next())
			{
				Usuario user = new Usuario();
				user.setId_usuario(rs.getInt("id_usuario"));
				user.setNombre(rs.getString("nombre"));
				user.setApellido(rs.getString("apellido"));
				user.setUsuario(rs.getString("usuario"));
				user.setClave(rs.getString("clave"));
				user.setCelular(rs.getString("celular"));
				lista.add(user);
			}
			
			model.setRowCount(0);
			
			for (Usuario usuario : lista) 
			{
				Object[] row = { usuario.getId_usuario(), usuario.getNombre(), usuario.getApellido(), usuario.getUsuario(), usuario.getClave(), usuario.getCelular() };
				model.addRow(row);
			}
		} catch (SQLException e) {
			System.out.println("Problemas al cargar la tabla de usuarios: " + e);
		}
	}
	
	/**
	 * 
	 * Método para modificar un usuario
	 * 
	 **/
	private void modificarUsuario() 
	{
		try {
			int fila = tablaUsuarios.getSelectedRow();
			
			if (fila == -1) 
			{
				JOptionPane.showMessageDialog(null, "Seleccione un registro para modificar.");
				return;
			}
			
			int id_usuario = Integer.parseInt(model.getValueAt(fila, 0).toString());

			String nombre = txtModNombre.getText();
			String apellido = txtModApellido.getText();
			String usuario = txtModUsuario.getText();
			String clave = txtModClave.getText();
			String celular = txtModCelular.getText();

			if (nombre.isEmpty() || apellido.isEmpty() || usuario.isEmpty() || clave.isEmpty() || celular.isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Los campos con (*) no pueden estar vacíos.");
				if (nombre.isEmpty())
				{
					txtModNombre.setBackground(Color.red);
				}
				else
				{
					if (apellido.isEmpty())
					{
						txtModApellido.setBackground(Color.red);
					}
					else
					{
						if (usuario.isEmpty())
						{
							txtModUsuario.setBackground(Color.red);
						}
						else
						{
							if (clave.isEmpty())
							{
								txtModClave.setBackground(Color.red);
							}
							else
							{
								if (celular.isEmpty())
								{
									txtModCelular.setBackground(Color.red);
								}
							}
						}
					}
				}
				return;
			}
			
			Connection c = Conexion.conectar();
			String sql = "UPDATE USUARIOS SET nombre = ?, apellido = ?, usuario = ?, clave = ?, celular = ? WHERE id_usuario = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, nombre);
			pst.setString(2, apellido);
			pst.setString(3, usuario);
			pst.setString(4, clave);
			pst.setString(5, celular);
			pst.setInt(6, id_usuario);

			int filaModificar = pst.executeUpdate();
			System.out.println(pst.toString());

			if (filaModificar > 0) 
			{
				JOptionPane.showMessageDialog(this, "Usuario modificado exitosamente.");
				cargarDatos();
			}
		} catch (SQLException e) {
			System.out.println("Problemas al modificar: " + e);
		}
	}
	
	/**
	 * 
	 * Método para eliminar un usuario
	 * 
	 **/
	private void eliminarUsuario() 
	{
		try {
			int fila = tablaUsuarios.getSelectedRow();
			if (fila == -1) 
			{
				JOptionPane.showMessageDialog(this, "Seleccione un usuario para eliminar.");
				return;
			}

			int id_usuario = Integer.parseInt(model.getValueAt(fila, 0).toString());
			
			Connection c = Conexion.conectar();
			String sql = "DELETE FROM USUARIOS WHERE id_usuario = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, id_usuario);
			int filaModificar = pst.executeUpdate();
			System.out.println(pst.toString());
			if (filaModificar > 0) 
			{
				JOptionPane.showMessageDialog(this, "Usuario eliminado exitosamente.");
				cargarDatos();
			}
		} catch (SQLException e) {
			System.out.println("Problemas al eliminar: " + e);
		}
	}
}
