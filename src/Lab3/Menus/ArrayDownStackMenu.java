package Lab3.Menus;

import Lab3.behavior.AppLoop;
import Lab3.implementations.ArrayDownStack;
import Lab3.interfaces.MenuInterface;

import java.util.Scanner;

public class ArrayDownStackMenu implements MenuInterface {
    private Scanner scanner;
    private AppLoop appLoop;
    private boolean flag = true;
    private ArrayDownStack<Object> stack;

    ArrayDownStackMenu(Scanner scanner, AppLoop appLoop) {
        this.scanner = scanner;
        this.appLoop = appLoop;
        this.stack = new ArrayDownStack<>(1);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public String takeUserInput() {
        System.out.println("INPUT CHOICE:");
        String input = this.scanner.nextLine();
        System.out.println("YOUR CHOICE: " + input);
        return input;
    }

    public String takeElementInput() {
        System.out.println("INPUT ELEMENT:");
        String input = this.scanner.nextLine();
        System.out.println("YOUR ELEMENT: " + input);
        return input;
    }

    @Override
    public void printChoices() {}

    @Override
    public void printMenu() {
        if (flag) {
            printGreetings();
            printStackChoices();
        }
        flag = true;
    }

    @Override
    public void printHelp() {
        System.out.println("\nCHOICES");
        printStackChoices();
    }

    @Override
    public void printQuit() {
        System.out.println("EXITTING MENU...");
    }

    @Override
    public void printInvalid() {
        System.out.println("INVALID CHOICE!");
    }

    @Override
    public void printGreetings() {
        System.out.println("WELCOME TO ARRAY UP STACK IMPLEMENTATION MENU!");
    }
}
