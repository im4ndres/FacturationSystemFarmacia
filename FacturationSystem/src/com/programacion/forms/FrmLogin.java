package com.programacion.forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.MatteBorder;

import com.programacion.control.ControlUsuario;
import com.programacion.modelo.Usuario;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JLabel lblUsuario;
	private JLabel lblClave;
	private JPasswordField txtClave;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public FrmLogin() {
		setBackground(Color.WHITE);
		setFont(new Font("PT Mono", Font.BOLD, 12));
		setTitle("Facturation System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setSize(400, 400);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setBackground(Color.BLACK);
		lblLogin.setForeground(Color.BLACK);
		lblLogin.setFont(new Font("Montserrat", Font.BOLD, 20));
		lblLogin.setBounds(166, 24, 67, 26);
		contentPane.add(lblLogin);
		
		JLabel lblLoginIco = new JLabel("");
		lblLoginIco.setForeground(Color.WHITE);
		lblLoginIco.setIcon(new ImageIcon("/Users/luisandres/eclipse-workspace/FacturationSystem/src/resources/login.png"));
		lblLoginIco.setBounds(135, 62, 130, 135);
		contentPane.add(lblLoginIco);
		
		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(Color.BLACK);
		lblUsuario.setFont(new Font("Montserrat", Font.PLAIN, 13));
		lblUsuario.setBounds(47, 217, 61, 16);
		contentPane.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER)
				{
					txtClave.requestFocus();
				}
			}
		});
		txtUsuario.setSelectionColor(UIManager.getColor("EditorPane.selectionBackground"));
		txtUsuario.setDisabledTextColor(Color.GRAY);
		txtUsuario.setForeground(Color.BLACK);
		txtUsuario.setFont(new Font("Montserrat", Font.PLAIN, 13));
		txtUsuario.setBackground(UIManager.getColor("CheckBox.background"));
		txtUsuario.setBounds(135, 212, 202, 26);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		lblClave = new JLabel("Contraseña:");
		lblClave.setForeground(Color.BLACK);
		lblClave.setFont(new Font("Montserrat", Font.PLAIN, 13));
		lblClave.setBounds(47, 255, 88, 16);
		contentPane.add(lblClave);
		
		txtClave = new JPasswordField();
		txtClave.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER)
				{
					if (!txtUsuario.getText().isEmpty() && !txtClave.getText().isEmpty())
					{
						ControlUsuario ctrlusu = new ControlUsuario();
						Usuario usuario = new Usuario();
						usuario.setUsuario(txtUsuario.getText().trim());
						usuario.setClave(txtClave.getText().trim());
						if (ctrlusu.loginUsuario(usuario))
						{
							JOptionPane.showMessageDialog(null, "Login con éxito");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Usuario o clave incorrectos");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Ingrese los datos requeridos");
					}
				}
			}
		});
		txtClave.setForeground(Color.BLACK);
		txtClave.setBackground(UIManager.getColor("CheckBox.background"));
		txtClave.setBounds(135, 250, 202, 26);
		contentPane.add(txtClave);
		
		JButton cmdIngresar = new JButton("Ingresar");
		cmdIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (!txtUsuario.getText().isEmpty() && !txtClave.getText().isEmpty())
				{
					ControlUsuario ctrlusu = new ControlUsuario();
					Usuario usuario = new Usuario();
					usuario.setUsuario(txtUsuario.getText().trim());
					usuario.setClave(txtClave.getText().trim());
					if (ctrlusu.loginUsuario(usuario))
					{
						//JOptionPane.showMessageDialog(null, "Login con éxito");
						FrmMenu menu = new FrmMenu();
						menu.setVisible(true);
						menu.setLocationRelativeTo(null);
						menu.setBackground(new Color(0, 0, 0, 0));
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Usuario o clave incorrectos");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Ingrese los datos requeridos");
				}
			}
		});
		cmdIngresar.setForeground(Color.BLACK);
		cmdIngresar.setBackground(new Color(0, 0, 255));
		cmdIngresar.setFont(new Font("Montserrat", Font.PLAIN, 13));
		cmdIngresar.setBounds(141, 301, 117, 29);
		contentPane.add(cmdIngresar);
		
	}
}
