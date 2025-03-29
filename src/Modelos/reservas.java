package Modelos;

import java.time.LocalDateTime;

public class reservas {

    //Atributos de la clase Reserva
    private int id;
    private habitaciones habitacion;
    private clientes cliente;
    private LocalDateTime check_in;
    private LocalDateTime check_out;
    private double precio;

    //CONSTRUCTOR de la clase reserva
    public reservas(int id,habitaciones habitacion,clientes cliente,LocalDateTime check_in,LocalDateTime check_out,double precio){
        this.id = id;
        this.habitacion = habitacion;
        this.cliente = cliente;
        this.check_in = check_in;
        this.check_out = check_out;
        this.precio = precio;
    }

    //Los SETTER
    public void setId(int id){
        this.id = id;
    }

    public void sethabitacion(habitaciones habitacion){
        this.habitacion = habitacion;
    }

    public void setcliente(clientes cliente){
        this.cliente = cliente;
    }

    public void setcheck_in(LocalDateTime check_in){
        this.check_in = check_in;
    }

    public void setcheck_out(LocalDateTime check_out){
        this.check_out = check_out;
    }

    public void setprecio(double precio){
        this.precio = precio;
    }

    //Los GETTER
    public int getId() {
        return this.id;
    }

    public habitaciones gethabitacion() {
        return this.habitacion;
    }

    public clientes getcliente() {
        return this.cliente;
    }

    public LocalDateTime getcheck_in() {
        return this.check_in;
    }

    public LocalDateTime getcheck_out() {
        return this.check_out;
    }

    public double getprecio() {
        return this.precio;
    }

}
