import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Confirms booking requests and allocates unique room identifiers.
 */
public class RoomAllocationService {
    private final Map<String, Set<String>> allocatedRoomIdsByType = new HashMap<>();
    private final Set<String> globallyAllocatedRoomIds = new HashSet<>();
    private int reservationCounter = 1;

    public RoomAllocationService() {
        allocatedRoomIdsByType.put("SingleRoom", new HashSet<>());
        allocatedRoomIdsByType.put("DoubleRoom", new HashSet<>());
        allocatedRoomIdsByType.put("SuiteRoom", new HashSet<>());
    }

    public synchronized Reservation allocateNextRequest(BookingRequestQueue queue, RoomInventory inventory)
            throws InvalidBookingException {
        BookingRequest request = queue.pollRequest();
        if (request == null) {
            throw new InvalidBookingException("No booking requests available for processing.");
        }
        return allocateReservation(request, inventory);
    }

    public synchronized Reservation allocateReservation(BookingRequest request, RoomInventory inventory)
            throws InvalidBookingException {
        BookingValidator validator = new BookingValidator();
        validator.validate(request, inventory);

        String roomType = request.getRoomType();
        inventory.reserveRoom(roomType);
        String roomId = generateUniqueRoomId(roomType);
        String reservationId = String.format("RES-%03d", reservationCounter++);
        return new Reservation(reservationId, request.getGuestName(), roomType, roomId);
    }

    public synchronized void releaseRoom(Reservation reservation) {
        Set<String> allocatedIds = allocatedRoomIdsByType.get(reservation.getRoomType());
        if (allocatedIds != null) {
            allocatedIds.remove(reservation.getRoomId());
        }
        globallyAllocatedRoomIds.remove(reservation.getRoomId());
    }

    private String generateUniqueRoomId(String roomType) {
        Set<String> allocatedIds = allocatedRoomIdsByType.computeIfAbsent(roomType, key -> new HashSet<>());
        int nextNumber = allocatedIds.size() + 1;
        String prefix = getRoomPrefix(roomType);
        String candidate = prefix + String.format("%03d", nextNumber);
        while (allocatedIds.contains(candidate) || globallyAllocatedRoomIds.contains(candidate)) {
            nextNumber++;
            candidate = prefix + String.format("%03d", nextNumber);
        }
        allocatedIds.add(candidate);
        globallyAllocatedRoomIds.add(candidate);
        return candidate;
    }

    private String getRoomPrefix(String roomType) {
        return switch (roomType) {
            case "SingleRoom" -> "SGL-";
            case "DoubleRoom" -> "DBL-";
            case "SuiteRoom" -> "STE-";
            default -> "RM-";
        };
    }
}