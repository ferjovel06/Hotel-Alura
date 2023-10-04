package com.ferjovel.hotelAlura.controller;

import java.sql.Date;
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
	
	public int editar(Date fechaEntrada, Date fechaSalida, String valor, String formaPago, Integer id) {
		return reservaDAO.editar(fechaEntrada, fechaSalida, valor, formaPago, id);
	}
	
	public int eliminar(Integer id) {
		return reservaDAO.eliminar(id);
	}
	
	public List<Reserva> buscar() {
		return reservaDAO.buscar();
	}
	
	public List<Reserva> buscarId(String id) {
		return reservaDAO.buscarId(id);
	}

}
