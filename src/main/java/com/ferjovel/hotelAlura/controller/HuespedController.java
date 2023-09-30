package com.ferjovel.hotelAlura.controller;

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
	
	public List<Huesped> buscar() {
		return huespedDAO.buscar();
	}
	
	public List<Huesped> buscarId(String id) {
		return huespedDAO.buscarId(id);
	}

}
