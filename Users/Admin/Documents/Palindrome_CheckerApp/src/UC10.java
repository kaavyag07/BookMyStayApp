// UseCase10PalindromeCheckerApp.java
import java.util.Scanner;

public class UC10
{

    // Iterative palindrome checker
    public static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string to check palindrome: ");
        String input = sc.nextLine();

        // Normalize: remove non-alphanumeric characters and lowercase
        String normalized = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        // Check palindrome
        if (isPalindrome(normalized)) {
            System.out.println("The string is a palindrome (case-insensitive & space-ignored).");
        } else {
            System.out.println("The string is NOT a palindrome.");
        }

        sc.close();
    }
}