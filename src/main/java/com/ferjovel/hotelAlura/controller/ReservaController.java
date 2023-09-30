package com.ferjovel.hotelAlura.controller;

import java.util.List;

import com.ferjovel.hotelAlura.dao.ReservaDAO;
import com.ferjovel.hotelAlura.factory.ConnectionFactory;
import com.ferjovel.hotelAlura.modelo.Reserva;

public class ReservaController {
	
	private ReservaDAO reservaDAO;
	
	public ReservaController() {
		this.reservaDAO = new ReservaDAO(new ConnectionFactory().recuperaConexion());
	}
	
	public void guardar(Reserva reserva) {
		reservaDAO.guardar(reserva);
	}
	
	public List<Reserva> buscar() {
		return reservaDAO.buscar();
	}
	
	public List<Reserva> buscarId(String id) {
		return reservaDAO.buscarId(id);
	}

}
