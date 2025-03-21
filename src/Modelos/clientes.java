package Modelos;

public class clientes {
    private int id;
    private String nombre;
    private String historial;

    public clientes(int id, String nombre, String historial){
        this.id = id;
        this.nombre = nombre;
        this.historial = historial;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }


    public String getHistorial(){
        return this.historial;
    }

    public void setHistorial(String historial){
        this.historial = historial;
    }
}

