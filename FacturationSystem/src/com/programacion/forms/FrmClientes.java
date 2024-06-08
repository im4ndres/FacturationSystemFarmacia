package com.programacion.forms;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.programacion.control.ControlCliente;
import com.programacion.modelo.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class FrmClientes extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCedula;
	private JTextField txtCelular;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmClientes frame = new FrmClientes();
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
	public FrmClientes() {
		setClosable(true);
		setIconifiable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		PanelRound panel = new PanelRound();
		panel.setRoundTopRight(20);
		panel.setRoundTopLeft(20);
		panel.setRoundBottomRight(20);
		panel.setRoundBottomLeft(20);
		panel.setBounds(0, 0, 376, 354);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Nuevo Cliente");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitulo.setBounds(121, 6, 133, 24);
		panel.add(lblTitulo);
		
		txtNombre = new JTextField();
		txtNombre.setBorder(new TitledBorder(null, "Nombre:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 15));
		txtNombre.setBounds(38, 42, 300, 50);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBorder(new TitledBorder(null, "Apellido:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtApellido.setFont(new Font("Arial", Font.PLAIN, 15));
		txtApellido.setColumns(10);
		txtApellido.setBounds(38, 104, 300, 50);
		panel.add(txtApellido);
		
		txtCedula = new JTextField();
		txtCedula.setBorder(new TitledBorder(null, "C\u00E9dula:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtCedula.setFont(new Font("Arial", Font.PLAIN, 15));
		txtCedula.setColumns(10);
		txtCedula.setBounds(38, 166, 300, 50);
		panel.add(txtCedula);
		
		txtCelular = new JTextField();
		txtCelular.setBorder(new TitledBorder(null, "Celular:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtCelular.setFont(new Font("Arial", Font.PLAIN, 15));
		txtCelular.setColumns(10);
		txtCelular.setBounds(38, 228, 300, 50);
		panel.add(txtCelular);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = new Cliente();
				ControlCliente controlCliente = new ControlCliente();
				
				if (txtNombre.getText().equals("") || txtApellido.getText().equals("") || txtCedula.getText().equals("") || txtCelular.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "No se admiten campos vacíos, por favor llénelos.");
					txtNombre.setBackground(Color.red);
					txtApellido.setBackground(Color.red);
					txtCedula.setBackground(Color.red);
					txtCelular.setBackground(Color.red);
				}
				else
				{
					if (!controlCliente.evitarRepetidos(txtCedula.getText().trim(), txtCelular.getText().trim()))
					{
						try {
							cliente.setNombre(txtNombre.getText().trim());
							cliente.setApellido(txtApellido.getText().trim());
							cliente.setCedula(txtCedula.getText().trim());
							cliente.setCelular(txtCelular.getText().trim());
							
							if (controlCliente.guardar(cliente))
							{
								JOptionPane.showMessageDialog(null, "Registro guardado");
								txtNombre.setBackground(Color.green);
								txtApellido.setBackground(Color.green);
								txtCedula.setBackground(Color.green);
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
						JOptionPane.showMessageDialog(null, "El cliente ya existe");
						txtNombre.setBackground(Color.yellow);
						txtApellido.setBackground(Color.yellow);
						txtCedula.setBackground(Color.yellow);
						txtCelular.setBackground(Color.yellow);
						limpiar();
					}
				}
			}
		});
		btnGuardar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnGuardar.setBounds(69, 290, 117, 40);
		panel.add(btnGuardar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setFont(new Font("Arial", Font.PLAIN, 15));
		btnSalir.setBounds(210, 290, 117, 40);
		panel.add(btnSalir);
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
		txtCedula.setText("");
		txtCelular.setText("");
	}
}
