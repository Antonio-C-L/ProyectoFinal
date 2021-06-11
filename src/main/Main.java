package main;

import java.time.LocalDate;
import clases.Alumno;
import dataAccess.DataAccess;

public class Main {

	public static void main(String[] args) {
		DataAccess da=new DataAccess();

		Alumno alumno=new Alumno("12345678A", "Antonio", "Candela", "Lora", LocalDate.parse("2002-06-10"));
		Alumno alumno2=new Alumno("12345678A", "Antonio", "Candela", "Lora", LocalDate.parse("2002-06-10"));
		//		da.addAlumno(alumno);
//		List <String> list=da.listaAlumnos();
//		for (String string : list) {
//			System.out.println(string);
//		}

//		System.out.println(da.buscarAlumno("12345678A"));
//
//		da.updateAlumno(alumno);
		da.delAlumno("12345678Z");
//		System.out.println(da.buscarAlumno("12345678A"));

		System.out.println(alumno.compareTo(alumno2));

		String[] alumnoA=da.buscarAlumnoPorDNI("12345678A");
		for (String string : alumnoA) {
			System.out.println(string);
		}
		
		Alumno a=da.reconstruccionAlumno(alumnoA);
		System.out.println(a.toString());

//		System.out.println(alumno.isMayorEdad());
		da.close();
	}

}
