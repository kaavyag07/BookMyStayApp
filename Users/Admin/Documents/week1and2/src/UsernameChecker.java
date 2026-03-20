import java.util.*;

public class UsernameChecker {

    // Maps username -> userId (simulate existing users)
    private Map<String, Integer> usernameMap;

    // Maps username -> number of attempts
    private Map<String, Integer> attemptFrequency;

    public UsernameChecker() {
        usernameMap = new HashMap<>();
        attemptFrequency = new HashMap<>();
    }

    // Add existing username (for setup)
    public void addUser(String username, int userId) {
        usernameMap.put(username, userId);
    }

    // Check availability
    public boolean checkAvailability(String username) {
        // Track attempts
        attemptFrequency.put(username, attemptFrequency.getOrDefault(username, 0) + 1);

        // Return true if username is not taken
        return !usernameMap.containsKey(username);
    }

    // Suggest alternative usernames
    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();
        int counter = 1;

        // Generate numerical variants
        while (suggestions.size() < 5) {
            String newUsername = username + counter;
            if (!usernameMap.containsKey(newUsername)) {
                suggestions.add(newUsername);
            }
            counter++;
        }

        // Add a dot variant if not already taken
        String dotVersion = username.replace("_", ".");
        if (!usernameMap.containsKey(dotVersion) && !suggestions.contains(dotVersion)) {
            suggestions.add(dotVersion);
        }

        return suggestions;
    }

    // Get most attempted username
    public String getMostAttempted() {
        String mostAttempted = null;
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : attemptFrequency.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostAttempted = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        return mostAttempted;
    }

    // Demo usage
    public static void main(String[] args) {
        UsernameChecker checker = new UsernameChecker();

        // Add some existing usernames
        checker.addUser("john_doe", 101);
        checker.addUser("admin", 1);

        System.out.println("Check 'john_doe': " + checker.checkAvailability("john_doe"));   // false
        System.out.println("Check 'jane_smith': " + checker.checkAvailability("jane_smith")); // true

        System.out.println("Suggestions for 'john_doe': " + checker.suggestAlternatives("john_doe"));

        // Simulate multiple attempts
        checker.checkAvailability("admin");
        checker.checkAvailability("admin");
        checker.checkAvailability("john_doe");

        System.out.println("Most attempted: " + checker.getMostAttempted()); // likely "admin"
    }
}