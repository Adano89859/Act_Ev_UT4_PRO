package Gestor;

import Modelos.habitaciones;
import Modelos.clientes;

import java.util.Scanner;

public class Gestor_Hotel {

    //Atributos de la clase Gestor_Hotel
    habitaciones[][]Hotel;
    clientes[] listaClientes;
    /*
    //CONSTRUCTOR de la clase Gestor_Hotel
     */
    public Gestor_Hotel(habitaciones[][]Hotel, clientes[] listaClientes) {
        this.Hotel = Hotel;
        this.listaClientes = listaClientes;
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

    /*
    Resultado: Este metodo otorga toda la información de cada uno de los clientes que estén hospedados
    si están hospedados (true) el array listaClientes lo almacena hasta 100 clientes, en caso contrario
    no hace ninguna acción y solo comenta que no ya clientes registrados. 
    */
    public void resumenCliente() {
        // Verificamos si hay clentes registrados si no no hace nada y mostrará un mensaje
        if (listaClientes.length ==0) {
            System.out.println("No hay clientes registrados ");
            return;
        }

        /**
         * Si hay clientes registrados entonces recorrerá el array del contador de clientes almacenados e imprimirá
         * los resultados @params(id, nombre, historial) traidos del modelo clientes
         */
        for (int i = 0; i < listaClientes.length; i++) {
            clientes cliente = listaClientes[i];
            System.out.println("-------------------------------------------");
            System.out.println("ID cliente: " + cliente.getId());
            System.out.println("Nombre del cliente: " + cliente.getNombre());
            System.out.println("Historial del cliente: " + cliente.getHistorial());
            System.out.println("-------------------------------------------");

        }

    }

    /*
    Este método se encarga de crear una reserva a partir de lo que desea el usuario y de si es posible
     */
    public void crearReserva(){
        //Creo un scanner para leer la información que introduce el usuario
        Scanner teclado = new Scanner(System.in);

        //Recojo el supuesto usuario que va a realizar la reserva
        System.out.println("Decidió crear una reserva ¿Quién es el interesado en hacer la reserva?");
        String persona = teclado.nextLine();

        //Compruebo que existe el usuario
        if(verificadorClienteExiste(persona)){
            //Ahora que sé que el usuario existe, le digo las habitaciones que están disponibles
            mostrarHabitacionesDisponibles();

            //Le pido al usuario la habitación que quiere
            System.out.println("Introduce el número de la habitación que deseas reservar:");
            String habitacionReservar = teclado.nextLine();

            //Verifico que la habitación seleccionada existe


        }else{
            System.out.println("ERROR: La persona que seleccionó no existe");
        }

    }

    /*
    @param El nombre de la persona
    @return Booleano que dice si la persona existe o no
    Método para verificar que la persona en cuestión existe
     */
    public boolean verificadorClienteExiste(String persona){
        for(clientes cliente : listaClientes){
            //Compruebo el nombre de cada cliente, y lo comparo con el texto metido por el usuario
            if(persona.equals(cliente.getNombre())){
                return true;
            }
        }
        return false;
    }

    /*
    Resultado: Este método te muestra si hay habitaciones disponibles, también cuales hay; esta información se muestra por terminal
     */
    public void mostrarHabitacionesDisponibles(){
        //Informo de que estas son las habitaciones disponibles
        System.out.println("Las habitaciones disponibles son:");

        //Creo una variable que va a comprobar que existen habitaciones disponibles
        boolean existenHabitacionesDisponibles = false;
        //Compruebo que al menos existe una habitación disponible
        for(habitaciones[] planta:Hotel){
            //Del desglosado previo, voy sacando el número de cada habitación
            for(habitaciones habitacion:planta){
                if(habitacion.getestado()==Estado.DISPONIBLE){
                    existenHabitacionesDisponibles = true;
                }
            }
        }

        //A partir de lo anterior hecho, recorro o nó este array de hotel de nuevo para mostrar las habitaciones disponibles
        if(existenHabitacionesDisponibles){
            for(habitaciones[] planta:Hotel){
                //Del desglosado previo, voy sacando el número de cada habitación
                for(habitaciones habitacion:planta){
                    if(habitacion.getestado()==Estado.DISPONIBLE){
                        //Muestro la información necesaria de las habitaciones
                        System.out.println("-------------------------------------------");
                        System.out.println("La habitación: "+habitacion.getNumeroHabitacion());
                        System.out.println("-------------------------------------------");
                    }
                }
            }
        }else{
            //Muestro que no existen habitaciones disponibles
            System.out.println("-------------------------------------------");
            System.out.println("No existen habitaciones disponibles");
            System.out.println("-------------------------------------------");
        }
    }


    public boolean verificadorHabitaciónExiste(String habitacionConcreta){
        for(habitaciones[] planta:Hotel){
            //Del desglosado previo, voy sacando el número de cada habitación
            for(habitaciones habitacion:planta){
                if(habitacionConcreta.equals(habitacion.getNumeroHabitacion())){
                    return true;
                }
            }
        }

        //Devuelvo false en caso de que no exista dicha habitación
        return false;
    }

}
