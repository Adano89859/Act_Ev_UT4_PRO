package Gestor;

import Modelos.habitaciones;

public class Gestor_Hotel {

    //Atributos de la clase Gestor_Hotel
    habitaciones[][]Hotel;

    /*
    //CONSTRUCTOR de la clase Gestor_Hotel
     */
    public Gestor_Hotel(habitaciones[][]Hotel) {
        this.Hotel = Hotel;
    }

    /*
    @return El objeto Hotel que contiene las habitaciones del hotel
     */
    public habitaciones[][] getHotel() {
        return Hotel;
    }

    /*
    Resultado: Este metodo otorga toda la información de cada una de las habitaciones del hotel
     */
    public void resumenHabitaciones(){
        //Hago un foreach del hotel, desglosandolo en plantas
        for(habitaciones[] planta:Hotel){
            //Del desglosado previo, voy sacando la información de cada habitación
            for(habitaciones habitacion:planta){
                //Con estos 4 System.out.println muestro toda la información necesaria de las habitaciones
                System.out.println("-------------------------------------------");
                System.out.println("La habitación "+habitacion.getNumeroHabitacion()+ " tiene el estado "+habitacion.getestado()+", y su precio por noche es "+habitacion.getprecio_noche()+", pues pertenece a la categoría "+habitacion.gettipoHabitacion()+".");
                System.out.println(habitacion.getdescripcion());
                System.out.println("-------------------------------------------");
            }
        }
    }

}
