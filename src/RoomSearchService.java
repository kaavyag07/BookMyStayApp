import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Read-only search service for available room types.
 */
public class RoomSearchService {
    public List<Room> searchAvailableRooms(RoomInventory inventory, Map<String, Room> catalog) {
        List<Room> availableRooms = new ArrayList<>();
        for (Map.Entry<String, Room> entry : catalog.entrySet()) {
            if (inventory.getAvailableCount(entry.getKey()) > 0) {
                availableRooms.add(entry.getValue());
            }
        }
        return availableRooms;
    }
}