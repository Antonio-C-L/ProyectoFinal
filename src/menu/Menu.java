package menu;

import java.util.List;
import java.util.Scanner;

import enums.DatosAlumno;
import excepciones.CancelacionException;

public class Menu {
	public static String elec;
	public static final String MENSAJE_INICIO="Bienvenido al Gestor Coseneca Version 0.0210615"
			+ "\n	Conectando a la BBDD, espere...";
	private static final String OPCION_NO_VALIDA = "Opción no valida";
	public static final String MENU_PRINCIPAL="MENU PRINCIPAL"
			+ "\n	1. Gestionar Alumnos"
			+ "\n	2. Gestionar Profesores (Sin implementar)"
			+ "\n	3. Gestionar Asignaturas (Sin implementar)"
			+ "\n	"
			+ "\n	0. Salir de la APP";
	private static final String GESTION_ALUMNO = "Menu de Gestión de Alumnos"
			+ "\n	1. Añadir Alumno"
			+ "\n	2. Mostrar lista de todos los Alumnos"
			+ "\n	3. Buscar Alumno por DNI"
			+ "\n	"
			+ "\n	0. Volver al Menu Inicial";
	private static final String MAX_CARACTER = " (Máximo de caracteres 20)";
	private static final String MAX_CARACTER_DNI = " (Máximo de caracteres 9)";
	private static final String MENSAJE_SALIDA = "Saliendo del programa, que tenga un buen dia";
	private static final String VOLVER_MENU_ANTERIOR = "Volviendo al menú anterior";
	private static final String FORMATO_FECHA = " (formato YYYY-MM-DD)";
	private static final String CANCELAR = " | Para cancelar introduzca \"0\"";
	private static final String CONFIRMACION = "Ya existe un alumno con ese DNI ¿estas seguro que desea añadirlo?"
			+ "\n	1. Si"
			+ "\n	2. No";
	private static final String VARIOS_ALUMNOS_DNI = "Existen varios alumnos con ese DNI:";
	private static final String SELECCIONAR_ALUMNO = "Seleccione el alumno con el que trabajar según su ID";
	private static final String CANCEL = "Operacion cancelada";
	private static final String SIN_COINCIDENCIAS = "No hay coincidencias";
	private static final String ERROR_CREACION_ALUMNO = "Error al crear el Alumno";
	private static final String MENU_MANEJO_ALUMNO = "¿Qué desea hacer?"
			+ "\n	1. Mostar datos"
			+ "\n	2. Cambiar datos"
			+ "\n	3. Eliminar alumno de la BBDD"
			+ "\n	4. ¿Es mayor de edad?"
			+ "\n	5. Ver notas (sin implementar aun)"
			+ "\n	6. Ver asignaturas (sin implementar aun)"
			+ "\n	"
			+ "\n	0. Nada";
	private static final String ELIMINACION_ALUMNO = "Alumno eliminado con existo";
	private static final String MODIFICAR_ALUMNO = "¿Qué desea hacer?"
			+ "\n	1. Mostar datos"
			+ "\n	2. Actualizar BBDD con los datos nuevos"
			+ "\n	3. Cambiar "+DatosAlumno.DNI
			+ "\n	4. Cambiar "+DatosAlumno.NOMBRE
			+ "\n	5. Cambiar "+DatosAlumno.APELLIDO1
			+ "\n	6. Cambiar "+DatosAlumno.APELLIDO2
			+ "\n	7. Cambiar "+DatosAlumno.FECHA_NACIMIENTO
			+ "\n	"
			+ "\n	0. Nada, esta opción no actualizara la BBDD";
	private static final String NO = "No";
	private static final String SI = "Si";
	private static final String ERROR_GRAVE = "Se ha producido un error inesperado, pongase en contacto con el administrador";
	public static void mostarMensajeInicio() {
		System.out.println(MENSAJE_INICIO);
	}

	public static void opcionNoValida()	{
		System.out.println(OPCION_NO_VALIDA);
	}

	public static String mostrarMenuPrincipal(Scanner sc) {
		System.out.println(MENU_PRINCIPAL);
		elec=sc.nextLine();
		return elec;
	}

	public static String mostrarGestionAlumno(Scanner sc) {
		System.out.println(GESTION_ALUMNO);
		elec=sc.nextLine();
		return elec;
	}

	public static String pedirNombre(Scanner sc) throws CancelacionException {
		String dato;
		do{
			System.out.println(DatosAlumno.NOMBRE+MAX_CARACTER+CANCELAR);//TODO cambiar
			dato = sc.nextLine();
		} while (!Validaciones.stringTamanyoMax(dato) && !dato.equals("0"));
		if (dato.equals("0"))
			throw new CancelacionException();
		return dato.toUpperCase().charAt(0)+dato.substring(1);
	}
	
	public static String pedirApellido1(Scanner sc) throws CancelacionException {
		String dato;
		do{
			System.out.println(DatosAlumno.APELLIDO1+MAX_CARACTER+CANCELAR);//TODO cambiar
			dato = sc.nextLine();
		} while (!Validaciones.stringTamanyoMax(dato) && !dato.equals("0"));
		if (dato.equals("0"))
			throw new CancelacionException();
		return dato.toUpperCase().charAt(0)+dato.substring(1);
	}
	
	public static String pedirApellido2(Scanner sc) throws CancelacionException {
		String dato;
		do{
			System.out.println(DatosAlumno.APELLIDO2+MAX_CARACTER+CANCELAR);//TODO cambiar
			dato = sc.nextLine();
		} while (!Validaciones.stringTamanyoMax(dato) && !dato.equals("0"));
		if (dato.equals("0"))
			throw new CancelacionException();
		return dato.toUpperCase().charAt(0)+dato.substring(1);
	}
	
	public static String pedirDNI(Scanner sc) throws CancelacionException {
		String dato;
		do{
			System.out.println(DatosAlumno.DNI+MAX_CARACTER_DNI+CANCELAR);//TODO cambiar
			dato = sc.nextLine();
		} while (!Validaciones.stringDNI(dato) && !dato.equals("0"));
		if (dato.equals("0"))
			throw new CancelacionException();
		return dato.toUpperCase();
	}
	
	public static String pedirFechaNacimiento(Scanner sc) throws CancelacionException {
		String dato;
		do{
			System.out.println(DatosAlumno.FECHA_NACIMIENTO+FORMATO_FECHA+CANCELAR);//TODO cambiar
			dato = sc.nextLine();
		} while (!Validaciones.validarFecha(dato) && !dato.equals("0"));
		if (dato.equals("0"))
			throw new CancelacionException();
		return dato;
	}
	
	public static String[] pedirDatosPersona(Scanner sc) throws CancelacionException {
		String[] datosAlumno= {pedirDNI(sc),pedirNombre(sc),pedirApellido1(sc),pedirApellido2(sc),pedirFechaNacimiento(sc)};
		return datosAlumno;
	}

	public static void salir() {
		System.out.println(MENSAJE_SALIDA);
	}

	public static void volverMenuAnterior() {
		System.out.println(VOLVER_MENU_ANTERIOR);
	}

	public static void mostrarLista(List<String> lista) {
		for (String string : lista) {
			System.out.println(string);
		}
	}

	public static boolean confirmar(String elec, Scanner sc) {
		do {
			System.out.println(CONFIRMACION);
			elec=sc.nextLine();
		} while (elec.equals("1") || elec.equals("2"));
		return elec.equals("1");
	}

	public static String variosAlumnosMismoDNI(List<String> list, Scanner sc, String elec) throws CancelacionException {
		System.out.println(VARIOS_ALUMNOS_DNI);
		mostrarLista(list);
		String alumno=null;
		do{
			System.out.println(SELECCIONAR_ALUMNO);
			elec = sc.nextLine();
		} while (!Validaciones.validarNumero(elec) && !elec.equals("0"));
		for (String string : list) {
			if(string.split(" ")[0].equals(elec))
				alumno=string;
		}
		if (elec.equals("0") || alumno==null)
			throw new CancelacionException();
		return alumno.split(" ")[0];
	}

	public static void operacionCancelada() {
		System.out.println(CANCEL);
	}
	public static void sinCoincidencias() {
		System.out.println(SIN_COINCIDENCIAS);
	}
	public static void errorCreacionAlumno() {
		System.out.println(ERROR_CREACION_ALUMNO);
	}
	
	public static String manejoAlumno(Scanner sc) {
		System.out.println(MENU_MANEJO_ALUMNO);
		elec=sc.nextLine();
		return elec;
	}

	public static void mostrarDatosAlumno(String alumno) {
		System.out.println(alumno);
	}

	public static void eliminacionAlumno() {
		System.out.println(ELIMINACION_ALUMNO);
	}
	
	public static String modificarAlumno(Scanner sc) {
		System.out.println(MODIFICAR_ALUMNO);
		elec=sc.nextLine();
		return elec;
	}
	
	public static void mayorEdad(boolean bool) {
//		bool ? System.out.println(SI) : System.out.println(NO); //TODO No funciona :S
		if(bool)
			System.out.println(SI);
		else
			System.out.println(NO);
	}
	
	public static void errorGrave() {
		System.out.println(ERROR_GRAVE);
	}
}
