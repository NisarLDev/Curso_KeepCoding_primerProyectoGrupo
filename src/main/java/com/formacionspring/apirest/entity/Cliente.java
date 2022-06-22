package com.formacionspring.apirest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Cliente")
public class Cliente implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String Nombre;
	
	@Column(nullable = false)
	private String Nif;
	
	@Column(nullable = false)
	private String Direccion;
	
	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	@Column(nullable = false,unique=true)
	private String Email;
	
	private int Telefono;
	
	@Column(name="create_at")
	private Date createAt;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		this.Nombre = Nombre;
	}

	public String getNif() {
		return Nif;
	}

	public void setNif(String apellido) {
		this.Nif = Nif;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String Email) {
		this.Email = Email;
	}

	public int getTelefono() {
		return Telefono;
	}

	public void setTelefono(int Telefono) {
		this.Telefono = Telefono;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
}
