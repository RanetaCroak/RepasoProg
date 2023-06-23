public class Sala {
    TipoSala sala;
    Mesa[][] mesas;

    // Constructor
    public Sala(TipoSala sala) {
        this.mesas = new Mesa[sala.getFilas()][sala.getColumnas()];
        for (int i = 0; i < mesas.length; i++) {
            for (int j = 0; j < mesas[i].length; j++) {
                mesas[i][j] = new Mesa();
            }
        }
    }

    // Metodos
    public int precioSala() {
        return this.sala.getPrecioMesaEuros();
    }

    public void mostrarMesas() {
        for (int i = 0; i < mesas.length; i++) {
            for (int j = 0; j < mesas[i].length; j++) {
                System.out.println("Fila " + i + " | Columna " + ((char) j) + 'A' + "\n" + mesas[i][j]);
            }
        }
    }

    public Mesa buscarMesa() throws ReservaException {
        for (Mesa[] mesas2 : mesas) {
            for (Mesa mesa : mesas2) {
                if (mesa.getNumeroComensales() < mesa.getMaximoComensales()) {
                    return mesa;
                }
            }
        }
        throw new ReservaException("No hay mesas disponibles en esta sala.");
    }

    // Getter y setter
    public Mesa[][] getMesas() {
        return mesas;
    }

    public void setMesas(Mesa[][] mesas) {
        this.mesas = mesas;
    }

}
