package Lab1.Menus;

import Lab1.Managers.FileManager;
import Lab1.Managers.Logger;
import Lab1.behavior.AppLoop;
import Lab1.behavior.Handler;
import Lab1.behavior.Printer;
import Lab1.interfaces.Menu;
import Lab1.models.University;

import java.util.Scanner;

public class ExitMenu implements Menu {
    private Scanner scanner;
    private University university;
    private FileManager fileManager;
    private Logger logger;

    public ExitMenu(Scanner scanner, University university, Printer printer, AppLoop appLoop) {
        this.scanner = scanner;
        this.university = university;
        this.fileManager = appLoop.getFileManager();
        this.logger = appLoop.getLogger();
    }

    @Override
    public void handleInput() {
        boolean flag = true;
        while (flag) {
            String input = takeUserInput();
            switch (input) {
                case "1", "S", "s" -> {
                    fileManager.saveUniversityData(university);
                    logger.saveStoreDatabase();
                    flag = false;
                }
                case "2", "R", "r" -> {
                    fileManager.resetDatabases();
                    logger.saveResetDatabase();
                    flag = false;
                }
                default -> System.out.println("| INVALID CHOICE! TRY AGAIN:         |");
            }
        }
    }

    @Override
    public String takeUserInput() {
        System.out.println("| INPUT CHOICE:                                                                                                                    |");
        String sample = scanner.nextLine();
        System.out.println("+--------------------------------------------------------------------------------------------------+");
        return sample;
    }

    @Override
    public void printMenu() {
        System.out.println("+------------------------------------+");
        System.out.println("| DO YOU WANT TO SAVE OR RESET DATA? |");
        System.out.println("| 1. SAVE                            |");
        System.out.println("| 2. RESET                           |");
        System.out.println("+------------------------------------+");
    }

    @Override
    public void printChoices() {
        System.out.println("+------------------------------------+");
        System.out.println("| DO YOU WANT TO RESET LOG FILE?     |");
        System.out.println("| 1. YES                             |");
        System.out.println("| 2. NO                              |");
        System.out.println("+------------------------------------+");
        boolean flag = true;
        while (flag) {
            String input = scanner.nextLine();
            switch (input) {
                case "1", "Y", "y" -> {
                    logger.resetLog();
                    logger.saveResetLog();
                    flag = false;
                }
                case "2", "N", "n" -> {
                    flag = false;
                }
                default -> System.out.println("| INVALID CHOICE! TRY AGAIN:         |");
            }
        }
    }

    @Override
    public void printHelp() {

    }

    @Override
    public void printQuit() {
        System.out.println("| EXITING PROGRAM...                 |");
        System.out.println("+------------------------------------+");
    }

    @Override
    public void printInvalid() {

    }

    @Override
    public void printGreetings() {

    }
}
