import java.util.InputMismatchException;
import java.util.Scanner;

import javax.xml.catalog.Catalog;

public class Aplicacion {

    public static void menuPrincipal() {
        System.out.println("Elige una opción");
        System.out.println("------------------------");
        System.out.println("1. Registrar cliente");
        System.out.println("2. Reservar sala y mesa");
        System.out.println("3. Cancelar reserva");
        System.out.println("4. Pedir la cuenta");
        System.out.println("5. Salir");
        System.out.println("------------------------");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre del restaurante: ");

        String nombreRestaurante = sc.nextLine();
        Restaurante restaurante = new Restaurante(nombreRestaurante);

        boolean salir = false;
        int opc = 0;
        String dniReserva;
        Cliente cliente = null;
        String sala;
        Sala salaSeleccionada = null;
        int fila;
        char columna;
        Mesa[][] mesas;
        // Clientes de prueba
        Cliente c1 = new Cliente("A", "1", 68035345);
        Cliente c2 = new Cliente("bC", "22", 68035345);
        Cliente c3 = new Cliente("V", "111", 68035345);
        Cliente c4 = new Cliente("F", "222", 68035345);
        ClienteVegano c11 = new ClienteVegano("AA", "2", 123123123);
        ClienteVegano c12 = new ClienteVegano("AB", "12312ASDA31231231", 123123123);
        ClienteVegano c13 = new ClienteVegano("AC", "123SAD1231231231", 123123123);
        ClienteVegano c14 = new ClienteVegano("AD", "123QWE1231231231", 123123123);
        ClienteVegano c15 = new ClienteVegano("AE", "12312ASD31231231", 123123123);

        restaurante.anyadirCliente(c2);
        restaurante.anyadirCliente(c1);
        restaurante.anyadirCliente(c15);
        restaurante.anyadirCliente(c11);
        restaurante.anyadirCliente(c13);
        restaurante.anyadirCliente(c12);
        restaurante.anyadirCliente(c3);
        restaurante.anyadirCliente(c14);
        restaurante.anyadirCliente(c4);

        do {
            menuPrincipal();
            try {
                opc = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Introduce un valor numérico\n");
                opc = 0;
            }
            switch (opc) {
                case 1:
                    String nombre = "";
                    String dni = "";
                    int telefono = 0;
                    try {
                        System.out.println("Nombre y apellidos del cliente:");
                        nombre = sc.nextLine();
                        System.out.println("DNI del cliente:");
                        dni = sc.nextLine();
                        System.out.println("Teléfono de contacto:");
                        telefono = sc.nextInt();
                        sc.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Introduce un valor numérico\n");
                        sc.nextLine();
                        opc = 0;
                        break;
                    }

                    Cliente clienteNuevo = new Cliente(nombre, dni, telefono);
                    try {
                        if (restaurante.compararDni(clienteNuevo)) {
                            throw new ReservaException("Ya existe un cliente con este DNI.\nOperación cancelada.");
                        }
                    } catch (ReservaException e) {
                        System.out.println(e.getMessage());
                        opc = 0;
                        break;
                    }
                    System.out.println("¿Es vegano? (s/n)");
                    if (sc.nextLine().equalsIgnoreCase("s")) {
                        clienteNuevo = new ClienteVegano(nombre, dni, telefono);
                        restaurante.inscribirClienteVegano((ClienteVegano) clienteNuevo);
                    }

                    // añadimos cliente
                    restaurante.anyadirCliente(clienteNuevo);
                    restaurante.ordenarClientes();
                    break;

                case 2:
                    System.out.println("Selecciona un cliente por DNI");
                    restaurante.mostrarClientes();
                    dniReserva = sc.nextLine();
                    cliente = null;
                    try {
                        cliente = restaurante.buscarCliente(dniReserva);
                        cliente.tieneMesa();
                    } catch (ReservaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("Elige sala (Normal / Premium)");
                    sala = sc.nextLine().toUpperCase();
                    sala = sala.isEmpty() ? " " : sala;

                    salaSeleccionada = null;
                    try {

                        switch (sala) {
                            case "NORMAL":
                                salaSeleccionada = restaurante.getSalaNormal();
                                break;

                            case "PREMIUM":
                                salaSeleccionada = restaurante.getSalaPremium();
                                break;
                            default:
                                System.out.println("La sala " + sala + " no existe.\n"
                                        + "Selecciona entre sala Normal y Premium");
                                opc = 0;
                                throw new Exception("Error al elegir la sala");
                        }
                    } catch (Exception e) {
                        break;
                    }

                    if (!cliente.puedeElegirMesa()) {
                        try {
                            Mesa mesaSeleccionada = salaSeleccionada.buscarMesa();
                            mesaSeleccionada.anyadirComensal(cliente);
                        } catch (ReservaException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        System.out.println("Mesa asignada aleatoriamente en la sala " + sala.toLowerCase() + ".");
                    } else {

                        salaSeleccionada.mostrarMesas();
                        System.out.println("\nElige una mesa: ");
                        System.out.print("\nFila: ");
                        try {
                            fila = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Columna: ");
                            columna = sc.nextLine().toUpperCase().charAt(0);
                        } catch (InputMismatchException e) {
                            System.out.println("Error de introducción de parámetros.");
                            break;
                        }

                        mesas = salaSeleccionada.getMesas();
                        try {
                            mesas[fila][columna - 'A'].anyadirComensal(cliente);
                        } catch (ReservaException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                    }
                    break;

                case 3:
                    System.out.println("Selecciona un cliente por DNI");
                    restaurante.mostrarClientes();
                    dniReserva = sc.nextLine();
                    cliente = null;
                    try {
                        cliente = restaurante.buscarCliente(dniReserva);
                    } catch (ReservaException e) {
                        System.out.println(e.getMessage());
                    }
                    try {
                        cliente.getMesaAsignada().cancelarReserva(cliente);
                    } catch (ReservaException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error inesperado" + e.getMessage());
                    }
                    System.out.println("Reserva cancelada.");
                    break;

                case 4:
                    System.out.println("Selecciona la sala de reserva (Normal/Premium)");
                    sala = sc.nextLine().toUpperCase();
                    sala = sala.isEmpty() ? " " : sala;

                    salaSeleccionada = null;
                    try {

                        switch (sala) {
                            case "NORMAL":
                                salaSeleccionada = restaurante.getSalaNormal();
                                break;

                            case "PREMIUM":
                                salaSeleccionada = restaurante.getSalaPremium();
                                break;
                            default:
                                System.out.println("La sala " + sala + " no existe.\n"
                                        + "Selecciona entre sala Normal y Premium");
                                opc = 0;
                                throw new Exception("Error al elegir la sala");
                        }
                    } catch (Exception e) {
                        break;
                    }

                    salaSeleccionada.mostrarMesas();
                    System.out.println("\nElige una mesa: ");
                    System.out.print("\nFila: ");
                    try {
                        fila = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Columna: ");
                        columna = sc.nextLine().toUpperCase().charAt(0);
                    } catch (InputMismatchException e) {
                        System.out.println("Error de introducción de parámetros.");
                        break;
                    }

                    mesas = salaSeleccionada.getMesas();
                    try {
                        double totalCuenta = mesas[fila][columna - 'A'].cuenta(salaSeleccionada);
                        System.out.println("Precio total: " + totalCuenta + " euros.");
                    } catch (Exception e) {
                        System.out.println("Error inesperado.");
                        System.out.println(e.getMessage());
                        break;
                    }
                    break;

                case 5:
                    System.out.println("Saliendo...");
                    salir = true;
                    break;

                default:
                    break;
            }

        } while (!salir);

        sc.close();
    }
}