/**
 * Demonstrates booking cancellation and inventory rollback.
 *
 * @author kaavyag07
 * @version 10.0
 */
public class UseCase10BookingCancellation {
    public static void main(String[] args) {
        try {
            RoomInventory inventory = new RoomInventory();
            RoomAllocationService allocationService = new RoomAllocationService();
            BookingHistory history = new BookingHistory();
            CancellationService cancellationService = new CancellationService();

            Reservation reservation = allocationService.allocateReservation(
                    new BookingRequest("Anaya", "DoubleRoom"), inventory);
            history.addReservation(reservation);

            System.out.println("Before cancellation: " + inventory.getAvailableCount("DoubleRoom"));
            cancellationService.cancelReservation(reservation.getReservationId(), history, inventory, allocationService);
            System.out.println("After cancellation: " + inventory.getAvailableCount("DoubleRoom"));
            System.out.println("Released room stack: " + cancellationService.getReleasedRoomIds());
            System.out.println("Updated reservation: " + reservation);
        } catch (InvalidBookingException exception) {
            System.out.println(exception.getMessage());
        }
    }
}