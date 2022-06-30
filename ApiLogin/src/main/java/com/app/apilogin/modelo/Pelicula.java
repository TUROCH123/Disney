package com.app.apilogin.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "peliculas")
public class Pelicula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "peliculas_id")
	private Integer id;

	@NotBlank
	@Column(name = "duracion", nullable = false, length = 20)
	private String duracion;

	@NotBlank
	@Column(name = "fechaEstreno", nullable = false, length = 120)
	private String fechaEstreno;

	@NotBlank
	@Column(name = "descripcion", nullable = false, length = 400)
	private String descripcion;
	@NotBlank
	@Column(name = "dirigidoPor", nullable = false, length = 120)
	private String dirigidoPor;

	@NotEmpty
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "peliculas_elencos", joinColumns = @JoinColumn(name = "peliculas_id"), inverseJoinColumns = @JoinColumn(name = "elencos_id"))
	private List<Elenco> elenco;

	public Pelicula() {
		super();
	}

	public Pelicula(Integer id, @NotBlank String duracion, @NotBlank String fechaEstreno, @NotBlank String descripcion,
			@NotBlank String dirigidoPor, @NotEmpty List<Elenco> elenco) {
		super();
		this.id = id;
		this.duracion = duracion;
		this.fechaEstreno = fechaEstreno;
		this.descripcion = descripcion;
		this.dirigidoPor = dirigidoPor;
		this.elenco = elenco;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getFechaEstreno() {
		return fechaEstreno;
	}

	public void setFechaEstreno(String fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDirigidoPor() {
		return dirigidoPor;
	}

	public void setDirigidoPor(String dirigidoPor) {
		this.dirigidoPor = dirigidoPor;
	}

	public List<Elenco> getElenco() {
		return elenco;
	}

	public void setElenco(List<Elenco> elenco) {
		this.elenco = elenco;
	}

}