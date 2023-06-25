public class ClienteVegano extends Cliente{

    // Constructor
    public ClienteVegano(String nombre, String dni, int telefono) {
        super(nombre, dni, telefono);
        this.menu = TipoMenu.VEGANO;
    }

    @Override
    public boolean puedeElegirMesa() {
       return false;
    }

}
