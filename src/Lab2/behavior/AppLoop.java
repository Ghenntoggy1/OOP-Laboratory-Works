package Lab2.behavior;

import Lab2.Menu.MainMenu;

import java.sql.Timestamp;
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
        boolean flag = true;
        while(flag) {
            String input = mainMenu.takeUserInput();
            String[] commandsList = input.split(" ");
            switch (commandsList[0]) {
                case "c", "commit" -> {
                    snapshotManagementSystem.loadStateFromCurrSnapshot();
                    snapshotManagementSystem.commit();
                }
                case "s", "status" -> snapshotManagementSystem.status();
                case "h", "help" -> mainMenu.printHelp();
                case "q", "quit" -> {
                    mainMenu.printExit();
                    flag = false;
                }
                case "i", "info" -> {
                    if (commandsList.length > 1) {
                        String fileName = commandsList[1];

                    } else {
                        System.out.println("WRONG INPUT!");
                    }
                }
                default -> System.out.println("INVALID COMMAND!");
            }
        }
        scanner.close();
    }
}
