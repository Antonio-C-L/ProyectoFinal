package clases;

import java.time.LocalDate;
import java.util.List;

public class Alumno extends Persona{
	private int id;
	private List<Nota> notas;
	
	public static final int DIECIOCHO=18;
	public static final int CERO=0;
	
	public int getIdAlumno() {
		return id;
	}
	public List<Nota> getNotas() {
		return notas;
	}
	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}

	public Alumno(String dni, String nombre, String apellido1, String apellido2, LocalDate fechaNacimiento) {
		super(dni, nombre, apellido1, apellido2, fechaNacimiento);
	}

	public Alumno(int id, String dni, String nombre, String apellido1, String apellido2, LocalDate fechaNacimiento) {
		super(dni, nombre, apellido1, apellido2, fechaNacimiento);
		this.id = id;
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
	
	/**	Metodo que comprueba si el alumno es mayor de edad
	 * <p>
	 * Precondiciones: El Alumno que llame al metodo no puede ser null
	 * <br>
	 * Postcondiciones: Ninguna
	 * <br>
	 * 
	 * @return boolean: True si es mayor, False si es menor
	 * 
	 * @author Antonio
	 */
	public boolean isMayorEdad() {
		return anyoDeMayoria() ? compareDay() : anyoMayor();
	}
	
	/** Metodo equals para comprobar si un objeto es igual al Alumno
	 * <p>
	 * Precondiciones: El Alumno que llame al metodo no puede ser null
	 * <br>
	 * Postcondiciones: Ninguna
	 * <br>
	 * @param obj El objeto con el que comparamos
	 * 
	 * @return boolean: True si es igual, False si es distinto 
	 * 
	 * @author Antonio
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
	
	/** Metodo para pasar el objeto a string
	 * <p>
	 * Precondiciones:  El Alumno que llame al metodo no puede ser null
	 * <br>
	 * Postcondiciones: Ninguna
	 * <br>
	 * @return String: El Objeto Alumno pasado a String
	 * 
	 * @author Antonio
	 */
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(getNombre()).append(" ").append(getApellido1()).append(" ").append(getApellido2()).append(" ").append(getFechaNacimiento());
		return sb.toString();
	}
	
}
