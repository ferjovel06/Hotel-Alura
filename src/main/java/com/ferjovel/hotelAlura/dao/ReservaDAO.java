package com.ferjovel.hotelAlura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ferjovel.hotelAlura.factory.ConnectionFactory;
import com.ferjovel.hotelAlura.modelo.Reserva;

public class ReservaDAO {
	final private Connection conexion;
	
	public ReservaDAO(Connection conexion) {
		this.conexion = conexion;
	}
	
	public void guardar(Reserva reserva) {
		try {
			final PreparedStatement statement = conexion.prepareStatement(
					"INSERT INTO reservas(fechaEntrada, fechaSalida, valor, formaPago) VALUES(?, ?, ?, ?);",
					Statement.RETURN_GENERATED_KEYS
					);
			
			try (statement) {
				statement.setDate(1, reserva.getFechaEntrada());
				statement.setDate(2, reserva.getFechaSalida());
				statement.setString(3, reserva.getValor());
				statement.setString(4, reserva.getFormaPago());
				statement.execute();
				
				final ResultSet resultSet = statement.getGeneratedKeys();
				
				try (resultSet) {
					
					while (resultSet.next()) {
						reserva.setId(resultSet.getInt(1));
						System.out.println(String.format("Fue insertada la reserva %s", reserva));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reserva> buscar() {
		List<Reserva> resultado = new ArrayList<>();
		
		try {
			final PreparedStatement statement = conexion
					.prepareStatement("SELECT id, fechaEntrada, fechaSalida, valor, formaPago FROM reservas;");
			try (statement) {
				statement.execute();
				
				transformarReservaEnResultSet(resultado, statement);
			}
			return resultado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reserva> buscarId(String id) { 
		List<Reserva> resultado = new ArrayList<>();
		
		try {
			final PreparedStatement statement = conexion
					.prepareStatement("SELECT id, fechaEntrada, fechaSalida, valor, formaPago "
							+ "FROM reservas WHERE id = ?;");
			
			try (statement) {
				statement.setString(1, id);
				statement.execute();
				
				transformarReservaEnResultSet(resultado, statement);
			}
			return resultado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	// TODO
	public int editar() {
		final Connection conexion = new ConnectionFactory().recuperaConexion();
		
		try (conexion) {
			final PreparedStatement statement = conexion.prepareStatement(
					"UPDATE reservas SET fechaEntrada = ?, fechaSalida = ?, valor = ?, "
					+ "formaPago = ?, WHERE id = ?");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void transformarReservaEnResultSet(List<Reserva> reserva, PreparedStatement statement) throws SQLException {
		final ResultSet resultSet = statement.getResultSet();
		try (resultSet) {
			while (resultSet.next()) {
				Reserva fila = new Reserva(resultSet.getInt(1), resultSet.getDate(2), 
						resultSet.getDate(3), resultSet.getString(4), resultSet.getString(5));
				reserva.add(fila);
			}
		}
		
	}

}