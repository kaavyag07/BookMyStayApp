/**
 * Demonstrates booking history storage and reporting.
 *
 * @author kaavyag07
 * @version 8.0
 */
public class UseCase8BookingHistoryReport {
    public static void main(String[] args) {
        try {
            RoomInventory inventory = new RoomInventory();
            RoomAllocationService allocationService = new RoomAllocationService();
            BookingHistory history = new BookingHistory();

            history.addReservation(allocationService.allocateReservation(
                    new BookingRequest("Aarav", "SingleRoom"), inventory));
            history.addReservation(allocationService.allocateReservation(
                    new BookingRequest("Meera", "DoubleRoom"), inventory));

            BookingReportService reportService = new BookingReportService();
            reportService.printReport(history);
        } catch (InvalidBookingException exception) {
            System.out.println(exception.getMessage());
        }
    }
}