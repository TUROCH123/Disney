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
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "series")
public class Serie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "series_id")
	private Integer id;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "series_temporadas", joinColumns = @JoinColumn(name = "series_id"), inverseJoinColumns = @JoinColumn(name = "temporadas_id"))
	private List<Temporada> temporadas;

	public Serie() {
		super();
	}

	public Serie(Integer id, List<Temporada> temporadas) {
		super();
		this.id = id;
		this.temporadas = temporadas;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Temporada> getTemporadas() {
		return temporadas;
	}

	public void setTemporadas(List<Temporada> temporadas) {
		this.temporadas = temporadas;
	}

}