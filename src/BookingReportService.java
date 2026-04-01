import java.util.List;

/**
 * Generates basic operational booking reports.
 */
public class BookingReportService {
    public void printReport(BookingHistory history) {
        List<Reservation> reservations = history.getReservations();
        System.out.println("Total confirmed or tracked reservations: " + reservations.size());
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }
}