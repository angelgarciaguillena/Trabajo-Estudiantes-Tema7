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
	 * @param estudiante Estudiante 
	 * @return
	 */
	public boolean crearEstudiante(Estudiante estudiante) {
		
		String consulta = "INSERT INTO estudiantes (nombre, apellido, fecha_nacimiento, email, telefono) VALUES " +
		"('" + estudiante.getNombre() + "', '" + estudiante.getApellido() + "', '" + estudiante.getFechaNacimiento() + "', '" + estudiante.getEmail() + "', '" + estudiante.getTelefono() + "')";
		
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
	
	public boolean modificarEstudianteEmail(int id, String email) {
		
		String consulta = "UPDATE estudiantes SET telefono = '" + email + "' WHERE id_estudiante = " + id;
		
		boolean realizado = false;
		
		PreparedStatement sentencia;
		
		try {
			sentencia = conexion.prepareStatement(consulta);
			sentencia.setString(5, email);
			sentencia.executeUpdate();
			realizado = true;
		} catch (SQLException e) {
			System.out.println("Error: No se ha podido modificar el email del estudiante " + e.getMessage());
		}
		
		return realizado;
	}
	
	public boolean modificarEstudianteTelefono(int id, String telefono) {
		
		String consulta = "UPDATE estudiantes SET telefono = '" + telefono + "' WHERE id_estudiante = " + id;
		
		boolean realizado = false;
		
		PreparedStatement sentencia;
		
		try {
			sentencia = conexion.prepareStatement(consulta);
			sentencia.setString(6, telefono);
			sentencia.executeUpdate();
			realizado = true;
		} catch (SQLException e) {
			System.out.println("Error: No se ha podido modificar el telefono del estudiante " + e.getMessage());
		}
		
		return realizado;
	}
	
	public boolean eliminarEstudiante(int id) {
		
		String consulta = "DELETE FROM estudiantes WHERE id_estudiante = " + id; 
		
		boolean realizado = false;
		
		Statement sentencia;
		
		try {
			sentencia = conexion.createStatement();
			sentencia.executeUpdate(consulta);
			realizado = true;
		} catch (SQLException e) {
			System.out.println("Error: No existe el estudiante " + e.getMessage());
		}
		
		return realizado;
	}
}