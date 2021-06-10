package clases;

import java.time.LocalDate;
import java.util.List;

public class Profesor extends Persona{
	private int id;
	private List<Alumno> alumnos;

	public int getIdProfesor() {
		return id;
	}
	public List<Alumno> getAlumnos() {
		return alumnos;
	}
	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public Profesor(String dni, String nombre, String apellido1, String apellido2, LocalDate fechaNacimiento, int idProfesor) {
		super(dni, nombre, apellido1, apellido2, fechaNacimiento);
		this.id = idProfesor;
	}
	

	@Override
	public String toString() {
		return super.toString();
	}
}
