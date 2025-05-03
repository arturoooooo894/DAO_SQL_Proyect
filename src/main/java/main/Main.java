package main;

import ClasesDAO.*;
import modelo.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final ClienteSQL clienteDAO = new ClienteSQL();
    private static final PaqueteTuristicoSQL paqueteDAO = new PaqueteTuristicoSQL();
    private static final ActividadSQL actividadDAO = new ActividadSQL();
    private static final ReservaSQL reservaDAO = new ReservaSQL();
    private static final ConsultasAvanzadasSQL consultasDAO = new ConsultasAvanzadasSQL();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> menuClientes();
                case 2 -> menuPaquetes();
                case 3 -> menuActividades();
                case 4 -> menuReservas();
                case 5 -> menuConsultasAvanzadas();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Gestión de Clientes");
        System.out.println("2. Gestión de Paquetes Turísticos");
        System.out.println("3. Gestión de Actividades");
        System.out.println("4. Gestión de Reservas");
        System.out.println("5. Consultas Avanzadas");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    // -------------------------------
    // SUBMENÚ CLIENTES
    // -------------------------------
    public static void menuClientes() {
        ClienteSQL clienteSQL = new ClienteSQL();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menú Clientes ---");
            System.out.println("1. Insertar cliente");
            System.out.println("2. Obtener cliente por ID");
            System.out.println("3. Listar todos los clientes");
            System.out.println("4. Actualizar cliente");
            System.out.println("5. Eliminar cliente");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> {
                    Cliente nuevo = new Cliente();
                    System.out.print("Nombre completo: ");
                    nuevo.setNombreCompleto(sc.nextLine());
                    System.out.print("Email: ");
                    nuevo.setEmail(sc.nextLine());
                    System.out.print("Teléfono: ");
                    nuevo.setTelefono(sc.nextLine());
                    clienteSQL.insertar(nuevo);
                    System.out.println("Cliente insertado.");
                }
                case 2 -> {
                    System.out.print("ID del cliente: ");
                    int id = sc.nextInt();
                    Cliente cliente = clienteSQL.obtenerPorId(id);
                    if (cliente != null) {
                        System.out.println(cliente);
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                }
                case 3 -> {
                    List<Cliente> lista = clienteSQL.obtenerTodos();
                    lista.forEach(System.out::println);
                }
                case 4 -> {
                    Cliente actualizar = new Cliente();
                    System.out.print("ID del cliente a actualizar: ");
                    actualizar.setId(sc.nextInt());
                    sc.nextLine(); // Limpiar buffer
                    System.out.print("Nuevo nombre completo: ");
                    actualizar.setNombreCompleto(sc.nextLine());
                    System.out.print("Nuevo email: ");
                    actualizar.setEmail(sc.nextLine());
                    System.out.print("Nuevo teléfono: ");
                    actualizar.setTelefono(sc.nextLine());
                    clienteSQL.actualizar(actualizar);
                    System.out.println("Cliente actualizado.");
                }
                case 5 -> {
                    System.out.print("ID del cliente a eliminar: ");
                    int id = sc.nextInt();
                    clienteSQL.eliminar(id);
                    System.out.println("Cliente eliminado.");
                }
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    // -------------------------------
    // SUBMENÚ PAQUETES
    // -------------------------------
    public static void menuPaquetes() {
        PaqueteTuristicoSQL dao = new PaqueteTuristicoSQL();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Gestión de Paquetes Turísticos ---");
            System.out.println("1. Crear paquete");
            System.out.println("2. Obtener paquete por ID");
            System.out.println("3. Mostrar todos los paquetes");
            System.out.println("4. Actualizar paquete");
            System.out.println("5. Eliminar paquete");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Destino: ");
                    String destino = scanner.nextLine();
                    System.out.print("Precio: ");
                    double precio = scanner.nextDouble();
                    System.out.print("Duración en días: ");
                    int duracion = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer

                    PaqueteTuristico nuevo = new PaqueteTuristico(0, nombre, destino, precio, duracion);
                    dao.insertar(nuevo);
                    System.out.println("Paquete creado.");
                    break;

                case 2:
                    System.out.print("ID del paquete: ");
                    int idBuscar = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer
                    PaqueteTuristico buscado = dao.obtenerPorId(idBuscar);
                    if (buscado != null) {
                        System.out.println(buscado);
                    } else {
                        System.out.println("No se encontró el paquete.");
                    }
                    break;

                case 3:
                    List<PaqueteTuristico> paquetes = dao.obtenerTodos();
                    paquetes.forEach(System.out::println);
                    break;

                case 4:
                    System.out.print("ID del paquete a actualizar: ");
                    int idActualizar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = scanner.nextLine();
                    System.out.print("Nuevo destino: ");
                    String nuevoDestino = scanner.nextLine();
                    System.out.print("Nuevo precio: ");
                    double nuevoPrecio = scanner.nextDouble();
                    System.out.print("Nueva duración: ");
                    int nuevaDuracion = scanner.nextInt();
                    scanner.nextLine();

                    PaqueteTuristico actualizado = new PaqueteTuristico(idActualizar, nuevoNombre, nuevoDestino, nuevoPrecio, nuevaDuracion);
                    dao.actualizar(actualizado);
                    System.out.println("Paquete actualizado.");
                    break;

                case 5:
                    System.out.print("ID del paquete a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    dao.eliminar(idEliminar);
                    System.out.println("Paquete eliminado.");
                    break;

                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 0);
    }


    // -------------------------------
    // SUBMENÚ ACTIVIDADES
    // -------------------------------
    public static void menuActividades() {
        ActividadSQL dao = new ActividadSQL();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Gestión de Actividades ---");
            System.out.println("1. Crear actividad");
            System.out.println("2. Obtener actividad por ID");
            System.out.println("3. Mostrar todas las actividades");
            System.out.println("4. Obtener actividades por ID de paquete");
            System.out.println("5. Actualizar actividad");
            System.out.println("6. Eliminar actividad");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("ID del paquete: ");
                    int idPaquete = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nombre de la actividad: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Descripción: ");
                    String descripcion = scanner.nextLine();
                    System.out.print("Costo adicional: ");
                    double costo = scanner.nextDouble();
                    scanner.nextLine();

                    Actividad nueva = new Actividad(0, idPaquete, nombre, descripcion, costo);
                    dao.insertar(nueva);
                    System.out.println("Actividad creada.");
                    break;

                case 2:
                    System.out.print("ID de la actividad: ");
                    int idBuscar = scanner.nextInt();
                    scanner.nextLine();
                    Actividad buscada = dao.obtenerPorId(idBuscar);
                    if (buscada != null) {
                        System.out.println(buscada);
                    } else {
                        System.out.println("No se encontró la actividad.");
                    }
                    break;

                case 3:
                    List<Actividad> actividades = dao.obtenerTodas();
                    actividades.forEach(System.out::println);
                    break;

                case 4:
                    System.out.print("ID del paquete: ");
                    int idPaqueteBuscar = scanner.nextInt();
                    scanner.nextLine();
                    List<Actividad> actividadesPorPaquete = dao.obtenerPorPaquete(idPaqueteBuscar);
                    actividadesPorPaquete.forEach(System.out::println);
                    break;

                case 5:
                    System.out.print("ID de la actividad a actualizar: ");
                    int idActualizar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nuevo ID de paquete: ");
                    int nuevoIdPaquete = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = scanner.nextLine();
                    System.out.print("Nueva descripción: ");
                    String nuevaDescripcion = scanner.nextLine();
                    System.out.print("Nuevo costo adicional: ");
                    double nuevoCosto = scanner.nextDouble();
                    scanner.nextLine();

                    Actividad actualizada = new Actividad(idActualizar, nuevoIdPaquete, nuevoNombre, nuevaDescripcion, nuevoCosto);
                    dao.actualizar(actualizada);
                    System.out.println("Actividad actualizada.");
                    break;

                case 6:
                    System.out.print("ID de la actividad a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    scanner.nextLine();
                    dao.eliminar(idEliminar);
                    System.out.println("Actividad eliminada.");
                    break;

                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 0);
    }




    // -------------------------------
    // SUBMENÚ RESERVAS
    // -------------------------------
    public static void menuReservas() {
        ReservaSQL dao = new ReservaSQL();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Gestión de Reservas ---");
            System.out.println("1. Crear reserva");
            System.out.println("2. Obtener reserva por ID");
            System.out.println("3. Mostrar todas las reservas");
            System.out.println("4. Actualizar reserva");
            System.out.println("5. Eliminar reserva");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("ID del cliente: ");
                    int idCliente = scanner.nextInt();
                    System.out.print("ID del paquete: ");
                    int idPaquete = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer

                    System.out.print("Fecha de reserva (YYYY-MM-DD): ");
                    String fechaStr = scanner.nextLine();
                    LocalDate fecha = LocalDate.parse(fechaStr);  // Convertir String a LocalDate

                    System.out.print("Estado de la reserva (PENDIENTE, CONFIRMADA, CANCELADA): ");
                    String estado = scanner.nextLine();

                    Reserva nueva = new Reserva(0, idCliente, idPaquete, fecha, estado);
                    dao.insertar(nueva);
                    System.out.println("Reserva creada.");
                    break;


                case 2:
                    System.out.print("ID de la reserva: ");
                    int idBuscar = scanner.nextInt();
                    scanner.nextLine();
                    Reserva buscada = dao.obtenerPorId(idBuscar);
                    if (buscada != null) {
                        System.out.println(buscada);
                    } else {
                        System.out.println("No se encontró la reserva.");
                    }
                    break;

                case 3:
                    List<Reserva> reservas = dao.obtenerTodas();
                    reservas.forEach(System.out::println);
                    break;

                case 4:
                    System.out.print("ID de la reserva a actualizar: ");
                    int idActualizar = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer

                    System.out.print("Nuevo ID del cliente: ");
                    int nuevoIdCliente = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nuevo ID del paquete: ");
                    int nuevoIdPaquete = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nueva fecha de reserva (YYYY-MM-DD): ");
                    String fechaString = scanner.nextLine();
                    LocalDate nuevaFecha = LocalDate.parse(fechaString);  // Conversión a LocalDate

                    System.out.print("Nuevo estado (PENDIENTE, CONFIRMADA, CANCELADA): ");
                    String nuevoEstado = scanner.nextLine();

                    Reserva actualizada = new Reserva(idActualizar, nuevoIdCliente, nuevoIdPaquete, nuevaFecha, nuevoEstado);
                    dao.actualizar(actualizada);
                    System.out.println("Reserva actualizada.");
                    break;

                case 5:
                    System.out.print("ID de la reserva a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    scanner.nextLine();
                    dao.eliminar(idEliminar);
                    System.out.println("Reserva eliminada.");
                    break;

                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 0);
    }

    // -------------------------------
    // SUBMENÚ CONSULTAS AVANZADAS
    // -------------------------------
    private static void menuConsultasAvanzadas() {
        int opcion;
        do {
            System.out.println("\n-- Consultas Avanzadas --");
            System.out.println("1. Mostrar actividades por paquete");
            System.out.println("2. Mostrar reservas por cliente");
            System.out.println("3. Ranking destinos más reservados");
            System.out.println("4. Clientes con múltiples reservas");
            System.out.println("0. Volver al menú principal");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> {
                    System.out.print("ID del paquete: ");
                    int id = Integer.parseInt(sc.nextLine());
                    consultasDAO.obtenerActividadesPorPaquete(id).forEach(System.out::println);
                }
                case 2 -> {
                    System.out.print("ID del cliente: ");
                    int id = Integer.parseInt(sc.nextLine());
                    consultasDAO.obtenerReservasPorCliente(id).forEach(System.out::println);
                }
                case 3 -> {
                    System.out.println("=== Ranking de Destinos ===");
                    consultasDAO.rankingDestinosMasReservados().forEach(System.out::println);
                }
                case 4 -> {
                    System.out.println("=== Clientes con múltiples reservas ===");
                    consultasDAO.clientesConMasDeUnaReserva().forEach(System.out::println);
                }
            }
        } while (opcion != 0);
    }
}
