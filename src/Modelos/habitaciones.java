package Modelos;

import Gestor.Estado;
import Gestor.Tipo_Habitacion;

public class habitaciones {

    //Atributos de la clase Habitaciones
    private int numeroHabitacion;
    private Tipo_Habitacion tipoHabitacion;
    private double precio_noche;
    private Estado estado;
    private String descripcion;

    /*
    *CONSTRUCTOR*
    @param Un número de habitación, un tipo de habitación, un precio por noche, un estado, y una descripción. Todo ello traído del main a iniciar la aplicación
    Resultado: Se crea en el Main, introduciendo la información que tendrá este objeto, y desde el constructor atribuyo la info del main al modelo actual
     */
    public habitaciones(int numeroHabitacion,Tipo_Habitacion tipoHabitacion,double precio_noche,Estado estado,String descripcion){
        this.numeroHabitacion = numeroHabitacion;
        this.tipoHabitacion = tipoHabitacion;
        this.precio_noche = precio_noche;
        this.estado = estado;
        this.descripcion = descripcion;
    }

}
