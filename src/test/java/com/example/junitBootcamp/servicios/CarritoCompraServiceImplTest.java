package com.example.junitBootcamp.servicios;

import static org.junit.jupiter.api.Assertions.*;

import com.example.junitBootcamp.bbdd.BaseDatosI;
import com.example.junitBootcamp.bbdd.BaseDatosImpl;
import com.example.junitBootcamp.model.Articulo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


import java.util.List;

@ExtendWith(MockitoExtension.class)
class CarritoCompraServiceImplTest {

	@InjectMocks
	private CarritoCompraServiceImpl carritoService = new CarritoCompraServiceImpl();

	@Mock
	private BaseDatosI baseDatos;

//	@BeforeEach
//	void setUp() throws Exception {
//		carritoService = new CarritoCompraServiceImpl();
//	}

	@Test
	void testLimpiarCesta() {
		Assertions.assertTrue(carritoService.getArticulos().isEmpty());
		carritoService.addArticulo(new Articulo("Camiseta", 18.99));
		Assertions.assertFalse(carritoService.getArticulos().isEmpty());
		carritoService.limpiarCesta();
		Assertions.assertTrue(carritoService.getArticulos().isEmpty());
	}

	@Test
	void testAddArticulo() {
		Assertions.assertTrue(carritoService.getArticulos().isEmpty());
		carritoService.addArticulo(new Articulo("Pantalon", 20.23));
		Assertions.assertFalse(carritoService.getArticulos().isEmpty());
	}

	@Test
	void testGetNumArticulos() {
		carritoService.addArticulo(new Articulo("Camiseta", 20.50));
		carritoService.addArticulo(new Articulo("Camiseta", 21.50));
		Integer res = carritoService.getNumArticulos();
		Assertions.assertEquals(2, res);
	}

	@Test
	void testGetArticulos() {
		carritoService.addArticulo(new Articulo("Falda", 20.50));
		carritoService.addArticulo(new Articulo("Pantalon", 21.50));
		List<Articulo> res = carritoService.getArticulos();
		assertEquals("Falda", res.get(0).getNombre());
		Assertions.assertEquals(2, res.size());
	}

	@Test
	void testTotalPrice() {
		carritoService.addArticulo(new Articulo("Camiseta", 10.00));
		carritoService.addArticulo(new Articulo("Camiseta", 10.00));
		Double res = carritoService.totalPrice();
		Assertions.assertEquals(20D, res);
	}

	@Test
	void testCalculadorDescuento() {
		Assertions.assertEquals(90D, carritoService.calculadorDescuento(100D, 10D));
	}

	@Test
	void aplicarDescuentoTest() {
		Articulo articulo = new Articulo("Camiseta", 20.00);
		when(baseDatos.findArticuloById(any(Integer.class))).thenReturn(articulo);
		Double res = carritoService.aplicarDescuento(1, 10D);
		Assertions.assertEquals(18D, res);
		verify(baseDatos).findArticuloById(1);
	}

	@Test
	void insertarTest() {
		Articulo articulo = new Articulo("Camiseta", 20.00);
		when(baseDatos.insertarArticulo(any(Articulo.class))).thenReturn(0);
		Integer identificador = carritoService.insertar(articulo);
		List<Articulo> articulos = carritoService.getArticulos();

		Assertions.assertEquals(0, identificador);
		Assertions.assertEquals("Camiseta", articulos.get(identificador).getNombre());
		Assertions.assertEquals(20D, articulos.get(identificador).getPrecio());
		verify(carritoService, atLeast(1)).insertar(any(Articulo.class));
	}

}
