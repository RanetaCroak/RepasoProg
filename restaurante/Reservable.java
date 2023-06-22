public interface Reservable {
    public void realizarReserva(Cliente cliente, Mesa mesa) throws ReservaException;
    public void cancelarReserva(Cliente cliente, Mesa mesa) throws ReservaException;
}
