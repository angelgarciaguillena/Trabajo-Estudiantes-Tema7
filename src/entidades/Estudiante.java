package entidades;

import java.util.Objects;

/**
 * Clase encargada de crear un nuevo objeto estudiante y de 
 * representar sus funciones basicas
 * 
 * @author Angel
 */
public class Estudiante {
	
	/**
	 * Creamos un atributo para almacenar el id del estudiante
	 */
	private int id;
	
	/**
	 * Creamos un atributo para almacenar el nombre del estudiante
	 */
	private String nombre;
	
	/**
	 * Creamos un atributo para almacenar el apellido del estudiante
	 */
	private String apellido;
	
	/**
	 * Creamos un atributo para almacenar la fecha de nacimiento del estudiante
	 */
	private String fechaNacimiento;
	
	/**
	 * Creamos un atributo para almacenar el email del estudiante
	 */
	private String email;
	
	/**
	 * Creamos un atributo para almacenar el telefono del estudiante
	 */
	private String telefono;
	
	/**
	 * Constructor que inicializa los atributos de la clase Estudiante comprobando que los valores
	 * son validos antes de asignarlos
	 * @param nombre Nombre del estudiante
	 * @param apellido Apellido del estudiante
	 * @param fechaNacimiento Fecha de nacimiento del estudiante
	 * @param email Email del estudiante
	 * @param telefono Telefono del estudiante
	 */
	public Estudiante(String nombre, String apellido, String fechaNacimiento, String email, String telefono) {
		
		if(nombre != null && !nombre.isBlank()) {
			this.nombre = nombre;
		}
		
		if(apellido != null && !apellido.isBlank()) {
			this.apellido = apellido;
		}
		
		if(fechaNacimiento != null && !fechaNacimiento.isBlank()) {
			this.fechaNacimiento = fechaNacimiento;
		}
		
		if(email != null && !email.isBlank()) {
			this.email = email;
		}
		
		if(telefono.length() == 9) {
			this.telefono = telefono;
		}
	}
	
	/**
	 * Funcion que devuelve el id del estudiante
	 * 
	 * @return Devuelve el id del estudiante
	 */
	public int getId() {
		return id;
	}

	/**
	 * Funcion que devuelve el nombre del estudiante
	 * 
	 * @return Devuelve el nombre del estudiante
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Funcion que devuelve el apellido del estudiante
	 * 
	 * @return Devuelve el apellido del estudiante
	 */
	public String getApellido() {
		return apellido;
	}
	
	/**
	 * Funcion que devuelve la fecha de nacimiento del estudiante
	 * 
	 * @return Devuelve la fecha de nacimiento del estudiante
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	/**
	 * Funcion que devuelve el email del estudiante
	 * 
	 * @return Devuelve el email del estudiante
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Funcion que devuelve el telefono del estudiante
	 * 
	 * @return Devuelve el telefono del estudiante
	 */
	public String getTelefono() {
		return telefono;
	}
	
	/**
	 * Funcion encargada de identificar al estudiante segun su id
	 * y devuelve un numero que identifica al estudiante
	 * 
	 * @return Devuelve un numero que identifica al estudiante
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	/**
	 * Funcion que compara si dos alumnos son iguales segun su id y devuelve 
	 * true si lo son o false si no son iguales
	 * 
	 * @return Devuelve true si son iguales o false si no lo son
	 */
	@Override
	public boolean equals(Object obj) {
		Estudiante estudiante2 = (Estudiante) obj;
		return this.id == estudiante2.id;
	}
	
	/**
	 * Funcion que devuelve una cadena con la informacion del estudiante
	 * 
	 * @return Devuelve una cadena con la informacion del estudiante
	 */
	@Override
	public String toString() {
		return this.nombre + " " + this.apellido + " - " + this.fechaNacimiento + " - " + this.email + " - " + this.telefono;
	}
}

/*
 * CREATE TABLE Estudiantes (
    id_estudiante INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE,
    email VARCHAR(100),
    telefono VARCHAR(20)
);
*/