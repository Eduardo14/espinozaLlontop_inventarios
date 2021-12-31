package com.espinozallontop.inventario.modelos;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
	
	@Id
	private Integer id;
	
	private String cod;
	
	private String name;
	
	private Double price;
	
	private Integer stock;
	
}
