package examenConFichero;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class ControladorSinGUI implements Gestionar {  // Este fichero es practicamente igual que Controlador, cambiando interacción con Vista/Usuario
	final String PATH="info.csv";					   //  se podría haber hecho adaptación para que fuera independiente del tipo de vista  
	final int ANO_ACTUAL=2023;
	Modelo modelo;
	
	
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	
	public float getEdadmedia(Object[] miLista)
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
	public char getMasUsada(Object[] miLista) {
		char letra=0;
		int count=0;
		HashMap<Character,Integer> histograma=new HashMap<>();
		
		if(miLista.length==0) {
			System.out.println("Lista Vacia");
			return(0);
		}	
		else if(miLista[0] instanceof Persona) {			
			for(int i=0;i<miLista.length;i++) {
				String[] palabras=((Persona)(miLista[i])).nombre.trim().split(" +");

				if(palabras.length>0)
					for(int j=0;j<palabras[0].length();j++) {
						char c=palabras[0].charAt(j);
						switch(c) {
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
						histograma.put(c, histograma.getOrDefault(c, 0)+1);
					}	
			}
			for(Map.Entry<Character,Integer> par:histograma.entrySet()) {
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
	void add(Persona persAux) {
		modelo.add(persAux);
	}
	
	void listaEntradas() {
		for(Persona p:modelo.getDatos()) 
			System.out.printf(String.format("%s , %d \n",p.nombre,p.anoNacimiento));		
	}
	
	void generaFichero() {  // es una acción independiente que no afecta al flujo de aplicación si falla
		File f=new File(PATH);
		System.out.println(f.getAbsolutePath());
		FileWriter fw;
		
		try {
			fw=new FileWriter(f);
		}
		catch (IOException ioe) {  // en caso de error al crear el FileWriter muestra mensaje y vuelve
			System.out.println("No se pudo crear el fichero");
			return;
		}
		try {
			for(Persona p:modelo.getDatos()) {
				fw.write(String.format("%s , %d \n",p.nombre,p.anoNacimiento));  //escribimos cada línea del .csv
			}    
			fw.write(String.format("Letra más usada: %s Edad media: %s\n",
					String.valueOf(getMasUsada(modelo.getDatos().toArray())),String.format("%.1f",getEdadmedia(modelo.getDatos().toArray()))));
			fw.flush();  
			fw.close();
			System.out.println("Fichero generado");  // feedback al usuario, todo ok
		}
		catch (IOException ioe) {  // en caso de error al escribir muestra mensaje y vuelve
			System.out.println("Error escribiendo al fichero");
		}			
	}
}
