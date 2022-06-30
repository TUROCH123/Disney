package com.app.apilogin.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tiposContenidos")
public class TipoContenido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tiposContenidos_id")
	private Integer id;

	@Column(name = "tipo", nullable = false, length = 60)
	private String tipo;

	public TipoContenido() {
		super();
	}

	public TipoContenido(Integer id, String tipo) {
		super();
		this.id = id;
		this.tipo = tipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
