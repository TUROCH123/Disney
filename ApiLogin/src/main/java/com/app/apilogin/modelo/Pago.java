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
@Table(name = "pagos")
public class Pago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pagos_id")
	private Integer id;

	@Column(name = "monto")
	private Double monto;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_pago", updatable = false, nullable = false)
	private Date fechaPago;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "medioPagos_id")
	private MedioPago medioPago;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "planes_id")
	private Planes planes;

	public Pago() {
		super();
	}

	public Pago(Integer id, Double monto, Date fechaPago, MedioPago medioPago, Planes planes) {
		super();
		this.id = id;
		this.monto = monto;
		this.fechaPago = fechaPago;
		this.medioPago = medioPago;
		this.planes = planes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public MedioPago getMedioPago() {
		return medioPago;
	}

	public void setMedioPago(MedioPago medioPago) {
		this.medioPago = medioPago;
	}

	public Planes getPlanes() {
		return planes;
	}

	public void setPlanes(Planes planes) {
		this.planes = planes;
	}

}
