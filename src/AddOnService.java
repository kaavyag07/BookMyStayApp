import java.io.Serializable;

/**
 * Represents an optional value-added booking service.
 */
public class AddOnService implements Serializable {
    private final String serviceName;
    private final double price;

    public AddOnService(String serviceName, double price) {
        this.serviceName = serviceName;
        this.price = price;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return serviceName + " (Rs. " + price + ")";
    }
}