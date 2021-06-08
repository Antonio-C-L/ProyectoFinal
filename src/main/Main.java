package main;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

import clases.Alumno;
import clases.Persona;
import dataAccess.DataAccess;

public class Main {

	public static void main(String[] args) {
		DataAccess da=new DataAccess();
		
		Alumno alumno=new Alumno("12345678A", "Pepe", "Garcia", "Vaquero", LocalDate.parse("1990-05-10"), 2);
//		da.addAlumno(alumno);
		List <String> lista=da.ListaAlumnos();
		
		for (String string : lista) {
			System.out.println(string);
		}
		
		da.close();
	}

}
