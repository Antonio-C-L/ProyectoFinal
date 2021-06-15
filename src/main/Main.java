package main;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import clases.Alumno;
import dataAccess.DataAccess;
import excepciones.*;
import menu.Menu;

public class Main {

	public static Alumno creacionAlumno(Scanner sc, DataAccess da) throws CancelacionException, ErrorCreacionAlumno {
		Alumno alumno = null;
		String[] datos=Menu.pedirDatosPersona(sc);
		try{
			alumno=da.reconstruccionAlumno(datos);
		}finally {
			if (alumno==null)
				throw new ErrorCreacionAlumno();
		}
		return alumno;
	}

	public static void gestionAlumnos(String elec, Scanner sc, DataAccess da) throws NumberFormatException, Exception {
		do {
			elec=Menu.mostrarGestionAlumno(sc);
			switch (elec) {
			case "0": {
				Menu.volverMenuAnterior();
				break;
			}
			case "1": {
				try {
					Alumno alumno=creacionAlumno(sc, da);
					if (da.existeAlumnoDNI(alumno.getDni())) {
						if(Menu.confirmar(elec, sc))
							da.addAlumno(alumno);
					}else
						da.addAlumno(alumno);
				} catch (CancelacionException e) {
					Menu.operacionCancelada();
				} catch (ErrorCreacionAlumno e) {
					Menu.errorCreacionAlumno();
				}
				break;
			}
			case "2":{
				List<String> list=da.listaAlumnos();
				Menu.mostrarLista(list);
				break;
			}
			case "3":{
				try {
					List<String> list=da.buscarAlumnoPorDNI(Menu.pedirDNI(sc));
					if(list.size()==1) {
						manejarAlumno(list.get(0), da, elec, sc);
					}else if(list.size()==0){
						Menu.sinCoincidencias();
					}else {
						manejarAlumno(da.buscarAlumnoPorID(Integer.parseInt(Menu.variosAlumnosMismoDNI(list, sc, elec))), da, elec, sc);
					}
				} catch (CancelacionException e) {
					Menu.operacionCancelada();
				}
				break;
			}
			default:
				Menu.opcionNoValida();
			}
		}while(!elec.equals("0"));
	}

	private static void manejarAlumno(String string, DataAccess da, String elec, Scanner sc) throws Exception {
		Alumno alumno=da.reconstruccionAlumnoConID(string.split("_"));//TODO hacer constante
		do{
			elec=Menu.manejoAlumno(sc);
			switch (elec) {
			case "0": {
				Menu.volverMenuAnterior();
				break;
			}
			case "1":{
				Menu.mostrarDatosAlumno(alumno.toString());
				break;
			}
			case "2":{
				modificarAlumno(alumno, da, elec, sc);
				break;
			}
			case "3":{
				da.delAlumnoID(alumno.getIdAlumno());
				Menu.eliminacionAlumno();
				break;
			}
			case "4":{
				Menu.mayorEdad(alumno.isMayorEdad());
				break;
			}
			case "5":{
				//TODO Sin implementar aun
				break;
			}
			case "6":{
				//TODO Sin implementar aun
				break;
			}
			default:{
				Menu.opcionNoValida();
				break;
			}
			}
		}while(!elec.equals("0") || !elec.equals("3"));
	}

	private static void modificarAlumno(Alumno alumno, DataAccess da, String elec, Scanner sc) throws Exception {
		do {
			elec=Menu.modificarAlumno(sc);
			try{
				switch (elec) {
				case "1":{
					Menu.mostrarDatosAlumno(alumno.toString());
					break;
				}
				case "2":{
					da.updateAlumno(alumno);
					break;
				}
				case "3":{
					alumno.setDni(Menu.pedirDNI(sc));
					break;
				}
				case "4":{
					alumno.setNombre(Menu.pedirNombre(sc));
					break;
				}
				case "5":{
					alumno.setApellido1(Menu.pedirApellido1(sc));
					break;
				}
				case "6":{
					alumno.setApellido2(Menu.pedirApellido2(sc));
					break;
				}
				case "7":{
					alumno.setFechaNacimiento(LocalDate.parse(Menu.pedirFechaNacimiento(sc)));
					break;
				}
				default:
					Menu.opcionNoValida();
					break;
				}
			}catch (CancelacionException e) {
				Menu.operacionCancelada();
			}
		}while (!elec.equals("0"));
	}

	public static void main(String[] args) {

		Menu.mostarMensajeInicio();
		DataAccess da = null;
		Scanner sc=null;
		String elec = null;
		try{
			da=new DataAccess();
			sc=new Scanner(System.in);
			do {
				elec=Menu.mostrarMenuPrincipal(sc);
				switch (elec) {
				case "0": {
					Menu.salir();
					break;
				}
				case "1": {
					gestionAlumnos(elec, sc, da);
					break;
				}
				case "2": {
					//TODO Sin implementar aun
					break;
				}
				case "3": {
					//TODO Sin implementar aun
					break;
				}
				default:
					Menu.opcionNoValida();
				}
			}while (!elec.equals("0"));

		}catch(Exception e){ // :)
			Menu.errorGrave();
			Errores.errorLog(e);
		}finally {
			try {
				da.close();
			} catch (Exception e) {
				Menu.errorGrave();
				Errores.errorLog(e);
			}
			sc.close();
		}

	}

}
