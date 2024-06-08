package com.programacion.modelo;

public class Usuario 
{
	// atributos
	private int id_usuario;
	private String nombre;
	private String apellido;
	private String usuario;
	private String clave;
	private String celular;
	
	// constructor sin parámetros
	public Usuario() 
	{
		this.id_usuario = 0;
		this.nombre = "";
		this.apellido = "";
		this.usuario = "";
		this.clave = "";
		this.celular = "";
	}
	
	// get and set
	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
	
}