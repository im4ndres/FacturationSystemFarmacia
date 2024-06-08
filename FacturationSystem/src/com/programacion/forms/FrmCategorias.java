package com.programacion.forms;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

import com.programacion.control.ControlCategoria;
import com.programacion.modelo.Categoria;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class FrmCategorias extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtNCategoria;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCategorias frame = new FrmCategorias();
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
	public FrmCategorias() {
		setIconifiable(true);
		setClosable(true);
		getContentPane().setBackground(new Color(0, 0, 0, 0));
		setTitle("Categorías");
		setBounds(100, 100, 450, 300);
		setSize(new Dimension(400, 200));
		getContentPane().setLayout(null);
		
		JLabel lblTituloNC = new JLabel("Nueva categoría");
		lblTituloNC.setFont(new Font("JetBrains Mono", Font.BOLD, 20));
		lblTituloNC.setBounds(98, 18, 180, 27);
		getContentPane().add(lblTituloNC);
		
		JLabel lblNCategoria = new JLabel("Categoría:");
		lblNCategoria.setFont(new Font("JetBrains Mono", Font.BOLD, 15));
		lblNCategoria.setBounds(51, 66, 90, 21);
		getContentPane().add(lblNCategoria);
		
		txtNCategoria = new JTextField();
		txtNCategoria.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		txtNCategoria.setBounds(153, 64, 196, 26);
		getContentPane().add(txtNCategoria);
		txtNCategoria.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Categoria categoria = new Categoria();
				ControlCategoria controlcat = new ControlCategoria();
			
				if (txtNCategoria.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "No se admite el campo vacío, por favor llénalo");
				}
				else
				{
					if (!controlcat.evitarRepetidos(txtNCategoria.getText().trim()))
					{
						categoria.setDescripcion(txtNCategoria.getText().trim());
						if (controlcat.guardar(categoria))
						{
							JOptionPane.showMessageDialog(null, "Categoría guardada con éxito");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Problemas al guardar");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "La categoría ya existe");
					}
				}
				txtNCategoria.setText("");
			}
		});
		btnGuardar.setFont(new Font("JetBrains Mono", Font.BOLD, 15));
		btnGuardar.setBounds(129, 102, 117, 40);
		getContentPane().add(btnGuardar);
		
		PanelRound panel = new PanelRound();
		panel.setRoundTopRight(20);
		panel.setRoundTopLeft(20);
		panel.setRoundBottomRight(20);
		panel.setRoundBottomLeft(20);
		panel.setBounds(0, 0, 400, 200);
		getContentPane().add(panel);
		panel.setLayout(null);

	}
}
