package com.app.apilogin.modelo;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "contenidos")
public class Contenido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contenidos_id")
	private Integer id;
	
	@NotBlank
	@Column(name = "nombre", nullable = false, length = 120)
	private String nombre;

	@NotBlank
	@Column(name = "descripcionGeneral", nullable = false, length = 120)
	private String descripcionGeneral;

	@Column(name = "imagen", nullable = false, length = 200)
	private String imagen;

	@Column(name = "pgeneral", nullable = false, length = 200)
	private String pgeneral;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tipoContenidos_id")
	private TipoContenido tipoContenidos;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "proveedores_id")
	private Proveedor proveedores;

	@NotEmpty
//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@ManyToMany(fetch= FetchType.LAZY)
//	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "contenidos_generos", joinColumns = @JoinColumn(name = "contenidos_id"), inverseJoinColumns = @JoinColumn(name = "generos_id"))
	private List<Genero> generos;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "peliculas_id")
	private Pelicula pelicula;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "series_id")
	private Serie serie;
	
	public Contenido() {
		super();
	}

	public Contenido(Integer id, @NotBlank String nombre, String descripcionGeneral, String imagen, String pgeneral,
			TipoContenido tipoContenidos, Proveedor proveedores, @NotEmpty List<Genero> generos, Pelicula pelicula,
			Serie serie) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcionGeneral = descripcionGeneral;
		this.imagen = imagen;
		this.pgeneral = pgeneral;
		this.tipoContenidos = tipoContenidos;
		this.proveedores = proveedores;
		this.generos = generos;
		this.pelicula = pelicula;
		this.serie = serie;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public String getDescripcionGeneral() {
		return descripcionGeneral;
	}

	public void setDescripcionGeneral(String descripcionGeneral) {
		this.descripcionGeneral = descripcionGeneral;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public String getPgeneral() {
		return pgeneral;
	}

	public void setPgeneral(String pgeneral) {
		this.pgeneral = pgeneral;
	}

	public TipoContenido getTipoContenidos() {
		return tipoContenidos;
	}

	public void setTipoContenidos(TipoContenido tipoContenidos) {
		this.tipoContenidos = tipoContenidos;
	}

	public Proveedor getProveedores() {
		return proveedores;
	}

	public void setProveedores(Proveedor proveedores) {
		this.proveedores = proveedores;
	}

	public List<Genero> getGeneros() {
		return generos;
	}

	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}