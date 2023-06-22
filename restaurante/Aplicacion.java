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
        do {
            menuPrincipal();

            switch (opc) {
                case 1:
                    System.out.println("Nombre y apellidos del cliente:");
                    String nombre = sc.nextLine();
                    System.out.println("DNI del cliente:");
                    String dni = sc.nextLine();
                    System.out.println("Teléfono de contacto:");
                    int telefono = sc.nextInt();
                    sc.nextLine();

                    // TODO comparar DNI antes de crear el cliente
                    Cliente clienteNuevo = new Cliente(nombre, dni, telefono);
                    System.out.println("¿Es vegano? (s/n)");
                    if (sc.nextLine().equalsIgnoreCase("s")) {
                        clienteNuevo = (ClienteVegano) clienteNuevo;
                    }
                    break;
            
                case 2:
                    
                    break;
            
                case 3:
                    
                    break;
            
                case 4:
                    
                    break;
            
                case 5:
                    
                    break;
            
                default:
                    break;
            }
            
        } while (!salir);

        sc.close();
    }
}