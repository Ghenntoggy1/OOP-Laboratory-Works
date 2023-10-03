package Lab1.Menus;

import Lab1.behavior.AppLoop;
import Lab1.behavior.Handler;
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
    private Handler handler;
    private boolean flag = true;

    public FacultyMenu(Scanner scanner, University university, Printer printer, AppLoop appLoop) {
        this.scanner = scanner;
        this.university = university;
        this.printer = printer;
        this.appLoop = appLoop;
        this.handler = appLoop.getHandler();
    }

    @Override
    public void printMenu() {
        if (flag) {
            printGreetings();
            printChoices();
        }
        flag = true;
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
        flag = false;
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
        boolean flag = true;
        while(flag) {
            String input = takeUserInput();
            String[] commandsList = input.split("/");
            switch (commandsList[0]) {
                case "cs" -> handleStudentCreate(commandsList);
                case "gs" -> handleStudentGraduate();
                case "das" -> handleDisplayAllStudents();
                case "des" -> handleDisplayEnrolledStudents();
                case "dgs" -> handleDisplayGraduatedStudents();
                case "csf" -> handleCheckStudent();
                case "h" -> printHelp();
                case "q" -> {
                    printQuit();
                    appLoop.setActiveMenu(new StartMenu(scanner, university, printer, appLoop));
                    flag = false;
                }
                default -> printInvalid();
            }
        }

    }

    private void handleCheckStudent() {
        flag = false;

        if (this.university.getFacultyList().isEmpty()) {
            System.out.println("| NO FACULTIES FOUND!                                                         |");
        }
        else {
            Faculty facultyFound = this.university.getFaculty(this.scanner);
            if (!facultyFound.getStudents().isEmpty()) {
                System.out.println("| FACULTY: " + facultyFound);
                handler.handleStudentBelong(facultyFound);
            } else {
                System.out.println("| NO STUDENTS FOUND!                                                          |");
            }
        }
    }

    private void handleDisplayAllStudents() {
        flag = false;

        if (this.university.getFacultyList().isEmpty()) {
            System.out.println("| NO FACULTIES FOUND!                                                         |");
        }
        else {
            Faculty facultyFound = this.university.getFaculty(this.scanner);
            if (!facultyFound.getStudents().isEmpty()) {
                System.out.println("| FACULTY: " + facultyFound);
                printer.printAllStudentsInFaculty(facultyFound);
            } else {
                System.out.println("| NO STUDENTS FOUND!                                                          |");
            }
        }
    }

    private void handleDisplayEnrolledStudents() {
        flag = false;

        if (this.university.getFacultyList().isEmpty()) {
            System.out.println("| NO FACULTIES FOUND!                                                         |");
        }
        else {
            Faculty facultyFound = this.university.getFaculty(this.scanner);
            if (!facultyFound.getStudents().isEmpty()) {
                System.out.println("| FACULTY: " + facultyFound);
                printer.printEnrolledStudentsInFaculty(facultyFound);
            } else {
                System.out.println("| NO STUDENTS FOUND!                                                          |");
            }
        }
    }

    private void handleDisplayGraduatedStudents() {
        flag = false;

        if (this.university.getFacultyList().isEmpty()) {
            System.out.println("| NO FACULTIES FOUND!                                                         |");
        }
        else {
            Faculty facultyFound = this.university.getFaculty(this.scanner);
            if (!facultyFound.getStudents().isEmpty()) {
                System.out.println("| FACULTY: " + facultyFound);
                printer.printGraduatedStudentsInFaculty(facultyFound);
            } else {
                System.out.println("| NO STUDENTS FOUND!                                                          |");
            }
        }
    }

    private void handleStudentGraduate() {
        flag = false;

        if (this.university.getFacultyList().isEmpty()) {
            System.out.println("| NO FACULTIES FOUND!                                                         |");
        }
        else {
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
            } else {
                System.out.println("NO STUDENTS FOUND!");
            }
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
        while (handler.isValidEmail(email)) {
            System.out.println("| INVALID EMAIL! INPUT CORRECT FORMAT    |");
            email = this.scanner.nextLine();
        }
        System.out.println("+----------------------------------------+");
        System.out.println("| INPUT DATE OF BIRTH (DD-MM-YYYY):      |");
        Date dateOfBirth = handler.handleDateInput();
        System.out.println("| INPUT ENROLLMENT DATE (DD-MM-YYYY):    |");
        Date enrollmentDate = handler.handleDateInput();

        while (handler.isValidDates(dateOfBirth, enrollmentDate)) {
            System.out.println("| INCORRECT DATE (DATE OF BIRTH AFTER ENROLLMENT DATE)                         |");
            System.out.println("| INPUT DATE OF BIRTH (DD-MM-YYYY):                                            |");
            dateOfBirth = handler.handleDateInput();
            System.out.println("| INPUT ENROLLMENT DATE (DD-MM-YYYY):                                          |");
            enrollmentDate = handler.handleDateInput();
        }

        Student student = new Student(firstName, lastName, email, enrollmentDate, dateOfBirth);
        Faculty faculty = facultyList.get(indexInt - 1);
        faculty.addStudent(student);
    }

    private void handleAddStudent(String[] commandsList) {
        Faculty faculty = this.university.getFacultyByName(scanner, commandsList[1]);
        Date enrollmentDate = handler.handleDateInput(commandsList[5]);
        Date dateOfBirth = handler.handleDateInput(commandsList[6]);

        while (handler.isValidEmail(commandsList[4])) {
            System.out.println("| INVALID EMAIL! INPUT CORRECT FORMAT                                          |");
            commandsList[4] = this.scanner.nextLine();
        }

        while (handler.isValidDates(dateOfBirth, enrollmentDate)) {
            System.out.println("| INCORRECT DATE (DATE OF BIRTH AFTER ENROLLMENT DATE)                         |");
            System.out.println("| INPUT DATE OF BIRTH (DD-MM-YYYY):                                            |");
            dateOfBirth =  handler.handleDateInput();
            System.out.println("| INPUT ENROLLMENT DATE (DD-MM-YYYY):                                          |");
            enrollmentDate = handler.handleDateInput();
        }

        Student student = new Student(commandsList[2], commandsList[3], commandsList[4], enrollmentDate, dateOfBirth);
        faculty.addStudent(student);
    }
}
