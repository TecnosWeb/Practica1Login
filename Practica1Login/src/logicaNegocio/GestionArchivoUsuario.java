package logicaNegocio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GestionArchivoUsuario {

	public boolean buscarUsuario(String nombreUsuario, String pass)
			throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader("datos.txt"));
		try {
			String line = br.readLine();
			String[] lineaSeparada;

			while (line != null) {
				line = br.readLine();
				lineaSeparada = line.split("|");
				if (lineaSeparada[0].equals(nombreUsuario)) {
					br.close();
					return true;
				}
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("No se encontró el registro");
			e.printStackTrace();
		}
		return false;
	}

	public boolean registrarUsuario(String user, String password,
			String nombre, String apellidos, String email) {
		try {
			FileWriter fr = new FileWriter("datos.txt", true);
			fr.write(user + "|" + password + "|" + nombre + "|" + apellidos
					+ "|" + email);
			fr.close();
			return true;
		} catch (IOException e) {
			System.out.println("No se encontró el registro");
			e.printStackTrace();
			return false;
		}
	}

	public GestionArchivoUsuario() {
	}

	public static void main(String[] args) {
		GestionArchivoUsuario gestion = new GestionArchivoUsuario();
		String user = "snake";
		String pass = "12345";
		String nombre = "Timo";
		String apellido = "Latisnere";
		String email = "shingrayfox@gmail.com";
		try {
			if (gestion.buscarUsuario(user, pass)) {
				System.out.println("Registor encontrado");
			} else {
				System.out.println("Archivo no encontrado!");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}