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
@Table(name="Producto")
public class Producto implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String Nombre;
	
	@Column(nullable = false)
	private String Nif;
	
	@Column(nullable = false)
	private String Cod_producto;
	
	@Column(nullable = false)
	private String Tipo;
	
	private double Precio;
	
	@Column(nullable = false)
	private String Fecha_Registro;
	
	private int Cantidad;

	
	public String getCod_producto() {
		return Cod_producto;
	}

	public void setCod_producto(String cod_producto) {
		Cod_producto = cod_producto;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public double getPrecio() {
		return Precio;
	}

	public void setPrecio(double precio) {
		Precio = precio;
	}

	public String getFecha_Registro() {
		return Fecha_Registro;
	}

	public void setFecha_Registro(String fecha_Registro) {
		Fecha_Registro = fecha_Registro;
	}

	public int getCantidad() {
		return Cantidad;
	}

	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
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
