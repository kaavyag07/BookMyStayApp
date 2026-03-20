// UseCase9PalindromeCheckerApp.java
import java.util.Scanner;

public class UC9
{

    // Recursive method to check palindrome
    public static boolean isPalindrome(String str, int start, int end) {
        // Base condition: If start crosses end, it's a palindrome
        if (start >= end) {
            return true;
        }
        // If characters at start and end don't match, not a palindrome
        if (str.charAt(start) != str.charAt(end)) {
            return false;
        }
        // Recursive call on the substring inside
        return isPalindrome(str, start + 1, end - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string to check palindrome: ");
        String input = sc.nextLine();

        // Normalize input: remove non-alphanumeric and lowercase
        input = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        // Call recursive palindrome checker
        boolean result = isPalindrome(input, 0, input.length() - 1);

        if (result) {
            System.out.println("The string is a palindrome.");
        } else {
            System.out.println("The string is NOT a palindrome.");
        }

        sc.close();
    }
}