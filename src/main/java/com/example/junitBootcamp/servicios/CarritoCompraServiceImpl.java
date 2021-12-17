package com.example.junitBootcamp.servicios;

import java.util.*;

import com.example.junitBootcamp.bbdd.BaseDatosI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.junitBootcamp.model.Articulo;

@Service
public class CarritoCompraServiceImpl implements CarritoCompraServiceI {
	
	private List<Articulo> cesta = new ArrayList<>();

	@Autowired
	private BaseDatosI baseDatos;

	@Override
	public void limpiarCesta() {
		cesta.clear();
	}

	@Override
	public void addArticulo(Articulo articulo) {
		cesta.add(articulo);
	}

	@Override
	public Integer getNumArticulos() {
		return cesta.size();
	}

	@Override
	public List<Articulo> getArticulos() {
		return cesta;
	}

	@Override
	public Double totalPrice() {
		Double total = 0D;
		for(Articulo articulo : cesta) {
			total = total + articulo.getPrecio();
		}
		return total;
	}

	@Override
	public Double calculadorDescuento(Double precio, Double porcentajeDescuento) {
		return precio - precio*porcentajeDescuento/100;
	}

	@Override
	public Double aplicarDescuento(Integer identificador) {

		return null;
	}

	@Override
	public Double aplicarDescuento(Integer idArticulo, Double porcentaje) {
		Double resultado = null;
		Articulo articulo = baseDatos.findArticuloById(idArticulo);
		if (Optional.ofNullable(articulo).isPresent()) {
			resultado = calculadorDescuento(articulo.getPrecio(), porcentaje);
		} else {
			System.out.println("No se ha encontrado articulo con ID: " + idArticulo);
		}
		return resultado;
	}

	@Override
	public Integer insertar(Articulo articulo) {
		Integer identificador = baseDatos.insertarArticulo(articulo);
		cesta.add(articulo);
		return identificador;
	}
}
