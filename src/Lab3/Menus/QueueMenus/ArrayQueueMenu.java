package Lab3.Menus.QueueMenus;

import Lab3.behavior.AppLoop;

import Lab3.implementations.Queue.ArrayQueue;

import Lab3.interfaces.QueueMenuInterface;

import java.util.Scanner;

public class ArrayQueueMenu implements QueueMenuInterface {
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
        String input = takeUserInput(this.scanner);
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
                        String input1 = takeElementInput(this.scanner);
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
                    System.out.println("LAST ELEMENT IN THE QUEUE: " + peekedElement);
                }
            }
            case "full", "f" -> {
                this.flag = false;
                System.out.println("QUEUE:\n" + this.arrayQueue.toString());
            }
            case "status" -> {
                this.flag = false;
                System.out.print("QUEUE IS: ");
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
                    String searchedElement = takeElementInput(this.scanner);
                    this.arrayQueue.search(searchedElement);
                }
            }
            case "empty" -> {
                this.flag = false;
                if (!this.arrayQueue.isEmpty()) {
                    this.arrayQueue.empty();
                }
                else {
                    System.out.println("NO ELEMENTS IN THE QUEUE!");
                }
            }
            case "help", "h" -> {
                this.flag = false;
                printHelp();
            }
            case "exit", "e" -> {
                this.arrayQueue.deleteQueue();
                printQuit();
                this.appLoop.setActiveMenu(new QueueMenu(this.scanner, this.appLoop));
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
    public void printGreetings() {
        System.out.println("WELCOME TO ARRAY QUEUE IMPLEMENTATION MENU!");
    }
}
