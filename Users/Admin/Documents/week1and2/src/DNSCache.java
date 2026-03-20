import java.util.*;
import java.util.concurrent.*;

public class DNSCache {

    private final int capacity;
    private final long defaultTTL; // in milliseconds
    private final Map<String, DNSEntry> cache;
    private final ScheduledExecutorService cleaner;

    // Stats
    private int hits = 0;
    private int misses = 0;

    public DNSCache(int capacity, long defaultTTL) {
        this.capacity = capacity;
        this.defaultTTL = defaultTTL;
        this.cache = new LinkedHashMap<String, DNSEntry>(capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<String, DNSEntry> eldest) {
                return size() > DNSCache.this.capacity;
            }
        };

        // Background thread to clean expired entries every second
        cleaner = Executors.newSingleThreadScheduledExecutor();
        cleaner.scheduleAtFixedRate(this::cleanupExpiredEntries, 1, 1, TimeUnit.SECONDS);
    }

    // DNS Entry class
    static class DNSEntry {
        String domain;
        String ipAddress;
        long expiryTime;
        DNSEntry(String domain, String ipAddress, long ttlMillis) {
            this.domain = domain;
            this.ipAddress = ipAddress;
            this.expiryTime = System.currentTimeMillis() + ttlMillis;
        }
    }

    // Resolve domain
    public String resolve(String domain) {
        long startTime = System.nanoTime();
        synchronized (cache) {
            DNSEntry entry = cache.get(domain);
            if (entry != null && entry.expiryTime > System.currentTimeMillis()) {
                hits++;
                return entry.ipAddress + " (Cache HIT)";
            } else {
                misses++;
                // Query upstream DNS (simulate)
                String ip = queryUpstreamDNS(domain);
                cache.put(domain, new DNSEntry(domain, ip, defaultTTL));
                return ip + " (Cache MISS → TTL: " + defaultTTL / 1000 + "s)";
            }
        }
    }

    // Simulate upstream DNS query
    private String queryUpstreamDNS(String domain) {
        // In real-world, use InetAddress.getByName(domain)
        // Here, simulate by returning dummy IP
        int lastOctet = new Random().nextInt(255);
        return "172.217.14." + lastOctet;
    }

    // Remove expired entries
    private void cleanupExpiredEntries() {
        long now = System.currentTimeMillis();
        synchronized (cache) {
            cache.entrySet().removeIf(e -> e.getValue().expiryTime <= now);
        }
    }

    // Cache statistics
    public String getCacheStats() {
        int total = hits + misses;
        double hitRate = total == 0 ? 0 : (hits * 100.0 / total);
        return String.format("Cache HITs: %d, MISSes: %d, Hit Rate: %.2f%%", hits, misses, hitRate);
    }

    // Shutdown cleaner thread
    public void shutdown() {
        cleaner.shutdown();
    }

    // Demo usage
    public static void main(String[] args) throws InterruptedException {
        DNSCache dnsCache = new DNSCache(3, 3000); // 3s TTL, capacity 3 entries

        System.out.println(dnsCache.resolve("google.com")); // MISS
        Thread.sleep(1000);
        System.out.println(dnsCache.resolve("google.com")); // HIT
        Thread.sleep(3100); // after TTL expires
        System.out.println(dnsCache.resolve("google.com")); // EXPIRED → MISS

        System.out.println(dnsCache.resolve("facebook.com")); // MISS
        System.out.println(dnsCache.resolve("twitter.com")); // MISS
        System.out.println(dnsCache.resolve("linkedin.com")); // MISS → triggers LRU eviction

        System.out.println(dnsCache.getCacheStats());

        dnsCache.shutdown();
    }
}