public interface Reservable {
    public void realizarReserva(Cliente cliente) throws ReservaException;
    public void cancelarReserva(Cliente cliente) throws ReservaException;
}
