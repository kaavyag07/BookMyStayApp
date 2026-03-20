// UseCase11PalindromeCheckerApp.java
import java.util.Scanner;

// PalindromeChecker class encapsulates all palindrome logic
class UC11
{
    private String input;

    // Constructor
    public PalindromeChecker(String input) {
        // Normalize input: remove non-alphanumeric and lowercase
        this.input = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    }

    // Public method to check palindrome
    public boolean checkPalindrome() {
        int left = 0;
        int right = input.length() - 1;

        while (left < right) {
            if (input.charAt(left) != input.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // Optional: Getter for normalized string
    public String getNormalizedInput() {
        return input;
    }
}

public class UseCase11PalindromeCheckerApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string to check palindrome: ");
        String userInput = sc.nextLine();

        // Create PalindromeChecker instance
        PalindromeChecker checker = new PalindromeChecker(userInput);

        // Check palindrome
        if (checker.checkPalindrome()) {
            System.out.println("The string is a palindrome (OOP service).");
        } else {
            System.out.println("The string is NOT a palindrome.");
        }

        sc.close();
    }
}