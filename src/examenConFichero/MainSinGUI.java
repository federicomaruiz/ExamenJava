package examenConFichero;

import java.util.Scanner;

public class MainSinGUI {
	static final int OPT_PERS=1;	// definimos las opciones
	static final int OPT_LIST=2;	
	static final int OPT_FICH=3;
	static final int OPT_SALIR=0;
	
	static final String CON_DATOS="["+String.valueOf(OPT_PERS)+String.valueOf(OPT_LIST)+String.valueOf(OPT_FICH)+String.valueOf(OPT_SALIR)+"]";
	static final String SIN_DATOS="["+String.valueOf(OPT_PERS)+String.valueOf(OPT_SALIR)+"]";

	static Scanner scn=new Scanner(System.in);
	static boolean imprime=false;	// no dejaremos listar ni generar fichero hasta que no haya datos

	static Modelo modelo;
	static ControladorSinGUI controlador;  // es casi igual que el controlador, pero sin usar la vista gráfica
	
	public static int ponMenu() {
		String entrada;
		int opcion=-1;				
		
		System.out.println("\nMenú - elija una opción");
		System.out.println(String.valueOf(OPT_PERS)+".- Añadir persona");
		if(imprime) {
			System.out.println(String.valueOf(OPT_LIST)+".- Listar Personas");
			System.out.println(String.valueOf(OPT_FICH)+".- Generar fichero CSV");
		}	
		System.out.println(String.valueOf(OPT_SALIR)+".- Salir");
		System.out.print("\nOpción : ");
		while(opcion<0) {
			entrada=scn.nextLine();
			if((imprime&&entrada.matches(CON_DATOS))||entrada.matches(SIN_DATOS))  // validamos opción válida
			  opcion=Integer.parseInt(entrada);
			else
			  System.out.println("Introduzca una opción correcta");
		}
		return(opcion);		
	}
	
	public static void pidePersona() { //recoge información de una persona y la añade al modelo
		String entrada="";
		boolean repite=true;
		Persona persAux=new Persona();

		while(repite) {
			System.out.print("Nombre : ");
			persAux.nombre=scn.nextLine().trim();
			if(!persAux.validaNombre())
				System.out.println("Nombre invalido");
			else 
				repite=false;
		}
		repite=true;
		while(repite) {
			System.out.print("Año de Nacimiento : ");
			entrada=scn.nextLine();
			if(entrada.matches("[0-9]+")) {
				persAux.anoNacimiento=Integer.parseInt(entrada);
				if(persAux.anoNacimiento>controlador.ANO_ACTUAL)
					System.out.println("El año no debe ser a futuro");
				else {
				  controlador.add(persAux);
				  repite=false;
				}
			}
			else
				System.out.println("El año debe ser un entero positivo");		
		}
		System.out.printf("Edad media: %.1f\n",controlador.getEdadmedia(modelo.getDatos().toArray()));  //mostramos los resultados
		System.out.printf("Letra más usada en la 1ª palabra: %c\n\n",controlador.getMasUsada(modelo.getDatos().toArray()));
		imprime=true;	// hay entradas, permitimos mostrar lista y generar fichero	
	}
	

	public static void main(String[] args) {
		
		int opcion=-1;
		
		modelo=new Modelo();		         // aunque no es interfaz gráfico, podemos reaprovechar aspectos del patrón MVC
		controlador=new ControladorSinGUI();
		
		controlador.setModelo(modelo);
		
		while(opcion!=OPT_SALIR) {					 // bucle de menú -esto seria el equivalente a la vista-
			switch(opcion=ponMenu()) {
				case OPT_PERS: pidePersona();		 // añadimos una persona
						break;
				case OPT_LIST: 						 // mostramos entradas
					controlador.listaEntradas();
					break;
				case OPT_FICH: 
						controlador.generaFichero(); // generamos el fichero	
						break;
				default: 
						System.out.println("...saliendo");
						scn.close();					
						break;
			}
		}		
	}

}
