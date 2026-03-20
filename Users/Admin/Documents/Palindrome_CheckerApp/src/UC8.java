// UseCase8PalindromeCheckerApp.java

import java.util.Scanner;

public class UC8
{

    // Node class for singly linked list
    static class Node {
        char data;
        Node next;

        Node(char data) {
            this.data = data;
            this.next = null;
        }
    }

    // Function to check if linked list is palindrome
    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null)
            return true;

        // Find middle using fast and slow pointers
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse second half
        Node secondHalfStart = reverseList(slow.next);

        // Compare first half and reversed second half
        Node firstHalfStart = head;
        Node secondHalfCopy = secondHalfStart; // To restore list later if needed
        boolean palindrome = true;
        while (secondHalfStart != null) {
            if (firstHalfStart.data != secondHalfStart.data) {
                palindrome = false;
                break;
            }
            firstHalfStart = firstHalfStart.next;
            secondHalfStart = secondHalfStart.next;
        }

        // Optional: Restore second half
        slow.next = reverseList(secondHalfCopy);

        return palindrome;
    }

    // Helper function to reverse a linked list
    public static Node reverseList(Node head) {
        Node prev = null;
        Node curr = head;
        while (curr != null) {
            Node nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        // Convert string to linked list
        Node head = null, tail = null;
        for (int i = 0; i < input.length(); i++) {
            Node newNode = new Node(input.charAt(i));
            if (head == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        // Check palindrome
        if (isPalindrome(head)) {
            System.out.println("Result: It is a Palindrome ✅");
        } else {
            System.out.println("Result: It is NOT a Palindrome ❌");
        }

        scanner.close();
    }
}