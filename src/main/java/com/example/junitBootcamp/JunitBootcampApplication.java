package com.example.junitBootcamp;

import com.example.junitBootcamp.model.Articulo;
import com.example.junitBootcamp.servicios.CarritoCompraServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JunitBootcampApplication implements CommandLineRunner {

	@Autowired
	private CarritoCompraServiceI carritoCompra;
	
	public static void main(String[] args) {
		SpringApplication.run(JunitBootcampApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		carritoCompra.addArticulo(new Articulo("Camiseta", 20.00));
		carritoCompra.addArticulo(new Articulo("Pantalon", 30.00));
		System.out.println("Cesta de la compra");
		System.out.println("----------------------");
		System.out.println("1 - " + carritoCompra.getArticulos().get(0).getNombre());
		System.out.println(carritoCompra.getArticulos().get(0).getPrecio());
		System.out.println("2 - " + carritoCompra.getArticulos().get(1).getNombre());
		System.out.println(carritoCompra.getArticulos().get(1).getPrecio());
		System.out.println("-----------------------");
		System.out.println("Total");
		System.out.println(carritoCompra.totalPrice());
		System.out.println("-----------------------");
	}

}
