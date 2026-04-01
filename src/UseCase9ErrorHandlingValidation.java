/**
 * Demonstrates validation and graceful failure handling.
 *
 * @author kaavyag07
 * @version 9.0
 */
public class UseCase9ErrorHandlingValidation {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        BookingValidator validator = new BookingValidator();

        try {
            validator.validate(new BookingRequest("", "SingleRoom"), inventory);
        } catch (InvalidBookingException exception) {
            System.out.println("Validation failure 1: " + exception.getMessage());
        }

        try {
            validator.validate(new BookingRequest("Kabir", "PresidentialRoom"), inventory);
        } catch (InvalidBookingException exception) {
            System.out.println("Validation failure 2: " + exception.getMessage());
        }

        try {
            inventory.updateAvailability("SingleRoom", -1);
        } catch (InvalidBookingException exception) {
            System.out.println("Validation failure 3: " + exception.getMessage());
        }

        System.out.println("System remained stable after handling invalid scenarios.");
    }
}