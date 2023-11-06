package Lab3.Menus;

import Lab3.behavior.AppLoop;
import Lab3.implementations.ArrayDownStack;
import Lab3.interfaces.MenuInterface;

import java.util.Scanner;

public class ArrayDownStackMenu implements MenuInterface {
    private Scanner scanner;
    private AppLoop appLoop;
    private boolean flag = true;
    private ArrayDownStack stack;

    ArrayDownStackMenu(Scanner scanner, AppLoop appLoop) {
        this.scanner = scanner;
        this.appLoop = appLoop;
        this.stack = new ArrayDownStack(1);
    }

    @Override
    public void handleInput() {
        String input = takeUserInput();
        String[] commandsList = input.split(" ");

        switch (commandsList[0]) {
            case "push" -> {
                this.flag = false;
                if (commandsList.length > 1) {
                    for (int i = 1; i < commandsList.length; i++) {
                        this.stack.push(commandsList[i]);
                    }
                }
                else {
                    while (true) {
                        String input1 = takeElementInput();
                        this.stack.push(input1);
                        System.out.println("MORE ELEMENTS? Y/N");
                        input1 = this.scanner.nextLine();
                        if (input1.equalsIgnoreCase("n")) {
                            break;
                        }
                    }
                }
            }
            case "pop" -> {
                this.flag = false;
                Object peekedElement = this.stack.peek();
                if (peekedElement != null) {
                    System.out.println("POPPED ELEMENT: " + this.stack.peek());
                    this.stack.pop();
                }

            }
            case "peek" -> {
                this.flag = false;
                Object peekedElement = this.stack.peek();
                if (peekedElement != null) {
                    System.out.println("LAST ELEMENT IN THE STACK: " + peekedElement);
                }
            }
            case "full", "f" -> {
                this.flag = false;
                System.out.println("STACK:\n" + this.stack.toString());
            }
            case "status" -> {
                this.flag = false;
                int sizeStack = this.stack.getStackArray().length;
                this.stack.setOccupiedSpace();
                int occupiedSpace = this.stack.getOccupiedSpace();
                System.out.print("STACK IS: ");
                if (this.stack.isEmpty()) {
                    System.out.println("EMPTY: 0 / " + sizeStack);
                }
                else if (this.stack.getOccupiedSpace() == sizeStack){
                    System.out.println("FULL: " + occupiedSpace + " / " + sizeStack + " ELEMENTS");
                }
                else {
                    System.out.println("PARTIALLY FULL: " + occupiedSpace + " / " + sizeStack + " ELEMENTS");
                }
            }
            case "search", "s" -> {
                this.flag = false;
                if (commandsList.length > 1) {
                    for (int i = 1; i < commandsList.length; i++) {
                        this.stack.search(commandsList[i]);
                    }
                }
                else {
                    String searchedElement = takeElementInput();
                    this.stack.search(searchedElement);
                }
            }
            case "empty" -> {
                this.flag = false;
                if (!this.stack.isEmpty()) {
                    this.stack.empty();
                }
                else {
                    System.out.println("NO ELEMENTS IN THE STACK!");
                }
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
        System.out.println("WELCOME TO ARRAY DOWN STACK IMPLEMENTATION MENU!");
    }
}
