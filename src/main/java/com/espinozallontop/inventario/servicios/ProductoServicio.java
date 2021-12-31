package com.espinozallontop.inventario.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.espinozallontop.inventario.modelos.Producto;

@Service
public class ProductoServicio {

	@Autowired
	private RestTemplate restTemplate;
	
	public Producto[] getProductosMock() {
		ResponseEntity<Producto[]> resp = restTemplate.getForEntity("https://api.mocki.io/v2/4ee16eda/prods", Producto[].class);
		return resp.getStatusCode() == HttpStatus.OK ? resp.getBody() : new Producto[0];
	}

	public Producto getStockExtra() {
		ResponseEntity<Producto> resp = restTemplate.getForEntity("https://api.mocki.io/v2/4ee16eda/stock", Producto.class);
		return resp.getStatusCode() == HttpStatus.OK ? resp.getBody() : null;
	}
	
}
