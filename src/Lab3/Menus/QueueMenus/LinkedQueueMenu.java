package Lab3.Menus.QueueMenus;

import Lab3.behavior.AppLoop;

import Lab3.implementations.Queue.LinkedQueue;

import Lab3.interfaces.QueueMenuInterface;

import java.util.Scanner;

public class LinkedQueueMenu implements QueueMenuInterface {
    private Scanner scanner;
    private AppLoop appLoop;
    private boolean flag = true;
    private LinkedQueue linkedQueue;

    public LinkedQueueMenu(Scanner scanner, AppLoop appLoop) {
        this.scanner = scanner;
        this.appLoop = appLoop;
        this.linkedQueue = new LinkedQueue();
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
                        this.linkedQueue.enqueue(commandsList[i]);
                    }
                }
                else {
                    while (true) {
                        String input1 = takeElementInput(this.scanner);
                        this.linkedQueue.enqueue(input1);
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
                Object rearElement = this.linkedQueue.getRearElement();
                if (rearElement != null) {
                    System.out.println("POPPED ELEMENT: " + this.linkedQueue.getRearElement());
                    this.linkedQueue.deque();
                }

            }
            case "peek" -> {
                this.flag = false;
                Object peekedElement = this.linkedQueue.peek();
                if (peekedElement != null) {
                    System.out.println("LAST ELEMENT IN THE QUEUE: " + peekedElement);
                }
            }
            case "full", "f" -> {
                this.flag = false;
                System.out.println("QUEUE:\n" + this.linkedQueue.toString());
            }
            case "status" -> {
                this.flag = false;
                System.out.print("QUEUE IS: ");
                if (this.linkedQueue.isEmpty()) {
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
                        this.linkedQueue.search(commandsList[i]);
                    }
                }
                else {
                    String searchedElement = takeElementInput(this.scanner);
                    this.linkedQueue.search(searchedElement);
                }
            }
            case "empty" -> {
                this.flag = false;
                if (!this.linkedQueue.isEmpty()) {
                    this.linkedQueue.empty();
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
                this.linkedQueue.deleteQueue();
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
        System.out.println("WELCOME TO LINKED LIST QUEUE IMPLEMENTATION MENU!");
    }
}
