public class Mesa {
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

    public double cuenta() {
        double totalCuenta = 0;

        return totalCuenta;
    }

    public void name() {
        
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
