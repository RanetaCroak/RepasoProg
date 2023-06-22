public class Sala {
    TipoSala sala;
    Mesa[][] mesas;

    // Constructor
    public Sala(TipoSala sala) {
        this.mesas = new Mesa[sala.getFilas()][sala.getColumnas()];
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
