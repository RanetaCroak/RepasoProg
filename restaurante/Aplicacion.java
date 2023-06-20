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


        boolean salir = false;
        do {
            menuPrincipal();
            
        } while (!salir);
    }
}