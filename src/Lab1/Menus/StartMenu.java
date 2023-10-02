package Lab1.Menus;

import Lab1.behavior.AppLoop;
import Lab1.behavior.Printer;
import Lab1.interfaces.Menu;
import Lab1.models.University;

import java.util.Scanner;

public class StartMenu implements Menu {
    private AppLoop appLoop;
    private Scanner scanner;
    private University university;
    private Printer printer;

    public StartMenu(Scanner scanner, University university, Printer printer, AppLoop appLoop) {
        this.scanner = scanner;
        this.university = university;
        this.printer = printer;
        this.appLoop = appLoop;
    }

    public void printMenu() {
        printGreetings();
        printChoices();
    }

    public void printGreetings() {
        System.out.println("+---------------------------------------------+");
        System.out.println("| WELCOME TO TUM's STUDENT MANAGEMENT SYSTEM! |");
        System.out.println("+---------------------------------------------+");
    }
    @Override
    public void printChoices() {
        System.out.println("| WHAT DO YOU WANT TO DO?                     |");
        System.out.println("| g - GENERAL OPERATIONS                      |");
        System.out.println("| f - FACULTY OPERATIONS                      |");
        System.out.println("| h - HELP                                    |");
        System.out.println("+---------------------------------------------+");
        System.out.println("| q - QUIT PROGRAM                            |");
        System.out.println("+---------------------------------------------+");
    }
    @Override
    public void printHelp() {
        System.out.println("| COMMANDS LIST                               |");
        printChoices();
    }
    @Override
    public void printQuit() {
        System.out.println("| EXITING PROGRAM...                          |");
        System.out.println("+---------------------------------------------+");
    }
    @Override
    public String takeUserInput() {
        System.out.println("| INPUT CHOICE:                               |");
        String sample = scanner.nextLine();
        System.out.println("+---------------------------------------------+");
        return sample;
    }
    @Override
    public void printInvalid() {
        System.out.println("| INVALID CHOICE! TRY AGAIN:                  |");
    }
    @Override
    public void handleInput() {
        String input = takeUserInput();
        String[] commandsList = input.split("/");

        switch (commandsList[0]) {
            case "g" -> appLoop.activeMenu = new GeneralMenu(scanner, university, printer, appLoop);
            case "f" -> appLoop.activeMenu = new FacultyMenu(scanner, university, printer, appLoop);
            case "q" -> {
                printQuit();
                appLoop.activeMenu = new ExitMenu();
            }
            case "h" -> printHelp();
            default -> printInvalid();
        }
    }
}
