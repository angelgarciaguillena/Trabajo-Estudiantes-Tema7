package main;

import java.util.ArrayList;
import java.util.Scanner;

import entidades.Estudiante;
import dao.EstudianteDAO;

public class EstudiantePrincipal {
	
	/*Creamos un Scanner*/
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		/*Creamos una variable para almacenar la opcion del usuario*/
		int opcion = 0;
		
		/*Creamos una variable para almacenar el id del estudiante*/
		int id;
		
		/*Creamos una variable para almacenar el email del estudiante*/
		String email;
		
		/*Creamos una variable para almacenar el telefono del estudiante*/
		String telefono;
		
		/*Creamos un estudiante*/
		Estudiante estudiante = null;
		
		/*Creamos a un estudianteDAO*/
		EstudianteDAO estudianteDAO = new EstudianteDAO();
		
		/*Creamos una lista para almacenar a los estudiantes*/
		ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
		
		/*Si la conexion no es valida no iniciamos el programa*/
		if(estudianteDAO.getConexion() != null) {
						
			/*Creamos un bucle do while para ejecutar el programa hasta que el usuario desee salir*/
			do {
				
				/*Mostramos el menu llamando a la funcion*/
				menuPrincipal();
				
				/*Leemos la opcion del usuario*/
				opcion = sc.nextInt();
				sc.nextLine();
				
				/*Creamos un switch para que depende de la opcion del usuario se realice una funcion u otra*/
				switch(opcion) {
				
				/*Si la opcion es 1 añadimos un nuevo estudiante*/
				case 1 -> {
					
					/*Creamos al estudiante llamando a la funcion*/
					estudiante = crearEstudiante();
					
					/*Si el estudiante no es nulo lo creamos*/
					if(estudiante != null) {
						
						/*Creamos al estudiante en la base de datos llamando a la funcion*/
						if(estudianteDAO.crearEstudiante(estudiante)) {
							System.out.println("Se ha creado al estudiante en la base de datos");
						}
					}
				}
				
				/*Si la opcion es 2 listamos a todos los estudiantes*/
				case 2 -> {
					
					/*Almacenamos a los estudiantes existentes en la base de datos llamando a la funcion*/
					estudiantes = estudianteDAO.listarEstudiantes();
					
					/*Mostramos a los estudiantes llamando a la funcion*/
					mostrarEstudiantes(estudiantes);
				}
				
				/*Si la opcion es 3 buscamos a un estudiante por su id*/
				case 3 -> {
					
					/*Pedimos la id del estudiante al usuario llamando a la funcion*/
					id = pedirId();
					
					/*Buscamos al estudiante en la base de datos llamando a la funcion*/
					estudiante = estudianteDAO.buscarEstudiante(id);
					
					/*Mostramos la informacion del estudiante*/
					if(estudiante.getId() != 0) {
						System.out.println("\n" + "Estudiane: " + estudiante);
					} else {
						System.out.println("No se ha encontrado al estudiante en la base de datos");
					}
				}
				
				/*Si la opcion es 4 modificamos un campo del estudiante*/
				case 4 -> {
					
					/*Pedimos al usuario que introduzca el id del estudiante llamando a la funcion*/
					id = pedirId();
					
					/*Si el estudiante se encuentra en la base de datos se realiza la modificacion*/
					if(estudianteDAO.buscarEstudiante(id).getId() != 0) {
						
						/*Mostramos el menu de modificacion al usuario*/
						menuModificacion();
						
						/*Leemos la opcion del usuario*/
						opcion = sc.nextInt();
						sc.nextLine();
						
						/*Creamos un switch para que depende de la opcion del usuario se realice una funcion u otra*/
						switch(opcion) {
						
						/*Si la opcion es 1 modificamos el email del estudiante*/
						case 1 -> {
							
							/*Pedimos el email al estudiante llamando a la funcion*/
							email = pedirEmail();
							
							/*Modificamos el email del estudiante llamando a la funcion*/
							if(estudianteDAO.modificarEstudianteEmail(id, email)) {
								System.out.println("Se ha modificado el email del estudiante en la base de datos");
							} 
						}
						
						/*Si la opcion es 2 modificamos el telefono del estudiante*/
						case 2 -> {
							
							/*Pedimos el telefono al estudiante llamando a la funcion*/
							telefono = pedirTelefono();
							
							/*Modificamos el telefono del estudiante llamando a la funcion*/
							if(estudianteDAO.modificarEstudianteTelefono(id, telefono)) {
								System.out.println("Se ha modificado el telefono del estudiante en la base de datos");
							} 
						}
						
						/*Si la opcion no es ninguna de las anteriores informamos al usuario de que su opcion no es valida*/
						default -> {
							System.out.println("Opcion no valida");
						}
						}
					} else {
						System.out.println("No se ha encontrado al estudiante en la base de datos");
					}
				}
				
				/*Si la opcion es 5 eliminamos a un estudiante*/
				case 5 -> {
					
					/*Pedimos la id del estudiante al usuario llamando a la funcion*/
					id = pedirId();
					
					/*Si el estudiante no se encuentra en la base de datos informamos al usuario pero
					 * si esta en la base de datos lo eliminamos*/
					if (estudianteDAO.buscarEstudiante(id).getId() != 0) {
						
						/*Eliminamos al estudiante llamando a la funcion*/
						if(estudianteDAO.eliminarEstudiante(id)) {
							System.out.println("Se ha eliminado al estudiante de la base de datos");
						} 
						
					} else {
						System.out.println("No se ha encontrado al estudiante en la base de datos");
					}
				}
				
				/*Si la opcion es 0 mostramos al usuario de que ha salido del programa*/
				case  0 -> {
					System.out.println("Has salido del programa");
				}
				
				/*Si no es ninguna de las opciones anteriores informamos al usuario de que la opcion no es valida*/
				default -> {
					System.out.println("Opcion no valida");
				}
				}
			} while(opcion != 0);
		}
		
		/*Cerramos el Scanner*/
		sc.close();
	}
	
	private static void menuPrincipal() {
		/*Mostramos el menu principal al usuario*/
		System.out.println("\n" + "    Gestion de Estudiantes");
		System.out.println("------------------------------");
		System.out.println("1. Crear estudiante");
		System.out.println("2. Listar todos los estudiantes");
		System.out.println("3. Buscar estudiante por id");
		System.out.println("4. Modificar estudiante");
		System.out.println("5. Eliminar estudiante");
		System.out.println("0. Salir");
		System.out.println("Introduce una opcion");
	}
	
	public static void menuModificacion() {
		/*Mostramos el menu de modificacion al usuario*/
		System.out.println("\n" + "    Menu de Modificacion");
		System.out.println("----------------------------");
		System.out.println("1. Modificar email");
		System.out.println("2. Modificar telefono");
		System.out.println("Introduce una opcion");
	}
	
	private static void mostrarEstudiantes(ArrayList<Estudiante> estudiantes){
		
		System.out.println("\n" + "Estudiantes:");
		
		/*Creamos un for each para recorrer la lista y mostrar todos los estudiantes*/
		for(Estudiante estudiante : estudiantes) {
			System.out.println(estudiante);
			System.out.println("-----------------------------------------------------------------------------");
		}
	}
	
	private static Estudiante crearEstudiante() {
		
		/*Creamos una variable para almacenar el nombre del estudiante*/
		String nombre;
		
		/*Creamos una variable para almacenar el apellido del estudiante*/
		String apellido;
		
		/*Creamos una variable para almacenar la fecha de nacimiento del estudiante*/
		String fechaNacimiento;
		
		/*Creamos una variable para almacenar el email del estudiante*/
		String email;
		
		/*Creamos una variable para almacenar el telefono del estudiante*/
		String telefono;
		
		/*Creamos un estudiante*/
		Estudiante estudiante = null;
		
		/*Pedimos al usuario que introduzca el nombre*/
		System.out.println("Introduce el nombre del estudiante");
		
		/*Leemos el nombre del estudiante*/
		nombre = sc.nextLine();
		
		/*Pedimos al usuario que introduzca el apellido*/
		System.out.println("Introduce el apellido del estudiante");
		
		/*Leemos el apellido del estudiante*/
		apellido = sc.nextLine();
		
		/*Pedimos al usuario que introduzca la fecha de nacimiento*/
		System.out.println("Introduce la fecha de nacimiento del estudiante");
		
		/*Leemos la fecha de nacimiento del estudiante*/
		fechaNacimiento = sc.nextLine();
		
		/*Pedimos al usuario que introduzca el email llamando a la funcion*/
		email = pedirEmail();
		
		/*Pedimos al usuario que introduzca el telefono llamando a la funcion*/
		telefono = pedirTelefono();
		
		/*Creamos al estudiante con los datos*/
		estudiante = new Estudiante(nombre, apellido, fechaNacimiento, email, telefono);
		
		/*Devolvemos al estudiante*/
		return estudiante;
	}
	
	private static int pedirId() {
		
		/*Creamos una variable para almacenar el id del estudiante*/
		int id;
		
		/*Pedimos al usuario que introduzca el id del estudiante*/
		System.out.println("Introduce el id del estudiante");
		
		/*Leemos el id del estudiante*/
		id = sc.nextInt();
		sc.nextLine();
		
		/*Devolvemos el id del estudiante*/
		return id;
	}
	
	private static String pedirEmail() {
		
		/*Creamos una variable para almacenar el email del estudiante*/
		String email;
		
		/*Pedimos al usuario que introduzca el email del estudiante*/
		System.out.println("Introduce el email del estudiante");
		
		/*Leemos el email del estudiante*/
		email = sc.nextLine();
		
		/*Devolvemos el email del estudiante*/
		return email;
	}
	
	private static String pedirTelefono() {
		
		/*Creamos una variable para almacenar el telefono del estudiante*/
		String telefono;
		
		/*Pedimos al usuario que introduzca el telefono del estudiante*/
		System.out.println("Introduce el telefono del estudiante");
		
		/*Leemos el telefono del estudiante*/
		telefono = sc.nextLine();
		
		/*Devolvemos el telefono del estudiante*/
		return telefono;
	}	
}