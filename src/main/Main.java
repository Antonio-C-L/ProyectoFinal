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

		Alumno alumno=new Alumno("12345678A", "Luis", "Garcia", "Romon", LocalDate.parse("1990-01-10"), 2);
//		da.addAlumno(alumno);
		List <String> list=da.ListaAlumnos();
//		for (String string : list) {
//			System.out.println(string);
//		}

//		System.out.println(da.buscarAlumno("12345678A"));
//
//		da.updateAlumno(alumno);
//		System.out.println(da.buscarAlumno("12345678A"));
		
		da.close();
	}

}
