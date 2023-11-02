package Lab3.Menus;

import Lab3.Menus.ExitMenu;
import Lab3.behavior.AppLoop;
import Lab3.interfaces.Menu;

import java.util.Scanner;

public class MainMenu implements Menu {
    Scanner scanner;
    AppLoop appLoop;
    private boolean flag = true;

    public MainMenu(Scanner scanner, AppLoop appLoop) {
        this.scanner = scanner;
        this.appLoop = appLoop;
    }

    @Override
    public void handleInput() {
        String input = takeUserInput();

        switch (input) {
            case "s", "stack" -> {
                appLoop.setActiveMenu(new StackMenu(this.scanner, this.appLoop));
            }
            case "q", "queue" -> {
                appLoop.setActiveMenu(new QueueMenu(this.scanner, this.appLoop));
            }
            case "h", "help" -> {
                flag = false;
                printHelp();
            }
            case "e", "exit" -> {
                printQuit();
                appLoop.setActiveMenu(new ExitMenu());
            }
            default -> printInvalid();
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
        System.out.println("stack, s - STACK IMPLEMENTATIONS");
        System.out.println("queue, q - QUEUE IMPLEMENTATIONS");
        System.out.println("help, h - CHOICES");
        System.out.println("exit, e - EXIT PROGRAM");
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

    }

    @Override
    public void printInvalid() {
        System.out.println("INVALID CHOICE!");
    }

    @Override
    public void printGreetings() {
        System.out.println("WELCOME TO STACK&QUEUE!");
    }
}
