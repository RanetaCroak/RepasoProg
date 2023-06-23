import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Restaurante implements ComparatorDniInterface {
    private String nombre;
    private ArrayList<Cliente> clientes;
    private Sala salaPremium;
    private Sala salaNormal;

    // Constructor
    public Restaurante(String nombre) {
        this.nombre = nombre;
        this.clientes = new ArrayList<Cliente>();
        // Se crean ambas salas
        this.salaPremium = new Sala(TipoSala.PREMIUM);
        this.salaNormal = new Sala(TipoSala.NORMAL);
    }

    // Metodos
    private boolean comprobarClienteVegano(ClienteVegano cliente) throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader("clientesVeganos.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] partes = line.split(":");
                String dni = partes[1];
                if (cliente.getDni().equals(dni)) {
                    throw new Exception();
                }
            }
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    public void inscribirClienteVegano(ClienteVegano cliente){
        try {
            if (!comprobarClienteVegano(cliente)) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("clientesVeganos.txt"))) {
                    writer.write(cliente.getNombre()+":"+cliente.getDni()+":"+cliente.getTelefono());
                    System.out.println("Cliente inscrito correctamente.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean compararDni(Cliente cliente) {
        for (Cliente cliente2 : clientes) {
            if (compare(cliente, cliente2) == 0) {
                return true;
            }
        }
        return false;
    }

    public Cliente buscarCliente(String dni) throws ReservaException {
        for (Cliente cliente : clientes) {
            if (cliente.getDni().equals(dni)) {
                return cliente;
            }
        }
        System.out.println("No se encontró el cliente con DNI \"" + dni + "\".");
        throw new ReservaException("No se encontró el cliente con DNI \"" + dni + "\".");
    }

    public void mostrarClientes() {
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
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
