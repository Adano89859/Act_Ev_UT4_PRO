package Gestor;

import Modelos.habitaciones;
import Modelos.clientes;
import Modelos.reservas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Gestor_Hotel {

    //Atributos de la clase Gestor_Hotel
    habitaciones[][]Hotel;
    clientes[] listaClientes;
    ArrayList<reservas> listaReservas;

    /*
    //CONSTRUCTOR de la clase Gestor_Hotel
     */
    public Gestor_Hotel(habitaciones[][]Hotel, clientes[] listaClientes, ArrayList<reservas> listaReservas) {
        this.Hotel = Hotel;
        this.listaClientes = listaClientes;
        this.listaReservas = listaReservas;
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
            //Llamo al método para mostrar las reservas que tiene este cliente
            buscarReservasActivasCliente(cliente);
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
            int habitacionReservar = teclado.nextInt();

            //Verifico que la habitación seleccionada existe
            if(verificadorHabitaciónExiste(habitacionReservar)){
                //Compruebo que la habitación seleccionada está disponible
                if(verificadorHabitaciónDisponible(habitacionReservar)){
                    //Le pregunto al usuario cuándo quiere iniciar su reserva
                    System.out.println("Introduce dentro de cuántos días quieres iniciar el check-in (Máximo en 90):");
                    int fechaCheckInEnInt = teclado.nextInt();

                    //Compruebo que la fecha de check in está en un rango válido
                    if(fechaCheckInEnInt<1){
                        System.out.println("ERROR: La fecha de check-in debe ser en al menos 1 día");
                    }else{
                        //Compruebo que no se excede del rango permitido
                        if(fechaCheckInEnInt>90){
                            System.out.println("ERROR: La fecha de check-in no debe superar el plazo de 90 días");
                        }else{
                            //Ahora que las comprobaciones se han realizado correctamente, podemos seguir trabajando
                            //Saco la fecha actual
                            LocalDateTime fechaActual = LocalDateTime.now();
                            //Sumo la fecha actual a dentro de cuanto será el check-in
                            LocalDateTime fechaCheckIn = fechaActual.plusDays(fechaCheckInEnInt);

                            //Pregunto cuántas noches será la reserva
                            System.out.println("¿Cuántas noches desea hospedarse?");
                            int fechaCheckOutEnInt = teclado.nextInt();

                            //Compruebo que la cantidad de noches no sea negativa
                            if(fechaCheckOutEnInt<1){
                                System.out.println("ERROR: No puede reservar menos de 1 noche");
                            }else{
                                //Creo la fecha de check-out
                                LocalDateTime fechaCheckOut = fechaCheckIn.plusDays(fechaCheckOutEnInt);

                                //Obtengo el precio total que tendrá la reserva en base a las noches y la habitación
                                habitaciones habitacionConcreta = obtenerHabitacionConcreta(habitacionReservar);
                                double precioNoches = (habitacionConcreta.getprecio_noche()*fechaCheckOutEnInt);

                                //Para obtenter el id de la reserva, extraigo cual debería ser ahora su posición en el array
                                int id = listaReservas.size();

                                //Cambio el estado de la habitación a reservado
                                cambiarEstadoHabitaciónAReservado(habitacionReservar);

                                //Meto la reserva en el listado de reservas y la creo
                                listaReservas.add(new reservas(id,obtenerHabitacionConcreta(habitacionReservar),obtenerClienteConcreto(persona), fechaCheckIn,fechaCheckOut,precioNoches));
                            }
                        }
                    }
                }

            }else{
                System.out.println("ERROR: La habitación que seleccionó no existe");
            }
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

    /*
    @param Pide el número de la habitación
    @return Devuelve true si la habitación existe, o false si la habitación no existe
    Resultado: Se le introduce un número que supuestamente es de una habitación, te devuelve true si existe, o false si no existe
     */
    public boolean verificadorHabitaciónExiste(int habitacionConcreta){
        for(habitaciones[] planta:Hotel){
            //Del desglosado previo, voy sacando el número de cada habitación
            for(habitaciones habitacion:planta){
                if(habitacionConcreta == habitacion.getNumeroHabitacion()){
                    return true;
                }
            }
        }
        //Devuelvo false en caso de que no exista dicha habitación
        return false;
    }

    /*
    @param Recoge el número de la habitación concreta
    @return Devuelve true si la habitación existe y está disponible, devuelve false si no está disponible o no existe
    Resultado: Este método pide el número de una habitación y te informa de si esa habitación existe y de si está disponible;
    según su resultado de true o false
     */
    public boolean verificadorHabitaciónDisponible(int habitacionConcreta){
        for(habitaciones[] planta:Hotel){
            //Del desglosado previo, voy sacando el número de cada habitación
            for(habitaciones habitacion:planta){
                //Filtro que sea la habitación de la que estamos tratando
                if(habitacionConcreta == habitacion.getNumeroHabitacion()){
                    //Compruebo que la habitación está disponible
                    if(habitacion.getestado() == Estado.DISPONIBLE){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /*
    @param Número de la habitación
    @return Objeto habitación
    Resultado: Este método requiere un número, y en base a ese, recorre el array del hotel para devolver la habitación concreta con ese número
     */
    public habitaciones obtenerHabitacionConcreta(int habitacionConcreta){
        for(habitaciones[] planta:Hotel){
            //Del desglosado previo, voy sacando el número de cada habitación
            for(habitaciones habitacion:planta){
                //Filtro que sea la habitación de la que estamos tratando
                if(habitacionConcreta == habitacion.getNumeroHabitacion()){
                    //Devuelvo el objeto habitación concreto que tiene el número indicado
                    return habitacion;
                }
            }
        }
        return null;
    }

    /*
    @param Nombre del cliente
    @return Objeto cliente
    Resultado: A partir del nombre del cliente introducido, devuelve el objeto cliente que tenga ese nombre
     */
    public clientes obtenerClienteConcreto(String clienteConcreto){
        //Recorro el listado de clientes existentes
        for(clientes cliente:listaClientes){
            //Filtro que estemos tratando con el cliente concreto
            if(clienteConcreto.equals(cliente.getNombre())){
                //Si es ese cliente, devolvemos el objeto cliente
                return cliente;
            }
        }
        return null;
    }

    /*
    Resultado: Este método te muestra la información de las reservas de un cliente
     */
    public void buscarReservasActivasCliente(clientes cliente){
        //Recorro el listado de reservas que existen
        for(reservas reserva:listaReservas){
            //Compruebo que la reserva tiene el mismo cliente que el cliente introducido
            if(reserva.getcliente()==cliente){
                //Doy la información sobre dicha reserva
                System.out.println("-------------------------------------------");
                System.out.println("La reserva de "+cliente.getNombre()+" tiene el id "+reserva.getId()+",");
                System.out.println("se reserva la habitación "+reserva.gethabitacion().getNumeroHabitacion()+" desde "+reserva.getcheck_in()+" hasta "+reserva.getcheck_out()+",");
                System.out.println("con un precio total de "+reserva.getprecio()+" euros.");
                System.out.println("-------------------------------------------");
            }
        }

    }

    /*
    Resultado: Este método muestra todas las reservas activas y su información
     */
    public void buscarReservasActivas(){
        //Compruebo que existan reservas
        if(verificadorExistenReservas()){
            //Recorro las reservas que existen
            for(reservas reserva:listaReservas){
                //Muestro la información de las reservas
                System.out.println("-------------------------------------------");
                System.out.println("La reserva con id "+reserva.getId()+" está a nombre de "+reserva.getcliente().getNombre()+",");
                System.out.println("se reserva la habitación "+reserva.gethabitacion().getNumeroHabitacion()+" desde "+reserva.getcheck_in()+" hasta "+reserva.getcheck_out()+",");
                System.out.println("con un precio total de "+reserva.getprecio()+" euros.");
                System.out.println("-------------------------------------------");
            }
        }else{
            //Informo de que no hay reservas en el sistema
            System.out.println("No existen reservas realizadas en el sistema");
        }
    }

    /*
    @return Booleano que dice si existen reservas
    Resultado: Método para verificar si existen reservas
     */
    public boolean verificadorExistenReservas(){
        //Uso el método "isEmpty()" para comprobar si está vacio
        if(listaReservas.isEmpty()){
            //Si el array de reservas está vacío, devuelvo false
            return false;
        }else{
            //Si el array no está vacío, devuelvo true
            return true;
        }
    }

    /*
    @param Trae el número de la habitación de la habitación concreta que va a ser alterada
    Resultado: Cambia el estado de la habitación seleccionada a "Reservado"
     */
    public void cambiarEstadoHabitaciónAReservado(int habitacionConcreta){
        for(habitaciones[] planta:Hotel){
            //Del desglosado previo, voy sacando el número de cada habitación
            for(habitaciones habitacion:planta){
                //Filtro que sea la habitación de la que estamos tratando
                if(habitacionConcreta == habitacion.getNumeroHabitacion()){
                    //Cambio el estado de la habitación a Reservado
                    habitacion.setestado(Estado.RESERVADA);
                }
            }
        }
    }

}
