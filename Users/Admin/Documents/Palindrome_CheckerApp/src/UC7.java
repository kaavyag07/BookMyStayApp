// UseCase7PalindromeCheckerApp.java

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class UC7
{

    public static void main(String[] args) {

        // Create Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Take input
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        // Initialize Deque
        Deque<Character> deque = new LinkedList<>();

        // Insert all characters into deque
        for (int i = 0; i < input.length(); i++) {
            deque.addLast(input.charAt(i));
        }

        // Compare front and rear characters
        boolean isPalindrome = true;
        while (deque.size() > 1) {
            if (!deque.removeFirst().equals(deque.removeLast())) {
                isPalindrome = false;
                break;
            }
        }

        // Display result
        if (isPalindrome) {
            System.out.println("Result: It is a Palindrome ✅");
        } else {
            System.out.println("Result: It is NOT a Palindrome ❌");
        }

        // Close scanner
        scanner.close();
    }
}