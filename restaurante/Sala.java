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

    // Getter y setter
    public Mesa[][] getMesas() {
        return mesas;
    }

    public void setMesas(Mesa[][] mesas) {
        this.mesas = mesas;
    }

}
