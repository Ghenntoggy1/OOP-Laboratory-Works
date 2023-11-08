package Lab3.Menus;

import Lab3.behavior.AppLoop;
import Lab3.interfaces.MenuInterface;

import java.util.Scanner;

public class StackMenu implements MenuInterface {
    private Scanner scanner;
    private AppLoop appLoop;
    private boolean flag = true;

    public StackMenu(Scanner scanner, AppLoop appLoop) {
        this.scanner = scanner;
        this.appLoop = appLoop;
    }

    @Override
    public void handleInput() {
        String input = takeUserInput(this.scanner);

        switch (input) {
            case "up", "u" -> {
                this.appLoop.setActiveMenu(new ArrayUpStackMenu(this.scanner, this.appLoop));
            }
            case "down", "d" -> {
                this.appLoop.setActiveMenu(new ArrayDownStackMenu(this.scanner, this.appLoop));
            }
            case "linked", "l" -> {
                this.appLoop.setActiveMenu(new LinkedStackMenu(this.scanner, this.appLoop));
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
    public void printMenu() {
        if (flag) {
            printGreetings();
            printChoices();
        }
        flag = true;
    }

    @Override
    public void printChoices() {
        System.out.println("up, u - ARRAY UP IMPLEMENTATION");
        System.out.println("down, d - ARRAY DOWN IMPLEMENTATION");
        System.out.println("linked, l - LINKED LIST IMPLEMENTATION");
        System.out.println("help, h - HELP");
        System.out.println("exit, e - EXIT MENU");
    }

    @Override
    public void printQuit() {
        System.out.println("EXITTING MENU...");
    }

    @Override
    public void printGreetings() {
        System.out.println("STACK IMPLEMENTATIONS MENU");
    }
}