package Main;
import Gestor.Estado;
import Gestor.Gestor_Hotel;
import Gestor.Tipo_Habitacion;
import Modelos.habitaciones;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Recurro a este metodo para crear las habitaciones y un array que las contenga
        habitaciones[][]Hotel = inicializarPlantasHabitaciones();
        //Uso el array de Hotel que contiene las habitaciones y lo introduzco en el constructor del Gestor
        Gestor_Hotel gestorHotel = inicializarGestor_Hotel(Hotel);

        //Inicializamos el Scanner para recoger la información por teclado
        Scanner teclado = new Scanner(System.in);

        //Inicializo las variables del main
        int eleccion;

        //Opciones referentes al Menú, Bienvenida y las opciones permitidas
        System.out.println("¡Bienvenido a nuestro hotel, Adán&Kevins'sl!");
        do{

            
            //Informo al usuario de las opciones que puede elegir
            System.out.println("1-Crear Reserva");
            System.out.println("2-Cancelar Reserva");
            System.out.println("3-Buscar Habitación a su gusto");
            System.out.println("4-Buscar Reservas activas");
            System.out.println("5-Resumen Todas Las Habitaciones");
            System.out.println("6-Resumen Todos Los Clientes y Sus Habitaciones Reservadas");
            System.out.println("7-Calcular Precio Total De Una Reserva");
            System.out.println("8-Salir de Adán&Kevins'sl");
            //Leemos la opción elegida por el usuario
            eleccion = teclado.nextInt();

            //A partir de la opción elegida por el usuario, llamamos al método indicado
            switch (eleccion) {
                case 1:
                    
                    break;
                    case 2:
                    
                    break;
                    case 3:
                    
                    break;
                    case 4:
                    
                    break;
                    case 5:
                        gestorHotel.resumenHabitaciones();
                    break;
                    case 6:
                    
                    break;
                    case 7:
                    
                    break;
                    case 8:
                    System.out.println("Gracias por confiar en Adán&Kevins'sl ¡Hasta la próxima!");
                    break;
                default:
                System.out.println("Lo sentimos, la opción que seleccionó no es posible.Posibles causas:");
                System.out.println("-No introdujo un número entro (por ejemplo:4)");
                System.out.println("-Introdujo un número entro menor que 1 o mayor que 8)");
                    throw new AssertionError();
            }
        }while(eleccion !=8);
        System.out.println("Considere alguna de las siguientes opciones:");

        //Cerramos el Scanner
        teclado.close();
    }

    /*
    Resultado: Este metodo crea los objetos habitación y los asigna dentro del array Hotel que representa el Hotel
     */
    public static habitaciones[][] inicializarPlantasHabitaciones(){
        //Creo el array bidimensional o matriz de Hotel
        habitaciones[][]Hotel = new habitaciones[3][5];
        //Introduzco las habitaciones de la planta 1 en el array
        Hotel[0][0] = new habitaciones(101, Tipo_Habitacion.INDIVIDUAL,10, Estado.DISPONIBLE,"Habitación individual y estandar");
        Hotel[0][1] = new habitaciones(102, Tipo_Habitacion.INDIVIDUAL,10, Estado.DISPONIBLE,"Habitación individual y estandar");
        Hotel[0][2] = new habitaciones(103, Tipo_Habitacion.INDIVIDUAL,10, Estado.DISPONIBLE,"Habitación individual y estandar");
        Hotel[0][3] = new habitaciones(104, Tipo_Habitacion.INDIVIDUAL,10, Estado.DISPONIBLE,"Habitación individual y estandar");
        Hotel[0][4] = new habitaciones(105, Tipo_Habitacion.INDIVIDUAL,10, Estado.DISPONIBLE,"Habitación individual y estandar");
        //Introduzco las habitaciones de la planta 2 en el array
        Hotel[1][0] = new habitaciones(201, Tipo_Habitacion.DOBLE,20, Estado.DISPONIBLE,"Habitación para dos personas, y estandar");
        Hotel[1][1] = new habitaciones(202, Tipo_Habitacion.DOBLE,20, Estado.DISPONIBLE,"Habitación para dos personas, y estandar");
        Hotel[1][2] = new habitaciones(203, Tipo_Habitacion.DOBLE,20, Estado.DISPONIBLE,"Habitación para dos personas, y estandar");
        Hotel[1][3] = new habitaciones(204, Tipo_Habitacion.DOBLE,20, Estado.DISPONIBLE,"Habitación para dos personas, y estandar");
        Hotel[1][4] = new habitaciones(205, Tipo_Habitacion.DOBLE,20, Estado.DISPONIBLE,"Habitación para dos personas, y estandar");
        //Introduzco las habitaciones de la planta 3 en el array
        Hotel[2][0] = new habitaciones(301, Tipo_Habitacion.SUITE,30, Estado.DISPONIBLE,"Habitación suite");
        Hotel[2][1] = new habitaciones(302, Tipo_Habitacion.SUITE,30, Estado.DISPONIBLE,"Habitación suite");
        Hotel[2][2] = new habitaciones(303, Tipo_Habitacion.SUITE,30, Estado.DISPONIBLE,"Habitación suite");
        Hotel[2][3] = new habitaciones(304, Tipo_Habitacion.SUITE,30, Estado.DISPONIBLE,"Habitación suite");
        Hotel[2][4] = new habitaciones(305, Tipo_Habitacion.SUITE,30, Estado.DISPONIBLE,"Habitación suite");
        //Devolvemos el array que acabamos de crear
        return Hotel;
    }

    public static Gestor_Hotel inicializarGestor_Hotel(habitaciones[][]Hotel){
        //Creo el objeto
        Gestor_Hotel gestorHotel = new Gestor_Hotel(Hotel);
        //Devuelvo el objeto para que pueda ser usado en main
        return gestorHotel;
    }

}