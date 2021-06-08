package clases;

import java.time.LocalDate;
import java.util.List;

public class Profesor extends Persona{
	private int id;
	private List<Alumno> alumnos;
	private List<Asignatura> asignaturas;
	public int getIdProfesor() {
		return id;
	}
	public void setIdProfesor(int idProfesor) {
		this.id = idProfesor;
	}
	public List<Alumno> getAlumnos() {
		return alumnos;
	}
	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	public List<Asignatura> getAsignaturas() {
		return asignaturas;
	}
	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}
	public Profesor(String dni, String nombre, String apellido1, String apellido2, LocalDate fechaNacimiento,
			int idProfesor, List<Alumno> alumnos, List<Asignatura> asignaturas) {
		super(dni, nombre, apellido1, apellido2, fechaNacimiento);
		this.id = idProfesor;
		this.alumnos = alumnos;
		this.asignaturas = asignaturas;
	}
	
	
}
