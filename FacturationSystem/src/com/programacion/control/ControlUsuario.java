package com.programacion.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.programacion.database.Conexion;
import com.programacion.modelo.Usuario;

public class ControlUsuario 
{
	// CONTROLAR EL LOGIN
	public boolean loginUsuario(Usuario persona)
	{
		boolean respuesta = false;
		Connection c = Conexion.conectar();
		String sql = "SELECT usuario, clave FROM USUARIOS WHERE usuario = '" + persona.getUsuario() + "' AND clave = '" + persona.getClave() + "'";
		Statement st;
		try {
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				respuesta = true;
			}
		} catch (SQLException e) {
			System.out.println("Error al iniciar sesión");
			JOptionPane.showMessageDialog(null, "Error al iniciar sesión");
		}
		return respuesta;
	}
	
	// ALMACENAR USUARIOS
	public boolean guardar(Usuario usuario)
	{
		boolean respuesta = false;
		Connection c = Conexion.conectar();
		Statement st;
		
		try {
			PreparedStatement consulta = c.prepareStatement("INSERT INTO USUARIOS VALUES(?, ?, ?, ?, ?, ?)");
			consulta.setInt(1, 0);
			consulta.setString(2, usuario.getNombre());
			consulta.setString(3, usuario.getApellido());
			consulta.setString(4, usuario.getUsuario());
			consulta.setString(5, usuario.getClave());
			consulta.setString(6, usuario.getCelular());
			
			if (consulta.executeUpdate() > 0)				
			{
				respuesta = true;
			}
			c.close();
		} catch (Exception e) {
			System.out.println("Problemas al guardar la información del usuario: " + e);
		}
		return respuesta;
	}
	// CONSULTA PARA SABER SI YA EXISTE TAL USUARIO
	public boolean evitarRepetidos(String usuario)
	{
		boolean respuesta = false;
		String sql = "SELECT usuario FROM USUARIOS WHERE usuario = '" + usuario + "';";
		Statement st;
		
		try {
			Connection c = Conexion.conectar();
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next())
			{
				respuesta = true;
			}
			c.close();
		} catch (Exception e) {
			System.out.println("Problemas al consultar los usuarios: " + e);
		}
		return respuesta;
	}
}