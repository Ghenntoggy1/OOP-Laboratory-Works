package Lab1.behavior;

import Lab1.models.StudyField;
import Lab1.models.University;

import java.util.Scanner;

public class Printer {
    private University university;
    private Scanner scanner;

    public Printer(University university, Scanner scanner) {
        this.university = university;
        this.scanner = scanner;
    }

     void startMenu() {
        System.out.println("+---------------------------------------------+");
        System.out.println("| WELCOME TO TUM's STUDENT MANAGEMENT SYSTEM! |");
        System.out.println("| WHAT DO YOU WANT TO DO?                     |");
        choiceStartMenu();
    }

     void helpStartMenu() {
        choiceStartMenu();
    }

     void choiceStartMenu() {
        System.out.println("| g - GENERAL OPERATIONS                      |");
        System.out.println("| f - FACULTY OPERATIONS                      |");
        System.out.println("| s - STUDENT OPERATIONS                      |");
        System.out.println("| h - HELP                                    |");
        System.out.println("+---------------------------------------------+");
        System.out.println("| q - QUIT PROGRAM                            |");
        System.out.println("+---------------------------------------------+");
    }

     void generalOperationsMenu() {
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
    
     void printFaculties() {
        System.out.print(university.toString());
        System.out.println("+---------------------------------------------+");
    }

     void printFaculties(StudyField studyField) {
        System.out.print(university.toString(studyField));
        System.out.println("+---------------------------------------------+");
    }

     void printFacultiesByField() {
        System.out.println("| INPUT STUDY FIELD:                          |");
        for (StudyField studyField : StudyField.values()) {
            System.out.println(studyField.ordinal() + 1 + ". " + studyField);
        }
        int indexInt = university.getFacultyFieldIndex(this.scanner);
        StudyField facultyField = StudyField.values()[indexInt - 1];

        printFaculties(facultyField);
    }

     void printFacultiesByField(String[] commandsList) {
        StudyField facultyField;
        try {
            facultyField = StudyField.valueOf(commandsList[1]);
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println("| INVALID FACULTY FIELD! TYPE NUMBER FROM THE LIST: |");
            for (StudyField studyField : StudyField.values()) {
                System.out.println(studyField.ordinal() + 1 + ". " + studyField);
            }
            int indexInt = university.getFacultyFieldIndex(this.scanner);
            facultyField = StudyField.values()[indexInt - 1];
        }
        printFaculties(facultyField);
    }
}
