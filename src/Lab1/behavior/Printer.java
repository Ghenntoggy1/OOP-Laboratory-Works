package Lab1.behavior;

import Lab1.models.Faculty;
import Lab1.models.Student;
import Lab1.models.StudyField;
import Lab1.models.University;

import java.util.List;
import java.util.Scanner;

public class Printer {
    // TODO implement the concept of Abstraction, and create MenuPrinter, HelpPrinter, GeneralPrinter
    // printMenu
    // printChoices
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
        System.out.println("| h - HELP                                    |");
        System.out.println("+---------------------------------------------+");
        System.out.println("| q - QUIT PROGRAM                            |");
        System.out.println("+---------------------------------------------+");
    }

    // TODO CONSISTENT NAMING
    void generalOperationsOptions() {
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

     void generalOperationsMenu() {
        System.out.println("| GENERAL OPERATIONS:                                                                              |");
        System.out.println("+--------------------------------------------------------------------------------------------------+");
        generalOperationsOptions();
    }

    void helpGeneralMenu() {
        generalOperationsOptions();
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
    void facultyOperationsOptions() {
        System.out.println("| cs - CREATE AND ASSIGN A NEW STUDENT                                                                                             |");
        System.out.println("| cs/<facultyName>/<firstName>/<lastName>/<email>/<enrollmentDate>/<dateOfBirth> - CREATE AND ASSIGN A NEW STUDENT (FAST COMMAND)  |");
        System.out.println("| gs - GRADUATE STUDENT                                                                                                            |");
        System.out.println("| das - DISPLAY ALL STUDENTS                                                                                                       |");
        System.out.println("| des - DISPLAY ENROLLED STUDENTS                                                                                                  |");
        System.out.println("| dgs - DISPLAY GRADUATED STUDENTS                                                                                                 |");
        System.out.println("| csf - CHECK STUDENT                                                                                                              |");
        System.out.println("| h - HELP MENU                                                                                                                    |");
        System.out.println("+----------------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("| q - QUIT MENU                                                                                                                    |");
        System.out.println("+----------------------------------------------------------------------------------------------------------------------------------+");
    }

    void facultyOperationsMenu() {
        System.out.println("| FACULTY OPERATIONS:                                                                                                              |");
        System.out.println("+----------------------------------------------------------------------------------------------------------------------------------+");
        facultyOperationsOptions();
    }

    void facultyHelpMenu() {
        facultyOperationsOptions();
    }

    public void printAllStudentsInFaculty(Faculty faculty) {
        List<Student> stlist = faculty.getStudents();
        for (int i = 0; i < stlist.size(); i++) {
            System.out.println("Student: " + faculty.getStudents().get(i).toString());
        }
    }

    public void printEnrolledStudentsInFaculty(Faculty faculty) {
        List<Student> stlist = faculty.getStudents();
        for (Student student : stlist) {
            if (student.getIsEnrolled()) {
                System.out.println("Student: " + student);
            }
        }
    }

    public void printGraduatedStudentsInFaculty(Faculty faculty) {
        List<Student> stlist = faculty.getStudents();
        for (Student student : stlist) {
            if (!student.getIsEnrolled()) {
                System.out.println("Student: " + student);
            }
        }
    }
}
