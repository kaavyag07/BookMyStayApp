import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FlashSaleManager {

    // productId -> remaining stock
    private Map<String, AtomicInteger> stockMap;

    // productId -> waiting list (FIFO)
    private Map<String, Queue<Integer>> waitingList;

    public FlashSaleManager() {
        stockMap = new ConcurrentHashMap<>();
        waitingList = new ConcurrentHashMap<>();
    }

    // Initialize product stock
    public void addProduct(String productId, int stock) {
        stockMap.put(productId, new AtomicInteger(stock));
    }

    // Check stock availability
    public int checkStock(String productId) {
        AtomicInteger stock = stockMap.get(productId);
        return stock != null ? stock.get() : 0;
    }

    // Purchase product (thread-safe)
    public String purchaseItem(String productId, int userId) {
        AtomicInteger stock = stockMap.get(productId);
        if (stock == null) return "Product not found";

        synchronized (stock) { // synchronize per product
            if (stock.get() > 0) {
                stock.decrementAndGet();
                return "Success, " + stock.get() + " units remaining";
            } else {
                waitingList.computeIfAbsent(productId, k -> new LinkedList<>()).add(userId);
                return "Added to waiting list, position #" + waitingList.get(productId).size();
            }
        }
    }

    // Get waiting list for a product
    public List<Integer> getWaitingList(String productId) {
        Queue<Integer> queue = waitingList.get(productId);
        return queue != null ? new ArrayList<>(queue) : new ArrayList<>();
    }

    // Demo usage
    public static void main(String[] args) {
        FlashSaleManager manager = new FlashSaleManager();

        manager.addProduct("IPHONE15_256GB", 5); // small stock for demo

        System.out.println(manager.checkStock("IPHONE15_256GB")); // 5

        System.out.println(manager.purchaseItem("IPHONE15_256GB", 101)); // Success
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 102)); // Success
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 103)); // Success
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 104)); // Success
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 105)); // Success
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 106)); // Waiting list

        System.out.println(manager.getWaitingList("IPHONE15_256GB")); // [106]
    }
}