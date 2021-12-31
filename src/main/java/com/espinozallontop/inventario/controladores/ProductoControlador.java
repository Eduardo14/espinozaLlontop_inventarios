package com.espinozallontop.inventario.controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.espinozallontop.inventario.excepciones.ProductoNoEncontradoExcepcion;
import com.espinozallontop.inventario.excepciones.ProductoStockMenorCeroExcepcion;
import com.espinozallontop.inventario.logica.ValidaStock;
import com.espinozallontop.inventario.modelos.Producto;
import com.espinozallontop.inventario.repositorios.ProductoRepositorio;

@RestController
public class ProductoControlador {

	@Autowired
	private ProductoRepositorio productoRepositorio;
	
	@GetMapping("productos/listar")
	public List<Map<String,String>> listarProductos() {
		List<Producto> productos = productoRepositorio.findAll();
		List<Map<String,String>> resp = new ArrayList<Map<String,String>>();
		for (Producto producto : productos) {
			Map<String,String> aMap = null;
			aMap = new HashMap<>();
			aMap.put("codigo", producto.getCod());
			aMap.put("nombre", producto.getName());
			resp.add(aMap);
		}
		return resp;
	}
	
	@PostMapping("productos/stock/{id}")
	public void actualizarStock(@PathVariable Integer id, @RequestParam(value="stock") Integer stock) {
		Producto producto = productoRepositorio.findById(id).orElseThrow(() -> new ProductoNoEncontradoExcepcion(id));
		producto.setStock(stock);
		
		ValidaStock validaStock = new ValidaStock(producto);
		if(!validaStock.stockMenorIgualACero()) {
			productoRepositorio.save(producto);
		}else {
			throw new ProductoStockMenorCeroExcepcion(producto);
		}
	}

	@GetMapping("productos/stock/{id}")
	public Integer obtenerStock(@PathVariable Integer id) {
		Producto producto = productoRepositorio.findById(id).orElseThrow(() -> new ProductoNoEncontradoExcepcion(id));
		return producto.getStock();
	}
	
}
