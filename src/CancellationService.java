import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Handles reservation cancellation and inventory rollback.
 */
public class CancellationService {
    private final Stack<String> releasedRoomIds = new Stack<>();
    private final Set<String> cancelledReservationIds = new HashSet<>();

    public void cancelReservation(String reservationId, BookingHistory history, RoomInventory inventory,
            RoomAllocationService allocationService) throws InvalidBookingException {
        Reservation reservation = history.findById(reservationId);
        if (reservation == null) {
            throw new InvalidBookingException("Reservation not found: " + reservationId + ".");
        }
        if (cancelledReservationIds.contains(reservationId) || reservation.getStatus() == ReservationStatus.CANCELLED) {
            throw new InvalidBookingException("Reservation already cancelled: " + reservationId + ".");
        }

        releasedRoomIds.push(reservation.getRoomId());
        inventory.releaseRoom(reservation.getRoomType());
        allocationService.releaseRoom(reservation);
        reservation.cancel();
        cancelledReservationIds.add(reservationId);
    }

    public Stack<String> getReleasedRoomIds() {
        return releasedRoomIds;
    }
}