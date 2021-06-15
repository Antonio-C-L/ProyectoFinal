package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clases.Alumno;
import dataAccess.DataAccess;

class DataAccessTest {

	DataAccess da;
	Alumno alumno;

	//TODO todo mal, cambie varias cosas y al final se quedo mal
	
//	@BeforeEach
//	void conexionBD() {
//		try {
//			da=new DataAccess();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		alumno=new Alumno("99999999Z", "Antonio", "Candela", "Lora", LocalDate.parse("0001-01-01"));
//	}
//
//	@AfterEach
//	void cerrarConexion() {
//		try {
//			da.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Test
//	void testBuscarAlumno() {
//
//		try {
//			da.addAlumno(alumno);
//			assertTrue(da.buscarAlumnoPorID(alumno.getIdAlumno())!=null);
//			da.delAlumnoID(alumno.getIdAlumno());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Test
//	void testEliminarAlumno() {
//		try {
//			da.addAlumno(alumno);
//			assertTrue(da.buscarAlumnoPorID(alumno.getIdAlumno())!=null);
//			da.delAlumnoID(alumno.getIdAlumno());
//			assertFalse(da.buscarAlumnoPorID(alumno.getIdAlumno())!=null);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Test
//	void testAnyadirAlumno() {
//		try {
//			assertFalse(da.buscarAlumnoPorID(alumno.getIdAlumno())!=null);
//			da.addAlumno(alumno);
//			assertTrue(da.buscarAlumnoPorID(alumno.getIdAlumno())!=null);
//			da.delAlumnoID(alumno.getIdAlumno());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}


}
