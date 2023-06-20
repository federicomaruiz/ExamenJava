package examenFichero;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

public class Modelo extends Info implements Gestionar {

	private Vista vista;
	
	private final String[] cabeceraTabla = { "Nombre", "Fecha" };
	private DefaultTableModel modeloTabla = new DefaultTableModel(new Object[][] {}, cabeceraTabla);
	
	private ArrayList<String> nombresList = new ArrayList<>();
	private ArrayList<Integer> anoNacimientoList = new ArrayList<>();
	private final int posicionListaNombres = 0;
	private final int posicionListaNacimiento = 1;
	private Object[] miLista = { nombresList, anoNacimientoList };

	private final int fechaActual = 2023;
	private HashMap<Character, Integer> letras = new HashMap<>();

	private final String archivo = "archivo.csv";

	public void setVista(Vista vista) {
		this.vista = vista;
	}

	public float getEdadMedia(Object[] miLista) {
		float total = 0;
		ArrayList<Integer> edades = (ArrayList<Integer>) miLista[posicionListaNacimiento];
		for (Integer edad : edades) {
			total += (fechaActual - edad);
		}
		float edadMedia = total / edades.size();
		return edadMedia;
	}

	public char getMasUsada(Object[] miLista) {
		ArrayList<String> nombres = (ArrayList<String>) miLista[posicionListaNombres];
		letras.clear();
		Character letra = null;

		int compara = Integer.MIN_VALUE;
		for (String nombre : nombres) {
			nombre = nombre.toUpperCase();
			char caracter = nombre.charAt(0);
			if (letras.containsKey(caracter)) {
				int cantidad = letras.get(caracter);
				letras.replace(caracter, ++cantidad);
				System.out.println(cantidad);
			} else
				letras.put(caracter, 1);
		}

		for (HashMap.Entry<Character, Integer> entrada : letras.entrySet()) {
			int val = entrada.getValue();
			if (val > compara) {
				letra = entrada.getKey();
				compara = val;
			}
		}
		return letra;
	}

	boolean validaNombre() {
		super.nombre = vista.getTxtNombre().trim();
		if (super.nombre.matches("[A-Za-zñÑáéíóúÁÉÍÓÚ\s]+")) {
			return true;
		}
		return false;
	}

	boolean validaFecha() {
		String fecha = vista.getTxtFecha().trim();
		if (fecha.matches("[0-9]{4}")) {
			int fechaIngresada = Integer.parseInt(fecha);
			if (fechaIngresada >= 1900 && fechaIngresada <= fechaActual) {
				super.anoNacimiento = fechaIngresada;
				return true;
			}
		}
		return false;
	}

	public void agregarRegistro(String nombre, String fecha) {
		nombresList.add(super.nombre);
		anoNacimientoList.add(super.anoNacimiento);
		String[] agregaDato = { nombre, fecha };
		modeloTabla.addRow(agregaDato);
	}

	public DefaultTableModel getModeloTabla() {
		return modeloTabla;
	}

	public Object[] getMiLista() {
		return this.miLista;
	}

	public void guardarArchivo() {
		File file = new File(archivo);
		FileWriter fw;
		int filas = this.modeloTabla.getRowCount();
		try {
			fw = new FileWriter(file);

			for (int i = 0; i < filas; i++) {
					String nombre = (String) this.modeloTabla.getValueAt(i, posicionListaNombres);
					String anoNacimiento = (String) this.modeloTabla.getValueAt(i, posicionListaNacimiento);
					fw.write(String.format(nombre + " " + anoNacimiento + "\n ") );
					System.out.println(String.format(nombre + " " +  anoNacimiento) );
			}
			
			fw.write(String.format("Letra: %s, Edad media: %s\n", vista.getLblAlertaLetra(),
					vista.getLblAlertaEdadMedia()));
			fw.flush();
			fw.close();
			System.out.println("Archivo creado");
		} catch (IOException ioe) {
			System.out.println("Error, no se pudo crear el fichero");
		}
		return;
	}

}
