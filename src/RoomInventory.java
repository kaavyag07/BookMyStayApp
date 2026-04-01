import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Centralized room inventory manager.
 *
 * @author kaavyag07
 * @version 3.0
 */
public class RoomInventory implements Serializable {
    private final Map<String, Integer> roomAvailability;

    public RoomInventory() {
        this.roomAvailability = new HashMap<>();
        initializeInventory();
    }

    public RoomInventory(Map<String, Integer> initialAvailability) {
        this.roomAvailability = new HashMap<>(initialAvailability);
    }

    private void initializeInventory() {
        roomAvailability.put("SingleRoom", 5);
        roomAvailability.put("DoubleRoom", 3);
        roomAvailability.put("SuiteRoom", 2);
    }

    public synchronized Map<String, Integer> getRoomAvailability() {
        return Collections.unmodifiableMap(new HashMap<>(roomAvailability));
    }

    public synchronized int getAvailableCount(String roomType) {
        return roomAvailability.getOrDefault(roomType, 0);
    }

    public synchronized boolean hasAvailability(String roomType) {
        return getAvailableCount(roomType) > 0;
    }

    public synchronized void updateAvailability(String roomType, int count) throws InvalidBookingException {
        validateRoomType(roomType);
        if (count < 0) {
            throw new InvalidBookingException("Availability cannot be negative for " + roomType + ".");
        }
        roomAvailability.put(roomType, count);
    }

    public synchronized void reserveRoom(String roomType) throws InvalidBookingException {
        validateRoomType(roomType);
        int available = roomAvailability.get(roomType);
        if (available <= 0) {
            throw new InvalidBookingException("No rooms available for " + roomType + ".");
        }
        roomAvailability.put(roomType, available - 1);
    }

    public synchronized void releaseRoom(String roomType) throws InvalidBookingException {
        validateRoomType(roomType);
        roomAvailability.put(roomType, roomAvailability.get(roomType) + 1);
    }

    public synchronized void validateRoomType(String roomType) throws InvalidBookingException {
        if (!roomAvailability.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType + ".");
        }
    }
}