package com.programacion.modelo;

public class Detalles 
{
	private int id_detalle;
	private int id_factura;
	private int id_producto;
	
	private String nombre;
	
	private int cantidad;
	private double precio_unitario;
	private double subtotal;
	private double total_pagar;
	
	public Detalles() 
	{
		super();
		this.id_detalle = 0;
		this.id_factura = 0;
		this.id_producto = 0;
		this.nombre = "";
		this.cantidad = 0;
		this.precio_unitario = 0.0;
		this.subtotal = 0.0;
		this.total_pagar = 0.0;
	}
	
	public Detalles(int id_detalle, int id_factura, int id_producto, String nombre, int cantidad,
			double precio_unitario, double subtotal, double total_pagar) 
	{
		super();
		this.id_detalle = id_detalle;
		this.id_factura = id_factura;
		this.id_producto = id_producto;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio_unitario = precio_unitario;
		this.subtotal = subtotal;
		this.total_pagar = total_pagar;
	}

	public int getId_detalle() {
		return id_detalle;
	}

	public void setId_detalle(int id_detalle) {
		this.id_detalle = id_detalle;
	}

	public int getId_factura() {
		return id_factura;
	}

	public void setId_factura(int id_factura) {
		this.id_factura = id_factura;
	}

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio_unitario() {
		return precio_unitario;
	}

	public void setPrecio_unitario(double precio_unitario) {
		this.precio_unitario = precio_unitario;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getTotal_pagar() {
		return total_pagar;
	}

	public void setTotal_pagar(double total_pagar) {
		this.total_pagar = total_pagar;
	}

	@Override
	public String toString() {
		return "Detalles [id_detalle=" + id_detalle + ", id_factura=" + id_factura + ", id_producto=" + id_producto
				+ ", nombre=" + nombre + ", cantidad=" + cantidad + ", precio_unitario=" + precio_unitario
				+ ", subtotal=" + subtotal + ", total_pagar=" + total_pagar + "]";
	}
	
	
}
