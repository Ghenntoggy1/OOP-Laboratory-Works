package Lab1.behavior;

import Lab1.models.Faculty;
import Lab1.models.StudyField;
import Lab1.models.University;

import java.util.Scanner;

public class AppLoop {
    private University university;
    private Scanner scanner;
    private String choice;
    private Printer printer;
    private Handler handler;

    public AppLoop() {
        this.university = new University();
        this.scanner = new Scanner(System.in);
        this.choice = "";
        this.printer = new Printer(this.university, this.scanner);
        this.handler = new Handler(this.printer, this.scanner, this.university);
    }

    private void handleGeneralMenuOption() {
        printer.generalOperationsMenu();
        String generalMenuOption = "";
        while (!generalMenuOption.equals("q")) {
            generalMenuOption = takeUserInput();
            String[] commandsList2 = generalMenuOption.split("/");
            switch (commandsList2[0]) {
                case "nf" -> handler.handleFacultyCreate(commandsList2);
                case "df" -> printer.printFaculties();
                case "sf" -> System.out.println("WIP");  // future feature
                case "q" -> {
                    System.out.println("| EXITING MENU...                             |");
                    System.out.println("+---------------------------------------------+");
                    printer.choiceStartMenu();
                }
                case "dff" -> handler.handleFacultyDisplay(commandsList2);
                case "h" -> printer.generalOperationsMenu();
                default -> System.out.println("| INVALID CHOICE! TRY AGAIN:                  |");
            }
        }
    }

    public void run() {
        printer.startMenu();
        while (!this.choice.equals("q")) {
            this.choice = takeUserInput();
            String[] commandsList = this.choice.split("/");

            switch (commandsList[0]) {
                case "g" -> handleGeneralMenuOption();
                case "f" -> System.out.println("WIP");  // future feature
                case "q" -> {
                    System.out.println("| EXITING PROGRAM...                          |");
                    System.out.println("+---------------------------------------------+");
                }
                case "h" -> printer.helpStartMenu();
                case "s" -> System.out.println("WIP");  // future feature
                default -> System.out.println("| INVALID CHOICE! TRY AGAIN:                  |");
            }
        }
        this.scanner.close();
    }

    private String takeUserInput() {
        System.out.println("| INPUT CHOICE:                               |");
        String sample = scanner.nextLine();
        System.out.println("+---------------------------------------------+");
        return sample;
    }
}
