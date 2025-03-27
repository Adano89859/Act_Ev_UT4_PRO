package Modelos;

import Gestor.Estado;
import Gestor.Tipo_Habitacion;

public class habitaciones {

    //Atributos de la clase Habitaciones y su relación con el cliente
    private int numeroHabitacion;
    private Tipo_Habitacion tipoHabitacion;
    private double precio_noche;
    private Estado estado;
    private String descripcion;

    /*
    *CONSTRUCTOR*
    @param Un número de habitación, un tipo de habitación, un precio por noche, un estado, una descripción.
    Resultado: Se crea en el Main, introduciendo la información que tendrá este objeto, y desde el constructor atribuyo la info del main al modelo actual
     */
    public habitaciones(int numeroHabitacion,Tipo_Habitacion tipoHabitacion,double precio_noche,Estado estado,String descripcion){
        this.numeroHabitacion = numeroHabitacion;
        this.tipoHabitacion = tipoHabitacion;
        this.precio_noche = precio_noche;
        this.estado = estado;
        this.descripcion = descripcion;
    }

    /*
    //Los diferentes GETTER y SETTER que tiene la clase habitaciones
     */
    public void setNumeroHabitacion(int numeroHabitacion){
        this.numeroHabitacion = numeroHabitacion;
    }

    public void settipoHabitacion(Tipo_Habitacion tipoHabitacion){
        this.tipoHabitacion = tipoHabitacion;
    }

    public void setestado(Estado estado){
        this.estado = estado;
    }

    public void descripcion(String descripcion){
        this.descripcion = descripcion;
    }


    public int getNumeroHabitacion(){return this.numeroHabitacion;}

    public Tipo_Habitacion gettipoHabitacion(){return this.tipoHabitacion;}

    public double getprecio_noche(){return this.precio_noche;}

    public Estado getestado(){return this.estado;}

    public String getdescripcion(){return this.descripcion;}

}
