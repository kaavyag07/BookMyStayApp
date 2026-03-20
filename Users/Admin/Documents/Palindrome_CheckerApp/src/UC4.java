

import java.util.Scanner;

public class UC4
{

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter a string: ");
        String input = scanner.nextLine();


        char[] charArray = input.toCharArray();


        int start = 0;
        int end = charArray.length - 1;

        boolean isPalindrome = true;


        while (start < end) {
            if (charArray[start] != charArray[end]) {
                isPalindrome = false;
                break;
            }
            start++;
            end--;
        }


        if (isPalindrome) {
            System.out.println("Result: It is a Palindrome ✅");
        } else {
            System.out.println("Result: It is NOT a Palindrome ❌");
        }


        scanner.close();
    }
}