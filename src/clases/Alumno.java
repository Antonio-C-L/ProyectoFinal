package clases;

import java.time.LocalDate;

public class Alumno extends Persona{
	private int id;
	private Nota notas;
	public static final int DIECIOCHO=18;
	public static final int CERO=0;
	public int getIdAlumno() {
		return id;
	}
	public Nota getNotas() {
		return notas;
	}
	public void setNotas(Nota notas) {
		this.notas = notas;
	}

	public Alumno(String dni, String nombre, String apellido1, String apellido2, LocalDate fechaNacimiento) {
		super(dni, nombre, apellido1, apellido2, fechaNacimiento);
	}

	private boolean anyoMayor() {
		return LocalDate.now().getYear()-getFechaNacimiento().getYear()>DIECIOCHO;
	}
	private boolean anyoDeMayoria() {
		return LocalDate.now().getYear()-getFechaNacimiento().getYear()==DIECIOCHO;
	}
	private boolean compareDay() {
		return LocalDate.now().getDayOfYear()-getFechaNacimiento().getDayOfYear()>=CERO;
	}
	
	public boolean isMayorEdad() {
		return anyoDeMayoria() ? compareDay() : anyoMayor();
	}
	
	/** Metodo para comprobar si dos series son iguales (comprobando el nombre) //TODO cambiar/terminar
	 * 
	 * Precondiciones: Se tiene que introduccir por parametros una serie valida segun la clase
	 * Postcondiciones: Devolver un booleano
	 * Entrada: Serie a comparar si son iguales
	 * Salida: Booleano que indica si son iguales
	 */
	@Override
	public boolean equals(Object obj) {
		boolean iguales=false;
		if(this==obj)
			iguales=true;
		else if(obj instanceof Alumno) {
			if (this.getDni().equals(((Alumno) obj).getDni()))
				iguales=true;
		}
		return iguales;
	}
	
	/** Metodo para pasar el objeto a string //TODO terminar/Cambiar
	 * 
	 * Precondiciones: Ninguna
	 * Postcondiciones: Ninguna
	 * Entrada: Nada
	 * Salida: String de los datos
	 */
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(getNombre()).append(getApellido1());
		return sb.toString();
	}
	
}
