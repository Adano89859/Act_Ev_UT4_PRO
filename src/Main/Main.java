package Main;
import Gestor.Estado;
import Gestor.Gestor_Hotel;
import Gestor.Tipo_Habitacion;
import Modelos.clientes;
import Modelos.habitaciones;

import java.util.ArrayList;
import java.util.Scanner;

import Modelos.reservas;
import Vista.MainVista;

public class Main {
    public static void main(String[] args) {

        //Recurrimos a este metodo para crear las habitaciones y un array que las contenga
        habitaciones[][]Hotel = inicializarPlantasHabitaciones();

        //Inicializamos un array con los clientes posibles
        clientes[] listaClientes = inicializadorClientes();

        //Inicializamos el array de las reservas, que empieza vacío
        ArrayList<reservas> listaReservas = inicializadorReservas();

        //Uso el array de Hotel que contiene las habitaciones y lo introduzco en el constructor del Gestor
        Gestor_Hotel gestorHotel = inicializarGestor_Hotel(Hotel,listaClientes, listaReservas);

        //Inicializamos el Scanner para recoger la información por teclado
        Scanner teclado = new Scanner(System.in);
        
        //Inicializamos las variables del main
        int eleccion;
        
        //Cargamos la vista
        MainVista mainVista = new MainVista();

        //Opciones referentes al Menú, Bienvenida y las opciones permitidas
        mainVista.mostrarBienvenida();

        do{

            //Creamos un menú de las opciones que se pueden elegir
            mainVista.mostrarMenuReserva();

            //Leemos la opción elegida por el usuario
            eleccion = teclado.nextInt();

            //A partir de la opción elegida por el usuario, llamamos al método indicado
            switch (eleccion) {
                case 1:
                    gestorHotel.crearReserva();
                    break;
                    case 2:
                    gestorHotel.cancelarReservaPorCliente();
                    
                    break;
                    case 3:
                    gestorHotel.buscarHabitaciones();
                    
                    break;
                    case 4:
                        //Metodo para ver todas las reservas activas
                        gestorHotel.buscarReservasActivas();
                    break;
                    case 5:
                        // Traemos del gestor el resumen de las habitaciones
                        gestorHotel.resumenHabitaciones();
                    break;
                    case 6:
                        // Traemos del gestor el resumen de los clientes
                        gestorHotel.resumenCliente();
                    break;
                    case 7:
                    
                    break;
                    case 8:
                        // Traemos de la vista el texto de despedida
                        mainVista.mostrarDespedida();
                    break;
                default:
                    // Traemos de la vista el texto de opción inválida
                    mainVista.mostrarOpcionInvalida();
                    throw new AssertionError();
            }
        }while(eleccion !=8);

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

    /*
    Este método inicializa el Gestor, introduciendo en él los valores que necesita
     */
    public static Gestor_Hotel inicializarGestor_Hotel(habitaciones[][]Hotel, clientes[] listaClientes, ArrayList<reservas> listaReservas){
        //Creo el objeto
        Gestor_Hotel gestorHotel = new Gestor_Hotel(Hotel,listaClientes,listaReservas);
        //Devuelvo el objeto para que pueda ser usado en main
        return gestorHotel;
    }

    /*
    Este método crea un array con los Clientes que tendrá el sistema
     */
    public static clientes[] inicializadorClientes(){
        // Creamos un array de clientes e inicializamos a tamaño a 5 de clientes
        clientes[] listaClientes = new clientes[5];
        //Introducimos en él los ejemplos de clientes que usaremos
        listaClientes[0] = new clientes(1,"ClienteEjemplo");
        listaClientes[1] = new clientes(2,"Kevin");
        listaClientes[2] = new clientes(3,"Adán");
        listaClientes[3] = new clientes(4,"Josue");
        listaClientes[4] = new clientes(5,"Mer");
        //Devolvemos el array creado
        return listaClientes;
    }

    /*
    Este método crea un arrayList que contendrá las reservas
     */
    public static ArrayList<reservas> inicializadorReservas(){
        //Creo el arrayList de las reservas, que por ahora está vacío
        ArrayList<reservas> listaReservas = new ArrayList<>();
        //Devolvemos el arrayList de las reservas
        return listaReservas;
    }

}