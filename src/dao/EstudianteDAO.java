package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Estudiante;
import utils.Constantes;

public class EstudianteDAO {
	
	/**
	 * Creamos un atributo para almacenar la conexion con la base de datos
	 */
	private Connection conexion;
	
	/**
	 * Constructor que inicializa los atributos de la clase EstudianteDAO 
	 * estableciendo la conexion con la base de datos y si no conecta 
	 * muestra un error
	 */
	public EstudianteDAO() {
		try {
			conexion = DriverManager.getConnection(Constantes.CONEXION, Constantes.USUARIO, Constantes.CONTRASEÑA);
		} catch (SQLException e) {
			System.out.println("Error: No se ha podido conectar con la base de datos");
		}
	}
	
	/**
	 * Funcion que devuelve la conexion con la base de datos
	 * 
	 * @return Devuelve la conexion con la base de datos
	 */
	public Connection getConexion() {
		return conexion;
	}
	
	public void crearEstudiante(Estudiante estudiante) {
		
		String consulta = "INSERT INTO estudiantes (nombre, apellido, fecha_nacimiento, email, telefono) VALUES " +
		"('" + estudiante.getNombre() + "', '" + estudiante.getApellido() + "', '" + estudiante.getFechaNacimiento() + "', '" + estudiante.getEmail() + "', '" + estudiante.getTelefono() + "')";
		
//		Statement sentencia;
//	}
//	
//	public ArrayList<Estudiante> listarEstudiantes() {
//		
//	}
//	
//	public Estudiante buscarEstudiante(int id) {
//		
	}
	
	public void modificarEstudianteEmail(int id) {
		
	}
	
	public void modificarEstudianteTelefono(int id) {
		
	}
	
	public void eliminarEstudiante(Estudiante estudiante) {
		
	}
	
	
}