package examenConFichero;

public class Persona extends Info {  // la clase que utilizaré para almacenar cada persona
	final String VALIDOS="([a-zA-ZñÑáéíóÁÉÍÓÚ]+[ ]*)+";  // valores admitidos, al menos 1 caracter
	
	boolean  validaNombre() {  // devuelve si el nombre es correcto
		return(nombre.matches(VALIDOS));
	}
}
