package com.programacion.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.programacion.database.Conexion;
import com.programacion.modelo.Cliente;

public class ControlCliente 
{
	// ALMACENAR CLIENTES
	public boolean guardar(Cliente cliente)
	{
		boolean respuesta = false;
		Connection c = Conexion.conectar();
		Statement st;
		
		try {
			PreparedStatement consulta = c.prepareStatement("INSERT INTO CLIENTES VALUES(?, ?, ?, ?, ?)");
			consulta.setInt(1, 0);
			consulta.setString(2, cliente.getNombre());
			consulta.setString(3, cliente.getApellido());
			consulta.setString(4, cliente.getCedula());
			consulta.setString(5, cliente.getCelular());
			
			if (consulta.executeUpdate() > 0)				
			{
				respuesta = true;
			}
			c.close();
		} catch (Exception e) {
			System.out.println("Problemas al guardar la información del cliente: " + e);
		}
		return respuesta;
	}
	// CONSULTA SABER SI YA EXISTE TAL CLIENTE
	public boolean evitarRepetidos(String cedula, String celular)
	{
		boolean respuesta = false;
		String sql = "SELECT cedula, celular FROM CLIENTES WHERE cedula = '" + cedula + "' OR celular = '" + celular + "';";
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
			System.out.println("Problemas al consultar los clientes: " + e);
		}
		return respuesta;
	}
}