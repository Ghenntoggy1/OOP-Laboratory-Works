package Lab3.interfaces;

import java.util.Scanner;

public interface StackMenuInterface extends MenuInterface{

    @Override
    default void printChoices() {
        System.out.println("push <element> <element2> ... <elementN> - PUSH ELEMENT");
        System.out.println("pop - POP");
        System.out.println("peek - PEEK");
        System.out.println("status - IS STACK EMPTY?");
        System.out.println("search, s <element1> <element2> ... <elementN> - SEARCH ELEMENT");
        System.out.println("full, f - DISPLAY FULL STACK");
        System.out.println("empty - EMPTY THE STACK");
        System.out.println("help, h - HELP");
        System.out.println("exit, e - EXIT MENU");
    }

    default String takeElementInput(Scanner scanner) {
        System.out.println("INPUT ELEMENT:");
        String input = scanner.nextLine();
        System.out.println("YOUR ELEMENT: " + input);
        return input;
    }
}
