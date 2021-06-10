package clases;

public class Asignatura {
	private String nombre;
	private String nombreClave;
	private int numHoras;
	
	public String getNombre() {
		return nombre;
	}
	public String getNombreClave() {
		return nombreClave;
	}
	public int getNumHoras() {
		return numHoras;
	}

	public Asignatura(String nombre, String nombreClave, int numHoras) {
		this.nombre = nombre;
		this.nombreClave = nombreClave;
		this.numHoras = numHoras;
	}
	
	
}
