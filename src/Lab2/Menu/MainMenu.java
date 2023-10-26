package Lab2.Menu;

import Lab1.Menus.ExitMenu;
import Lab1.Menus.FacultyMenu;
import Lab1.Menus.GeneralMenu;

import java.util.Scanner;

public class MainMenu {
    Scanner scanner;

    public MainMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public String takeUserInput() {
        System.out.println("INPUT CHOICE:");
        String sample = scanner.nextLine();
        System.out.println("YOUR CHOICE: " + sample);
        return sample;
    }

    public void handleInput() {
        while(true) {
            String input = takeUserInput();
            String[] commandsList = input.split(" ");
            if (commandsList[0].equals("q") || commandsList[0].equals("quit")) {
                break;
            }
            switch (commandsList[0]) {
                case "c", "commit" -> System.out.println("Commit WIP");
                case "s", "status" -> System.out.println("Status WIP");
                case "h", "help" -> printHelp();
                default -> {
                    if (commandsList[0].equals("i") || commandsList[0].equals("info")) {
                        if (commandsList.length > 1) {
                            String fileName = commandsList[1];
                            System.out.println(fileName);
                        }
                        else {
                            System.out.println("WRONG INPUT!");
                        }
                    }
                    else {
                        System.out.println("INVALID COMMAND!");
                    }
                }
            }
        }
    }

    public void printChoices() {
        System.out.println("commit - COMMIT");
        System.out.println("info <filename> - DISPLAY INFORMATION ABOUT <filename>");
        System.out.println("status - DISPLAY CHANGES");
    }

    public void printMenu() {
        System.out.println("WELCOME TO MyGit!");
        System.out.println("WHAT DO YOU WANT TO DO?");
    }

    public void printHelp() {
        System.out.println("CHOICES");
        printChoices();
    }

    public void printExit() {
        System.out.println("EXITTING PROGRAM...");
    }
}
