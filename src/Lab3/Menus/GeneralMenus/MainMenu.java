package Lab3.Menus.GeneralMenus;

import Lab3.Menus.QueueMenus.QueueMenu;
import Lab3.Menus.StackMenus.StackMenu;
import Lab3.behavior.AppLoop;

import Lab3.interfaces.MenuInterface;

import java.util.Scanner;

public class MainMenu implements MenuInterface {
    Scanner scanner;
    AppLoop appLoop;
    private boolean flag = true;

    public MainMenu(Scanner scanner, AppLoop appLoop) {
        this.scanner = scanner;
        this.appLoop = appLoop;
    }

    @Override
    public void handleInput() {
        String input = takeUserInput(this.scanner);

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
    public void printQuit() {

    }

    @Override
    public void printGreetings() {
        System.out.println("WELCOME TO STACK&QUEUE!");
    }
}
