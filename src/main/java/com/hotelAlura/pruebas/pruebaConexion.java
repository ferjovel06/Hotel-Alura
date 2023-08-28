package com.hotelAlura.pruebas;

import java.sql.Connection;
import java.sql.SQLException;

import com.hotelAlura.factory.ConnectionFactory;


public class pruebaConexion {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();

		for (int i = 1; i <= 20; i++) {
			Connection conexion = connectionFactory.recuperaConexion();

			System.out.println("Abriendo la conexion numero " + (i));
		}

	}

}
