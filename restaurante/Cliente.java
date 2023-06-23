public class Cliente implements Comparable<Cliente> {
    private String nombre;
    private String dni;
    private int telefono;
    private Mesa mesaAsignada;
    TipoMenu menu;

    // Constructor
    public Cliente(String nombre, String dni, int telefono) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.menu = TipoMenu.NORMAL;
    }

    // Metodos

    public boolean puedeElegirMesa() {
        return true;
    }

    @Override
    public String toString() {
        return "nombre: " + nombre + ", DNI: " + dni + ", telefono: " + telefono + ", mesaAsignada="
                + mesaAsignada + ", menu: " + menu;
    }

    @Override
    public int compareTo(Cliente cliente2) {    
        // Pasa los clientes Veganos al final
        if (this.getClass().getSimpleName() == "ClienteVegano" && cliente2.getClass().getSimpleName() != "ClienteVegano") {
            return 1;
        }
        return this.nombre.compareTo(cliente2.getNombre());
    }
    // getSet

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Mesa getMesaAsignada() {
        return mesaAsignada;
    }

    public void setMesaAsignada(Mesa mesaAsignada) {
        this.mesaAsignada = mesaAsignada;
    }

}
