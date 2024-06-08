package com.programacion.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.programacion.database.Conexion;
import com.programacion.modelo.Detalles;
import com.programacion.modelo.Factura;

public class Control_RegistrarVenta 
{
	public static int idFactRegistro;
	java.math.BigDecimal idCol;
	
	// ALMACENAR VENTAS
	public boolean guardar(Factura objeto)
	{
		boolean respuesta = false;
		Connection c = Conexion.conectar();
		Statement st;
		
		try {
			PreparedStatement consulta = c.prepareStatement("INSERT INTO FACTURAS VALUES(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			consulta.setInt(1, 0);
			consulta.setInt(2, objeto.getId_cliente());
			consulta.setDouble(3, objeto.getValor_pagar());
			consulta.setString(4, objeto.getFecha_emision());
			
			if (consulta.executeUpdate() > 0)				
			{
				respuesta = true;
			}
			ResultSet rs = consulta.getGeneratedKeys();
			while (rs.next())
			{
				idCol = rs.getBigDecimal(1);
				idFactRegistro = idCol.intValue();
			}
			c.close();
		} catch (Exception e) {
			System.out.println("Problemas al guardar la información de ventas: " + e);
		}
		return respuesta;
	}
	
	// ALMACENAR DETALLE DE VENTA
	public boolean guardarDetalle(Detalles detalle)
	{
		boolean respuesta = false;
		Connection c = Conexion.conectar();
		Statement st;
		
		try {
			PreparedStatement consulta = c.prepareStatement("INSERT INTO DETALLES VALUES(?, ?, ?, ?, ?, ?, ?)");
			consulta.setInt(1, 0);
			consulta.setInt(2, detalle.getId_factura());
			consulta.setInt(3, detalle.getId_producto());
			consulta.setInt(4, detalle.getCantidad());
			consulta.setDouble(5, detalle.getPrecio_unitario());
			consulta.setDouble(6, detalle.getSubtotal());
			consulta.setDouble(7, detalle.getTotal_pagar());
			
			if (consulta.executeUpdate() > 0)				
			{
				respuesta = true;
			}
			c.close();
		} catch (Exception e) {
			System.out.println("Problemas al guardar detalles de ventas: " + e);
		}
		return respuesta;
	}	
	
	
}
