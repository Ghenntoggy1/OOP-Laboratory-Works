package Lab1.Menus;

import Lab1.behavior.AppLoop;
import Lab1.behavior.Printer;
import Lab1.interfaces.Menu;
import Lab1.models.Faculty;
import Lab1.models.StudyField;
import Lab1.models.University;

import java.util.Scanner;

public class GeneralMenu implements Menu {
    private Scanner scanner;
    private University university;
    private Printer printer;
    private AppLoop appLoop;
    
    public GeneralMenu(Scanner scanner, University university, Printer printer, AppLoop appLoop) {
        this.scanner = scanner;
        this.university = university;
        this.printer = printer;
        this.appLoop = appLoop;
    }

    @Override
    public String takeUserInput() {
        System.out.println("| INPUT CHOICE:                                                                                    |");
        String sample = scanner.nextLine();
        System.out.println("+--------------------------------------------------------------------------------------------------+");
        return sample;
    }

    @Override
    public void printMenu() {
        printGreetings();
        printChoices();
    }

    @Override
    public void printGreetings() {
        System.out.println("| GENERAL OPERATIONS:                                                                              |");
        System.out.println("+--------------------------------------------------------------------------------------------------+");
    }

    @Override
    public void printChoices() {
        System.out.println("| nf - CREATE A NEW FACULTY                                                                        |");
        System.out.println("| nf/<facultyName>/<facultyAbbreviation>/<studyField> - CREATE A NEW FACULTY (FAST COMMAND)        |");
        System.out.println("| sf - SEARCH FACULTY A STUDENT BELONGS TO                                                         |");
        System.out.println("| df - DISPLAY UNIVERSITY FACULTIES                                                                |");
        System.out.println("| dff - DISPLAY FACULTIES BELONGING TO A FIELD                                                     |");
        System.out.println("| dff/<studyField> - DISPLAY FACULTIES BELONGING TO A FIELD (FAST COMMAND)                         |");
        System.out.println("| h - HELP MENU                                                                                    |");
        System.out.println("+--------------------------------------------------------------------------------------------------+");
        System.out.println("| q - QUIT MENU                                                                                    |");
        System.out.println("+--------------------------------------------------------------------------------------------------+");
    }

    @Override
    public void printHelp() {
        System.out.println("| COMMAND LIST:                                                                                    |");
        printChoices();
    }

    @Override
    public void printQuit() {
        System.out.println("| EXITING MENU...                                                                                  |");
        System.out.println("+--------------------------------------------------------------------------------------------------+");
    }

    @Override
    public void printInvalid() {
        System.out.println("| INVALID CHOICE! TRY AGAIN:                                                                       |");
    }

    @Override
    public void handleInput() {
        String input = takeUserInput();
        String[] commandsList2 = input.split("/");
        switch (commandsList2[0]) {
            case "nf" -> {
                handleFacultyCreate(commandsList2);
                System.out.println("| SUCCESS!                                                                                         |");
            }
            case "df" -> {
                if (!this.university.getFacultyList().isEmpty()) {
                    printer.printFaculties();
                }
                else {
                    System.out.println("| FACULTIES NOT FOUND!                                                                             |");
                }
            }
            case "dff" -> {
                if (!this.university.getFacultyList().isEmpty()) {
                    handleFacultyDisplay(commandsList2);
                }
                else {
                    System.out.println("| FACULTIES NOT FOUND!                                                                             |");
                }
            }
            case "sf" -> System.out.println("WIP");  // future feature
            case "h" -> printHelp();
            case "q" -> {
                printQuit();
                appLoop.activeMenu = new StartMenu(scanner, university, printer, appLoop);
            }
            default -> printInvalid();
        }
    }

    private void handleFacultyCreate(String[] commandsList) {
        if (commandsList.length == 4) {
            handleAddFaculty(commandsList);
        }
        else {
            handleAddFaculty();
        }
    }

    private void handleFacultyDisplay(String[] commandsList) {
        if (commandsList.length == 2) {
            printer.printFacultiesByField(commandsList);
        }
        else {
            printer.printFacultiesByField();
        }
    }

    private void handleAddFaculty() {
        System.out.println("| INPUT FACULTY NAME:                                                                              |");
        String facultyName = this.scanner.nextLine();
        System.out.println("+--------------------------------------------------------------------------------------------------+");
        System.out.println("| INPUT FACULTY ABBREVIATION:                                                                      |");
        String facultyAbbreviation = this.scanner.nextLine();
        System.out.println("+--------------------------------------------------------------------------------------------------+");
        System.out.println("| CHOOSE FACULTY FIELD:                                                                            |");
        for (StudyField studyField : StudyField.values()) {
            System.out.println(studyField.ordinal() + 1 + ". " + studyField);
        }
        int indexInt = university.getFacultyFieldIndex(this.scanner);
        StudyField facultyField = StudyField.values()[indexInt - 1];
        System.out.println("+---------------------------------------------------------------------------------------------------+");
        Faculty faculty = new Faculty(facultyName, facultyAbbreviation, facultyField);
        this.university.addFaculty(faculty);
    }

    private void handleAddFaculty(String[] commandsList) {
        boolean flag = true;
        StudyField facultyField = null;
        while(flag) {
            try {
                facultyField = StudyField.valueOf(commandsList[3]);
                flag = false;
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println("| INVALID FACULTY FIELD! INPUT FROM THE LIST:                                                      |");
                for (StudyField studyField : StudyField.values()) {
                    System.out.println(studyField.ordinal() + 1 + ". " + studyField);
                }
                int indexInt = university.getFacultyFieldIndex(this.scanner);
                facultyField = StudyField.values()[indexInt - 1];
                commandsList[3] = facultyField.toString();
            }
        }

        Faculty faculty = new Faculty(commandsList[1], commandsList[2], facultyField);
        this.university.addFaculty(faculty);
    }
}
