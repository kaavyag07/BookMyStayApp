/**
 * Demonstrates FIFO request intake for booking requests.
 *
 * @author kaavyag07
 * @version 5.0
 */
public class UseCase5BookingRequestQueue {
    public static void main(String[] args) {
        BookingRequestQueue queue = new BookingRequestQueue();
        queue.addRequest(new BookingRequest("Aarav", "SingleRoom"));
        queue.addRequest(new BookingRequest("Meera", "DoubleRoom"));
        queue.addRequest(new BookingRequest("Ishaan", "SuiteRoom"));

        System.out.println("Queued booking requests in arrival order:");
        while (!queue.isEmpty()) {
            System.out.println(queue.pollRequest());
        }
    }
}