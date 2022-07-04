package com.app.apilogin.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "audios")
public class Audio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "audios_id")
	private Integer id;

	@NotBlank
	@Column(name = "idioma", nullable = false, length = 120)
	private String idioma;

	public Audio() {
		super();
	}

	public Audio(Integer id, @NotBlank String idioma) {
		super();
		this.id = id;
		this.idioma = idioma;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

}