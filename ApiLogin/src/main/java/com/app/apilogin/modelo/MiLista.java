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
@Table(name = "miListas")
public class MiLista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "miListas_id")
	private Integer id;

	@Column(name = "nombre", nullable = false, length = 120)
	private String nombre;

	@Column(name = "descripcion", nullable = false, length = 120)
	private String descripcion;
	
	@Column(name = "imagen", nullable = false, length = 200)
	private String imagen;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "contenidos_id")
	private Contenido contenido;
	
	public MiLista() {
		super();
	}

	public MiLista(Integer id, String nombre, String descripcion, String imagen, Contenido contenido) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.contenido = contenido;
	}

	public Contenido getContenido() {
		return contenido;
	}

	public void setContenido(Contenido contenido) {
		this.contenido = contenido;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}