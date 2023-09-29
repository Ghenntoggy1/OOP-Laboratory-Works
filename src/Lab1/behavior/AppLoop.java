package Lab1.behavior;

import Lab1.models.Faculty;
import Lab1.models.StudyField;
import Lab1.models.University;

import java.util.Scanner;

public class AppLoop {
    private University university;
    private Scanner scanner;
    private String choice;

    public AppLoop() {
        this.university = new University();
        this.scanner = new Scanner(System.in);
        this.choice = "";
    }

    private void startMenu() {
        System.out.println("+---------------------------------------------+");
        System.out.println("| WELCOME TO TUM's STUDENT MANAGEMENT SYSTEM! |");
        System.out.println("| WHAT DO YOU WANT TO DO?                     |");
        choiceStartMenu();
    }

    private void helpStartMenu() {
        choiceStartMenu();
    }

    private void choiceStartMenu() {
        System.out.println("| g - GENERAL OPERATIONS                      |");
        System.out.println("| f - FACULTY OPERATIONS                      |");
        System.out.println("| s - STUDENT OPERATIONS                      |");
        System.out.println("| h - HELP                                    |");
        System.out.println("+---------------------------------------------+");
        System.out.println("| q - QUIT PROGRAM                            |");
        System.out.println("+---------------------------------------------+");
    }

    private void generalOperationsMenu() {
        System.out.println("| nf - CREATE A NEW FACULTY                                                                        |");
        System.out.println("| nf/<facultyName>/<facultyAbbreviation/<studyField> - CREATE A NEW FACULTY (FAST COMMAND)         |");
        System.out.println("| sf - SEARCH FACULTY A STUDENT BELONGS TO (BY EMAIL)                                              |");
        System.out.println("| df - DISPLAY UNIVERSITY FACULTIES                                                                |");
        System.out.println("| dff - DISPLAY FACULTIES BELONGING TO A FIELD                                                     |");
        System.out.println("| dff/<studyField> - DISPLAY FACULTIES BELONGING TO A FIELD (FAST COMMAND)                         |");
        System.out.println("| h - HELP MENU                                                                                    |");
        System.out.println("+--------------------------------------------------------------------------------------------------+");
        System.out.println("| q - QUIT MENU                                                                                    |");
        System.out.println("+--------------------------------------------------------------------------------------------------+");
    }

    private void handleGeneralMenuOption() {
        generalOperationsMenu();
        String generalMenuOption = "";
        while (!generalMenuOption.equals("q")) {
            generalMenuOption = takeUserInput();
            String[] commandsList2 = generalMenuOption.split("/");
            switch (commandsList2[0]) {
                case "nf" -> handleFacultyCreate(commandsList2);
                case "df" -> printFaculties();
                case "sf" -> System.out.println("WIP");  // future feature
                case "q" -> {
                    System.out.println("| EXITING MENU...                             |");
                    System.out.println("+---------------------------------------------+");
                    choiceStartMenu();
                }
                case "dff" -> handleFacultyDisplay(commandsList2);
                case "h" -> generalOperationsMenu();
                default -> System.out.println("| INVALID CHOICE! TRY AGAIN:                  |");
            }
        }
    }

    public void run() {
        startMenu();
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
                case "h" -> helpStartMenu();
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

    private void handleFacultyCreate(String[] commandsList) {
        if (commandsList.length == 4) {
            addFaculty(commandsList);
        }
        else {
            addFaculty();
        }
    }

    public void handleFacultyDisplay(String[] commandsList) {
        if (commandsList.length == 2) {
            printFacultiesByField(commandsList);
        }
        else {
            printFacultiesByField();
        }
    }

    private int getFacultyFieldIndex() {
        int indexInt = 0;
        boolean flag = true;

        while (flag) {
            try {
                String index = this.scanner.nextLine();
                indexInt = Integer.parseInt(index);

                flag = false;
                if (indexInt > StudyField.values().length || indexInt <= 0) {
                    System.out.println("| INVALID CHOICE OF FACULTY FIELD! CHOOSE FROM BELOW (INDEX): |");
                    for (StudyField studyField : StudyField.values()) {
                        System.out.println(studyField.ordinal() + 1 + ". " + studyField);
                    }
                    flag = true;
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("| INVALID CHOICE OF FACULTY FIELD! CHOOSE FROM BELOW (INDEX): |");
                for (StudyField studyField : StudyField.values()) {
                    System.out.println(studyField.ordinal() + 1 + ". " + studyField);
                }

            }
        }

        return indexInt;
    }
    private void addFaculty() {
        System.out.println("| INPUT FACULTY NAME:                         |");
        String facultyName = this.scanner.nextLine();
        System.out.println("+---------------------------------------------+");
        System.out.println("| INPUT FACULTY ABBREVIATION:                 |");
        String facultyAbbreviation = this.scanner.nextLine();
        System.out.println("+---------------------------------------------+");
        System.out.println("| CHOOSE FACULTY FIELD:                       |");
        for (StudyField studyField : StudyField.values()) {
            System.out.println(studyField.ordinal() + 1 + ". " + studyField);
        }
        int indexInt = getFacultyFieldIndex();
        StudyField facultyField = StudyField.values()[indexInt - 1];
        System.out.println("+---------------------------------------------+");
        Faculty faculty = new Faculty(facultyName, facultyAbbreviation, facultyField);
        this.university.addFaculty(faculty);
    }

    private void addFaculty(String[] arguments) {
        boolean flag = true;
        StudyField facultyField = null;
        while(flag) {
            try {
                facultyField = StudyField.valueOf(arguments[3]);
                flag = false;
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println("| INVALID FACULTY FIELD! INPUT FROM THE LIST: |");
                for (StudyField studyField : StudyField.values()) {
                    System.out.println(studyField.ordinal() + 1 + ". " + studyField);
                }
                int indexInt = getFacultyFieldIndex();
                facultyField = StudyField.values()[indexInt - 1];
                arguments[3] = facultyField.toString();
            }
        }

        Faculty faculty = new Faculty(arguments[1], arguments[2], facultyField);
        this.university.addFaculty(faculty);
    }

    private void printFaculties() {
        System.out.print(university.toString());
        System.out.println("+---------------------------------------------+");
    }

    private void printFaculties(StudyField studyField) {
        System.out.print(university.toString(studyField));
        System.out.println("+---------------------------------------------+");
    }

    private void printFacultiesByField() {
        System.out.println("| INPUT STUDY FIELD:                          |");
        for (StudyField studyField : StudyField.values()) {
            System.out.println(studyField.ordinal() + 1 + ". " + studyField);
        }
        int indexInt = getFacultyFieldIndex();
        StudyField facultyField = StudyField.values()[indexInt - 1];

        printFaculties(facultyField);
    }

    private void printFacultiesByField(String[] commandsList) {
        StudyField facultyField;
        try {
            facultyField = StudyField.valueOf(commandsList[1]);
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println("| INVALID FACULTY FIELD! TYPE NUMBER FROM THE LIST: |");
            for (StudyField studyField : StudyField.values()) {
                System.out.println(studyField.ordinal() + 1 + ". " + studyField);
            }
            int indexInt = getFacultyFieldIndex();
            facultyField = StudyField.values()[indexInt - 1];
        }
        printFaculties(facultyField);
    }
}
