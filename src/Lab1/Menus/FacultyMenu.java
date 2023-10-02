package Lab1.Menus;

import Lab1.behavior.AppLoop;
import Lab1.behavior.Printer;
import Lab1.interfaces.Menu;
import Lab1.models.Faculty;
import Lab1.models.Student;
import Lab1.models.University;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FacultyMenu implements Menu {
    private Scanner scanner;
    private University university;
    private Printer printer;
    private AppLoop appLoop;

    public FacultyMenu(Scanner scanner, University university, Printer printer, AppLoop appLoop) {
        this.scanner = scanner;
        this.university = university;
        this.printer = printer;
        this.appLoop = appLoop;
    }

    @Override
    public void printMenu() {
        printGreetings();
        printChoices();
    }
    
    @Override
    public void printGreetings() {
        System.out.println("| FACULTY OPERATIONS:                                                                                                              |");
        System.out.println("+--------------------------------------------------------------------------------------------------+");
    }

    @Override
    public void printChoices() {
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

    @Override
    public void printHelp() {
        System.out.println("| COMMAND LIST:                                                                                    |");
        printChoices();
    }

    @Override
    public void printQuit() {
        System.out.println("| EXITING MENU...                                                                                                                  |");
        System.out.println("+--------------------------------------------------------------------------------------------------+");
    }

    @Override
    public void printInvalid() {
        System.out.println("| INVALID CHOICE! TRY AGAIN:                                                                                                       |");
    }

    @Override
    public String takeUserInput() {
        System.out.println("| INPUT CHOICE:                                                                                                                    |");
        String sample = scanner.nextLine();
        System.out.println("+--------------------------------------------------------------------------------------------------+");
        return sample;
    }

    @Override
    public void handleInput() {
        String input = takeUserInput();
        String[] commandsList = input.split("/");
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
                    System.out.println("| NO STUDENTS FOUND!                                                          |");
                }
            }
            case "des" -> {
                Faculty facultyFound = this.university.getFaculty(this.scanner);
                if (!facultyFound.getStudents().isEmpty()) {
                    System.out.println("| FACULTY: " + facultyFound);
                    printer.printEnrolledStudentsInFaculty(facultyFound);
                }
                else {
                    System.out.println("| NO STUDENTS FOUND!                                                          |");
                }
            }
            case "dgs" -> {
                Faculty facultyFound = this.university.getFaculty(this.scanner);
                if (!facultyFound.getStudents().isEmpty()) {
                    System.out.println("| FACULTY: " + facultyFound);
                    printer.printGraduatedStudentsInFaculty(facultyFound);
                }
                else {
                    System.out.println("| NO STUDENTS FOUND!                                                          |");
                }
            }
            case "csf" -> {
                Faculty facultyFound = this.university.getFaculty(this.scanner);
                if (!facultyFound.getStudents().isEmpty()) {
                    System.out.println("| FACULTY: " + facultyFound);
                    handleStudentBelong(facultyFound);
                } else {
                    System.out.println("| NO STUDENTS FOUND!                                                          |");
                }
            }
            case "h" -> printHelp();
            case "q" -> {
                printQuit();
                appLoop.activeMenu = new StartMenu(scanner, university, printer, appLoop);
            }
            default -> printInvalid();
        }

    }

    private int idConversion(String id) {
        boolean flag = true;
        Integer idConverted = null;
        while (flag) {
            try {
                idConverted = Integer.parseInt(id);
                flag = false;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("| INVALID ID! INPUT AGAIN:            |");
                id = this.scanner.nextLine();
            }
        }
        return idConverted;
    }

    private void handleStudentBelong(Faculty facultyFound) {
        System.out.println("| CHOOSE SEARCH TYPE:                         |");
        System.out.println("| id - SEARCH BY ID                           |");
        System.out.println("| em - SEARCH BY EMAIL                        |");
        boolean flag = true;
        while (flag) {
            String choice = scanner.nextLine();
            switch (choice) {
                case "id" -> {
                    handleStudentBelongId(facultyFound);
                    flag = false;
                }
                case "em" -> {
                    handleStudentBelongMail(facultyFound);
                    flag = false;
                }
                default -> System.out.println("| INVALID CHOICE! CHOOSE CORRECTLY:           |");
            }
        }
    }

    private void handleStudentBelongId(Faculty facultyFound) {
        System.out.println("| INPUT ID OF THE STUDENT:                    |");
        String idString = scanner.nextLine();
        int id = idConversion(idString);
        List<Student> studentList = facultyFound.getStudents();
        boolean flag = false;
        for (Student student : studentList) {
            if (student.getId() == id) {
                System.out.println(student);
                flag = true;
            }
        }
        if (flag) {
            System.out.println("STUDENT WITH ID " + id + " NOT FOUND!");
        }
    }

    private void handleStudentBelongMail(Faculty facultyFound) {
        System.out.println("| INPUT EMAIL OF THE STUDENT:                 |");
        String email = scanner.nextLine();
        List<Student> studentList = facultyFound.getStudents();
        boolean flag = false;
        for (Student student : studentList) {
            if (student.getEmail().equals(email)) {
                System.out.println(student);
                flag = true;
            }
        }
        if (flag) {
            System.out.println("| STUDENT WITH EMAIL " + email + " NOT FOUND! |");
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
        while (isValidEmail(email)) {
            System.out.println("| INVALID EMAIL! INPUT CORRECT FORMAT    |");
            email = this.scanner.nextLine();
        }
        System.out.println("+----------------------------------------+");
        System.out.println("| INPUT DATE OF BIRTH (DD-MM-YYYY):      |");
        Date dateOfBirth = handleDateInput();
        System.out.println("| INPUT ENROLLMENT DATE (DD-MM-YYYY):    |");
        Date enrollmentDate = handleDateInput();

        while (isValidDates(dateOfBirth, enrollmentDate)) {
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
        return dateOfBirth.after(enrollmentDate);
    }

    private void handleAddStudent(String[] commandsList) {
        Faculty faculty = this.university.getFacultyByName(scanner, commandsList[1]);
        Date enrollmentDate = handleDateInput(commandsList[5]);
        Date dateOfBirth = handleDateInput(commandsList[6]);

        while (isValidEmail(commandsList[4])) {
            System.out.println("| INVALID EMAIL! INPUT CORRECT FORMAT                                          |");
            commandsList[4] = this.scanner.nextLine();
        }

        while (isValidDates(dateOfBirth, enrollmentDate)) {
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

    private boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern).matcher(emailAddress).matches();
    }

    private boolean isValidEmail(String email) {
        boolean isValid;
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        isValid = patternMatches(email, regexPattern);
        return !isValid;
    }
}
