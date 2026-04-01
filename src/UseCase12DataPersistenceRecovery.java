/**
 * Demonstrates saving and restoring booking and inventory state.
 *
 * @author kaavyag07
 * @version 12.0
 */
public class UseCase12DataPersistenceRecovery {
    public static void main(String[] args) {
        String filePath = "hotel-booking-state.ser";
        PersistenceService persistenceService = new PersistenceService();

        try {
            RoomInventory inventory = new RoomInventory();
            RoomAllocationService allocationService = new RoomAllocationService();
            BookingHistory history = new BookingHistory();

            history.addReservation(allocationService.allocateReservation(
                    new BookingRequest("Aarav", "SuiteRoom"), inventory));
            history.addReservation(allocationService.allocateReservation(
                    new BookingRequest("Meera", "SingleRoom"), inventory));

            SystemState stateToSave = new SystemState(inventory.getRoomAvailability(), history.getReservations());
            persistenceService.save(filePath, stateToSave);
            System.out.println("State saved successfully.");

            SystemState restoredState = persistenceService.load(filePath);
            if (restoredState == null) {
                System.out.println("No saved state found. Starting with a safe default state.");
                return;
            }

            RoomInventory restoredInventory = new RoomInventory(restoredState.getInventorySnapshot());
            BookingHistory restoredHistory = new BookingHistory();
            for (Reservation reservation : restoredState.getReservations()) {
                restoredHistory.addReservation(reservation);
            }

            System.out.println("Recovered inventory: " + restoredInventory.getRoomAvailability());
            System.out.println("Recovered bookings: " + restoredHistory.getReservations().size());
            for (Reservation reservation : restoredHistory.getReservations()) {
                System.out.println(reservation);
            }
        } catch (InvalidBookingException | java.io.IOException exception) {
            System.out.println("Persistence flow handled safely: " + exception.getMessage());
        }
    }
}