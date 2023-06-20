public class Cliente {
    private String nombre;
    private String dni;
    private int telefono;
    private Mesa mesaAsignada;

    // Constructor
    public Cliente(String nombre, String dni, int telefono) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
    }

    // Metodos

    public boolean puedeElegirMesa() {
        return true;
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
