package com.programacion.modelo;

public class Factura 
{
	private int id_factura;
	private int id_cliente;
	private double valor_pagar;
	private String fecha_emision;
	
	public Factura() 
	{
		this.id_factura = 0;
		this.id_cliente = 0;
		this.valor_pagar = 0.0;
		this.fecha_emision = "";
	}
	
	public Factura(int id_factura, int id_cliente, double valor_pagar, String fecha_emision) 
	{
		this.id_factura = id_factura;
		this.id_cliente = id_cliente;
		this.valor_pagar = valor_pagar;
		this.fecha_emision = fecha_emision;
	}

	public int getId_factura() {
		return id_factura;
	}

	public void setId_factura(int id_factura) {
		this.id_factura = id_factura;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public double getValor_pagar() {
		return valor_pagar;
	}

	public void setValor_pagar(double valor_pagar) {
		this.valor_pagar = valor_pagar;
	}

	public String getFecha_emision() {
		return fecha_emision;
	}

	public void setFecha_emision(String fecha_emision) {
		this.fecha_emision = fecha_emision;
	}
	
}
