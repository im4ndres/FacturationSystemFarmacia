package com.programacion.modelo;

public class Producto 
{
	private int id_producto;
	private int id_categoria;
	private String nombre;
	private int stock;
	private double precio;
	private String descripcion;
	
	public Producto()
	{
		this.id_producto = 0;
		this.id_categoria = 0;
		this.nombre = "";
		this.stock = 0;
		this.precio = 0.0;
		this.descripcion = "";
	}

	public Producto(int id_producto, int id_categoria, String nombre, int stock, double precio, String descripcion) 
	{
		this.id_producto = id_producto;
		this.id_categoria = id_categoria;
		this.nombre = nombre;
		this.stock = stock;
		this.precio = precio;
		this.descripcion = descripcion;
	}

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public int getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}