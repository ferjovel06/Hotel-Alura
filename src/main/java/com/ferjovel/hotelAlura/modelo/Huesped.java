package com.ferjovel.hotelAlura.modelo;

import java.sql.Date;

public class Huesped {
	
	private Integer id;
	
	private String nombre;
	
	private String apellido;
	
	private Date fechaNacimiento;
	
	private String nacionalidad;
	
	private String telefono;
	
	private Integer idReserva;

	
	public Huesped(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, 
			String telefono, Integer idReserva) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.idReserva = idReserva;
	}
	
	public Huesped(Integer id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad,
			String telefono, Integer idReserva) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.idReserva = idReserva;
	}
	
	public Integer getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getApellido() {
		return this.apellido;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public String getNacionalidad() {
		return this.nacionalidad;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public int getIdReserva() {
		return this.idReserva;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return String.format("{id: %s, nombre: %s, apellido: %s, nacionalidad: %s, id reserva: %d", 
				this.id, this.nombre, this.apellido, this.nacionalidad, this.idReserva);
	}

}
