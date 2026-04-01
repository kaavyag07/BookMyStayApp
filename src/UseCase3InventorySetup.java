import java.util.Map;

/**
 * Demonstrates centralized inventory management using a HashMap.
 *
 * @author kaavyag07
 * @version 3.0
 */
public class UseCase3InventorySetup {
    public static void main(String[] args) {
        try {
            RoomInventory inventory = new RoomInventory();
            Map<String, Integer> availability = inventory.getRoomAvailability();

            System.out.println("Centralized Room Inventory");
            for (Map.Entry<String, Integer> entry : availability.entrySet()) {
                System.out.println(entry.getKey() + " => " + entry.getValue() + " rooms available");
            }

            inventory.updateAvailability("DoubleRoom", 2);
            System.out.println("Updated DoubleRoom availability => " + inventory.getAvailableCount("DoubleRoom"));
        } catch (InvalidBookingException exception) {
            System.out.println(exception.getMessage());
        }
    }
}