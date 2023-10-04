package com.ferjovel.hotelAlura.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ferjovel.hotelAlura.modelo.Huesped;

public class HuespedDAO {

	final private Connection conexion;

	public HuespedDAO(Connection conexion) {
		this.conexion = conexion;
	}

	public void guardar(Huesped huesped) {
		try (conexion) {
			final PreparedStatement stm = conexion.prepareStatement(
					"INSERT INTO huespedes(nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva) "
							+ "VALUES(?, ?, ?, ?, ?, ?);",
					Statement.RETURN_GENERATED_KEYS);

			try (stm) {
				stm.setString(1, huesped.getNombre());
				stm.setString(2, huesped.getApellido());
				stm.setDate(3, huesped.getFechaNacimiento());
				stm.setString(4, huesped.getNacionalidad());
				stm.setString(5, huesped.getTelefono());
				stm.setInt(6, huesped.getIdReserva());
				stm.execute();

				final ResultSet resultSet = stm.getGeneratedKeys();

				try (resultSet) {

					while (resultSet.next()) {
						huesped.setId(resultSet.getInt(1));
						System.out.println(String.format("Fue insertado el huesped %s", huesped));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	public int editar(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, Integer idReserva, Integer id) {
		try {
			final PreparedStatement statement = conexion.prepareStatement(
					"UPDATE huespedes SET nombre = ?, apellido = ?, fechaNacimiento = ?, "
							+ "nacionalidad = ?, telefono = ?, idReserva = ? WHERE id = ?");
			
			try (statement) {
				statement.setString(1, nombre);;
				statement.setString(2, apellido);
				statement.setDate(3, fechaNacimiento);
				statement.setString(4, nacionalidad);
				statement.setString(5, telefono);
				statement.setInt(6, idReserva);
				statement.setInt(7, id);
				statement.execute();
				
				int updateCount = statement.getUpdateCount();
				return updateCount;
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int eliminar(int id) {
		try {

			final PreparedStatement statement = conexion.prepareStatement(
					"DELETE FROM huespedes WHERE id = ?");

			try (statement) {
				statement.setInt(1, id);
				statement.execute();

				int updateCount = statement.getUpdateCount();
				return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Huesped> buscar() {
		List<Huesped> resultado = new ArrayList<>();

		try {
			final PreparedStatement statement = conexion.prepareStatement(
					"SELECT id, nombre, apellido, fechaNacimiento, "
					+ "nacionalidad, telefono, idReserva FROM huespedes;");
			try (statement) {
				statement.execute();

				transformarHuespedEnResultSet(resultado, statement);
			}
			return resultado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Huesped> buscarId(String id) {
		List<Huesped> resultado = new ArrayList<>();
		
		try {
			final PreparedStatement statement = conexion
					.prepareStatement("SELECT id, nombre, apellido, fechaNacimiento, nacionalidad, "
							+ "telefono, idReserva FROM huespedes WHERE id = ?;");
			
			try (statement) {
				statement.setString(1, id);
				statement.execute();
				
				transformarHuespedEnResultSet(resultado, statement);
			}
			return resultado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	private void transformarHuespedEnResultSet(List<Huesped> huesped, PreparedStatement statement) throws SQLException {
		final ResultSet resultSet = statement.getResultSet();
		try (resultSet) {
			while (resultSet.next()) {
				Huesped fila = new Huesped(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getDate(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7));
				huesped.add(fila);
			}
		}
	}

}
