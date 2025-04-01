# ACTIVIDAD EVALUATIVA DE LA UT4 DE PROGRAMACIÓN: SISTEMA DE GESTIÓN DE HOTELES

# INTRODUCCIÓN:
Esta actividad consiste en desarrollar un sistema de gestión de hoteles funcional, que 
consiste en gestionar el complejo por plantas y habitaciones diferentes, gestionar las reservas y los clientes con sus respectivas habitaciones reservadas, todas las habitaciones tienen un tipo adaptado al tipo de cliente que podrá escoger entre una suite, habitación dual o una individual de carácter estándar, el estado de las habitaciones puede variar entre disponible, (libre), reservada(en espera) u ocupada (reserva en acción).

Para gestionar la modularidad del código, se ha optado por seguir el esquema del MVC (Modelo vista controlador) donde se recogen todas las partes del código y se interconectan entre sí entre llamadas a los métodos de creación, separando la parte de funcionalidad de la parte del diseño de la aplicación.

# PARTES DE LA APLICACIÓN (MODELO MVC):

# VISTA:
Es la parte donde se recoge mayormente el diseño de la aplicación y la parte interactiva, donde se crea una visualización de la aplicación que mejora la experiencia de los usuarios, una vez implementada, se llamará a sus corriespondientes métodos a otras partes de la aplicación implementando en la sección precisa la llamada donde se quiere implementar la sección correspondiente de la vista.

# MODELO:
Es una parte funcional de la aplicación que se encarga de crear los objetos base que interactuarán con el sistema de gestión, para este caso, hemos implementado 3 modelos diferentes que hacen referencia a 3 objetos distintos, el modelo de clientes (Usuarios), donde se recogen atributos como (el código identificativo del cliente, nombre e historial dentro del hotel). El modelo de las habitaciones del hotel donde se atrubuye (numero de habitación, tipo, precio, estado y descripción), y por último, el modelo de reservas encargadas por los clientes donde se recogen (el id de reserva, la habitación reservada, el cliente que la reserva, el precio de la reserva y las fechas de plazo comprendidas de la reserva).

# CONTROLADOR:
Es la parte más funcional de la aplicación ya que es donde se recoge toda la gestión del hotel y la interacción de los objetos externos creados en la vista y el modelo, para implementar esta pate, hemos recurrido mayormente a estructuras condicionales para evaluar, verificar y comparar si las condiciones implementadas se cumplen de correctamente, por otra parte, hemos implementado métodos y estructuras de filtrado para obtener un objeto concreto dentro de una clase concreta y relacionarlo con otro objeto concreto que puede ser de otra clase concreta. Ej: un cliente concreto llamado (Josué) tiene una reserva en una habitación concreta (habitación 304).

La llamada de los métodos se hará desde el Main o (parte ejecutable del programa), por otra parte, dentro del propio gestor hemos aprovechado la implementación de unos métodos para complementar otros, mediante llamadas, aprovechando la funcionalidad de cada parte y ahorrando espacio de memoria.

Dentro del controlador tenemos 3 clases (EL gestor) donde se lleva toda la lógica de la aplicación, (La clase tipo de habitacion) donde declaramos el tipo de habitación y la clase (Estado) donde se declara y controla cada tipo de estado que tiene una habitación.

# APP (MAIN):
Es la parte ejecutable de la aplicación y la más interactiva, se encuentra siempre del lado del cliente, y es donde se acciona el programa, está repleta de llamadas a los métodos de accón ya implementados en el gestor y donde se crean foncionalmente los objetos para los usuarios.

En esta parte diseñamos un menú principal donde el usuario podra escoger hasta 8 opciones, las opciones son las siguientes: 

# 1. Crear reserva.
# 2. Cancelar reserva.
# 3. Buscar habitación.
# 4. Buscar reservas activas.
# 5. Resumen de las habitaciones.
# 6. Resumen de los clientes.
# 7. Calcular el precio total de una reserva.
# 8. Salir de la app.

Cada una de las opciones lleva a realizar acciones diferntes, algunas de ellas llevan a condiciones preguntando si desea continuar, otras, llevan a pequeños submenús indicando concretamente la acción que quiere realizar y sobre que objeto quiere o puede realizarla, cada acción retornará al menú principal hasta que el usuario cierre sesión en el programa.

# CREADO POR: Adán Romero y Kevin Raseg ©2025. @1ºB-DAM