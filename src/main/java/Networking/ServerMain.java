package Networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerMain {

	public static void montar() {
		int port = 10;
		//int hilosMaximos = 5;

		//ExecutorService executor = Executors.newFixedThreadPool(hilosMaximos);
		//UtilsJPA.getEntityManager();
		try (ServerSocket server = new ServerSocket(port)) {
			System.out.println("Esperando peticiones...");
			while (true) {
				Socket socket = server.accept();
				System.out.println("cliente conectado");

				ClienteHilo hilo = new ClienteHilo(socket);
				Thread hiloThread = new Thread(hilo);
				hiloThread.start();

			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
