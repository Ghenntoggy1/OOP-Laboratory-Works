package Lab1.behavior;

import Lab1.models.Faculty;
import Lab1.models.Student;
import Lab1.models.StudyField;
import Lab1.models.University;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class Handler {
    private final Printer printer;
    private final Scanner scanner;
    private final University university;
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
                case "nf" -> {
                    handleFacultyCreate(commandsList2);
                    System.out.println("| SUCCESS!                                    |");
                }
                case "df" -> {
                    if (!this.university.getFacultyList().isEmpty()) {
                        printer.printFaculties();
                    }
                    else {
                        System.out.println("| FACULTIES NOT FOUND!                        |");
                    }
                }
                case "dff" -> {
                    if (!this.university.getFacultyList().isEmpty()) {
                        handleFacultyDisplay(commandsList2);
                    }
                    else {
                        System.out.println("| FACULTIES NOT FOUND!                        |");
                    }
                }
                case "sf" -> System.out.println("WIP");  // future feature
                case "q" -> {
                    System.out.println("| EXITING MENU...                             |");
                    System.out.println("+---------------------------------------------+");
                    printer.choiceStartMenu();
                }
                case "h" -> printer.helpGeneralMenu();
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
                case "gs" -> handleStudentGraduate();
                case "das" -> {
                    Faculty facultyFound = this.university.getFaculty(this.scanner);
                    if (!facultyFound.getStudents().isEmpty()) {
                        System.out.println("| FACULTY: " + facultyFound);
                        printer.printAllStudentsInFaculty(facultyFound);
                    }
                    else {
                        System.out.println("| NO STUDENTS FOUND!                          |");
                    }
                }
                case "des" -> {
                    Faculty facultyFound = this.university.getFaculty(this.scanner);
                    if (!facultyFound.getStudents().isEmpty()) {
                        System.out.println("| FACULTY: " + facultyFound);
                        printer.printEnrolledStudentsInFaculty(facultyFound);
                    }
                    else {
                        System.out.println("| NO STUDENTS FOUND!                          |");
                    }
                }
                case "dgs" -> {
                    Faculty facultyFound = this.university.getFaculty(this.scanner);
                    if (!facultyFound.getStudents().isEmpty()) {
                        System.out.println("| FACULTY: " + facultyFound);
                        printer.printGraduatedStudentsInFaculty(facultyFound);
                    }
                    else {
                        System.out.println("| NO STUDENTS FOUND!                          |");
                    }
                }
                case "csf" -> System.out.println("WIP");  // future feature
                case "h" -> printer.facultyHelpMenu();
                case "q" -> {
                    System.out.println("| EXITING MENU...                             |");
                    System.out.println("+---------------------------------------------+");
                    printer.choiceStartMenu();
                }
                default -> System.out.println("| INVALID CHOICE! TRY AGAIN:        |");
            }
        }
    }

    private void handleStudentGraduate() {
        Faculty faculty = this.university.getFaculty(this.scanner);

        List<Student> studentsList = faculty.getStudents();
        if (!studentsList.isEmpty()) {
            System.out.println("| CHOOSE STUDENT YOU WANT TO GRADUATE (INDEX): |");
            System.out.println("| STUDENTS:                                    |");
            for (int i = 0; i < studentsList.size(); i++) {
                System.out.println((i + 1) + ". " + studentsList.get(i).toString());
            }
            String choice = scanner.nextLine();

            boolean flag = true;
            Integer index = null;

            while (flag) {
                try {
                    index = Integer.parseInt(choice);
                    flag = false;
                } catch (NumberFormatException numberFormatException) {
                    System.out.println("| INVALID INPUT! INPUT AGAIN (INDEX):         |");
                    choice = this.scanner.nextLine();
                }
            }
            Student graduatedStudent = studentsList.get(index - 1);
            graduatedStudent.setIsEnrolled(false);
            System.out.println("| SUCCESS!                                    |");
        }
        else {
            System.out.println("NO STUDENTS FOUND!");
        }
    }

    void handleStudentCreate(String[] commandsList) {
        List<Faculty> facultyList = this.university.getFacultyList();
        if (commandsList.length == 7) {
            if (!facultyList.isEmpty()) {
                handleAddStudent(commandsList);
                System.out.println("| SUCCESS!                                    |");
            }
            else {
                System.out.println("| NO FACULTIES FOUND! ADD FACULTIES!     |");
            }

        }
        else {
            if (!facultyList.isEmpty()) {
                handleAddStudent(facultyList);
                System.out.println("| SUCCESS!                                    |");
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
        while (!isValidEmail(email)) {
            System.out.println("| INVALID EMAIL! INPUT CORRECT FORMAT    |");
            email = this.scanner.nextLine();
        }
        System.out.println("+----------------------------------------+");
        System.out.println("| INPUT DATE OF BIRTH (DD-MM-YYYY):      |");
        Date dateOfBirth = handleDateInput();
        System.out.println("| INPUT ENROLLMENT DATE (DD-MM-YYYY):    |");
        Date enrollmentDate = handleDateInput();

        while (!isValidDates(dateOfBirth, enrollmentDate)) {
            System.out.println("| INCORRECT DATE (DATE OF BIRTH AFTER ENROLLMENT DATE)                         |");
            System.out.println("| INPUT DATE OF BIRTH (DD-MM-YYYY):                                            |");
            dateOfBirth = handleDateInput();
            System.out.println("| INPUT ENROLLMENT DATE (DD-MM-YYYY):                                          |");
            enrollmentDate = handleDateInput();
        }

        Student student = new Student(firstName, lastName, email, enrollmentDate, dateOfBirth);
        Faculty faculty = facultyList.get(indexInt - 1);
        faculty.addStudent(student);
    }

    private boolean isValidDates(Date dateOfBirth, Date enrollmentDate) {
        return !dateOfBirth.after(enrollmentDate);
    }

    private void handleAddStudent(String[] commandsList) {
        Faculty faculty = this.university.getFacultyByName(scanner, commandsList[1]);
        Date enrollmentDate = handleDateInput(commandsList[5]);
        Date dateOfBirth = handleDateInput(commandsList[6]);

        while (!isValidEmail(commandsList[4])) {
            System.out.println("| INVALID EMAIL! INPUT CORRECT FORMAT                                          |");
            commandsList[4] = this.scanner.nextLine();
        }

        while (!isValidDates(dateOfBirth, enrollmentDate)) {
            System.out.println("| INCORRECT DATE (DATE OF BIRTH AFTER ENROLLMENT DATE)                         |");
            System.out.println("| INPUT DATE OF BIRTH (DD-MM-YYYY):                                            |");
            dateOfBirth = handleDateInput();
            System.out.println("| INPUT ENROLLMENT DATE (DD-MM-YYYY):                                          |");
            enrollmentDate = handleDateInput();
        }

        Student student = new Student(commandsList[2], commandsList[3], commandsList[4], enrollmentDate, dateOfBirth);
        faculty.addStudent(student);
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

   private boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern).matcher(emailAddress).matches();
    }

    private boolean isValidEmail(String email) {
        boolean isValid;
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        isValid = patternMatches(email, regexPattern);
        return isValid;
    }
}
