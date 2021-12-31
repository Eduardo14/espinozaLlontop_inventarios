package com.espinozallontop.inventario.modelos;

import java.time.LocalDate;
import java.time.LocalTime;

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
public class Transaccion {

	@Id
	private Integer id;
	
	private Integer cliente_id;
	
	private LocalDate fecha;
	
	private LocalTime hora;
	
}
