package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clases.Alumno;
import dataAccess.DataAccess;

class DataAccessTest {
	
	DataAccess da;
	Alumno alumno;
	
	@BeforeEach
	void conexionBD() {
		da=new DataAccess();
		alumno=new Alumno("99999999Z", "Antonio", "Candela", "Lora", LocalDate.parse("0001-01-01"));
	}
	
	@AfterEach
	void cerrarConexion() {
		da.close();
	}
	
	@Test
	void testBuscarAlumno() {
		da.addAlumno(alumno);
		assertTrue(da.buscarAlumnoPorDNI(alumno.getDni())!=null);
		da.delAlumno(alumno.getDni());
	}
	
	@Test
	void testEliminarAlumno() {
		da.addAlumno(alumno);
		assertTrue(da.buscarAlumnoPorDNI(alumno.getDni())!=null);
		da.delAlumno(alumno.getDni());
		assertFalse(da.buscarAlumnoPorDNI(alumno.getDni())!=null);
	}
	
	@Test
	void testAnyadirAlumno() {
		assertFalse(da.buscarAlumnoPorDNI(alumno.getDni())!=null);
		da.addAlumno(alumno);
		assertTrue(da.buscarAlumnoPorDNI(alumno.getDni())!=null);
		da.delAlumno(alumno.getDni());
	}
	
	
}
