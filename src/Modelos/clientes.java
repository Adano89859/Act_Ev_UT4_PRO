package Modelos;

import java.util.ArrayList;

public class clientes {
    // @Params
    private int id;
    private String nombre;
    private ArrayList<String> historialCliente = new ArrayList<>();

    /*
    *CONSTRUCTOR*
    @param Cliente(id, nombre, historial)
    Resultado: Se crea en el Main, introduciendo la información que tendrá este objeto, y desde el constructor atribuyo la info del main al modelo actual
     */
    public clientes(int id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

    // Obtenemos el ID
    public int getId(){
        return this.id;
    }

    // Introducimos el ID
    public void setId(int id){
        this.id = id;
    }

    // Obtenemos el nombre
    public String getNombre(){
        return this.nombre;
    }

    // Introducimos el nombre
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    // Obtenemos el historial
    public ArrayList<String> getHistorial(){
        //Devuelvo el arrayList del cliente
        return this.historialCliente;
    }

    // Introducimos el historial
    public void setHistorial(String historial){
        //Meto el historial traído de otro sitio adentro del array
        historialCliente.add(historial);
    }
}