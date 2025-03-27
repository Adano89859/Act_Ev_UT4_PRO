package Modelos;

public class clientes {
    // @Params
    private int id;
    private String nombre;
    private String historial;

    /*
    *CONSTRUCTOR*
    @param Cliente(id, nombre, historial)
    Resultado: Se crea en el Main, introduciendo la información que tendrá este objeto, y desde el constructor atribuyo la info del main al modelo actual
     */
    public clientes(int id, String nombre, String historial){
        this.id = id;
        this.nombre = nombre;
        this.historial = historial;
    }

    // Obtenemos el ID
    public int getId(){
        return this.id;
    }

    // Mostramos el ID
    public void setId(int id){
        this.id = id;
    }

    // Obtenemos el nombre
    public String getNombre(){
        return this.nombre;
    }

    // Mostramos el nombre
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    // Obtenemos el historial
    public String getHistorial(){
        return this.historial;
    }

    // Mostramos el historial
    public void setHistorial(String historial){
        this.historial = historial;
    }
}