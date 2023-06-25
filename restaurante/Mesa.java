
public class Mesa implements Reservable, ComparatorDniInterface {
    final private int maximoComensales = 4;
    private int numeroComensales;
    private Cliente[] comensales;
    private int fila;

    private char columna;

    // Constructor
    public Mesa(int fila, char columna) {
        this.numeroComensales = 0;
        this.comensales = new Cliente[maximoComensales];
        this.fila = fila;
        this.columna = columna;
    }

    // Metodos
    public void anyadirComensal(Cliente cliente) throws ReservaException {
        if (this.numeroComensales == maximoComensales) {
            throw new ReservaException("La mesa está llena");
        }
        this.comensales[this.getNumeroComensales()] = cliente;
        // Sumar 1 comensal a la mesa
        this.setNumeroComensales(numeroComensales + 1);
        cliente.setMesaAsignada(this);
        System.out.println("Mesa asignada correctamente -> " + cliente.getMesaAsignada());
    }

    @Override
    public String toString() {
        if (this.numeroComensales == maximoComensales) {
            System.out.print("LLENA | ");
        }
        return "Comensales: " + numeroComensales + "/" + maximoComensales;
    }

    public double cuenta(Sala sala) {
        double totalCuenta = sala.precioSala();
        Cliente cliente;

        for (int i = 0; i < comensales.length; i++) {
            cliente = comensales[i];
            if (cliente != null) {
                totalCuenta += cliente.menu.getPrecioEuros();

                try {
                    this.cancelarReserva(cliente);
                } catch (ReservaException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        }

        return totalCuenta;
    }

    @Override
    public void realizarReserva(Cliente cliente) throws ReservaException {
        try {
            if (this.getNumeroComensales() >= this.getMaximoComensales()) {
                throw new ReservaException("Esta mesa está llena.");
            }
            anyadirComensal(cliente);
        } catch (ReservaException e) {
            System.out.println("Ha ocurrido un problema con la reserva.\n" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Algo ha fallado.");
        }
    }

    @Override
    public void cancelarReserva(Cliente cliente) throws ReservaException {
        try {
            for (int i = 0; i < comensales.length; i++) {
                if (comensales[i].equals(cliente)) {
                    comensales[i] = null;
                    cliente.setMesaAsignada(null);
                    System.out.println("Reserva cancelada correctamente.");
                }
            }
            throw new ReservaException();
        } catch (ReservaException e) {
            System.out.println("No se ha encontrado");
        }
    }

    // GetSet
    public int getMaximoComensales() {
        return maximoComensales;
    }

    public int getNumeroComensales() {
        return numeroComensales;
    }

    public void setNumeroComensales(int numeroComensales) {
        this.numeroComensales = numeroComensales;
    }

    public Cliente[] getComensales() {
        return comensales;
    }

    public void setComensales(Cliente[] comensales) {
        this.comensales = comensales;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public char getColumna() {
        return columna;
    }

    public void setColumna(char columna) {
        this.columna = columna;
    }
}
