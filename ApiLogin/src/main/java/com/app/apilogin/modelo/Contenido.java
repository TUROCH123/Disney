package com.app.apilogin.modelo;

import java.util.ArrayList;
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
//import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
//import org.hibernate.annotations.LazyCollection;
//import org.hibernate.annotations.LazyCollectionOption;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "contenidos")
public class Contenido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(name = "nombre", nullable = false, length = 120)
	private String nombre;

	@Column(name = "descripcion", nullable = false, length = 120)
	private String descripcion;

	@Column(name = "imagen", nullable = false, length = 200)
	private String imagen;

	@Column(name = "pgeneral", nullable = false, length = 200)
	private String pgeneral;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_tipoContenidos")
	private TipoContenido tipoContenidos;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_proveedores")
	private Proveedor proveedores;

//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//	@LazyCollection(LazyCollectionOption.FALSE)
	@NotEmpty
	@ManyToMany(fetch= FetchType.LAZY)
//	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "contenidos_generos", joinColumns = @JoinColumn(name = "contenidos_id"), inverseJoinColumns = @JoinColumn(name = "generos_id"))
	private List<Genero> generos = new ArrayList<>();

	
	
	public Contenido() {
		super();
	}

	public Contenido(Long id, String nombre, String descripcion, String imagen, String pgeneral,
			TipoContenido tipoContenidos, Proveedor proveedores, List<Genero> generos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.pgeneral = pgeneral;
		this.tipoContenidos = tipoContenidos;
		this.proveedores = proveedores;
		this.generos = generos;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}