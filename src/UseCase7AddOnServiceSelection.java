/**
 * Demonstrates optional service selection for a confirmed reservation.
 *
 * @author kaavyag07
 * @version 7.0
 */
public class UseCase7AddOnServiceSelection {
    public static void main(String[] args) {
        try {
            RoomInventory inventory = new RoomInventory();
            RoomAllocationService allocationService = new RoomAllocationService();
            Reservation reservation = allocationService.allocateReservation(
                    new BookingRequest("Siya", "SuiteRoom"), inventory);

            AddOnServiceManager serviceManager = new AddOnServiceManager();
            serviceManager.attachService(reservation.getReservationId(), new AddOnService("Breakfast", 499.0));
            serviceManager.attachService(reservation.getReservationId(), new AddOnService("Airport Pickup", 899.0));

            System.out.println("Reservation: " + reservation);
            System.out.println("Selected services: " + serviceManager.getServices(reservation.getReservationId()));
            System.out.println("Total add-on cost: Rs. "
                    + serviceManager.calculateTotalAdditionalCost(reservation.getReservationId()));
        } catch (InvalidBookingException exception) {
            System.out.println(exception.getMessage());
        }
    }
}