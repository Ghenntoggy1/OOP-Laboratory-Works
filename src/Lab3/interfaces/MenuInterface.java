package Lab3.interfaces;

import java.util.Scanner;

public interface MenuInterface {
    void handleInput();
    void printMenu();
    void printChoices();
    void printGreetings();

    default String takeUserInput(Scanner scanner) {
        System.out.println("INPUT CHOICE:");
        String input = scanner.nextLine();
        System.out.println("YOUR CHOICE: " + input);
        return input;
    }

    default void printQuit() {
        System.out.println("EXITTING MENU...");
    }

    default void printHelp() {
        System.out.println("HELP");
        printChoices();
    }

    default void printInvalid() {
        System.out.println("INVALID CHOICE!");
    }
}
