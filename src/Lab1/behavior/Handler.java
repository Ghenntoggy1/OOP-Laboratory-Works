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
    private final Scanner scanner;
    public Handler(Scanner scanner) {
        this.scanner = scanner;
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

    public void handleStudentBelong(Faculty facultyFound) {
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
        boolean flag = true;
        for (Student student : studentList) {
            if (student.getId() == id) {
                System.out.println(student);
                flag = false;
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
        boolean flag = true;
        for (Student student : studentList) {
            if (student.getEmail().equals(email)) {
                System.out.println(student);
                flag = false;
            }
        }
        if (flag) {
            System.out.println("| STUDENT WITH EMAIL " + email + " NOT FOUND! |");
        }
    }

    public boolean isValidDates(Date dateOfBirth, Date enrollmentDate) {
        return dateOfBirth.after(enrollmentDate);
    }

    public Date handleDateInput() {
        String dateInput = this.scanner.nextLine();

        return getDate(dateInput);
    }

    public Date handleDateInput(String dateInput) {
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

    public boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern).matcher(emailAddress).matches();
    }

    public boolean isValidEmail(String email) {
        boolean isValid;
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        isValid = patternMatches(email, regexPattern);
        return !isValid;
    }

    public Date handleDateReading(String dateRead) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        Date date = null;
        try {
            date = inputFormat.parse(dateRead);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
