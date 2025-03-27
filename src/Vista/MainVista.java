package Vista;

public class MainVista {

    public void mostrarBienvenida(){
        System.out.println("¡Bienvenido/a a nuestro hotel, Adán&Kevins'sl!");
    }
    
    public void mostrarMenuReserva(){
        System.out.println("1-Crear Reserva");
        System.out.println("2-Cancelar Reserva");
        System.out.println("3-Buscar Habitación a su gusto");
        System.out.println("4-Buscar Reservas activas");
        System.out.println("5-Resumen Todas Las Habitaciones");
        System.out.println("6-Resumen Todos Los Clientes y Sus Habitaciones Reservadas");
        System.out.println("7-Calcular Precio Total De Una Reserva");
        System.out.println("8-Salir de Adán&Kevins'sl");
    }

    public void mostrarDespedida(){
        System.out.println("Gracias por confiar en Adán&Kevins.sl ¡Hasta la próxima!");
    }

    public void mostrarOpcionInvalida(){
        System.out.println("Lo sentimos, la opción que seleccionó no es posible.Posibles causas:");
        System.out.println("-No introdujo un número entro (por ejemplo:4)");
        System.out.println("-Introdujo un número entro menor que 1 o mayor que 8)");
        System.out.println("Considere alguna de las siguientes opciones: ");
    }
}
