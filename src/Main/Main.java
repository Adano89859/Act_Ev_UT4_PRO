package Main;
import Gestor.Estado;
import Gestor.Tipo_Habitacion;
import Modelos.habitaciones;

public class Main {
    public static void main(String[] args) {

        //Recurro a este metodo para crear las habitaciones y un array que las contenga
        inicializarPlantasHabitaciones();

    }

    /*
    Resultado: Este metodo crea los objetos habitación y los asigna dentro del array Hotel que representa el Hotel
     */
    public static void inicializarPlantasHabitaciones(){
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
    }

}