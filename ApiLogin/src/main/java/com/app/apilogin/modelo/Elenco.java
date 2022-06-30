package com.app.apilogin.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "elencos")
public class Elenco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "elencos_id")
	private Integer id;
	
	@NotBlank
	@Column(name = "nombre", nullable = false, length = 20)
	private String nombre;

	public Elenco() {
		super();
	}

	public Elenco(Integer id, @NotBlank String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}