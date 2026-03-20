// UseCase13PalindromeCheckerApp.java
import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayDeque;
import java.util.Deque;

public class UC13
{

    // Stack-based approach
    public static boolean stackPalindrome(String str) {
        str = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) stack.push(c);
        for (char c : str.toCharArray()) {
            if (c != stack.pop()) return false;
        }
        return true;
    }

    // Deque-based approach
    public static boolean dequePalindrome(String str) {
        str = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        Deque<Character> deque = new ArrayDeque<>();
        for (char c : str.toCharArray()) deque.addLast(c);
        while (deque.size() > 1) {
            if (!deque.removeFirst().equals(deque.removeLast())) return false;
        }
        return true;
    }

    // Two-pointer iterative approach
    public static boolean twoPointerPalindrome(String str) {
        str = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string to check palindrome: ");
        String input = sc.nextLine();

        System.out.println("\nRunning performance tests...\n");

        // Stack-based
        long start = System.nanoTime();
        boolean stackResult = stackPalindrome(input);
        long end = System.nanoTime();
        long stackTime = end - start;
        System.out.println("Stack-based: Result=" + stackResult + ", Time=" + stackTime + " ns");

        // Deque-based
        start = System.nanoTime();
        boolean dequeResult = dequePalindrome(input);
        end = System.nanoTime();
        long dequeTime = end - start;
        System.out.println("Deque-based: Result=" + dequeResult + ", Time=" + dequeTime + " ns");

        // Two-pointer
        start = System.nanoTime();
        boolean twoPointerResult = twoPointerPalindrome(input);
        end = System.nanoTime();
        long twoPointerTime = end - start;
        System.out.println("Two-pointer: Result=" + twoPointerResult + ", Time=" + twoPointerTime + " ns");

        sc.close();
    }
}