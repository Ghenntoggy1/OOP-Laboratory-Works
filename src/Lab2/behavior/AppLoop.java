package Lab2.behavior;

import Lab2.Menu.MainMenu;

import java.util.Scanner;

public class AppLoop {
    Scanner scanner;
    MainMenu mainMenu;
    SnapshotManagementSystem snapshotManagementSystem;
    public AppLoop() {
        this.scanner = new Scanner(System.in);
        this.mainMenu = new MainMenu(this.scanner);
        this.snapshotManagementSystem = new SnapshotManagementSystem();
    }
    public void run() {
        mainMenu.printMenu();
        mainMenu.printChoices();
        while(true) {
            String input = mainMenu.takeUserInput();
            String[] commandsList = input.split(" ");
            if (commandsList[0].equals("q") || commandsList[0].equals("quit")) {
                mainMenu.printExit();
                break;
            }
            switch (commandsList[0]) {
                case "c", "commit" -> {
                    snapshotManagementSystem.commit();
                    System.out.println(snapshotManagementSystem.getLastSnapshotDate());
                }
                case "s", "status" -> System.out.println("Status WIP");
                case "h", "help" -> mainMenu.printHelp();
                default -> {
                    if (commandsList[0].equals("i") || commandsList[0].equals("info")) {
                        if (commandsList.length > 1) {
                            String fileName = commandsList[1];
                            System.out.println(fileName);
                            System.out.println("Info WIP");
                        } else {
                            System.out.println("WRONG INPUT!");
                        }
                    } else {
                        System.out.println("INVALID COMMAND!");
                    }
                }
            }
        }
        scanner.close();
    }
}
