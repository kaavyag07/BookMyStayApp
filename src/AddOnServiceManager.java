import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Maps reservations to the selected add-on services.
 */
public class AddOnServiceManager {
    private final Map<String, List<AddOnService>> servicesByReservationId = new HashMap<>();

    public void attachService(String reservationId, AddOnService service) {
        servicesByReservationId.computeIfAbsent(reservationId, key -> new ArrayList<>()).add(service);
    }

    public List<AddOnService> getServices(String reservationId) {
        return servicesByReservationId.getOrDefault(reservationId, List.of());
    }

    public double calculateTotalAdditionalCost(String reservationId) {
        double total = 0.0;
        for (AddOnService service : getServices(reservationId)) {
            total += service.getPrice();
        }
        return total;
    }
}