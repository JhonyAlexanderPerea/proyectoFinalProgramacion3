package repository.csvdao;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import model.Publicador;
import exception.GduqExceptions;
import exception.GduqExceptions.CsvDePublicadorMalFormado;
import exception.GduqExceptions.PublicadorDuplicadoException;

/**
 * Proporciona un adaptador enetre la aplicación y el sistema de
 * persistencia mediante archivos csv.
 *
 * Funciona como una aproximación a la práctica denominada
 * Data Access Object (Objeto de acceso a datos).
 *
 * Esta clase es un ejercicio de práctica de las utilidades del paquete
 * de java.io para escribir y leer archivos.
 *
 * Esta clase implementa la interfaz Closeable a fin de que alguna clase
 * externa que la instancie pueda invocar su método de cerrado de recursos
 * en en este caso significa cerrar los flujos o conexiones con los archivos.
 * Adicionalmente si esta clase es instanciada dentro de la clausula denominada
 * try con recursos (try with resources) entonces al final del blocke de los
 * corchetes (ámbito) el systema Java automáticamente llamará la función close()
 * a fin de cerrar los flujos de datos con los archivos csv. Por ejemplo:
 *
 * try(PublicadorDao miPublicador = new PublicadorDao()) {...}
 *
 * La sentencia anterior hará un llamado automático al metodo close() de la variable
 * miPublicador cuando la ejecución del programa llegue al corchete de cierre.
 */
public class PublicadorCsvDao implements Closeable {

    // Miembros tipo constante.

    /**
     * Caracter o conjunto de caracteres que definen la separación de los campos
     * o datos en el archivo csv, por ejemplo: ,   ;   |   ||   &   &&
     */
    private static final String CSV_SEPARADOR = ";";

    /**
     * Indica que el flujo de datos a trabajar es de escritura.
     */
    public static final String FLUJO_DATOS_ESCRITURA = "ESCRITURA";

    /**
     * Indica que el flujo de datos a trabajar es de lectura.
     */
    public static final String FLUJO_DATOS_LECTURA = "LECTURA";

    /**
     * Indica que se requiere trabajar con los flujos de escritura y escritura.
     */
    public static final String FLUJO_DATOS_LECTURA_ESCRITURA = "LECTURA_ESCRITURA";

    /**
     * Indica el nombre del sistema de persistencia
     */
    public static final String SISTEMA_DE_PERSISTENCIA = "Archivos CSV";


    // Miembros tipo variable.

    /**
     * Objeto utilizado para extraer línea por línea del archivo
     * en el cual están registrados los publicadores.
     */
    private BufferedReader lectorBufereado;

    /**
     * Objeto para conectarse al archivo csv y leer de el los
     * datos de los publicadores guardados.
     */
    private FileReader lector;

    /**
     * Objeto para conectarse al archivo csv y realizar en el
     * la escritura o guardado de publicadores.
     */
    private FileWriter escritor;

    /**
     * Objeto para formatear cada línea de texto que se escribe
     * en el archivo csv.
     */
    private Formatter formateador;

    /**
     * Formato en el que se escriben las líneas o renglones en el
     * archivo csv. El formato es el siguiente:
     *
     * Id,Nombre,ruta de su carpeta de artículos,ruta de su carpeta de fotos.
     */
    private String formato;

    /**
     * Ruta del archivo csv en el cual se guardan los publicadores
     * que se registran.
     */
    private String rutaArchivo;


    // Miembros tipo método (Constructor).

    /**
     * Constructor.
     */
    public PublicadorCsvDao() {
        super();
        this.rutaArchivo = "C:/Users/lenovo/Documents/grupo_dia/publicadores.csv";
        this.formato = "%s,%s,%s%s";
    }


    // Miembros tipo método.

    /**
     * Establece la conexión con el archivo en el cual se registran
     * los publicadores.
     *
     * @throws IOException En caso de presentarse problemas para abrir
     *         o conectarse con el archivo, entonces se lanza la excepción
     *         hacía los invocadores de este método.
     */
    private void abrirFlujoDeDatos(String tipo) throws IOException {
        if(PublicadorCsvDao.FLUJO_DATOS_ESCRITURA == tipo || PublicadorCsvDao.FLUJO_DATOS_LECTURA_ESCRITURA == tipo)
        {	// Se abre el flujo de datos de escritura.
            this.escritor = new FileWriter(
                    this.rutaArchivo, // Ruta del archivo csv.
                    true			  // Indicación para añadir el texto al final del archivo.
            );
            this.formateador = new Formatter(this.escritor);
        }
        else if(PublicadorCsvDao.FLUJO_DATOS_LECTURA == tipo || PublicadorCsvDao.FLUJO_DATOS_LECTURA_ESCRITURA == tipo)
        {	// Se abre el flujo de datos de lectura.
            this.lector = new FileReader(this.rutaArchivo);
            this.lectorBufereado = new BufferedReader(this.lector);
        }
    }

    /**
     * Cierra los flujos de datos conectados a los archivos csv.
     *
     * @param tipo El tipo de recurso a cerrar, lectura o escritura.
     * @throws IOException Si se presenta un error al intentar cerrar.
     */
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

    /**
     * El id es un consecutivo, la idea es que no se repita, por lo tanto
     * cuando se va registrar un nuevo Publicador puede ser util consultar
     * el id máximo cual es en el momento actual.
     *
     * @return el número del id máximo.
     * @throws IOException En caso de presentarse problemas para abrir
     *         o conectarse con el archivo, entonces se lanza la excepción
     *         hacía los invocadores de este método.
     * @throws CsvDePublicadorMalFormado Si el archivo csv no tiene la cantidad de columnas necesarias.
     */
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
                throw new GduqExceptions().new CsvDePublicadorMalFormado();
            }
        }
        return idMax;
    }

    /**
     * Verifica si el nombre del publicador que se requiere registrar ya
     * existe en el archivo de publicadores guardados.
     *
     * @param nombre Nombre del publicador que se pretende registrar.
     * @return true si el nombre ya existe, de otro modo false.
     * @throws IOException Si durante la gestión del flujo de datos se presenta un error.
     * @throws CsvDePublicadorMalFormado Si el archivo csv no tiene la cantidad de columnas necesarias.
     */
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
                throw new GduqExceptions().new CsvDePublicadorMalFormado();
            }
        }
        return false;
    }

    /**
     * El id es un consecutivo, para guardar un nuevo publicador se
     * incrementa el id máximo actual en una unidad, es decir si el
     * último publicador guardado fue el 5 entonces el nuevo será el 6.
     *
     * @return El id generado para guardar el nuevo Publicador.
     * @throws IOException En caso de presentarse problemas para abrir
     *         o conectarse con el archivo, entonces se lanza la excepción
     *         hacía los invocadores de este método.
     * @throws CsvDePublicadorMalFormado Si el archivo csv no tiene la cantidad de columnas necesarias.
     */
    private long generarId() throws CsvDePublicadorMalFormado, IOException {
        long id = consultarIdMaximo();
        return ++id;
    }

    /**
     * Almacena un publicador en el archivo csv.
     *
     * @param publicador El publicador a guardar.
     * @return El identificador del publicador guardado.
     * @throws PublicadorDuplicadoException Si el publicador que se desea registrar
     *         ya se encuentra en el archivo CSV
     * @throws IOException En caso de presentarse problemas para abrir
     *         o conectarse con el archivo, entonces se lanza la excepción
     *         hacía los invocadores de este método.
     * @throws CsvDePublicadorMalFormado Si el archivo csv no tiene la cantidad de columnas necesarias.
     */
    public long guardarPublicador(Publicador publicador)
            throws PublicadorDuplicadoException, IOException, CsvDePublicadorMalFormado {

        try {
            // Se abre el flujo de datos con el archivo csv.
            abrirFlujoDeDatos(PublicadorCsvDao.FLUJO_DATOS_LECTURA_ESCRITURA);

            // Se verifica si ya existe un publicador registrado con el mismo nombre.
            if(existePublicador(publicador.getNombre())) {
                PublicadorDuplicadoException pde = new GduqExceptions().new PublicadorDuplicadoException();
                pde.setSistemaDePersistencia(PublicadorCsvDao.SISTEMA_DE_PERSISTENCIA);
                pde.setNombreDelPublicador(publicador.getNombre());
                throw pde;
            }

            // Se genera el identificador del publicador a registrar.
            long idPublicador = generarId();

            // Se guarda el publicador en el archivo csv
            this.formateador.format(
                    this.formato, idPublicador, publicador.getNombre(),
                    publicador.getRutaCarpetaArticulos(), publicador.getRutaCarpetaFotos()
            );

            return idPublicador; // Tal vez alguien necesite el id.

        } finally {
            cerrarFlujoDeDatos(PublicadorCsvDao.FLUJO_DATOS_LECTURA_ESCRITURA);
        }
    }
}
