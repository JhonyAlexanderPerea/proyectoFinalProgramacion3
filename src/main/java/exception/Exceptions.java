package exception;

import model.Cliente;

public class   Exceptions extends Exception {

    private static final long serialVersionUID = 1L;

    public Exceptions() {
        super();
    }

    public Exceptions(String message) {
        super(message);
    }

    public Exceptions(String message, Exception excep) {
        super(message, excep);
    }

    // Miembros tipo clase

    public class PublicadorDuplicadoException extends Exceptions {

        private static final long serialVersionUID = 1L;
        private String sistemaDePersistencia; 	// BD o CSV.
        private String nombreDelPublicador;		// Nombre que se intento guardar.

        public PublicadorDuplicadoException() {
            super("..."); // TODO Gestionar un proveedor de mensajes.
        }

        public PublicadorDuplicadoException(
                String sistemaDePersistencia, String nombreDelPublicador) {
            super("dd");
            this.sistemaDePersistencia = sistemaDePersistencia;
            this.nombreDelPublicador = nombreDelPublicador;
        }

        public String getSistemaDePersistencia() {
            return sistemaDePersistencia;
        }

        public void setSistemaDePersistencia(String sistemaDePersistencia) {
            this.sistemaDePersistencia = sistemaDePersistencia;
        }

        public String getNombreDelPublicador() {
            return nombreDelPublicador;
        }

        public void setNombreDelPublicador(String nombreDelPublicador) {
            this.nombreDelPublicador = nombreDelPublicador;
        }

        @Override
        public String getMessage() {
            return ""; // TODO Gestionar un proveedor de mensajes.
        }

        @Override
        public String toString() {
            // TODO Gestionar un proveedor de mensajes.
            return "PublicadorDuplicadoException [sistemaDePersistencia=" + sistemaDePersistencia
                    + ", nombreDelPublicador=" + nombreDelPublicador + "]";
        }
    }

    public class CsvDePublicadorMalFormado extends Exceptions {

        private static final long serialVersionUID = 1L;

        public CsvDePublicadorMalFormado() {

        }
    }

    public class ClienteDuplicadoException extends Exceptions {

        private static final long serialVersionUID = 1L;
        private String sistemaDePersistencia; 	// BD o CSV.
        private String nombreDelCliente;		// Nombre del cliente que se intento guardar.

        public ClienteDuplicadoException() {
            super("..."); // TODO Gestionar un proveedor de mensajes.
        }

        public ClienteDuplicadoException(
                String sistemaDePersistencia, String nombreDelCliente) {
            super("dc");
            this.sistemaDePersistencia = sistemaDePersistencia;
            this.nombreDelCliente = nombreDelCliente;
        }

        public String getNombreDelCliente() {
            return nombreDelCliente;
        }

        public void setNombreDelCliente(String nombreDelCliente) {
            this.nombreDelCliente = nombreDelCliente;
        }

        @Override
        public String toString() {
            // TODO Gestionar un proveedor de mensajes.
            return "ClienteDuplicadoException [sistemaDePersistencia=" + sistemaDePersistencia
                    + ", nombreDelCliente=" + nombreDelCliente + "]";
        }


        public String getSistemaDePersistencia() {
            return sistemaDePersistencia;
        }

        public void setSistemaDePersistencia(String sistemaDePersistencia) {
            this.sistemaDePersistencia = sistemaDePersistencia;
        }
    }

    public class CsvDeClienteMalFormado extends Exceptions {

        private static final long serialVersionUID = 1L;

        public CsvDeClienteMalFormado() {

        }
    }
}