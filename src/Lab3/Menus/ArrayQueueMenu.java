package Lab3.Menus;

import Lab3.behavior.AppLoop;
import Lab3.implementations.ArrayQueue;
import Lab3.interfaces.MenuInterface;

import java.util.Scanner;

public class ArrayQueueMenu implements MenuInterface {
    private Scanner scanner;
    private AppLoop appLoop;
    private boolean flag = true;
    private ArrayQueue arrayQueue;

    public ArrayQueueMenu(Scanner scanner, AppLoop appLoop) {
        this.scanner = scanner;
        this.appLoop = appLoop;
        this.arrayQueue = new ArrayQueue(1);
    }

    @Override
    public void handleInput() {
        String input = takeUserInput();
        String[] commandsList = input.split(" ");

        switch (commandsList[0]) {
            case "enqueue", "en" -> {
                this.flag = false;
                if (commandsList.length > 1) {
                    for (int i = 1; i < commandsList.length; i++) {
                        this.arrayQueue.enqueue(commandsList[i]);
                    }
                }
                else {
                    while (true) {
                        String input1 = takeElementInput();
                        this.arrayQueue.enqueue(input1);
                        System.out.println("MORE ELEMENTS? Y/N");
                        input1 = this.scanner.nextLine();
                        if (input1.equalsIgnoreCase("n")) {
                            break;
                        }
                    }
                }
            }
            case "dequeue", "d" -> {
                this.flag = false;
                Object rearElement = this.arrayQueue.getRearElement();
                if (rearElement != null) {
                    System.out.println("POPPED ELEMENT: " + this.arrayQueue.getRearElement());
                    this.arrayQueue.deque();
                }

            }
            case "peek" -> {
                this.flag = false;
                Object peekedElement = this.arrayQueue.peek();
                if (peekedElement != null) {
                    System.out.println("LAST ELEMENT IN THE STACK: " + peekedElement);
                }
            }
            case "full", "f" -> {
                this.flag = false;
                System.out.println("STACK:\n" + this.arrayQueue.toString());
            }
            case "status" -> {
                this.flag = false;
                System.out.print("STACK IS: ");
                if (this.arrayQueue.isEmpty()) {
                    System.out.println("EMPTY");
                }
                else {
                    System.out.println("FULL");
                }
            }
            case "search", "s" -> {
                this.flag = false;
                if (commandsList.length > 1) {
                    for (int i = 1; i < commandsList.length; i++) {
                        this.arrayQueue.search(commandsList[i]);
                    }
                }
                else {
                    String searchedElement = takeElementInput();
                    this.arrayQueue.search(searchedElement);
                }
            }
            case "empty" -> {
                this.flag = false;
                if (!this.arrayQueue.isEmpty()) {
                    this.arrayQueue.empty();
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
                this.appLoop.setActiveMenu(new QueueMenu(this.scanner, this.appLoop));
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
    public void printChoices() {
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
        System.out.println("WELCOME TO ARRAY QUEUE IMPLEMENTATION MENU!");
    }
}
