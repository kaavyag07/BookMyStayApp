import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Serializable snapshot of inventory and booking history.
 */
public class SystemState implements Serializable {
    private final Map<String, Integer> inventorySnapshot;
    private final List<Reservation> reservations;

    public SystemState(Map<String, Integer> inventorySnapshot, List<Reservation> reservations) {
        this.inventorySnapshot = new HashMap<>(inventorySnapshot);
        this.reservations = List.copyOf(reservations);
    }

    public Map<String, Integer> getInventorySnapshot() {
        return inventorySnapshot;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}