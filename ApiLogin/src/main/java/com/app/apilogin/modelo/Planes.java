package com.app.apilogin.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "planes")
public class Planes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_tipoPlanes")
    private TipoPlanes tipoPlanes;

	public Planes() {
		super();
	}

	public Planes(Long id, TipoPlanes tipoPlanes) {
		super();
		this.id = id;
		this.tipoPlanes = tipoPlanes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoPlanes getTipoPlanes() {
		return tipoPlanes;
	}

	public void setTipoPlanes(TipoPlanes tipoPlanes) {
		this.tipoPlanes = tipoPlanes;
	}

    
}
