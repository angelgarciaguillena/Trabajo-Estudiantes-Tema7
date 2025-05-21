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
		
		/*Creamos un estudiante*/
		Estudiante estudiante = null;
		
		/*Creamos a un estudianteDAO*/
		EstudianteDAO estudianteDAO = new EstudianteDAO();
		
		/*Creamos una lista para almacenar a los estudiantes*/
		ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
		
		/*Si la conexion no es valida no iniciamos el programa*/
		if(estudianteDAO.getConexion() != null) {
			
			/*Mostramos el menu llamando a la funcion*/
			menu();
			
			/*Leemos la opcion del usuario*/
			opcion = sc.nextInt();
			sc.nextLine();
			
			/*Creamos un bucle do while para ejecutar el programa hasta que el usuario desee salir*/
			do {
				/*Creamos un switch para que depende de la opcion del usuario se realice una funcion u otra*/
				switch(opcion) {
				
				/*Si la opcion es 1 añadimos un nuevo estudiante*/
				case 1 -> {
					
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
					System.out.println("\n" + "Estudiane: " + estudiante);
				}
				
				/*Si la opcion es 4 modificamos un campo del estudiante*/
				case 4 -> {
					
				}
				
				/*Si la opcion es 5 eliminamos a un estudiante*/
				case 5 -> {
					
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
	
	private static void menu() {
		/*Mostramos el menu al usuario*/
		System.out.println("1. Crear estudiante");
		System.out.println("2. Listar todos los estudiantes");
		System.out.println("3. Buscar estudiante por id");
		System.out.println("4. Modificar estudiante");
		System.out.println("5. Eliminar estudiante");
		System.out.println("0. Salir");
		System.out.println("Introduce una opcion");
	}
	
	private static void mostrarEstudiantes(ArrayList<Estudiante> estudiantes){
		
		System.out.println("\n" + "Estudiantes:");
		
		/*Creamos un for each para recorrer la lista y mostrar todos los estudiantes*/
		for(Estudiante estudiante : estudiantes) {
			System.out.println(estudiante);
		}
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
}
