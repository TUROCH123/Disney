package com.app.apilogin.modelo;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "temporadas")
public class Temporada {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "temporadas_id")
	private Integer id;

	@NotBlank
	@Column(name = "nombre", nullable = false, length = 20)
	private String nombre;

	@NotBlank
	@Column(name = "fechaEstreno", nullable = false, length = 120)
	private String fechaEstreno;

	@NotEmpty
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//	@OneToMany(fetch = FetchType.LAZY)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "temporadas_capitulos", joinColumns = @JoinColumn(name = "temporadas_id"), inverseJoinColumns = @JoinColumn(name = "capitulos_id"))
	private List<Capitulo> capitulos;

	public Temporada() {
		super();
	}

	public Temporada(Integer id, @NotBlank String nombre, @NotBlank String fechaEstreno,
			@NotEmpty List<Capitulo> capitulos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaEstreno = fechaEstreno;
		this.capitulos = capitulos;
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

	public String getFechaEstreno() {
		return fechaEstreno;
	}

	public void setFechaEstreno(String fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}

	public List<Capitulo> getCapitulos() {
		return capitulos;
	}

	public void setCapitulos(List<Capitulo> capitulos) {
		this.capitulos = capitulos;
	}

}