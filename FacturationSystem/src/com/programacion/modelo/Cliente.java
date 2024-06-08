package com.programacion.modelo;

public class Cliente 
{
	private int id_cliente;
	private String nombre;
	private String apellido;
	private String cedula;
	private String celular;
	
	public Cliente() 
	{
		this.id_cliente = 0;
		this.nombre = "";
		this.apellido = "";
		this.cedula = "";
		this.celular = "";
	}
	
	public Cliente(int id_cliente, String nombre, String apellido, String cedula, String celular) 
	{
		this.id_cliente = id_cliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula = cedula;
		this.celular = celular;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
}