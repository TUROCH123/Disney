package com.app.apilogin.modelo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "recibos")
public class Recibo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_recibo", updatable = false, nullable = false)
	private Date fechaRecibo;

	@Column(name = "direccion", nullable = false, length = 45)
	private String direccion;

	@Column(name = "codigo_recibo", nullable = false, length = 45)
	private String codigoRecibo;

	@Column(name = "numero_documento", nullable = false, length = 45)
	private String numeroDocumento;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_usuarios")
	private Usuario usuario;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_pagos")
	private Pago pago;

	public Recibo() {
		super();
	}

	public Recibo(Long id, Date fechaRecibo, String direccion, String codigoRecibo, String numeroDocumento,
			Usuario usuario, Pago pago) {
		super();
		this.id = id;
		this.fechaRecibo = fechaRecibo;
		this.direccion = direccion;
		this.codigoRecibo = codigoRecibo;
		this.numeroDocumento = numeroDocumento;
		this.usuario = usuario;
		this.pago = pago;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaRecibo() {
		return fechaRecibo;
	}

	public void setFechaRecibo(Date fechaRecibo) {
		this.fechaRecibo = fechaRecibo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCodigoRecibo() {
		return codigoRecibo;
	}

	public void setCodigoRecibo(String codigoRecibo) {
		this.codigoRecibo = codigoRecibo;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Pago getPago() {
		return pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}

}
