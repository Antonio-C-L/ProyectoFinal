package dataAccess;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import clases.Alumno;

public class DataAccess {
	private Properties p;
	Connection conexionBD=null;
	Statement sentencia = null;

	public static final String[] TABLA_ALUMNO={"ID", "DNI", "Nombre", "Apellido1", "Apellido2", "FechaNacimiento"};
	public static final String INSERT_ALUMNO = "insert into Alumno (ID, DNI, Nombre, Apellido1, Apellido2, FechaNacimiento) values (";
	public static final String PROPERTIES="src\\config\\config.properties";

	public DataAccess() {
		p = new Properties();
		try {
			p.load(new FileReader(PROPERTIES));
			conexionBD=DriverManager.getConnection(p.getProperty("sourceURL"), p.getProperty("admin"), p.getProperty("adminPass"));
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	public List<String> ListaAlumnos() {
		String miOrden = "select "+TABLA_ALUMNO[1]+","+TABLA_ALUMNO[2]+","+TABLA_ALUMNO[3]+","+TABLA_ALUMNO[4]+" "+"from"+" "+Alumno.class.getSimpleName();
		List<String> list=new ArrayList<String>();
		try {
			sentencia = conexionBD.createStatement();
			ResultSet res = sentencia.executeQuery(miOrden);
			while (res.next()) {
				list.add(res.getString(TABLA_ALUMNO[1])+" "+res.getString(TABLA_ALUMNO[2])+" "+res.getString(TABLA_ALUMNO[3])+" "+res.getString(TABLA_ALUMNO[4]));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				sentencia.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public void addAlumno(Alumno alumno) {
		String miOrden = INSERT_ALUMNO+alumno.getIdAlumno()+",\'"+alumno.getDni()+"\',\'"+alumno.getNombre()+"\',\'"+alumno.getApellido1()+"\',\'"+alumno.getApellido2()+"\',\'"+alumno.getFechaNacimiento()+"\')";
		System.out.println(miOrden);
		try {
			sentencia=conexionBD.createStatement();
			sentencia.executeUpdate(miOrden);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				sentencia.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void close() {
		try {
			conexionBD.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
