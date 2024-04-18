package Networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
	private static boolean serverRunning = true;

	public static void montar() {
		int port = 10;

		try (ServerSocket server = new ServerSocket(port)) {
			System.out.println("Esperando peticiones...");
			while (serverRunning) {
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

	public static void detenerServidor() {
		serverRunning = false;
	}
}
