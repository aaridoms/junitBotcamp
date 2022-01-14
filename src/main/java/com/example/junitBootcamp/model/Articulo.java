package com.example.junitBootcamp.model;

public class Articulo {

	private String nombre;
	
	private Double precio;

	private Double descuento;

	public Articulo(String nombre, double precio double descuento) {
		this.nombre = nombre;
		this.precio = precio;
		this.descuento = descuento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Double getDescuento() {
	    return descuento;
	}

	public void setDescuento(Double descuento) {
	    this.descuento = descuento;
	}
}
