package dataAccess;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import clases.Alumno;

public class DataAccess {
	private Properties p;
	private Connection conexionBD=null;
	private Statement sentencia = null;

	public static final String[] TABLA_ALUMNO={"ID", "DNI", "Nombre", "Apellido1", "Apellido2", "FechaNacimiento"};
	public static final String INSERT_ALUMNO = "insert into "+Alumno.class.getSimpleName()+" (ID, DNI, Nombre, Apellido1, Apellido2, FechaNacimiento) values (";//TODO quitar/cambiar/arreglar
	public static final String PROPERTIES="src\\config\\config.properties";

	public DataAccess() throws FileNotFoundException, IOException, SQLException {
		p = new Properties();
		p.load(new FileReader(PROPERTIES));
		conexionBD=DriverManager.getConnection(p.getProperty("sourceURL"), p.getProperty("admin"), p.getProperty("adminPass"));
	}

	//Para cerrar
	private void cerrar(AutoCloseable obj) throws Exception {
		obj.close();
	}

	//Obtener ultimo ID
	private int getUltimoID() throws Exception {
		String miOrden="select top 1 ID from "+Alumno.class.getSimpleName()+" order by ID desc";
		int num=0;
		try {
			sentencia=conexionBD.createStatement();
			ResultSet res = sentencia.executeQuery(miOrden);
			while (res.next()) {
				num=res.getInt(1);
			}
		}finally {
			cerrar(sentencia);
		}
		return num;
	}

	public boolean existeAlumnoDNI(String dni) throws Exception {
		String miOrden="select dni from "+Alumno.class.getSimpleName()+" where dni=\'"+dni+"\'";
		boolean exist=false;
		try {
			sentencia=conexionBD.createStatement();
			ResultSet res = sentencia.executeQuery(miOrden);
			while (res.next() && exist==false) {
				exist=res.getString(1).equals(dni);
			}
		}finally {
			cerrar(sentencia);
		}
		return exist;
	}

	public boolean existeAlumnoID(int id) throws Exception {
		String miOrden="select id from "+Alumno.class.getSimpleName()+" where id=\'"+id+"\'";
		boolean exist=false;
		try {
			sentencia=conexionBD.createStatement();
			ResultSet res = sentencia.executeQuery(miOrden);
			while (res.next() && exist==false) {
				exist=Integer.parseInt(res.getString(1))==(id);
			}
		}finally {
			cerrar(sentencia);
		}
		return exist;
	}


	public List<String> listaAlumnos() throws Exception {
		String miOrden = "select "+TABLA_ALUMNO[1]+","+TABLA_ALUMNO[2]+","+TABLA_ALUMNO[3]+","+TABLA_ALUMNO[4]+" from"+" "+Alumno.class.getSimpleName();
		List<String> list=new ArrayList<String>();
		try {
			sentencia = conexionBD.createStatement();
			ResultSet res = sentencia.executeQuery(miOrden);
			while (res.next()) {
				list.add(res.getString(TABLA_ALUMNO[1])+" "+res.getString(TABLA_ALUMNO[2])+" "+res.getString(TABLA_ALUMNO[3])+" "+res.getString(TABLA_ALUMNO[4]));
			}
		}finally {
			cerrar(sentencia);
		}
		return list;
	}

	public void addAlumno(Alumno alumno) throws Exception {
		String miOrden = INSERT_ALUMNO+(getUltimoID()+1)+",\'"+alumno.getDni()+"\',\'"+alumno.getNombre()+"\',\'"+alumno.getApellido1()+"\',\'"+alumno.getApellido2()+"\',\'"+alumno.getFechaNacimiento()+"\')";
		try {
			sentencia=conexionBD.createStatement();
			sentencia.executeUpdate(miOrden);
		}finally {
			cerrar(sentencia);
		}
	}

	public void delAlumnoID(int id) throws Exception {
		if(existeAlumnoID(id)) {
			String miOrden = "delete from "+Alumno.class.getSimpleName()+" where id=\'"+id+"\'";
			try {
				sentencia=conexionBD.createStatement();
				sentencia.executeUpdate(miOrden);
			}finally {
				cerrar(sentencia);
			}
		}
	}

	public void updateAlumno(Alumno alumno) throws Exception {
		if(existeAlumnoDNI(alumno.getDni())) {
			String miOrden = "update "+Alumno.class.getSimpleName()+" set "+TABLA_ALUMNO[2]+"=\'"+alumno.getNombre()+"\', "+TABLA_ALUMNO[3]+"=\'"+alumno.getApellido1()+"\', "+TABLA_ALUMNO[4]+"=\'"+alumno.getApellido2()+"\', "+TABLA_ALUMNO[5]+"=\'"+alumno.getFechaNacimiento().toString()+"\' where "+TABLA_ALUMNO[1]+"=\'"+alumno.getDni()+"\'";
			System.out.println(miOrden);
			try {
				sentencia=conexionBD.createStatement();
				sentencia.executeUpdate(miOrden);
			}finally {
				cerrar(sentencia);
			}
		}
	}

	public List<String> buscarAlumnoPorDNI(String dni) throws Exception {
		String miOrden = "select "+TABLA_ALUMNO[0]+","+TABLA_ALUMNO[1]+","+TABLA_ALUMNO[2]+","+TABLA_ALUMNO[3]+","+TABLA_ALUMNO[4]+","+TABLA_ALUMNO[5]+" from"+" "+Alumno.class.getSimpleName()+" where dni=\'"+dni+"\'";
		List<String> list=new LinkedList<String>();
		try {
			sentencia=conexionBD.createStatement();
			ResultSet res = sentencia.executeQuery(miOrden);
			while (res.next()) {
				list.add(res.getString(TABLA_ALUMNO[0])+"_"+res.getString(TABLA_ALUMNO[1])+"_"+res.getString(TABLA_ALUMNO[2])+"_"+res.getString(TABLA_ALUMNO[3])+"_"+res.getString(TABLA_ALUMNO[4])+"_"+res.getString(TABLA_ALUMNO[5]));
			}
		}finally {
			cerrar(sentencia);
		}
		return list;
	}

	public String buscarAlumnoPorID(int id) throws Exception {
		String miOrden = "select "+TABLA_ALUMNO[0]+","+TABLA_ALUMNO[1]+","+TABLA_ALUMNO[2]+","+TABLA_ALUMNO[3]+","+TABLA_ALUMNO[4]+","+TABLA_ALUMNO[5]+" from"+" "+Alumno.class.getSimpleName()+" where id=\'"+id+"\'";
		String alumno = null;
		try {
			sentencia=conexionBD.createStatement();
			ResultSet res = sentencia.executeQuery(miOrden);
			while (res.next()) {
				alumno=res.getString(TABLA_ALUMNO[0])+"_"+res.getString(TABLA_ALUMNO[1])+"_"+res.getString(TABLA_ALUMNO[2])+"_"+res.getString(TABLA_ALUMNO[3])+"_"+res.getString(TABLA_ALUMNO[4])+"_"+res.getString(TABLA_ALUMNO[5]);
			}
		}finally {
			cerrar(sentencia);
		}
		return alumno;
	}
	
	public Alumno reconstruccionAlumno(String[] datosAlumno) {
		return new Alumno(datosAlumno[0], datosAlumno[1], datosAlumno[2], datosAlumno[3], LocalDate.parse(datosAlumno[4]));
	}

	public Alumno reconstruccionAlumnoConID(String[] datosAlumno) {
		return new Alumno(Integer.parseInt(datosAlumno[0]), datosAlumno[1], datosAlumno[2], datosAlumno[3], datosAlumno[4], LocalDate.parse(datosAlumno[5]));
	}
	
	public void close() throws Exception {
		cerrar(conexionBD);
	}


}
