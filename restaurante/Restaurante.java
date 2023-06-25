import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

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

    public void anyadirCliente(Cliente cliente) {
        try {

            this.clientes.add(cliente);
            System.out.println("Cliente añadido correctamente.");
        } catch (Exception e) {
            System.out.println("Error al añadir al cliente\n" + e.getMessage());
        }
    }

    private boolean comprobarClienteVegano(ClienteVegano cliente) throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader("./restaurante/clientesVeganos.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] partes = line.split(":");
                String dni = partes[1];
                if (cliente.getDni().equals(dni)) {
                    throw new Exception("El cliente con DNI " + dni + " ya está en el archivo.");
                }
            }
            // Si no se encuentra ninguna coincidencia, se llega a este punto y se retorna
            // false
            return false;
        } catch (Exception e) {
            // Si se lanza una excepción, significa que se encontró una coincidencia y se
            // retorna true
            return true;
        }
    }

    public void inscribirClienteVegano(ClienteVegano cliente) {

        try {
            if (!comprobarClienteVegano(cliente)) {
                try {
                    BufferedWriter writer = new BufferedWriter(
                            new FileWriter("./restaurante/clientesVeganos.txt", true));
                    writer.write(cliente.getNombre() + ":" + cliente.getDni() + ":" + cliente.getTelefono());
                    writer.newLine();
                    writer.close();
                    System.out.println("Se ha escrito en el archivo de clientes veganos correctamente.");
                } catch (IOException e) {
                    System.err.println("Error al escribir en el archivo: " + e.getMessage());
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

    public void ordenarClientes() {
        Collections.sort(this.getClientes());
        mostrarClientes();
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
