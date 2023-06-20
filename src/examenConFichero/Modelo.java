package examenConFichero;

import java.util.ArrayList;

public class Modelo {  // clase que almacena los datos
	ArrayList<Persona> datos=new ArrayList<>();
	
	void add(Persona persona) {
		datos.add(persona);
	}
	
	ArrayList<Persona> getDatos() {
		return(datos);
	}

}
