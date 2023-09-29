package Lab1.behavior;

import Lab1.models.Faculty;
import Lab1.models.Student;
import Lab1.models.StudyField;
import Lab1.models.University;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Handler {
    private Printer printer;
    private Scanner scanner;
    private University university;
    public Handler(Printer printer, Scanner scanner, University university) {
        this.printer = printer;
        this.scanner = scanner;
        this.university = university;
    }
    void handleFacultyCreate(String[] commandsList) {
        if (commandsList.length == 4) {
            handleAddFaculty(commandsList);
        }
        else {
            handleAddFaculty();
        }
    }

    void handleFacultyDisplay(String[] commandsList) {
        if (commandsList.length == 2) {
            printer.printFacultiesByField(commandsList);
        }
        else {
            printer.printFacultiesByField();
        }
    }

    void handleStartMenu() {
        String startMenuOption = "";
        while (!startMenuOption.equals("q")) {
            startMenuOption = takeUserInput();
            String[] commandsList = startMenuOption.split("/");

            switch (commandsList[0]) {
                case "g" -> handleGeneralMenuOption();
                case "f" -> handleFacultyMenuOption();  // future feature
                case "q" -> {
                    System.out.println("| EXITING PROGRAM...                          |");
                    System.out.println("+---------------------------------------------+");
                }
                case "h" -> printer.helpStartMenu();
                case "s" -> System.out.println("WIP");  // future feature
                default -> System.out.println("| INVALID CHOICE! TRY AGAIN:                  |");
            }
        }
    }

    private void handleGeneralMenuOption() {
        printer.generalOperationsMenu();
        String generalMenuOption = "";
        while (!generalMenuOption.equals("q")) {
            generalMenuOption = takeUserInput();
            String[] commandsList2 = generalMenuOption.split("/");
            switch (commandsList2[0]) {
                case "nf" -> handleFacultyCreate(commandsList2);
                case "df" -> printer.printFaculties();
                case "sf" -> System.out.println("WIP");  // future feature
                case "q" -> {
                    System.out.println("| EXITING MENU...                             |");
                    System.out.println("+---------------------------------------------+");
                    printer.choiceStartMenu();
                }
                case "dff" -> handleFacultyDisplay(commandsList2);
                case "h" -> printer.generalOperationsMenu();
                default -> System.out.println("| INVALID CHOICE! TRY AGAIN:                  |");
            }
        }
    }

    private void handleFacultyMenuOption() {
        printer.facultyOperationsMenu();
        String facultyMenuOption = "";
        while (!facultyMenuOption.equals("q")) {
            facultyMenuOption = takeUserInput();
            String[] commandsList = facultyMenuOption.split("/");
            switch (commandsList[0]) {
                case "cs" -> handleStudentCreate(commandsList);
                case "gs" -> handleStudentGraduate();  // future feature
                case "das" -> {
                    Faculty facultyFound = getFaculty();
                    printer.printAllStudentsInFaculty(facultyFound);
                }
                case "des" -> System.out.println("WIP");  // future feature
                case "dgs" -> System.out.println("WIP");  // future feature
                case "csf" -> System.out.println("WIP");  // future feature
                case "h" -> printer.facultyHelpMenu();
                case "q" -> {
                    System.out.println("| EXITING MENU...                             |");
                    System.out.println("+---------------------------------------------+");
                    printer.choiceStartMenu();
                }
                default -> System.out.println("| INVALID CHOICE! TRY AGAIN:                  |");
            }
        }
    }

    private void handleStudentGraduate() {  // faculty exclusive
        System.out.println("STUDENTS:");
        for (Faculty faculty : this.university.getFacultyList()) {
            for (Student student : faculty.getStudents()) {
                System.out.println(student.toString());
            }
        }
        boolean flag = true;
        String input = this.scanner.nextLine();

        while (flag) {
            try {
                int indexInt = Integer.parseInt(input);
                flag = false;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("| INCORRECT INDEX! TRY AGAIN: |");
                input = this.scanner.nextLine();
            }
        }

    }

    Faculty getFaculty() {
        List<Faculty> facultyList = this.university.getFacultyList();
        System.out.println("| CHOOSE FACULTY (INDEX):                |");
        for (Faculty faculty : facultyList) {
            System.out.println(facultyList.indexOf(faculty) + 1 + ". " + faculty);
        }
        int indexInt = this.university.getFacultyIndex(scanner);
        return facultyList.get(indexInt - 1);
    }

    void handleStudentCreate(String[] commandsList) {
        List<Faculty> facultyList = this.university.getFacultyList();
        if (commandsList.length == 7) {
            if (!facultyList.isEmpty()) {
                handleAddStudent(commandsList);
            }
            else {
                System.out.println("| NO FACULTIES FOUND! ADD FACULTIES!     |");
            }

        }
        else {
            if (!facultyList.isEmpty()) {
                handleAddStudent(facultyList);
            }
            else {
                System.out.println("| NO FACULTIES FOUND! ADD FACULTIES!     |");
            }
        }
    }

    private void handleAddStudent(List<Faculty> facultyList) {
        System.out.println("| CHOOSE FACULTY (INDEX):                |");
        for (Faculty faculty : facultyList) {
            System.out.println(facultyList.indexOf(faculty) + 1 + ". " + faculty);
        }
        int indexInt = this.university.getFacultyIndex(scanner);
        System.out.println("| INPUT STUDENT FIRST NAME:              |");
        String firstName = this.scanner.nextLine();
        System.out.println("+----------------------------------------+");
        System.out.println("| INPUT STUDENT LAST NAME:               |");
        String lastName = this.scanner.nextLine();
        System.out.println("+----------------------------------------+");
        System.out.println("| INPUT STUDENT EMAIL                    |");
        String email = this.scanner.nextLine();
        System.out.println("+----------------------------------------+");
        System.out.println("| INPUT ENROLLMENT DATE (DD-MM-YYYY):    |");
        Date enrollmentDate = handleDateInput();
        System.out.println("| INPUT DATE OF BIRTH (DD-MM-YYYY):      |");
        Date dateOfBirth = handleDateInput();

        Student student = new Student(firstName, lastName, email, enrollmentDate, dateOfBirth);
        Faculty faculty = facultyList.get(indexInt - 1);
        faculty.addStudent(student);
    }

    private void handleAddStudent(String[] commandsList) {
        Faculty faculty = this.university.getFacultyByName(scanner, commandsList[1]);
        System.out.println("| ENROLLMENT DATE |");
        Date enrollmentDate = handleDateInput(commandsList[5]);
        System.out.println("| DATE OF BIRTHDAY |");
        Date dateOfBirth = handleDateInput(commandsList[6]);

        Student student = new Student(commandsList[2], commandsList[3], commandsList[4], enrollmentDate, dateOfBirth);
        faculty.addStudent(student);
    }

    private boolean handleEnrollmentStatus() {
        boolean flag = true;

        boolean isEnrolled = true;

        while(flag) {
            String enrollmentStatus = this.scanner.nextLine();
            switch (enrollmentStatus) {
                case "E", "e", "Enrolled" -> {
                    isEnrolled = true;
                    flag = false;
                }
                case "G", "g", "Graduate"-> {
                    isEnrolled = false;
                    flag = false;
                }
                default -> {
                    System.out.println("| INVALID INPUT! INPUT \"E\" OR \"G\"     ");
                    enrollmentStatus = this.scanner.nextLine();
                }
            }
        }

        return isEnrolled;
    }

    private boolean handleEnrollmentStatus(String enrollmentStatus) {
        boolean flag = true;

        boolean isEnrolled = true;

        while(flag) {
            switch (enrollmentStatus) {
                case "E", "e", "Enrolled" -> {
                    flag = false;
                }
                case "G", "g", "Graduate"-> {
                    isEnrolled = false;
                    flag = false;
                }
                default -> System.out.println("| INVALID INPUT! INPUT \"E\" OR \"G\"     ");
            }
        }
        return isEnrolled;
    }

    private Date handleDateInput() {
        String dateInput = this.scanner.nextLine();

        return getDate(dateInput);
    }

    private Date handleDateInput(String dateInput) {

        return getDate(dateInput);
    }

    private Date getDate(String dateInput) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);

        boolean flag = true;
        Date dateFormatted = null;

        while (flag) {
            try {
                dateFormatted = dateFormat.parse(dateInput);
                flag = false;
            } catch (ParseException e) {
                System.out.println("| INVALID DATE! INPUT AGAIN (DD-MM-YYYY):         |");
                dateInput = this.scanner.nextLine();
            }
        }
        return dateFormatted;
    }

    private void handleAddFaculty() {
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
        int indexInt = university.getFacultyFieldIndex(this.scanner);
        StudyField facultyField = StudyField.values()[indexInt - 1];
        System.out.println("+---------------------------------------------+");
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
                System.out.println("| INVALID FACULTY FIELD! INPUT FROM THE LIST: |");
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

    private String takeUserInput() {
        System.out.println("| INPUT CHOICE:                               |");
        String sample = scanner.nextLine();
        System.out.println("+---------------------------------------------+");
        return sample;
    }
}
