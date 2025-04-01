package Gestor;

import Modelos.habitaciones;
import Modelos.clientes;
import Modelos.reservas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.temporal.ChronoUnit;

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

        try {
        
        //Recojo el supuesto usuario que va a realizar la reserva
        System.out.println("Decidió crear una reserva ¿Quién es el interesado en hacer la reserva?");
        String persona = teclado.nextLine();

        //Compruebo que existe el usuario
        if(verificadorClienteExiste(persona)){
            //Añado la restricción de que el cliente solo puede tener tres reservas
            if(verificadorClienteTieneMenosDe3Reservas(persona)){
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

                                    //Introduzco esta reserva como un elemento del historial del Cliente
                                    String historial_estaReserva_usuario = "El id de esta reserva es "+String.valueOf(id)+", la habitación reservada es "+habitacionConcreta.getNumeroHabitacion()+", con la fecha de check-in "+fechaCheckIn+" y la fecha check-out" +fechaCheckOut+ ",a un precio total de "+precioNoches+" euros.";
                                    introducirHistorialCliente(persona,historial_estaReserva_usuario);

                                    //Meto la reserva en el listado de reservas y la creo
                                    listaReservas.add(new reservas(id,obtenerHabitacionConcreta(habitacionReservar),obtenerClienteConcreto(persona), fechaCheckIn,fechaCheckOut,precioNoches));
                                }
                            }
                        }
                    }else{
                        System.out.println("ERROR: La habitación que seleccionó no está disponible");
                    }
                }else{
                    System.out.println("ERROR: La habitación que seleccionó no existe");
                }
            }else{
                System.out.println("ERROR: El usuario ya excedió el límite de reservas, 3.");
            }
        }else{
            System.out.println("ERROR: La persona que seleccionó no existe");
        }
        } catch (ReservaNoDisponibleException e) {
            System.out.println(e.getMessage());
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


    public void buscarHabitaciones(){

        Scanner teclado = new Scanner(System.in);
        // Menú mostrando los tipos de habitaciones
        System.out.println("Tipos de habitaciones: ");
        System.out.println("1. Habitación individual: (Estándar para 1 persona). ");
        System.out.println("2. Habitación dual: (Estándar para 2 personas). ");
        System.out.println("3. Habitación suite: (Ideal para colectivos y familias numerosas). ");

        // Pide al usuario que ingrese el numero del tipo de habitación
        System.out.println("Escribe el numero del tipo de habitación a buscar: ");
        int option = teclado.nextInt();

        //Mostramos las opciones que cumplen lo solicitado
        mostrarHabitacionesFiltradas(option);
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
     * @param Nombre del cliente, Id de la reserva
     * @return Objeto Cliente, Objeto Reserva (Eliminar)
     * Resultado: El cliente seleccionado eliminara la reserva registrada mediante su Id (codigo de reserva)
     */
    public void cancelarReservaPorCliente() throws ClienteNoEncontradoException {
        //Inicializo 2 teclados, porque me está dando errores
        Scanner teclado = new Scanner(System.in);
        Scanner teclado2 = new Scanner(System.in);
        try {
            
        // Pedimos por teclado el nombre del cliente que desea eiminar la reserva
        System.out.println("Seleccione el cliente que quiere cancelar la reserva: ");
        String nombreCliente = teclado.nextLine();

        // Obtenemos de la clase cliente el cliente seleccionado llamando al metodo obtenerClienteConcreto
        clientes clienteSeleccionado = obtenerClienteConcreto(nombreCliente);

        /*
         * Evaluamos si el cliente existe y está registrado en el sistema
         * Preguntamos si el cliente de verdad va a cancelar la reserva antes de continuar
         * Evaluamos la acción según la desición del cliente (si/no)
         */
        if (clienteSeleccionado != null) {
            System.out.println("El cliente "+ clienteSeleccionado.getNombre() + "ha sido seleccionado.");

            //Le mostramos a este usuario las reservas que posee, para que pueda elegir
            buscarReservasActivasCliente(clienteSeleccionado);

            //Pedimos por teclado que introduzca el código de su reserva
            System.out.println("Introduzca el id de su reserva a cancelar ");
            int idReserva = teclado.nextInt();

            //Preguntamos si quiere confirmar que quiere cancelar la reserva
            System.out.println("¿Desea cancelar la reseva (s/n)?");
            String option = teclado2.nextLine();

            // Acciones si la desición del cliente es (s) "si"
            if (option.equals("s")) {

                
                //Recorro el listado de reservas exitente
                for (reservas Reserva : listaReservas) {
                    //Comprobamos que la reserva pertenece al cliente
                    if(Reserva.getcliente().getNombre().equals(nombreCliente)){

                        // Filtramos la fecha de la reserva
                        LocalDateTime fechaReserva = LocalDateTime.now();
                        // Comprobamos si la reserva aún no ha comenzado
                        // Devuelve false si no existe o si ya ha comenzado la reserva
                        if (Reserva.getId() == idReserva) {
                            if (Reserva.getcheck_in().isBefore(fechaReserva)) {
                                System.out.println("Error: la reserva ya ha comenzado " + Reserva.getcheck_in());
                            } else {
                                //Cambio el estado de la habitación original a DISPONIBLE
                                cambiarEstadoHabitaciónADisponible(Reserva.gethabitacion().getNumeroHabitacion());

                                // Elimina la reserva
                                listaReservas.remove(Reserva);
                                System.out.println("Reserva eliminada correctamente ");
                                return;

                            }
                        }

                        System.out.println("Error: no se ha encontrado una reserva con el id: " + idReserva);
                        return;

                    }


                }
            } else if (option.equals("n")) {
                // Acciones si la desición del cliente es (n) "no"
                System.out.println("Cancelando operación...");
                return;
            }else{
                //Informo si introdujo una opción no válida
                System.out.println("Opción no válida: Cancelando operación...");
                return;
            }

        } else {
            
            System.out.println("Lo sentimos, no se ha encontrado un cliente con ese nombre");
        }

        } catch (ClienteNoEncontradoException e) {
            System.out.println(e.getMessage());
        } 

        teclado.close();
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

    /*
    @param Trae el número de la habitación de la habitación concreta que va a ser alterada
    Resultado: Cambia el estado de la habitación seleccionada a "DISPONIBLE"
     */
    public void cambiarEstadoHabitaciónADisponible(int habitacionConcreta){
        for(habitaciones[] planta:Hotel){
            //Del desglosado previo, voy sacando el número de cada habitación
            for(habitaciones habitacion:planta){
                //Filtro que sea la habitación de la que estamos tratando
                if(habitacionConcreta == habitacion.getNumeroHabitacion()){
                    //Cambio el estado de la habitación a Reservado
                    habitacion.setestado(Estado.DISPONIBLE);
                }
            }
        }
    }

    /*
    @param Pide el nombre del usuario que va a ser modificado y el historial que se le va a introducir
    Resultado: Introduce el historial de la reserva en el cliente seleccionado
     */
    public void introducirHistorialCliente(String persona, String historial){
        for(clientes cliente:listaClientes){
            //Filtro que estemos tratando con el cliente concreto
            if(persona.equals(cliente.getNombre())){
                //Si es ese cliente, le introducimos el historial
                cliente.setHistorial(historial);
            }
        }
    }

    /*
    param Pido el objeto cliente del que se va a tratar
    Resultado: A partir del objeto cliente solicitado, devuelvo la información de su arrayList de historial
     */
    public void buscarHistorialCliente(clientes cliente){
        //Muestro el historial del cliente
        System.out.println("El historial de "+cliente.getNombre()+" es:");
        System.out.println(cliente.getHistorial());
    }

    /*
    @param pide el nombre del cliente
    @return Devuelve true si el cliente tiene menos de 3 reservas, devuelve false si el cliente tiene 3 o más reservas
    Resultado: Este método comprueba que el cliente tenga menos de tres reservas , o en su defecto, tenga tres o más
     */
    public boolean verificadorClienteTieneMenosDe3Reservas(String cliente){
        //Creo una variable que va a contar la cantidad de reservas que tiene el cliente
        int cantidadReservasTieneCliente=0;
        //Recorro el array de reservas
        for(reservas reserva:listaReservas){
            //Filtro que la reserva pertenezca al usuario seleccionado
            if(reserva.getcliente().getNombre().equals(cliente)){
                //Si la reserva est´ña a nombre del cliente, añado 1 a las reservas que son del cliente
                cantidadReservasTieneCliente++;
            }
        }
        //Si el cliente tiene 3 reservas o más, devuelvo false
        if(cantidadReservasTieneCliente>=3){
            return false;
        }
        //Si el cliente tiene menos de 3 reservas, devuelvo true
        return true;
    }


     /*
     *@Param: Solicitamos el objeto cliente y el objeto reserva
     * Resultado: el cliente selecciona su reserva en base al tipo de habitación y
     * calculamos el precio de la habitación por el numero de noches hospedadas 
     */

    public void calcularPrecioHabitacion(){
        Scanner teclado = new Scanner(System.in);

        // Pedimos por teclado el nombre del cliente que desea calcular la reserva
        System.out.println("Seleccione el cliente que quiere calcular la reserva: ");
        String nombreCliente = teclado.nextLine();
 
        // Obtenemos de la clase cliente el cliente seleccionado llamando al metodo obtenerClienteConcreto
        clientes clienteSeleccionado = obtenerClienteConcreto(nombreCliente);

        /*
         * Evaluamos si el cliente existe y está registrado en el sistema
         * Preguntamos si el cliente de verdad quiere calcular el precio de su reserva antes de continuar
         * Evaluamos la acción según la desición del cliente (si/no)
         */

        if (clienteSeleccionado != null) {
            System.out.println("El cliente "+ clienteSeleccionado.getNombre() + "ha sido seleccionado.");
            System.out.println("El " + clienteSeleccionado.getNombre() +": ¿Desea calcular la reseva e imprimir ticket (s/n)?");
            String option = teclado.nextLine();

            // Acciones si la desición del cliente es (s) "si"
            if (option.equals("s")) {

                // Solicitamos el id de la reserva y la buscamos en la linta de reservas
                System.out.println("Introduzca el código de su reserva: ");
                int reservaID = teclado.nextInt();

                for (reservas Reserva : listaReservas) {
                    // Verificamos que la reserva existe
                    if (Reserva.getId() == reservaID) {
                        // listamos las reservas que tiene disponible el cliente
                        System.out.println("|--Listando las reservas disponibles--| ");
                        System.out.println("ID: " + Reserva.getId());
                        System.out.println("Habitación: " + Reserva.gethabitacion());
                        System.out.println("Precio por noche inicial: " + Reserva.getprecio());
                        System.out.println("|------------------------------------|");


                        // Inicializo los parametros necesarios para calcular el precio de la habitación
                        // por noche según su tipo
                        double precioPorNoche = 0;
                        String tipoHabitacion = " ";

                        /*
                        * Solicitamos por teclado que seleccione un tipo de habitación
                        * caso1: selecciona una suite
                        * caso2: selecciona una habitación dual
                        * caso3: selecciona una habitación individual 
                        */
                        System.out.println("Seleccione una habitación: ");
                        String desition = teclado.nextLine();

                        switch (desition) {
                            case "SUITE":
                            System.out.println("Selección de habitaciones suite");
                                precioPorNoche = 3000;
                                tipoHabitacion = "HABITACIÓN SUITE";
                                break;
                            case "DOBLE":
                            System.out.println("Selección de habitaciones estándar doble");
                                precioPorNoche = 1000;
                                tipoHabitacion = "HABITACIÓN DOBLE";
                                break;
                            case "Individual":
                            System.out.println("Selección de habitaciones estándar individuales");
                                precioPorNoche = 500;
                                tipoHabitacion = "HABITACIÓN INDIVIDUAL";
                                break;
                        
                            default:
                            System.out.println("Error: opcion no valida, intentelo de nuevo.");
                                return;
                        }
                    
                        /*
                        * Utilizamos la librería ChronoUnit para optimizar el plazo que dure la reserva
                        * es decir, el número de moches que dure la reserva para calcular el precio, entre
                        * su fecha de inicio y fecha de fin del plazo de la reserva 
                        */
                        long noches = ChronoUnit.DAYS.between(
                        Reserva.getcheck_in().toLocalDate(),
                        Reserva.getcheck_out().toLocalDate());
                        
                        // Calculamos el precio total de la habitación por el número de noches hospedadas
                        double totalPrecio = noches * precioPorNoche;

                        // Imprimimos los resultados con la información de la habitación con su precio estándar
                        // y su precio total por noches.
                        System.out.println(" |--RESUMEN DE LA RESERVA: --| ");
                        System.out.println("Tipo de reserva: " + tipoHabitacion);
                        System.out.println("Noches de la reserva " + noches);
                        System.out.println("Precio de reserva/noche: " + precioPorNoche + "€");
                        System.out.println("Subtotal: " + totalPrecio + "€");
                        System.out.println(" |--------------------------|");

                    } else {
                        // Devuelve false si la reserva no existe o no se ha registrado en el sistema 
                        System.out.println("ERROR: la reserva no existe o no se ha registrado correctamente.");
                    }
                    return;
                }

            } else {
                // Acciones si la desición del cliente es (n) "no"
                System.out.println("Cancelando operación...");
                return;
            }
            
        } else {
            // Devuelve false si el cliente no está registrado en el sistema o no existe
            System.out.println("ERROR: Lo sentimos, no hay registrado ningún cliente con ese nombre");
            return;
        } 
        teclado.close();
    }

    /*
    @param Pide la selección filtradora de la categoría
    Resultado: Muestra las habitaciones de esa categoría
     */
    public void mostrarHabitacionesFiltradas(int numeroFiltradorTipo){
        //Muestro las habitaciones según el tipo especificado
        switch (numeroFiltradorTipo){
            case 1:
                //Adquiero solo esa planta
                habitaciones[] planta1 = Hotel[0];
                    for(habitaciones habitacion:planta1){
                        //Muestro las habitaciones de esa planta
                        System.out.println(" |--------------------------|");
                        System.out.println("Número de habitación: " + habitacion.getNumeroHabitacion());
                        System.out.println("Tipo de habitación: " + habitacion.gettipoHabitacion());
                        System.out.println("Estado de habitación: " + habitacion.getestado());
                        System.out.println("Descripción de la habitación: " + habitacion.getdescripcion());
                        System.out.println("Precio por noche: " + habitacion.getprecio_noche());
                        System.out.println(" |--------------------------|");
                    }
                break;
            case 2:
                //Adquiero solo esa planta
                habitaciones[] planta2 = Hotel[1];
                for(habitaciones habitacion:planta2){
                    //Muestro las habitaciones de esa planta
                    System.out.println(" |--------------------------|");
                    System.out.println("Número de habitación: " + habitacion.getNumeroHabitacion());
                    System.out.println("Tipo de habitación: " + habitacion.gettipoHabitacion());
                    System.out.println("Estado de habitación: " + habitacion.getestado());
                    System.out.println("Descripción de la habitación: " + habitacion.getdescripcion());
                    System.out.println("Precio por noche: " + habitacion.getprecio_noche());
                    System.out.println(" |--------------------------|");
                }
                break;
            case 3:
                //Adquiero solo esa planta
                habitaciones[] planta3 = Hotel[2];
                for(habitaciones habitacion:planta3){
                    //Muestro las habitaciones de esa planta
                    System.out.println(" |--------------------------|");
                    System.out.println("Número de habitación: " + habitacion.getNumeroHabitacion());
                    System.out.println("Tipo de habitación: " + habitacion.gettipoHabitacion());
                    System.out.println("Estado de habitación: " + habitacion.getestado());
                    System.out.println("Descripción de la habitación: " + habitacion.getdescripcion());
                    System.out.println("Precio por noche: " + habitacion.getprecio_noche());
                    System.out.println(" |--------------------------|");
                }
                break;
            default:
                System.out.println("No seleccionó una opción válida");
                break;
        }
    }

}
