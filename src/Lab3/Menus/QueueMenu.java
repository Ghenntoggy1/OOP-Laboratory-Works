package Lab3.Menus;

import Lab3.behavior.AppLoop;
import Lab3.interfaces.MenuInterface;

import java.util.Scanner;

public class QueueMenu implements MenuInterface {
    private Scanner scanner;
    private AppLoop appLoop;
    private boolean flag = true;

    public QueueMenu(Scanner scanner, AppLoop appLoop) {
        this.scanner = scanner;
        this.appLoop = appLoop;
    }

    @Override
    public void handleInput() {
        String input = takeUserInput();

        switch (input) {
            case "array", "a" -> {
                this.appLoop.setActiveMenu(new ArrayQueueMenu(this.scanner, this.appLoop));
            }
            case "priority", "p" -> {
                System.out.println("priority WIP");
                //this.appLoop.setActiveMenu(new PriorityQueueMenu(this.scanner, this.appLoop));
            }
            case "linked", "l" -> {
                this.appLoop.setActiveMenu(new LinkedQueueMenu(this.scanner, this.appLoop));
            }
            case "help", "h" -> {
                this.flag = false;
                printHelp();
            }
            case "exit", "e" -> {
                printQuit();
                this.appLoop.setActiveMenu(new MainMenu(this.scanner, this.appLoop));
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
    public void printMenu() {
        if (flag) {
            printGreetings();
            printChoices();
        }
        flag = true;
    }

    @Override
    public void printChoices() {
        System.out.println("array, a - ARRAY IMPLEMENTATION");
        System.out.println("priority, p - PRIORITY QUEUE IMPLEMENTATION");
        System.out.println("linked, l - LINKED LIST IMPLEMENTATION");
        System.out.println("help, h - HELP");
        System.out.println("exit, e - EXIT MENU");
    }

    @Override
    public void printHelp() {
        System.out.println("HELP");
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
        System.out.println("QUEUE IMPLEMENTATIONS MENU");
    }
}
