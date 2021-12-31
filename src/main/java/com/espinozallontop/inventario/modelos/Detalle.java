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
public class Detalle {

	@Id
	private Integer id;

	private Integer transaccion_id;

	private Integer tienda_id;
	
	private Integer producto_id;
	
	private Integer cantidad;
	
}
