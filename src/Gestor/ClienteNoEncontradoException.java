package Gestor;

public class ClienteNoEncontradoException extends RuntimeException {
    public ClienteNoEncontradoException(String message) {

        super("ERROR tipo Excepción: EL cliente no fue encontrado.");
    }
}
