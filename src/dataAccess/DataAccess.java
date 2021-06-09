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
	private Connection conexionBD=null;
	private Statement sentencia = null;

	public static final String[] TABLA_ALUMNO={"ID", "DNI", "Nombre", "Apellido1", "Apellido2", "FechaNacimiento"};
	public static final String INSERT_ALUMNO = "insert into "+Alumno.class.getSimpleName()+" (ID, DNI, Nombre, Apellido1, Apellido2, FechaNacimiento) values (";
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

	//Para cerrar
	private void cerrar(AutoCloseable obj) {
		try {
			obj.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int getUltimoID() {
		String miOrden="select top 1 ID from "+Alumno.class.getSimpleName()+" order by ID desc";
		int num=0;
		try {
			sentencia=conexionBD.createStatement();
			ResultSet res = sentencia.executeQuery(miOrden);
			while (res.next()) {
				num=res.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			cerrar(sentencia);
		}
		return num;
	}

	private boolean existeAlumno(String dni) {
		String miOrden="select dni from "+Alumno.class.getSimpleName();
		boolean exist=false;
		try {
			sentencia=conexionBD.createStatement();
			ResultSet res = sentencia.executeQuery(miOrden);
			while (res.next() && exist==false) {
				exist=res.getString(1).equals(dni);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			cerrar(sentencia);
		}
		return exist;
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
			cerrar(sentencia);
		}
		return list;
	}

	public void addAlumno(Alumno alumno) {
		String miOrden = INSERT_ALUMNO+getUltimoID()+1+",\'"+alumno.getDni()+"\',\'"+alumno.getNombre()+"\',\'"+alumno.getApellido1()+"\',\'"+alumno.getApellido2()+"\',\'"+alumno.getFechaNacimiento()+"\')";
		try {
			sentencia=conexionBD.createStatement();
			sentencia.executeUpdate(miOrden);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			cerrar(sentencia);
		}
	}

	public void deleteAlumno(String dni) {
		if(existeAlumno(dni)) {
			String miOrden = "delete from Alumno where DNI=\'"+dni+"\'";
			try {
				sentencia=conexionBD.createStatement();
				sentencia.executeUpdate(miOrden);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				cerrar(sentencia);
			}
		}
	}

	public void updateAlumno(Alumno alumno) {
		if(existeAlumno(alumno.getDni())) {
			String miOrden = "update Alumno set "+TABLA_ALUMNO[2]+"=\'"+alumno.getNombre()+"\', "+TABLA_ALUMNO[3]+"=\'"+alumno.getApellido1()+"\', "+TABLA_ALUMNO[4]+"=\'"+alumno.getApellido2()+"\', "+TABLA_ALUMNO[5]+"=\'"+alumno.getFechaNacimiento().toString()+"\' where "+TABLA_ALUMNO[1]+"=\'"+alumno.getDni()+"\'";
			System.out.println(miOrden);
			try {
				sentencia=conexionBD.createStatement();
				sentencia.executeUpdate(miOrden);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				cerrar(sentencia);
			}
		}
	}

	public String buscarDatosAlumno(String dni) {
		String miOrden = "select "+TABLA_ALUMNO[1]+","+TABLA_ALUMNO[2]+","+TABLA_ALUMNO[3]+","+TABLA_ALUMNO[4]+" "+"from"+" "+Alumno.class.getSimpleName()+" where dni=\'"+dni+"\'";
		String alumno = null;
		try {
			sentencia=conexionBD.createStatement();
			ResultSet res = sentencia.executeQuery(miOrden);
			while (res.next()) {
				alumno=res.getString(TABLA_ALUMNO[1])+" "+res.getString(TABLA_ALUMNO[2])+" "+res.getString(TABLA_ALUMNO[3])+" "+res.getString(TABLA_ALUMNO[4]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			cerrar(sentencia);
		}
		return alumno;
	}


	public void close() {
		cerrar(conexionBD);
	}


}
