package com.programacion.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.programacion.database.Conexion;
import com.programacion.modelo.Producto;

public class ControlProducto 
{
	// ALMACENAR PRODUCTOS
	public boolean guardar(Producto producto)
	{
		boolean respuesta = false;
		Connection c = Conexion.conectar();
		Statement st;
		
		try {
			PreparedStatement consulta = c.prepareStatement("INSERT INTO PRODUCTOS VALUES(?, ?, ?, ?, ?, ?)");
			consulta.setInt(1, 0);
			consulta.setInt(2, producto.getId_categoria());
			consulta.setString(3, producto.getNombre());
			consulta.setInt(4, producto.getStock());
			consulta.setDouble(5, producto.getPrecio());
			consulta.setString(6, producto.getDescripcion());
			
			if (consulta.executeUpdate() > 0)				
			{
				respuesta = true;
			}
			c.close();
		} catch (Exception e) {
			System.out.println("Problemas al guardar el producto: " + e);
		}
		return respuesta;
	}
	// CONSULTA SABER SI YA EXISTE TAL PRODUCTO
	public boolean evitarRepetidos(String nombre_producto)
	{
		boolean respuesta = false;
		String sql = "SELECT nombre FROM PRODUCTOS WHERE nombre = '" + nombre_producto + "';";
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
			System.out.println("Problemas al consultar el producto: " + e);
		}
		return respuesta;
	}
}