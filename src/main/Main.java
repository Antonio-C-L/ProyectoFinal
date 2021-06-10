package main;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Properties;

import clases.Alumno;
import clases.Asignatura;
import clases.Persona;
import clases.Profesor;
import dataAccess.DataAccess;

public class Main {

	public static void main(String[] args) {
		DataAccess da=new DataAccess();

		Alumno alumno=new Alumno("12345678A", "Antonio", "Candela", "Lora", LocalDate.parse("2002-06-10"));
		Alumno alumno2=new Alumno("12345678A", "Antonio", "Candela", "Lora", LocalDate.parse("2002-06-10"));
		//		da.addAlumno(alumno);
		List <String> list=da.listaAlumnos();
//		for (String string : list) {
//			System.out.println(string);
//		}

//		System.out.println(da.buscarAlumno("12345678A"));
//
//		da.updateAlumno(alumno);
		da.deleteAlumno("12345678Z");
//		System.out.println(da.buscarAlumno("12345678A"));6575

		System.out.println(alumno.compareTo(alumno2));

		System.out.println(LocalDate.now().minusYears(20));
//		System.out.println(alumno.isMayorEdad());
		da.close();
	}

}
