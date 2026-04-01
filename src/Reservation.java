import java.io.Serializable;

/**
 * Confirmed reservation record.
 */
public class Reservation implements Serializable {
    private final String reservationId;
    private final String guestName;
    private final String roomType;
    private final String roomId;
    private ReservationStatus status;

    public Reservation(String reservationId, String guestName, String roomType, String roomId) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
        this.status = ReservationStatus.CONFIRMED;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomId() {
        return roomId;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void cancel() {
        this.status = ReservationStatus.CANCELLED;
    }

    @Override
    public String toString() {
        return "Reservation{id='" + reservationId + "', guest='" + guestName
                + "', roomType='" + roomType + "', roomId='" + roomId
                + "', status=" + status + "}";
    }
}