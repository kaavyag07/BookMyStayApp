import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates thread-safe booking allocation under concurrent load.
 *
 * @author kaavyag07
 * @version 11.0
 */
public class UseCase11ConcurrentBookingSimulation {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        RoomAllocationService allocationService = new RoomAllocationService();
        BookingHistory history = new BookingHistory();

        List<Thread> threads = new ArrayList<>();
        String[] guests = {"Aarav", "Meera", "Kabir", "Siya", "Rohan", "Naina"};

        for (String guest : guests) {
            Thread thread = new Thread(() -> {
                try {
                    Reservation reservation = allocationService.allocateReservation(
                            new BookingRequest(guest, "SingleRoom"), inventory);
                    synchronized (history) {
                        history.addReservation(reservation);
                    }
                    System.out.println(Thread.currentThread().getName() + " confirmed " + reservation);
                } catch (InvalidBookingException exception) {
                    System.out.println(Thread.currentThread().getName() + " failed: " + exception.getMessage());
                }
            }, "GuestThread-" + guest);
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Final SingleRoom inventory: " + inventory.getAvailableCount("SingleRoom"));
        System.out.println("Confirmed reservations stored: " + history.getReservations().size());
    }
}