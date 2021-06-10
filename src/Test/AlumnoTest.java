package Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import clases.Alumno;

class AlumnoTest {

	@Test
	void testIsMayorEdad20Anyos() {
		Alumno alumno=new Alumno("00000000A", "Nombre", "Apellido1", "Apellido2", LocalDate.now().minusYears(20));
		assertTrue(alumno.isMayorEdad());
	}

	@Test
	void testIsMayorEdad18Anyos() {
		Alumno alumno=new Alumno("00000000A", "Nombre", "Apellido1", "Apellido2", LocalDate.now().minusYears(18));
		assertTrue(alumno.isMayorEdad());
	}

	@Test
	void testIsMayorEdad15Anyos() {
		Alumno alumno=new Alumno("00000000A", "Nombre", "Apellido1", "Apellido2", LocalDate.now().minusYears(15));
		assertFalse(alumno.isMayorEdad());
	}

	@Test
	void testEqualsTrue() {
		Alumno alumno1=new Alumno("00000000A", "Nombre", "Apellido1", "Apellido2", LocalDate.now().minusYears(15));
		Alumno alumno2=new Alumno("00000000A", "Nombre", "Apellido1", "Apellido2", LocalDate.now().minusYears(15));
		assertTrue(alumno1.equals(alumno2));;
	}
	
	@Test
	void testEqualsFalse() {
		Alumno alumno1=new Alumno("12345678A", "Nombre", "Apellido1", "Apellido2", LocalDate.now().minusYears(15));
		Alumno alumno2=new Alumno("12345678B", "Nombre", "Apellido1", "Apellido2", LocalDate.now().minusYears(15));
		assertFalse(alumno1.equals(alumno2));
	}
	
	//TODO hacer mas test
}
