package com.app.apilogin.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombre", nullable = false, length = 60)
	private String nombre;

	@Column(name = "apellido", nullable = false, length = 60)
	private String apellido;

	@Column(name = "email", nullable = false, length = 60, unique = true)
	private String email;

//	@Column(name = "celular", nullable = false, length = 60, unique = true)
	@Column(name = "celular", nullable = false, length = 60)
	private String celular;

	@Column(name = "password", nullable = false, length = 60)
	private String password;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_Inscripcion", updatable = false, nullable = false)
	private Date fechaInscripcion;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_Vencimiento", updatable = false, nullable = false)
	private Date fechaVencimiento;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "usuarios_perfiles", joinColumns = @JoinColumn(name = "usuarios_id"), inverseJoinColumns = @JoinColumn(name = "perfiles_id"))
	private List<Perfiles> perfiles = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_roles")
	private Roles roles;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_planes")
	private Planes planes;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "usuarios_medioPagos", joinColumns = @JoinColumn(name = "usuarios_id"), inverseJoinColumns = @JoinColumn(name = "medioPagos_id"))
	private List<MedioPago> medioPago = new ArrayList<>();

	public Usuario() {
		super();
	}

	public Usuario(Long id, String nombre, String apellido, String email, String celular, String password,
			Date fechaInscripcion, Date fechaVencimiento, List<Perfiles> perfiles, Roles roles, Planes planes,
			List<MedioPago> medioPago) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.celular = celular;
		this.password = password;
		this.fechaInscripcion = fechaInscripcion;
		this.fechaVencimiento = fechaVencimiento;
		this.perfiles = perfiles;
		this.roles = roles;
		this.planes = planes;
		this.medioPago = medioPago;
	}

	public List<MedioPago> getMedioPago() {
		return medioPago;
	}

	public void setMedioPago(List<MedioPago> medioPago) {
		this.medioPago = medioPago;
	}

	public Planes getPlanes() {
		return planes;
	}

	public void setPlanes(Planes planes) {
		this.planes = planes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public List<Perfiles> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<Perfiles> perfiles) {
		this.perfiles = perfiles;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

}