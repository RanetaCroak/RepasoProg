public class ReservaException extends Exception {
    public ReservaException() {
        super();
    }

    public ReservaException(String mensaje) {
        super(mensaje);
    }

    public ReservaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    @Override
    public String toString() {
        System.out.println("\nMensaje de error original:");
        System.out.println(super.toString());
        
        System.out.println("\nMensaje de error personalizado:");
        return "Error al realizar la reserva: " + this.getMessage();
    }
}
