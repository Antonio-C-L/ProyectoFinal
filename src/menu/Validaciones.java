package menu;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Iterator;

public class Validaciones {

	//Validar si el String esta vacio
	private static boolean stringVacio(String string) {
		return string.equals("");
	}

	//Comprobar tamaño
	private static boolean stringMayorCero(String string) {
		return string.length()>0;//TODO Poner 0 como constante
	}

	public static boolean stringTamanyoMax(String string) {
		return string.length()<=20; //TODO Poner 20 como constante
	}

	public static boolean stringDNI(String string) {
		return string.length()==9? bienFormado(string): false;
	}
	private static boolean bienFormado(String string) {
		return letraDNI(string) && validarNumero(string.substring(0, 8));
	}
	private static boolean letraDNI(String string) {
		return string.toUpperCase().charAt(8)>='A' && string.toUpperCase().charAt(8)<='Z';
	}
	public static boolean validarNumero(String string) {
		boolean valido = !stringVacio(string);
		for (int i = 0; i < string.length() && valido; i++) {
			valido = Character.isDigit(string.charAt(i));
		}
		return valido;
	}

	public static boolean validarFecha(String string) {
		String[] fecha=string.split("-");//TODO hacer constante
		boolean valido=true;
		if(fecha.length==3){
			for (int i=0; i<fecha.length && valido; i++) {
				valido=validarNumero(fecha[i]);
			}
			try {
				LocalDate.of(Integer.parseInt(fecha[0]), Integer.parseInt(fecha[1]), Integer.parseInt(fecha[2]));
			}catch(DateTimeException e) {
				valido=false;
			}
		}
		return valido;
	}

}
