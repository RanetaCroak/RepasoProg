import java.util.Scanner;

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
                    System.out.println("Nombre y apellidos del cliente:");
                    String nombre = sc.nextLine();
                    System.out.println("DNI del cliente:");
                    String dni = sc.nextLine();
                    System.out.println("Teléfono de contacto:");
                    int telefono = sc.nextInt();
                    sc.nextLine();

                    Cliente clienteNuevo = new Cliente(nombre, dni, telefono);
                    restaurante.compararDni(clienteNuevo);
                    System.out.println("¿Es vegano? (s/n)");
                    if (sc.nextLine().equalsIgnoreCase("s")) {
                        // TODO revisar error al castear como vegano
                        /*
                         * class Cliente cannot be cast to class ClienteVegano (Cliente and
                         * ClienteVegano are in unnamed module of loader 'app')
                         */
                        clienteNuevo = (ClienteVegano) clienteNuevo;
                    }
                    break;

                case 2:
                    System.out.println("Selecciona un cliente por DNI");
                    restaurante.mostrarClientes();
                    dniReserva = sc.nextLine();
                    cliente = null;
                    try {
                        cliente = restaurante.buscarCliente(dniReserva);
                    } catch (ReservaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("Elige sala (Normal / Premium)");
                    String sala = sc.nextLine().toUpperCase();
                    TipoSala tipo = TipoSala.valueOf(sala);

                    // Se comprueba que la sala existe
                    try {
                        if (tipo == null) {
                            throw new ReservaException();
                        }
                    } catch (ReservaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    Sala salaSeleccionada = null;

                    switch (sala) {
                        case "NORMAL":
                            salaSeleccionada = restaurante.getSalaNormal();
                            break;

                        case "PREMIUM":
                            salaSeleccionada = restaurante.getSalaPremium();
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
                        System.out.println("Elige una mesa: ");
                        System.out.print("Fila: ");
                        int fila = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Columna ");
                        char columna = sc.nextLine().toUpperCase().charAt(0);

                        Mesa[][] mesas = salaSeleccionada.getMesas();
                        mesas[fila][columna - 'A'].anyadirComensal(cliente);
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
                    }

                    break;

                case 4:

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