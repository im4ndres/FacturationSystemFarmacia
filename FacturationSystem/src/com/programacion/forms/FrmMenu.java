package com.programacion.forms;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDesktopPane;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMenu frame = new FrmMenu();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1300, 850);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 1300, 850);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JDesktopPane dpaneMenus = new JDesktopPane();
		dpaneMenus.setBackground(Color.DARK_GRAY);
		dpaneMenus.setBounds(150, 75, 1000, 700);
		panel.add(dpaneMenus);
		dpaneMenus.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1300, 50);
		panel.add(menuBar);
		
		JMenu mnUsuarios = new JMenu("Usuarios");
		mnUsuarios.setPreferredSize(new Dimension(260, 19));
		mnUsuarios.setFont(new Font("SansSerif", Font.BOLD, 20));
		menuBar.add(mnUsuarios);
		
		JMenuItem cmdUsuarioNuevo = new JMenuItem("Usuario nuevo");
		cmdUsuarioNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmUsuarios frmUsuarios = new FrmUsuarios();
				dpaneMenus.add(frmUsuarios);
				frmUsuarios.setVisible(true);
			}
		});
		cmdUsuarioNuevo.setFont(new Font("SansSerif", Font.PLAIN, 20));
		mnUsuarios.add(cmdUsuarioNuevo);
		
		JMenuItem mntmGestionarUsuarios = new JMenuItem("Gestionar usuarios");
		mntmGestionarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmGestionUsuarios frmGestionUsuarios = new FrmGestionUsuarios();
				dpaneMenus.add(frmGestionUsuarios);
				frmGestionUsuarios.setVisible(true);
			}
		});
		mntmGestionarUsuarios.setFont(new Font("SansSerif", Font.PLAIN, 20));
		mnUsuarios.add(mntmGestionarUsuarios);
		
		JMenu mnCategorías = new JMenu("Categorías");
		mnCategorías.setPreferredSize(new Dimension(260, 19));
		mnCategorías.setFont(new Font("SansSerif", Font.BOLD, 20));
		menuBar.add(mnCategorías);
		
		JMenuItem mntmCategoraNueva = new JMenuItem("Categoría Nueva");
		mntmCategoraNueva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmCategorias frmCategorias = new FrmCategorias();
				dpaneMenus.add(frmCategorias);
				frmCategorias.setVisible(true);
			}
		});
		mntmCategoraNueva.setFont(new Font("SansSerif", Font.PLAIN, 20));
		mnCategorías.add(mntmCategoraNueva);
		
		JMenuItem mntmGestionarCategoras = new JMenuItem("Gestionar Categorías");
		mntmGestionarCategoras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmGestionCategorias frmGestionCategorias = new FrmGestionCategorias();
				dpaneMenus.add(frmGestionCategorias);
				frmGestionCategorias.setVisible(true);
			}
		});
		mntmGestionarCategoras.setFont(new Font("SansSerif", Font.PLAIN, 20));
		mnCategorías.add(mntmGestionarCategoras);
		
		JMenu mnProductos = new JMenu("Productos");
		mnProductos.setPreferredSize(new Dimension(260, 19));
		mnProductos.setFont(new Font("SansSerif", Font.BOLD, 20));
		menuBar.add(mnProductos);
		
		JMenuItem mntmProductoNuevo = new JMenuItem("Producto Nuevo");
		mntmProductoNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmProductos frmProductos = new FrmProductos();
				dpaneMenus.add(frmProductos);
				frmProductos.setVisible(true);
			}
		});
		mntmProductoNuevo.setFont(new Font("SansSerif", Font.PLAIN, 20));
		mnProductos.add(mntmProductoNuevo);
		
		JMenuItem mntmGestionarProductos = new JMenuItem("Gestionar Productos");
		mntmGestionarProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmGestionProductos frmGestionProductos = new FrmGestionProductos();
				dpaneMenus.add(frmGestionProductos);
				frmGestionProductos.setVisible(true);
			}
		});
		mntmGestionarProductos.setFont(new Font("SansSerif", Font.PLAIN, 20));
		mnProductos.add(mntmGestionarProductos);
		
		JMenu mnClientes = new JMenu("Clientes");
		mnClientes.setPreferredSize(new Dimension(260, 19));
		mnClientes.setFont(new Font("SansSerif", Font.BOLD, 20));
		menuBar.add(mnClientes);
		
		JMenuItem mntmClienteNuevo = new JMenuItem("Cliente Nuevo");
		mntmClienteNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmClientes frmClientes = new FrmClientes();
				dpaneMenus.add(frmClientes);
				frmClientes.setVisible(true);
			}
		});
		mntmClienteNuevo.setFont(new Font("SansSerif", Font.PLAIN, 20));
		mnClientes.add(mntmClienteNuevo);
		
		JMenuItem mntmGestionarClientes = new JMenuItem("Gestionar Clientes");
		mntmGestionarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmGestionClientes frmGestionClientes = new FrmGestionClientes();
				dpaneMenus.add(frmGestionClientes);
				frmGestionClientes.setVisible(true);
				
			}
		});
		mntmGestionarClientes.setFont(new Font("SansSerif", Font.PLAIN, 20));
		mnClientes.add(mntmGestionarClientes);
		
		JMenu mnFacturar = new JMenu("Facturar");
		mnFacturar.setPreferredSize(new Dimension(260, 19));
		mnFacturar.setFont(new Font("SansSerif", Font.BOLD, 20));
		menuBar.add(mnFacturar);
		
		JMenuItem mntmFacturar = new JMenuItem("Facturar");
		mntmFacturar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmFacturar frmFacturar = new FrmFacturar();
				dpaneMenus.add(frmFacturar);
				frmFacturar.setVisible(true);
			}
		});
		mntmFacturar.setFont(new Font("SansSerif", Font.PLAIN, 20));
		mnFacturar.add(mntmFacturar);
		
	}
}
