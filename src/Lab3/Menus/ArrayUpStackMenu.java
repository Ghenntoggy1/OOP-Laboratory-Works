package Lab3.Menus;

import Lab3.behavior.AppLoop;
import Lab3.interfaces.Menu;
import Lab3.interfaces.StackInterface;

import java.util.Scanner;

public class ArrayUpStackMenu implements Menu {
    private Scanner scanner;
    private AppLoop appLoop;
    private boolean flag = true;
    private Class<?> dataType;

    public ArrayUpStackMenu(Scanner scanner, AppLoop appLoop) {
        this.scanner = scanner;
        this.appLoop = appLoop;
    }

    @Override
    public void handleInput() {
        System.out.println("Choose data type (Integer or String): ");
        String dataTypeInput = scanner.nextLine().toLowerCase();

        if (dataTypeInput.equals("integer")) {
            dataType = Integer.class;
        } else if (dataTypeInput.equals("string")) {
            dataType = String.class;
        } else {
            System.out.println("Invalid data type choice. Please choose Integer or String.");
            dataType = null;
        }

        String input = takeUserInput();
        String[] commandsList = input.split(" ");

        switch (commandsList[0]) {
            case "push" -> {
                System.out.println("PUSH WIP");
            }
            case "pop" -> {
                System.out.println("POP WIP");
            }
            case "peek" -> {
                System.out.println("PEEK WIP");
            }
            case "search" -> {
                System.out.println("SEARCH WIP");
            }
            case "help", "h" -> {
                this.flag = false;
                printHelp();
            }
            case "exit", "e" -> {
                printQuit();
                this.appLoop.setActiveMenu(new StackMenu(this.scanner, this.appLoop));
            }
            default -> {
                printInvalid();
            }
        }
    }

    @Override
    public String takeUserInput() {
        System.out.println("INPUT CHOICE:");
        String input = scanner.nextLine();
        System.out.println("YOUR CHOICE: " + input);
        return input;
    }

    @Override
    public void printChoices() {
        System.out.println("push <element> - PUSH ELEMENT");
        System.out.println("pop <element> - POP ELEMENT");
        System.out.println("peek - PEEK");
        System.out.println("search <element> - SEARCH ELEMENT");
        System.out.println("help, h - HELP");
        System.out.println("exit, e - EXIT MENU");
    }

    @Override
    public void printMenu() {
        if (flag) {
            printGreetings();
            printChoices();
        }
        flag = true;
    }

    @Override
    public void printHelp() {
        System.out.println("\nCHOICES");
        printChoices();
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
