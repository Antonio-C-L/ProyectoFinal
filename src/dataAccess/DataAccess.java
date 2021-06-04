package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataAccess {
	String sourceURL ="C:\\Database\\ProyectoFinal";
	String usuario = "administador";
	String password = "1234";
	Connection conexionBD;

	public DataAccess() {
		try {
			conexionBD=DriverManager.getConnection(sourceURL, usuario, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void Consulta() {
		Statement sentencia = null;
		String miOrden = "SELECT Nombre,apellidos FROM Alumno";
		try {
			sentencia = conexionBD.createStatement();
			ResultSet res = sentencia.executeQuery(miOrden);
			System.out.println(res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
