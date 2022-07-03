package com.app.apilogin.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ajustes")
public class Ajustes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ajustes_id")
	private Integer id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tipoAjustes_id")
	private TipoAjustes tipoAjustes;

	public Ajustes() {
		super();
	}

	public Ajustes(Integer id, TipoAjustes tipoAjustes) {
		super();
		this.id = id;
		this.tipoAjustes = tipoAjustes;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoAjustes getTipoAjustes() {
		return tipoAjustes;
	}

	public void setTipoAjustes(TipoAjustes tipoAjustes) {
		this.tipoAjustes = tipoAjustes;
	}
}