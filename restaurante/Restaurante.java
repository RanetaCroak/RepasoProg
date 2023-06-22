import java.util.ArrayList;

public class Restaurante {
    private String nombre;
    private ArrayList<Cliente> clientes;
    private Sala salaPremium;
    private Sala salaNormal;

    // Constructor
    public Restaurante(String nombre) {
        this.nombre = nombre;
        this.clientes = new ArrayList<Cliente>();
        this.salaPremium = new Sala(TipoSala.PREMIUM);
        this.salaNormal = new Sala(TipoSala.NORMAL);
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Sala getSalaPremium() {
        return salaPremium;
    }

    public void setSalaPremium(Sala salaPremium) {
        this.salaPremium = salaPremium;
    }

    public Sala getSalaNormal() {
        return salaNormal;
    }

    public void setSalaNormal(Sala salaNormal) {
        this.salaNormal = salaNormal;
    }

}
