import java.util.Scanner;
import java.util.Stack;

public class infix_to_postfix_prifix_convertor {

    // Precedence function
    static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-': return 1;
            case '*':
            case '/': return 2;
            case '^': return 3;
        }
        return -1;
    }

    // Infix to Postfix
    static String infixToPostfix(String exp) {
        String result = "";
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                result += c;
            } 
            else if (c == '(') {
                stack.push(c);
            } 
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result += stack.pop();
                }
                stack.pop();
            } 
            else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    result += stack.pop();
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    // Infix to Prefix
    static String infixToPrefix(String exp) {
        StringBuilder input = new StringBuilder(exp);
        input.reverse();

        // Swap brackets
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(')
                input.setCharAt(i, ')');
            else if (input.charAt(i) == ')')
                input.setCharAt(i, '(');
        }

        String postfix = infixToPostfix(input.toString());
        return new StringBuilder(postfix).reverse().toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== INFIX CONVERTER =====");
            System.out.println("1. Convert to Postfix");
            System.out.println("2. Convert to Prefix");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            if (choice == 1 || choice == 2) {
                System.out.print("Enter Infix Expression: ");
                String infix = sc.nextLine();

                if (choice == 1) {
                    System.out.println("Postfix: " + infixToPostfix(infix));
                } else {
                    System.out.println("Prefix: " + infixToPrefix(infix));
                }
            } 
            else if (choice == 3) {
                System.out.println("Exiting program...");
            } 
            else {
                System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 3);

        sc.close();
    }
}