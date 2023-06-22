public class Mesa implements Reservable, ComparatorDniInterface {
    final private int maximoComensales = 4;
    private int numeroComensales;
    private Cliente[] comensales;

    // Constructor
    public Mesa() {
        this.numeroComensales = 0;
        this.comensales = new Cliente[maximoComensales];
    }

    // Metodos
    public void anyadirComensal(Cliente cliente) {
        this.comensales[this.getNumeroComensales()] = cliente;
        // Sumar 1 comensal a la mesa
        this.setNumeroComensales(numeroComensales + 1);
    }

    public double cuenta(Sala sala) {
        double totalCuenta = sala.precioSala();
        for (Cliente cliente : comensales) {
            totalCuenta += cliente.menu.getPrecioEuros();
        }

        return totalCuenta;
    }

    @Override
    public void realizarReserva(Cliente cliente, Mesa mesa) throws ReservaException {
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
    public void cancelarReserva(Cliente cliente, Mesa mesa) throws ReservaException {
        try {
            for (int i = 0; i < comensales.length; i++) {
                if (comensales[i].equals(cliente)) {
                    comensales[i] = null;
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

}
