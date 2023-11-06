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
        this.mainMenu.printMenu();
        this.mainMenu.printChoices();
        boolean flag = true;
        while(flag) {
            String input = this.mainMenu.takeUserInput();
            String[] commandsList = input.split(" ");
            switch (commandsList[0]) {
                case "c", "commit" -> {
                    this.snapshotManagementSystem.loadStateFromCurrSnapshot();
                    this.snapshotManagementSystem.commit();
                }
                case "s", "status" -> this.snapshotManagementSystem.printStatus();
                case "h", "help" -> this.mainMenu.printHelp();
                case "q", "quit" -> {
                    this.mainMenu.printExit();
                    flag = false;
                }
                case "i", "info" -> {
                    if (commandsList.length > 1) {
                        String fileName = commandsList[1];
                        this.snapshotManagementSystem.informationAboutFile(fileName);
                    } else {
                        System.out.println("WRONG INPUT!");
                    }
                }
                default -> System.out.println("INVALID COMMAND!");
            }
        }
        this.scanner.close();
    }
}
