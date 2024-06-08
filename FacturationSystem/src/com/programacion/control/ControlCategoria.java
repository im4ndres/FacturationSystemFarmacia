package com.programacion.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.programacion.database.Conexion;
import com.programacion.modelo.Categoria;

public class ControlCategoria 
{
	// ALMACENAR CATEGORIAS
	public boolean guardar(Categoria tipo)
	{
		boolean respuesta = false;
		Connection c = Conexion.conectar();
		Statement st;
		
		try {
			PreparedStatement consulta = c.prepareStatement("INSERT INTO CATEGORIAS VALUES(?, ?)");
			consulta.setInt(1, 0);
			consulta.setString(2, tipo.getDescripcion());
			
			if (consulta.executeUpdate() > 0)
			{
				respuesta = true;
			}
			c.close();
		} catch (Exception e) {
			System.out.println("Problemas al guardar la categoría: " + e);
		}
		return respuesta;
	}
	// CONSULTA SABER SI YA EXISTE TAL CATEGORIA
	public boolean evitarRepetidos(String categoria)
	{
		boolean respuesta = false;
		String sql = "SELECT DESCRIPCION FROM CATEGORIAS WHERE DESCRIPCION = '" + categoria + "';";
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
			System.out.println("Problemas al consultar la categoría: " + e);
		}
		return respuesta;
	}
}
