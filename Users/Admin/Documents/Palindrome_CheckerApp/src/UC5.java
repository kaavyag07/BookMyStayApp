
import java.util.Scanner;
import java.util.Stack;

public class UC5 {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter a string: ");
        String input = scanner.nextLine();


        Stack<Character> stack = new Stack<>();


        for (int i = 0; i < input.length(); i++) {
            stack.push(input.charAt(i));
        }


        String reversed = "";
        while (!stack.isEmpty()) {
            reversed = reversed + stack.pop();
        }


        System.out.println("Reversed String: " + reversed);


        if (input.equals(reversed)) {
            System.out.println("Result: It is a Palindrome ✅");
        } else {
            System.out.println("Result: It is NOT a Palindrome ❌");
        }


        scanner.close();
    }
}