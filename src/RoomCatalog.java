import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Provides access to the available room domain objects.
 */
public final class RoomCatalog {
    private RoomCatalog() {
    }

    public static Map<String, Room> createCatalog() {
        Map<String, Room> catalog = new LinkedHashMap<>();
        catalog.put("SingleRoom", new SingleRoom());
        catalog.put("DoubleRoom", new DoubleRoom());
        catalog.put("SuiteRoom", new SuiteRoom());
        return catalog;
    }
}