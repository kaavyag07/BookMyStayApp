import java.util.List;
import java.util.Map;

/**
 * Demonstrates read-only room search against inventory state.
 *
 * @author kaavyag07
 * @version 4.0
 */
public class UseCase4RoomSearch {
    public static void main(String[] args) {
        try {
            RoomInventory inventory = new RoomInventory();
            inventory.updateAvailability("SuiteRoom", 0);

            Map<String, Room> catalog = RoomCatalog.createCatalog();
            RoomSearchService searchService = new RoomSearchService();
            List<Room> availableRooms = searchService.searchAvailableRooms(inventory, catalog);

            System.out.println("Available Rooms");
            for (Room room : availableRooms) {
                room.displayDetails();
                System.out.println("Current availability: " + inventory.getAvailableCount(room.getRoomType()));
                System.out.println();
            }
        } catch (InvalidBookingException exception) {
            System.out.println(exception.getMessage());
        }
    }
}