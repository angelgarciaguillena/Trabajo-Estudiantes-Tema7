package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.Estudiante;
import utils.Constantes;

/**
 * Clase encargada de realizar las funciones crear, modificar, eliminar y leer en la 
 * base de datos
 * 
 * @author Angel
 */
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
	
	/**
	 * Funcion que añade un nuevo estudiante a la base de datos y devuelve true si se ha añadido
	 * o false si no se ha podido añadir
	 * 
	 * @param estudiante Estudiante qe se va a añadir a la base de datos
	 * @return Devuelve true si el estudiante se ha añadido o false si no se ha podido añadir
	 */
	public boolean crearEstudiante(Estudiante estudiante) {
		
		String consulta = "INSERT INTO estudiantes (nombre, apellido, fecha_nacimiento, email, telefono) VALUES " +
		"(?, ?, ?, ?, ?)";
		
		boolean realizado = false;
		
		PreparedStatement sentencia;
		
		try {
			sentencia = conexion.prepareStatement(consulta);
			sentencia.setString(1, estudiante.getNombre());
			sentencia.setString(2, estudiante.getApellido());
			sentencia.setString(3, estudiante.getFechaNacimiento());
			sentencia.setString(4, estudiante.getEmail());
			sentencia.setString(5, estudiante.getTelefono());
			
			sentencia.executeUpdate();
			
			realizado = true;
			
		} catch (SQLException e) {
			System.out.println("Error: No se ha podido insertar al estudiante " + e.getMessage());
		}
		
		return realizado;
	}
	
	/**
	 * Funcion que se encarga de devolver una lista con todos los
	 * estudiantes de la base de datos
	 * 
	 * @return Devuelve una lista con todos los estudiantes de la base de datos
	 */
	public ArrayList<Estudiante> listarEstudiantes() {
		
		String consulta = "SELECT * FROM estudiantes";
		
		int idEstudiante = 0;
		
		String nombre = "";
		
		String apellido = "";
		
		String fechaNacimiento = "";
		
		String email = "";
		
		String telefono = "";
		
		Estudiante estudiante = null;
		
		ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
		
		Statement sentencia;
		
		try {
			sentencia = conexion.createStatement();
			
			ResultSet rs = sentencia.executeQuery(consulta);
			
			while(rs.next()) {
				idEstudiante = rs.getInt(1);
				nombre = rs.getString(2);
				apellido = rs.getString(3);
				fechaNacimiento = rs.getString(4);
				email = rs.getString(5);
				telefono = rs.getString(6);
				
				estudiante = new Estudiante(idEstudiante, nombre, apellido, fechaNacimiento, email, telefono);
				
				estudiantes.add(estudiante);
			}
			
		} catch (SQLException e) {
			System.out.println("Error: No se ha podido mostrar a los estudiantes de la base de datos " + e.getMessage());
		}
		
		return estudiantes;
	}
	
	/**
	 * Funcion que busca a un estudiante en la base de datos segun el id y
	 * devuelve al estudiante 
	 *  
	 * @param id Id del estudiante que se quiere buscar
	 * @return Devuelve al estudiante
	 */
	public Estudiante buscarEstudiante(int id) {
		
		String consulta = "SELECT * FROM estudiantes WHERE id_estudiante = " + id;
		
		int idEstudiante = 0;
		
		String nombre = "";
		
		String apellido = "";
		
		String fechaNacimiento = "";
		
		String email = "";
		
		String telefono = "";
		
		Estudiante estudiante = null;
		
		Statement sentencia;
		
		try {
			sentencia = conexion.createStatement();
			
			ResultSet rs = sentencia.executeQuery(consulta);
			
			if(rs.next()) {
				idEstudiante = rs.getInt(1);
				nombre = rs.getString(2);
				apellido = rs.getString(3);
				fechaNacimiento = rs.getString(4);
				email = rs.getString(5);
				telefono = rs.getString(6);
			}
			
		} catch (SQLException e) {
			System.out.println("Error: No se ha encontrado al estudiante " + e.getMessage());
		}
		
		estudiante = new Estudiante(idEstudiante, nombre, apellido, fechaNacimiento, email, telefono);
		
		return estudiante;
	}
	
	/**
	 * Funcion encargada de modificar el email de un estudiante en la base de datos y
	 * devuelve true si lo ha podido modificar o false si no ha podido
	 * 
	 * @param id Id del estudiante que se quiere cambiar el email
	 * @param email Nuevo email del estudiante
	 * @return Devuelve true si se ha podido cambiar el email o false si no ha podido
	 */
	public boolean modificarEstudianteEmail(int id, String email) {
		
		String consulta = "UPDATE estudiantes SET email = ? WHERE id_estudiante = ?";
		
		boolean realizado = false;
		
		PreparedStatement sentencia;
		
		try {
			sentencia = conexion.prepareStatement(consulta);
			sentencia.setString(1, email);
			sentencia.setInt(2, id);
			
			sentencia.executeUpdate();
			
			realizado = true;
			
		} catch (SQLException e) {
			System.out.println("Error: No se ha podido modificar el email del estudiante " + e.getMessage());
		}
			
		return realizado;
	}
	
	/**
	 * Funcion encargada de buscar al estudiante por su id y modificar el telefono 
	 * del estudiante y devuelve true si se ha modificado o false si no se ha modificado
	 * 
	 * @param id Id del estudiante que se quiere modificar el telefono
	 * @param telefono Nuevo telefono del estudiante
	 * @return Devuelve true si se ha modificado el telefono o false si no se ha modificado
	 */
	public boolean modificarEstudianteTelefono(int id, String telefono) {
		
		String consulta = "UPDATE estudiantes SET telefono = ? WHERE id_estudiante = ?";
		
		boolean realizado = false;
		
		PreparedStatement sentencia;
		
		try {
			sentencia = conexion.prepareStatement(consulta);
			sentencia.setString(1, telefono);
			sentencia.setInt(2, id);
			
			sentencia.executeUpdate();
			
			realizado = true;
			
		} catch (SQLException e) {
			System.out.println("Error: No se ha podido modificar el telefono del estudiante " + e.getMessage());
		}
		
		return realizado;
	}
	
	/**
	 * Funcion encargada de buscar y eliminar al estudiante de la base de datos y devuelve true
	 * si se ha eliminado de la base de datos o false si no se ha podido eliminar
	 * 
	 * @param id Id del estudiante que se quiere eliminar de la base de datos
	 * @return Devuelve true si se ha eliminado el estudiante o false si no se ha eliminado
	 */
	public boolean eliminarEstudiante(int id) {

	    String consultaCalificacion = "DELETE FROM calificaciones WHERE id_estudiante = ?"; 
	    String consultaMatricula = "DELETE FROM matriculas WHERE id_estudiante = ?"; 
	    String consultaEstudiante = "DELETE FROM estudiantes WHERE id_estudiante = ?"; 

	    boolean realizado = false;

	    PreparedStatement sentencia;

	    try {
            sentencia = conexion.prepareStatement(consultaMatricula);
            sentencia.setInt(1, id);
            sentencia.executeUpdate();

            sentencia = conexion.prepareStatement(consultaCalificacion);
            sentencia.setInt(1, id);
            sentencia.executeUpdate();

            sentencia = conexion.prepareStatement(consultaEstudiante);
            sentencia.setInt(1, id);
            sentencia.executeUpdate();

            realizado = true;

        } catch (SQLException e) {
            System.out.println("Error: No existe el estudiante " + e.getMessage());
	    }
	    
	    return realizado;
	}
}