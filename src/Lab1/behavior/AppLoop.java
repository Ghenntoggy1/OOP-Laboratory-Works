package Lab1.behavior;

import Lab1.models.Faculty;
import Lab1.models.StudyField;
import Lab1.models.University;

import java.util.Scanner;

public class AppLoop {
    private University university;
    private Scanner scanner;
    private String choice;
    // may use state for different menus

    public AppLoop() {
        this.university = new University();
        this.scanner = new Scanner(System.in);
        this.choice = "";
    }

    public void run() {
        System.out.println("WELCOME TO TUM's STUDENT MANAGEMENT SYSTEM!");
        System.out.println("WHAT DO YOU WANT TO DO?");
        System.out.println("g - GENERAL OPERATIONS");
        System.out.println("f - FACULTY OPERATIONS");
        System.out.println("s - STUDENT OPERATIONS");
        System.out.println("\nq - QUIT PROGRAM");

        while (!this.choice.equals("q")) {
            this.choice = takeUserInput();
            String[] commandsList = this.choice.split("/");

            switch (commandsList[0]) {
                case "f" -> {
                    handleFacultyCreate(commandsList);
                }
                case "pf" -> printFaculties();
                case "q" -> {}
                default -> {
                    System.out.print("INVALID CHOICE! TRY AGAIN: ");
                }
            }
        }
        this.scanner.close();
    }

    private String takeUserInput() {
        System.out.println("INPUT CHOICE: ");
        return scanner.nextLine();
    }

    private void handleFacultyCreate(String[] commandsList) {
        if (commandsList.length == 4) {
            addFaculty(commandsList);
        }
        else {
            addFaculty();
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
                    System.out.println("INVALID CHOICE OF FACULTY FIELD! CHOOSE FROM BELOW (INDEX): ");
                    for (StudyField studyField : StudyField.values()) {
                        System.out.println(studyField.ordinal() + 1 + ". " + studyField);
                    }
                    flag = true;
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("INVALID CHOICE OF FACULTY FIELD! CHOOSE FROM BELOW (INDEX): ");
                for (StudyField studyField : StudyField.values()) {
                    System.out.println(studyField.ordinal() + 1 + ". " + studyField);
                }
            }
        }

        return indexInt;
    }


    private void addFaculty() {
        System.out.println("INPUT FACULTY NAME: ");
        String facultyName = this.scanner.nextLine();
        System.out.println("INPUT FACULTY ABBREVIATION: ");
        String facultyAbbreviation = this.scanner.nextLine();
        System.out.println("CHOOSE FACULTY FIELD: ");
        for (StudyField studyField : StudyField.values()) {
            System.out.println(studyField.ordinal() + 1 + ". " + studyField);
        }
        int indexInt = getFacultyFieldIndex();
        StudyField facultyField = StudyField.values()[indexInt - 1];

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
                System.out.println("INVALID FACULTY FIELD! INPUT FROM THE LIST:");
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
        System.out.println(university);
    }
}
