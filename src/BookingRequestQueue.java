import java.util.LinkedList;
import java.util.Queue;

/**
 * FIFO queue for incoming booking requests.
 */
public class BookingRequestQueue {
    private final Queue<BookingRequest> queue = new LinkedList<>();

    public synchronized void addRequest(BookingRequest request) {
        queue.offer(request);
    }

    public synchronized BookingRequest pollRequest() {
        return queue.poll();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    public synchronized int size() {
        return queue.size();
    }
}