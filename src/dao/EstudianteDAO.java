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
		
		/*Creamos un try catch para que si no se ha podido establecer
		 * la conexion con la base de datos avisamos al usuario*/
		try {
			
			/*Establecemos la conexion con la base de datos*/
			conexion = DriverManager.getConnection(Constantes.CONEXION, Constantes.USUARIO, Constantes.CONTRASEÑA);
		
		/*Si se produce un error con la conexion en la base de datos avisamos al usuario*/
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
		
		/*Creamos una variable para almacenar la consulta que insertara al alumno en la base de datos*/
		String consulta = "INSERT INTO estudiantes (nombre, apellido, fecha_nacimiento, email, telefono) VALUES " +
		"(?, ?, ?, ?, ?)";
		
		/*Creamos una variable para almacenar si se ha insertado el estudiante o no*/
		boolean realizado = false;
		
		/*Creamos la sentencia*/
		PreparedStatement sentencia;
		
		/*Creamos un try catch para notificar al usuario en caso de que se produzca un error*/
		try {
			
			/*Preparamos la sentencia SQL*/
			sentencia = conexion.prepareStatement(consulta);
			
			/*Almacenamos el nombre del estudiante en la consulta*/
			sentencia.setString(1, estudiante.getNombre());
			
			/*Almacenamos el apellido del estudiante en la consulta*/
			sentencia.setString(2, estudiante.getApellido());
			
			/*Almacenamos la fecha de nacimiento del estudiante en la consulta*/
			sentencia.setString(3, estudiante.getFechaNacimiento());
			
			/*Almacenamos el email del estudiante en la consulta*/
			sentencia.setString(4, estudiante.getEmail());
			
			/*Almacenamos el telefono del estudiante en la consulta*/
			sentencia.setString(5, estudiante.getTelefono());
			
			/*Ejecutamos la sentencia*/
			sentencia.executeUpdate();
			
			/*Establecemos que se ha insertado el estudiante*/
			realizado = true;
			
		/*Si se produce un error al insertar al estudiante avisamos al usuario*/
		} catch (SQLException e) {
			System.out.println("Error: No se ha podido insertar al estudiante " + e.getMessage());
		}
		
		/*Devolvemos si se ha realizado la sentencia*/
		return realizado;
	}
	
	/**
	 * Funcion que se encarga de devolver una lista con todos los
	 * estudiantes de la base de datos
	 * 
	 * @return Devuelve una lista con todos los estudiantes de la base de datos
	 */
	public ArrayList<Estudiante> listarEstudiantes() {
		
		/*Creamos una variable para almacenar la consulta que muestra a los alumnos en la base de datos*/
		String consulta = "SELECT * FROM estudiantes";
		
		/*Creamos una variable para almacenar el id del estudiante*/
		int idEstudiante = 0;
		
		/*Creamos una variable para almacenar el nombre del estudiante*/
		String nombre = "";
		
		/*Creamos una variable para almacenar el apellido del estudiante*/
		String apellido = "";
		
		/*Creamos una variable para almacenar la fecha de nacimiento del estudiante*/
		String fechaNacimiento = "";
		
		/*Creamos una variable para almacenar el email del estudiante*/
		String email = "";
		
		/*Creamos una variable para almacenar el telefono del estudiante*/
		String telefono = "";
		
		/*Creamos un estudiante*/
		Estudiante estudiante = null;
		
		/*Creamos una lista para almacenar a los estudiantes*/
		ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
		
		/*Creamos la sentencia*/
		Statement sentencia;
		
		/*Creamos un try catch para notificar al usuario en caso de que se produzca un error*/
		try {
			
			/*Preparamos la sentencia SQL*/
			sentencia = conexion.createStatement();
			
			/*Ejecutamos la consulta*/
			ResultSet rs = sentencia.executeQuery(consulta);
			
			/*Creamos un bucle while para almacenar todos los datos y añadir al estudiante a la lista mientras la consulta haya funcionado*/
			while(rs.next()) {
				
				/*Almacenamos lel id del estudiante*/
				idEstudiante = rs.getInt(1);
				
				/*Almacenamos el nombre del estudiante*/
				nombre = rs.getString(2);
				
				/*Almacenamos el apellido del estudiante*/
				apellido = rs.getString(3);
				
				/*Almacenamos la fecha de nacimiento del estudiante*/
				fechaNacimiento = rs.getString(4);
				
				/*Almacenamos el email del estudiante*/
				email = rs.getString(5);
				
				/*Almacenamos el telefono del estudiante*/
				telefono = rs.getString(6);
				
				/*Creamos un nuevo estudiante*/
				estudiante = new Estudiante(idEstudiante, nombre, apellido, fechaNacimiento, email, telefono);
				
				/*Añadimos al estudiante a la lista*/
				estudiantes.add(estudiante);
			}
		
		/*Si se produce un error en la consulta avisamos al usuario*/
		} catch (SQLException e) {
			System.out.println("Error: No se ha podido mostrar a los estudiantes de la base de datos " + e.getMessage());
		}
		
		/*Devolvemos la lista con los estudiantes*/
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
		
		/*Creamos una variable para almacenar la consulta que muestra al estudiante si su id es la misma*/
		String consulta = "SELECT * FROM estudiantes WHERE id_estudiante = " + id;
		
		/*Creamos una variable para almacenar el id del estudiante*/
		int idEstudiante = 0;
		
		/*Creamos una variable para almacenar el nombre del estudiante*/
		String nombre = "";
		
		/*Creamos una variable para almacenar el apellido del estudiante*/
		String apellido = "";
		
		/*Creamos una variable para almacenar la fecha de nacimiento del estudiante*/
		String fechaNacimiento = "";
		
		/*Creamos una variable para almacenar el email del estudiante*/
		String email = "";
		
		/*Creamos una variable para almacenar el telefono del estudiante*/
		String telefono = "";
		
		/*Creamos un estudiante*/
		Estudiante estudiante = null;
		
		/*Creamos la sentencia*/
		Statement sentencia;
		
		/*Creamos un try catch para notificar al usuario en caso de que se produzca un error*/
		try {
			
			/*Preparamos la sentencia*/
			sentencia = conexion.createStatement();
			
			/*Ejecutamos la sentencia*/
			ResultSet rs = sentencia.executeQuery(consulta);
			
			/*Si la consulta tiene resultados almacenamos los datos del estudiante*/
			if(rs.next()) {
				
				/*Almacenamos el id del estudiante*/
				idEstudiante = rs.getInt(1);
				
				/*Almacenamos el nombre del estudiante*/
				nombre = rs.getString(2);
				
				/*Almacenamos el apellido del estudiante*/
				apellido = rs.getString(3);
				
				/*Almacenamos la fecha de nacimiento del estudiante*/
				fechaNacimiento = rs.getString(4);
				
				/*Almacenamos el email del estudiante*/
				email = rs.getString(5);
				
				/*Almacenamos el telefono del estudiante*/
				telefono = rs.getString(6);
			}
			
		/*Si se produce un error en la consulta avisamos al usuario*/
		} catch (SQLException e) {
			System.out.println("Error: No se ha encontrado al estudiante " + e.getMessage());
		}
		
		/*Creamos al estudiante*/
		estudiante = new Estudiante(idEstudiante, nombre, apellido, fechaNacimiento, email, telefono);
		
		/*Devolvemos el estudiante*/
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
		
		/*Creamos una variable para almacenar la consulta que actualiza el email del estudiante*/
		String consulta = "UPDATE estudiantes SET email = ? WHERE id_estudiante = ?";
		
		/*Creamos una variable para almacenar si se ha realizado la actualizacion o no*/
		boolean realizado = false;
		
		/*Creamos una sentencia*/
		PreparedStatement sentencia;
		
		/*Creamos un try catch para notificar al usuario en caso de que se produzca un error*/
		try {
			
			/*Preparamos la consulta*/
			sentencia = conexion.prepareStatement(consulta);
			
			/*Almacenamos el nuevo email en la consulta*/
			sentencia.setString(1, email);
			
			/*Almacenamos el id del estudiante en la consulta*/
			sentencia.setInt(2, id);
			
			/*Ejecutamos la consulta*/
			sentencia.executeUpdate();
			
			/*Establecemos que se ha realizado la modificacion*/
			realizado = true;
			
		/*Si se produce un error en la actualizacion del email del estudiante avisamos al usuario*/
		} catch (SQLException e) {
			System.out.println("Error: No se ha podido modificar el email del estudiante " + e.getMessage());
		}
		
		/*Devolvemos si se ha realizado la modificacion o no*/
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
		
		/*Creamos una variable para almacenar la consulta que actualiza el telefono del estudiante*/
		String consulta = "UPDATE estudiantes SET telefono = ? WHERE id_estudiante = ?";
		
		/*Creamos una variable para almacenar si se ha realizado la actualizacion o no*/
		boolean realizado = false;
		
		/*Creamos una sentencia*/
		PreparedStatement sentencia;
		
		/*Creamos un try catch para notificar al usuario en caso de que se produzca un error*/
		try {
			
			/*Preparamos la consulta*/
			sentencia = conexion.prepareStatement(consulta);
			
			/*Almacenamos el nuevo telefono del estudiante en la consulta*/
			sentencia.setString(1, telefono);
			
			/*Almacenamos el id del estudiante en la consulta*/
			sentencia.setInt(2, id);
			
			/*Ejecutamos la sentencia*/
			sentencia.executeUpdate();
			
			/*Establecemos que se ha realizado la modificacion*/
			realizado = true;
			
		/*Si se produce un error en la modificacion del telefono del estudiante avisamos al usuario*/
		} catch (SQLException e) {
			System.out.println("Error: No se ha podido modificar el telefono del estudiante " + e.getMessage());
		}
		
		/*Devolvemos si se ha realizado la modificacion o no*/
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
		
		/*Creamos una consulta que eliminara al estudiante de la tabla califiaciones*/
	    String consultaCalificacion = "DELETE FROM calificaciones WHERE id_estudiante = ?"; 
	    
	    /*Creamos una consulta que eliminara al estudiante de la tabla matriculas*/
	    String consultaMatricula = "DELETE FROM matriculas WHERE id_estudiante = ?"; 
	    
	    /*Creamos una consulta que eliminara al estudiante de la tabla estudiantes*/
	    String consultaEstudiante = "DELETE FROM estudiantes WHERE id_estudiante = ?"; 
	    
	    /*Creamos una variable para almacenar si se ha eliminado al estudiante o no*/
	    boolean realizado = false;
	    
	    /*Creamos la sentencia*/
	    PreparedStatement sentencia;
	    
	    /*Creamos un try catch para notificar al usuario en caso de que se produzca un error*/
	    try {
	    	/*Preparamos la consulta de la tabla matriculas*/
            sentencia = conexion.prepareStatement(consultaMatricula);
            
            /*Añadimos el id del estudiante en la consulta de la tabla matriculas*/
            sentencia.setInt(1, id);
            
            /*Ejecutamos la consulta que afecta a la tabla matriculas*/
            sentencia.executeUpdate();
            
            /*Preparamos la consulta de la tabla calificaciones*/
            sentencia = conexion.prepareStatement(consultaCalificacion);
            
            /*Añadimos el id del estudiante en la consulta de la tabla calificaciones*/
            sentencia.setInt(1, id);
            
            /*Ejecutamos la consulta que afecta a la tabla calificaciones*/
            sentencia.executeUpdate();
            
            /*Preparamos la consulta de la tabla estudiantes*/
            sentencia = conexion.prepareStatement(consultaEstudiante);
            
            /*Añadimos el id del estudiante en la consulta de la tabla estudiantes*/
            sentencia.setInt(1, id);
            
            /*Ejecutamos la consulta que afecta a la tabla estudiantes*/
            sentencia.executeUpdate();
            
            /*Establecemos que se ha eliminado al estudiante de la base de datos*/
            realizado = true;
            
        /*Si se produce un error al eliminar al estudiante avisamos al usuario*/
        } catch (SQLException e) {
            System.out.println("Error: No existe el estudiante " + e.getMessage());
	    }
	    
	    /*Devolvemos si se ha eliminado al estudiante o no*/
	    return realizado;
	}
}