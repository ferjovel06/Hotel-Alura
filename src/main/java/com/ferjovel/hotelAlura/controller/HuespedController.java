package com.ferjovel.hotelAlura.controller;

import java.sql.Date;
import java.util.List;

import com.ferjovel.hotelAlura.dao.HuespedDAO;
import com.ferjovel.hotelAlura.factory.ConnectionFactory;
import com.ferjovel.hotelAlura.modelo.Huesped;

public class HuespedController {
	
	private HuespedDAO huespedDAO;
	
	public HuespedController() {
		this.huespedDAO = new HuespedDAO(new ConnectionFactory().recuperaConexion());
	}
	
	public void guardar(Huesped huesped) {
		this.huespedDAO.guardar(huesped);
	}
	
	public int editar(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, Integer idReserva, Integer id) {
		return huespedDAO.editar(nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva, id);
	}
	
	public List<Huesped> buscar() {
		return huespedDAO.buscar();
	}
	
	public List<Huesped> buscarId(String id) {
		return huespedDAO.buscarId(id);
	}

}
