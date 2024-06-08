package com.programacion.forms;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.programacion.control.ControlUsuario;
import com.programacion.modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class FrmUsuarios extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtUsuario;
	private JTextField txtClave;
	private JTextField txtCelular;
	private JButton btnSalir_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmUsuarios frame = new FrmUsuarios();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmUsuarios() {
		setIconifiable(true);
		setClosable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 710, 230);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Nuevo Cliente");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitulo.setBounds(288, 6, 133, 24);
		panel.add(lblTitulo);
		
		txtNombre = new JTextField();
		txtNombre.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Nombre:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 15));
		txtNombre.setBounds(30, 48, 200, 40);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBorder(new TitledBorder(null, "Apellido:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtApellido.setFont(new Font("Arial", Font.PLAIN, 15));
		txtApellido.setColumns(10);
		txtApellido.setBounds(30, 115, 200, 40);
		panel.add(txtApellido);
		
		txtUsuario = new JTextField();
		txtUsuario.setBorder(new TitledBorder(null, "Usuario:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtUsuario.setFont(new Font("Arial", Font.PLAIN, 15));
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(254, 48, 200, 40);
		panel.add(txtUsuario);
		
		txtClave = new JTextField();
		txtClave.setBorder(new TitledBorder(null, "Clave:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtClave.setFont(new Font("Arial", Font.PLAIN, 15));
		txtClave.setColumns(10);
		txtClave.setBounds(254, 115, 200, 40);
		panel.add(txtClave);
		
		txtCelular = new JTextField();
		txtCelular.setBorder(new TitledBorder(null, "Celular:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtCelular.setFont(new Font("Arial", Font.PLAIN, 15));
		txtCelular.setColumns(10);
		txtCelular.setBounds(479, 48, 200, 40);
		panel.add(txtCelular);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario usuario = new Usuario();
				ControlUsuario controlUsuario = new ControlUsuario();
				
				if (txtNombre.getText().equals("") || txtApellido.getText().equals("") || txtUsuario.getText().equals("") || txtClave.getText().equals("") || txtCelular.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "No se admiten campos vacíos, por favor llénelos.");
					txtNombre.setBackground(Color.red);
					txtApellido.setBackground(Color.red);
					txtUsuario.setBackground(Color.red);
					txtClave.setBackground(Color.red);
					txtCelular.setBackground(Color.red);
				}
				else
				{
					if (!controlUsuario.evitarRepetidos(txtUsuario.getText().trim()))
					{
						try {
							usuario.setNombre(txtNombre.getText().trim());
							usuario.setApellido(txtApellido.getText().trim());
							usuario.setUsuario(txtUsuario.getText().trim());
							usuario.setCelular(txtClave.getText().trim());
							usuario.setCelular(txtCelular.getText().trim());
							
							if (controlUsuario.guardar(usuario))
							{
								JOptionPane.showMessageDialog(null, "Registro guardado");
								txtNombre.setBackground(Color.green);
								txtApellido.setBackground(Color.green);
								txtUsuario.setBackground(Color.green);
								txtClave.setBackground(Color.green);
								txtCelular.setBackground(Color.green);
								
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
					else
					{
						JOptionPane.showMessageDialog(null, "El usuario ya existe");
						txtNombre.setBackground(Color.yellow);
						txtApellido.setBackground(Color.yellow);
						txtUsuario.setBackground(Color.yellow);
						txtClave.setBackground(Color.yellow);
						txtCelular.setBackground(Color.yellow);
						
						limpiar();
					}
				}
			}
		});
		btnGuardar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnGuardar.setBounds(520, 92, 117, 40);
		panel.add(btnGuardar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setFont(new Font("Arial", Font.PLAIN, 15));
		btnSalir.setBounds(6, 184, 117, 40);
		panel.add(btnSalir);
		
		btnSalir_1 = new JButton("Salir");
		btnSalir_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir_1.setFont(new Font("Arial", Font.PLAIN, 15));
		btnSalir_1.setBounds(520, 133, 117, 40);
		panel.add(btnSalir_1);
	}
	
	/*
	 * 
	 * Para limpiar los txt
	 * 
	 * */
	public void limpiar()
	{
		txtNombre.setText("");
		txtApellido.setText("");
		txtUsuario.setText("");
		txtClave.setText("");
		txtCelular.setText("");
	}
}
