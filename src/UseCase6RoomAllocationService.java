/**
 * Demonstrates reservation confirmation and safe room allocation.
 *
 * @author kaavyag07
 * @version 6.0
 */
public class UseCase6RoomAllocationService {
    public static void main(String[] args) {
        try {
            BookingRequestQueue queue = new BookingRequestQueue();
            queue.addRequest(new BookingRequest("Aarav", "SingleRoom"));
            queue.addRequest(new BookingRequest("Meera", "DoubleRoom"));

            RoomInventory inventory = new RoomInventory();
            RoomAllocationService allocationService = new RoomAllocationService();

            Reservation firstReservation = allocationService.allocateNextRequest(queue, inventory);
            Reservation secondReservation = allocationService.allocateNextRequest(queue, inventory);

            System.out.println("Confirmed Reservations");
            System.out.println(firstReservation);
            System.out.println(secondReservation);
            System.out.println("Remaining SingleRoom inventory: " + inventory.getAvailableCount("SingleRoom"));
            System.out.println("Remaining DoubleRoom inventory: " + inventory.getAvailableCount("DoubleRoom"));
        } catch (InvalidBookingException exception) {
            System.out.println(exception.getMessage());
        }
    }
}