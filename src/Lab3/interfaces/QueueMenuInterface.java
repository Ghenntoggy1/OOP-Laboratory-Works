package Lab3.interfaces;

import java.util.Scanner;

public interface QueueMenuInterface extends MenuInterface{
    default void printChoices() {
        System.out.println("enqueue, en <element> <element2> ... <elementN> - ENQUEUE ELEMENT");
        System.out.println("dequeue, d - DEQUEUE");
        System.out.println("peek, p - PEEK");
        System.out.println("status - IS STACK EMPTY?");
        System.out.println("search, s <element1> <element2> ... <elementN> - SEARCH ELEMENT");
        System.out.println("full, f - DISPLAY FULL STACK");
        System.out.println("empty - EMPTY THE STACK");
        System.out.println("help, h - HELP");
        System.out.println("exit, e - EXIT MENU");
    }

    @Override
    default void printQuit() {
        System.out.println("EXITTING MENU...");
    }

    default String takeElementInput(Scanner scanner) {
        System.out.println("INPUT ELEMENT:");
        String input = scanner.nextLine();
        System.out.println("YOUR ELEMENT: " + input);
        return input;
    }
}
