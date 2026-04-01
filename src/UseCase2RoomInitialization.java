import java.util.Map;

/**
 * Demonstrates basic room modeling with static availability.
 *
 * @author kaavyag07
 * @version 2.0
 */
public class UseCase2RoomInitialization {
    public static void main(String[] args) {
        Map<String, Room> catalog = RoomCatalog.createCatalog();

        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        System.out.println("Hotel Room Initialization");
        System.out.println();

        catalog.get("SingleRoom").displayDetails();
        System.out.println("Available: " + singleAvailable);
        System.out.println();

        catalog.get("DoubleRoom").displayDetails();
        System.out.println("Available: " + doubleAvailable);
        System.out.println();

        catalog.get("SuiteRoom").displayDetails();
        System.out.println("Available: " + suiteAvailable);
    }
}