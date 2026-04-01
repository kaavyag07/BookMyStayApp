/**
 * Validates booking requests before mutation occurs.
 */
public class BookingValidator {
    public void validate(BookingRequest request, RoomInventory inventory) throws InvalidBookingException {
        if (request == null) {
            throw new InvalidBookingException("Booking request cannot be null.");
        }
        if (request.getGuestName() == null || request.getGuestName().isBlank()) {
            throw new InvalidBookingException("Guest name is required.");
        }
        inventory.validateRoomType(request.getRoomType());
        if (!inventory.hasAvailability(request.getRoomType())) {
            throw new InvalidBookingException("Requested room type is unavailable: " + request.getRoomType() + ".");
        }
    }
}