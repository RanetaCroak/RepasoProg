import java.util.Comparator;

public interface ComparatorDniInterface extends Comparator<Cliente> {

    @Override
    default int compare(Cliente cliente1, Cliente cliente2) {
        String dni1 = cliente1.getDni();
        String dni2 = cliente2.getDni();

        return dni1.compareToIgnoreCase(dni2);
    }

}
