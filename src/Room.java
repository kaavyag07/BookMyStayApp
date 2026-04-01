import java.io.Serializable;

/**
 * Abstract representation of a hotel room.
 *
 * @author kaavyag07
 * @version 2.0
 */
public abstract class Room implements Serializable {
    private final String roomType;
    private final int beds;
    private final int size;
    private final double pricePerNight;

    protected Room(String roomType, int beds, int size, double pricePerNight) {
        this.roomType = roomType;
        this.beds = beds;
        this.size = size;
        this.pricePerNight = pricePerNight;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getBeds() {
        return beds;
    }

    public int getSize() {
        return size;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void displayDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: Rs. " + pricePerNight);
    }
}