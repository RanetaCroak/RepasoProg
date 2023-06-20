public class ClienteVegano extends Cliente{

    // Constructor
    public ClienteVegano(String nombre, String dni, int telefono) {
        super(nombre, dni, telefono);
    }


    @Override
    public boolean puedeElegirMesa() {
       return false;
    }
}
