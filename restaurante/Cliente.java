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
        this.mesaAsignada = null;
    }

    // Metodos

    public void tieneMesa() throws ReservaException {
        if (this.mesaAsignada != null) {
            throw new ReservaException("Este cliente ya disponde de una mesa asignada.");
        }
    }

    public boolean puedeElegirMesa() {
        return true;
    }

    @Override
    public String toString() {
        return "nombre: " + nombre + ", DNI: " + dni + ", telefono: " + telefono + ", mesa asignada: "
                + (mesaAsignada == null ? "no" : mesaAsignada.getFila() + mesaAsignada.getColumna()) + ", menu: "
                + menu;
    }

    @Override
    public int compareTo(Cliente cliente2) {
        // Pasa los clientes Veganos al final
        boolean esClienteVegano1 = this instanceof ClienteVegano;
        boolean esClienteVegano2 = cliente2 instanceof ClienteVegano;

        // Si es vegano y el otro no, se pasa hacia el final (return 1)
        // Si NO es vegano y el otro si, se adelanta (Se pasa al vegano más hacia el
        // final)
        if (esClienteVegano1 && !esClienteVegano2) {
            return 1; // cliente actual es mayor que cliente2
        } else if (!esClienteVegano1 && esClienteVegano2) {
            return -1; // cliente2 es mayor que cliente actual
        }

        // Comparación basada en el nombre
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
