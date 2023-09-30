package com.ferjovel.hotelAlura.pruebas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ferjovel.hotelAlura.factory.ConnectionFactory;

public class PruebaInsercion {
	
	public static void main(String[] args) throws SQLException {
		ConnectionFactory factory = new ConnectionFactory();
		Connection conexion = factory.recuperaConexion();
		
		PreparedStatement stm = conexion.prepareStatement(
				"INSERT INTO RESERVA (fechaEntrada, fechaSalida, precio, formaPago)"
				+ "VALUES (?, ?, ?, ?);",
				Statement.RETURN_GENERATED_KEYS);
		
		Date fechaEntrada = Date.valueOf("2023-08-25");
		Date fechaSalida = Date.valueOf("2023-08-27");
		
		stm.setDate(1, fechaEntrada);
		stm.setDate(2, fechaSalida);
		stm.setString(3, "100");
		stm.setString(4, "Efectivo");
		
		stm.execute();
		
		ResultSet rst = stm.getGeneratedKeys();
		
		while (rst.next()) {
			Integer id = rst.getInt(1);
			System.out.println("El id creado fue: " + id);
		}
		
		conexion.close();
	}

}
