package repository.csvdao;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import model.Publicador;
import exception.Exceptions;
import exception.Exceptions.CsvDePublicadorMalFormado;
import exception.Exceptions.PublicadorDuplicadoException;

public class PublicadorCsvDao implements Closeable {

    private static final String CSV_SEPARADOR = ";";
    public static final String FLUJO_DATOS_ESCRITURA = "ESCRITURA";
    public static final String FLUJO_DATOS_LECTURA = "LECTURA";
    public static final String FLUJO_DATOS_LECTURA_ESCRITURA = "LECTURA_ESCRITURA";
    public static final String SISTEMA_DE_PERSISTENCIA = "Archivos CSV";
    private BufferedReader lectorBufereado;
    private FileReader lector;
    private FileWriter escritor;
    private Formatter formateador;
    private String formato;
    private String rutaArchivo;

    public PublicadorCsvDao() {
        super();
        this.rutaArchivo = "C:/Users/lenovo/Documents/grupo_dia/publicadores.csv";
        this.formato = "%s,%s,%s%s";
    }

    private void abrirFlujoDeDatos(String tipo) throws IOException {
        if(PublicadorCsvDao.FLUJO_DATOS_ESCRITURA == tipo || PublicadorCsvDao.FLUJO_DATOS_LECTURA_ESCRITURA == tipo)
        {
            this.escritor = new FileWriter(
                    this.rutaArchivo,
                    true
            );
            this.formateador = new Formatter(this.escritor);
        }
        else if(PublicadorCsvDao.FLUJO_DATOS_LECTURA == tipo || PublicadorCsvDao.FLUJO_DATOS_LECTURA_ESCRITURA == tipo)
        {	// Se abre el flujo de datos de lectura.
            this.lector = new FileReader(this.rutaArchivo);
            this.lectorBufereado = new BufferedReader(this.lector);
        }
    }

    private void cerrarFlujoDeDatos(String tipo) throws IOException {
        if(PublicadorCsvDao.FLUJO_DATOS_ESCRITURA == tipo || PublicadorCsvDao.FLUJO_DATOS_LECTURA_ESCRITURA == tipo)
        {
            if(this.formateador != null)
                this.formateador.close();
            if(this.escritor != null)
                this.escritor.close();
        }
        else if(PublicadorCsvDao.FLUJO_DATOS_LECTURA == tipo || PublicadorCsvDao.FLUJO_DATOS_LECTURA_ESCRITURA == tipo)
        {
            if(this.lectorBufereado != null)
                this.lectorBufereado.close();
            if(this.lector != null)
                this.lector.close();
        }
    }

    @Override
    public void close() throws IOException {
        cerrarFlujoDeDatos(PublicadorCsvDao.FLUJO_DATOS_LECTURA_ESCRITURA);
    }

    private long consultarIdMaximo() throws IOException, CsvDePublicadorMalFormado {
        long idActual;
        long idMax = 0;
        String linea;
        while( (linea = lectorBufereado.readLine()) != null ) {
            String[] datos = linea.split(PublicadorCsvDao.CSV_SEPARADOR);
            if(datos.length >= 1) {
                idActual = Long.parseLong(datos[0]); // Se espera que el Id esté en la posición 0.
                if(idActual > idMax) {
                    idMax = idActual;
                }
            } else {
                throw new Exceptions().new CsvDePublicadorMalFormado();
            }
        }
        return idMax;
    }

    public boolean existePublicador(String nombre) throws IOException, CsvDePublicadorMalFormado {
        String linea;
        String nombreRegistroActual;
        while( (linea = lectorBufereado.readLine()) != null ) {
            String[] datos = linea.split(PublicadorCsvDao.CSV_SEPARADOR);
            if(datos.length >= 2) {
                nombreRegistroActual = datos[1];
                if(nombre == nombreRegistroActual) {
                    return true;
                }
            } else {
                throw new Exceptions().new CsvDePublicadorMalFormado();
            }
        }
        return false;
    }

    private long generarId() throws CsvDePublicadorMalFormado, IOException {
        long id = consultarIdMaximo();
        return ++id;
    }

    public long guardarPublicador(Publicador publicador)
            throws PublicadorDuplicadoException, IOException, CsvDePublicadorMalFormado {

        try {
            abrirFlujoDeDatos(PublicadorCsvDao.FLUJO_DATOS_LECTURA_ESCRITURA);

            if(existePublicador(publicador.getNombre())) {
                PublicadorDuplicadoException pde = new Exceptions().new PublicadorDuplicadoException();
                pde.setSistemaDePersistencia(PublicadorCsvDao.SISTEMA_DE_PERSISTENCIA);
                pde.setNombreDelPublicador(publicador.getNombre());
                throw pde;
            }

            long idPublicador = generarId();

            this.formateador.format(
                    this.formato, idPublicador, publicador.getNombre(),
                    publicador.getRutaCarpetaArticulos(), publicador.getRutaCarpetaFotos()
            );

            return idPublicador;

        } finally {
            cerrarFlujoDeDatos(PublicadorCsvDao.FLUJO_DATOS_LECTURA_ESCRITURA);
        }
    }
}
