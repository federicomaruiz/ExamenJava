package examenConFichero;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class Controlador implements Gestionar {
	final String PATH="info.csv";
	final int ANO_ACTUAL=2023;
	final int PALABRA=0;  // 0 para 1ª palabra	
	Vista pantalla;
	Modelo modelo;

	
	public  void setPantalla(Vista pantalla) {
		this.pantalla = pantalla;
	}
	public  void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	
	
	public float getEdadmedia(Object[] miLista) // devuelve la edad media, 0 si hay error
	{
		float suma=0;
		
		if(miLista.length==0) {
			System.out.println("Lista Vacia");
			return(0F);
		}	
		else if(miLista[0] instanceof Persona) {
			for(int i=0;i<miLista.length;i++) {
				suma+=((Persona)(miLista[i])).anoNacimiento;
			}	
			return(ANO_ACTUAL-suma/miLista.length);
		}
		else {
			System.out.println("Tipo erroneo");
			return(0F);
		}				
	}
	
	public char getMasUsada(Object[] miLista) {  // calcula y devuelve la letra más frecuente en la 1ª palabra
		char letra=0;
		int count=0;
		HashMap<Character,Integer> histograma=new HashMap<>();  // almacenará la frecuencia de cada letra
		
		if(miLista.length==0) {
			System.out.println("Lista Vacia");
			return(0);
		}	
		else if(miLista[0] instanceof Persona) {			
			for(int i=0;i<miLista.length;i++) {  //recorremos las personas
				String[] palabras=((Persona)(miLista[i])).nombre.trim().split(" +");  // pasamos a array de palabras

				if(palabras.length>PALABRA)
					for(int j=0;j<palabras[PALABRA].length();j++) {	// recorremos cada letra de la 1ª palabra (la n-sima)
						char c=palabras[PALABRA].charAt(j);           
						switch(c) {									// convertimos a mayúsculas (y quitamos acentos si se quisiera)
						case 'á': 
							c='Á';  // pondríamos 'A' si no quisieramos distinguir acentuadas de no acentuadas (el enunciado no lo pide)
							break;  //   en ese caso tendríamos que redirigir también 'Á' a 'A'
						case 'é': 
							c='É';
							break;
						case 'í': 
							c='Í';
							break;
						case 'ó': 
							c='Ó';
							break;
						case 'ú': 
							c='Ú';
							break;
						case 'ñ': 
							c='Ñ';
							break;
						default:
							c=Character.toUpperCase(c);
						}
						histograma.put(c, histograma.getOrDefault(c, 0)+1);  // guardamos en el map que mantiene las frecuencias, incrementando el contador
					}	
			}
			for(Map.Entry<Character,Integer> par:histograma.entrySet()) {    // buscamos la ocurrencia más alta (nos quedamos la 1ª si hay varias iguales)
				if(par.getValue()>count) {
					count=par.getValue();
					letra=par.getKey();
				}
			}
			return(letra);
		}
		else {
			System.out.println("Tipo erroneo");
			return(0);
		}			
	}
	
	
	String add(String nombre, String ano) {  // comprueba entrada y añade al modelo si es válida
		Persona persAux=new Persona();
		persAux.nombre=nombre;
		if(!persAux.validaNombre())
			return("Nombre invalido");
		else if(!ano.matches("[0-9]+"))
			return("El año debe ser un entero positivo");		
		persAux.anoNacimiento=Integer.parseInt(ano);
		if(persAux.anoNacimiento>ANO_ACTUAL)
			return("El año no debe ser a futuro");
		modelo.add(persAux);
		Object[] fila={ persAux.nombre, persAux.anoNacimiento};  // y actualizamos la vista
		pantalla.tModel.addRow(fila);
		pantalla.setLblMediaTxt(String.format("%.1f",getEdadmedia(modelo.getDatos().toArray())));  // calculando resultados
		pantalla.setLblMasUsadaTxt(String.valueOf(getMasUsada(modelo.getDatos().toArray())));
		pantalla.vaciaCampos();			// si era correcta borramos los datos introducidos
		pantalla.habilitaFich();		// si hemos añadido permitimos generar fichero
		return(null);					// no hay error que reportar
	}
	
	void generaFichero() {  // es una acción independiente que no afecta al flujo de aplicación si falla
		File f=new File(PATH);
		System.out.println(f.getAbsolutePath());
		FileWriter fw;
		
		try {
			fw=new FileWriter(f);
		}
		catch (IOException ioe) {  // en caso de error al crear el FileWriter muestra mensaje y vuelve
			JOptionPane.showMessageDialog(null, "No se pudo crear el fichero");
			return;
		}
		try {
			for(Persona p:modelo.getDatos()) {	// recorremos modelo y sacamos a fichero
				fw.write(String.format("%s , %d \n",p.nombre,p.anoNacimiento));
			}    
			fw.write(String.format("Letra más usada: %s Edad media: %s\n",  // sacamos resultados, sin recalcular
					pantalla.getLblMasUsadaTxt(),pantalla.getLblMediaTxt()));
			fw.flush();  
			fw.close();
			JOptionPane.showMessageDialog(null, "Fichero generado");
		}
		catch (IOException ioe) {  // en caso de error al escribir muestra mensaje y vuelve
			JOptionPane.showMessageDialog(null, "Error escribiendo al fichero");
		}				
	}
	
}
