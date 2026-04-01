/**
 * Custom exception for invalid booking scenarios.
 */
public class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}