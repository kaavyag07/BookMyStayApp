import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Stores confirmed reservations in insertion order.
 */
public class BookingHistory implements Serializable {
    private final List<Reservation> reservations = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public List<Reservation> getReservations() {
        return Collections.unmodifiableList(reservations);
    }

    public Reservation findById(String reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId().equals(reservationId)) {
                return reservation;
            }
        }
        return null;
    }
}