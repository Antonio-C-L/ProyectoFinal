package clases;

import java.time.LocalDate;

public abstract class Persona implements Comparable<Persona>{
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private LocalDate fechaNacimiento;
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Persona(String dni, String nombre, String apellido1, String apellido2, LocalDate fechaNacimiento) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.fechaNacimiento = fechaNacimiento;
	}
	
	/**
	 * CompareTo creado para ordenar las series al compararlas segun el nombre //TODO cambiar/terminar
	 * 
	 * Precondiciones: Ambas series deben existir
	 * Postcondicones: Se devuelve un numero segun el orden
	 * Entrada: Serie a comparar
	 * Salida: Entero que indicara el orden
	 */
	@Override
	public int compareTo(Persona o) {
		return (getApellido1()+getApellido2()+getNombre()).compareTo((o.getApellido1()+o.getApellido2()+o.getNombre()));//Compara y devuelve la diferencia del primer caracter que sea distinto, los siguientes no los tiene cuenta
	}
}
