package clases;

import java.time.LocalDate;

public class Alumno extends Persona{
	private int id;//TODO como hacerlo
	private Nota notas;
	
	public int getIdAlumno() {
		return id;
	}
	public void setIdAlumno(int idAlumno) {
		this.id = idAlumno;
	}
	public Nota getNotas() {
		return notas;
	}
	public void setNotas(Nota notas) {
		this.notas = notas;
	}
	
	public Alumno(String dni, String nombre, String apellido1, String apellido2, LocalDate fechaNacimiento,	int idAlumno) {
		super(dni, nombre, apellido1, apellido2, fechaNacimiento);
		this.id = idAlumno;
	}
	
	
}
