package model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Seguridad {
    private static final String FILENAME = "src/main/resources/seguridad/usuarios.txt"; // Nombre del archivo donde se almacenarán los usuarios y contraseñas

    public boolean validarUsuario(String nombreUsuario, String contraseña) {
        ArrayList<Usuario> usuarios = cargarUsuarios();
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreCompleto().equals(nombreUsuario) && usuario.getContraseña().equals(contraseña)) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<Usuario> cargarUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                usuarios.add(new Usuario(parts[0], parts[1]));
            }
        } catch (IOException e) {
            // Manejo de errores de lectura del archivo (puede que el archivo aún no exista)
            System.err.println("Error al cargar usuarios: " + e.getMessage());
        }
        return usuarios;
    }
}