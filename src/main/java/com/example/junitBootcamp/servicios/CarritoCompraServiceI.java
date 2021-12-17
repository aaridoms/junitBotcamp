package com.example.junitBootcamp.servicios;

import java.util.List;
import java.util.Map;

import com.example.junitBootcamp.bbdd.BaseDatosI;
import com.example.junitBootcamp.model.Articulo;
import org.springframework.beans.factory.annotation.Autowired;

public interface CarritoCompraServiceI {

	public void limpiarCesta();
	
	public void addArticulo(Articulo articulo);
	
	public Integer getNumArticulos();
	
	public List<Articulo> getArticulos();
	
	public Double totalPrice();
	
	public Double calculadorDescuento(Double precio, Double porcentajeDescuento);

	public Double aplicarDescuento(Integer identificador);

	public Double aplicarDescuento(Integer idArticulo, Double porcentaje);

	public Integer insertar(Articulo articulo);
}
