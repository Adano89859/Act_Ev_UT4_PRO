package Gestor;

public class ReservaNoDisponibleException extends RuntimeException {
    public ReservaNoDisponibleException() {

        super("ERROR tipo Excepción: No se pudo ealizar la reserva, no está disponible.");
    }
}
