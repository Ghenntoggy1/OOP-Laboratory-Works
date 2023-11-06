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
        String input = takeUserInput();

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
        System.out.println("up, u - ARRAY UP IMPLEMENTATION");
        System.out.println("down, d - ARRAY DOWN IMPLEMENTATION");
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
        System.out.println("STACK IMPLEMENTATIONS MENU");
    }
}


//StackInterface<Integer> stackInteger = new ArrayStackUp<>(1, this.scanner);
//                String s = scanner.nextLine();
//                stackInteger.push(Integer.parseInt(s));
//                String result = stackInteger.toString();
//                System.out.println(result);
//                if (stackInteger.isEmpty()) {
//                    System.out.println("True");
//                }
//                else {
//                    System.out.println("False");
//                }
//                s = scanner.nextLine();
//                stackInteger.push(Integer.parseInt(s));
//                result = stackInteger.toString();
//                System.out.println(result);
//                if (stackInteger.isEmpty()) {
//                    System.out.println("True");
//                }
//                else {
//                    System.out.println("False");
//                }
//                int value = stackInteger.peek();
//                System.out.println("Last value: " + value);
//                stackInteger.pop();
//                value = stackInteger.peek();
//                System.out.println("Last value: " + value);
//                System.out.println(stackInteger);
//                if (stackInteger.isEmpty()) {
//                    System.out.println("True");
//                }
//                else {
//                    System.out.println("False");
//                }
//                stackInteger.pop();
//                System.out.println(stackInteger);
//                if (stackInteger.isEmpty()) {
//                    System.out.println("True");
//                }
//                else {
//                    System.out.println("False");
//                }
//                s = scanner.nextLine();
//                stackInteger.push(Integer.parseInt(s));
//                result = stackInteger.toString();
//                System.out.println(result);
//                if (stackInteger.isEmpty()) {
//                    System.out.println("True");
//                }
//                else {
//                    System.out.println("False");
//                }
//                value = stackInteger.peek();
//                System.out.println("Last value: " + value);
