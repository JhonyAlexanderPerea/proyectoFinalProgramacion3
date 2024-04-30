package Networking;

import java.io.*;
import java.net.Socket;


public class ClienteHilo implements Runnable {

	private final Socket sCliente;

	public ClienteHilo(Socket sCliente) {
		this.sCliente = sCliente;
	}

	@Override
	public void run() {
		try {
			// Leer el archivo enviado por el cliente y guardarlo
			InputStream inputStream = sCliente.getInputStream();

			// El nombre del archivo es enviado primero por el cliente
			String nombreArchivo = recibirNombreArchivo(inputStream);

			if (nombreArchivo != null && !nombreArchivo.isEmpty()) {
				// Guardar el contenido del archivo
				guardarContenidoArchivo(inputStream, nombreArchivo);
				System.out.println("Archivo recibido correctamente desde el cliente: " + nombreArchivo);
			} else {
				System.out.println("No se recibió el nombre del archivo correctamente.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String recibirNombreArchivo(InputStream is) throws IOException, ClassNotFoundException {
		ObjectInputStream inputStream = new ObjectInputStream(is);
		String nombreArchivo = (String) inputStream.readObject();
		return nombreArchivo;
	}

	private void guardarContenidoArchivo(InputStream inputStream, String nombreArchivo) throws IOException {

		File directorio = new File("C:\\Users\\JHONY\\Documents\\GitHub\\proyectoFinalProgramacion3\\src\\main\\resources\\");

		if (!directorio.exists()) {
			directorio.mkdirs();  // Crear directorio y subdirectorios si es necesario
		}

		File archivoDestino = new File("C:\\Users\\JHONY\\Documents\\GitHub\\proyectoFinalProgramacion3\\src\\main\\resources\\" + nombreArchivo);

		// Crear el archivo en el directorio destino
		if (!archivoDestino.exists()) {
			archivoDestino.createNewFile(); // Crear el archivo si no existe
		}
		// Guardar el contenido del archivo
		System.out.println(nombreArchivo);
		try (FileOutputStream fileOutputStream = new FileOutputStream(archivoDestino)) {
			byte[] buffer = new byte[4096];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				fileOutputStream.write(buffer, 0, bytesRead);
			}
		}
	}
}

