package Lab1.Managers;

import Lab1.behavior.Handler;
import Lab1.models.Faculty;
import Lab1.models.Student;
import Lab1.models.University;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class BatchManager {
    private Handler handler;
    private University university;
    private Scanner scanner;

    private static final String PATH = "C:\\IT Roma\\OOP Labs\\Labs\\src\\Lab1\\DataBases\\";
    private static final String BATCH_FILE_PATH = PATH + "batchEnrollment.txt";

    public BatchManager(Handler handler, University university, Scanner scanner) {
        this.handler = handler;
        this.university = university;
        this.scanner = scanner;
    }

    public void batchEnrollStudents() {
        if (!this.university.getFacultyList().isEmpty()) {
            try (BufferedReader batchReader = new BufferedReader(new FileReader(BATCH_FILE_PATH))) {
                String line;
                while ((line = batchReader.readLine()) != null) {
                    String[] studentData = line.split(",");
                    if (studentData.length == 7) {
                        String firstName = studentData[0].trim();
                        String lastName = studentData[1].trim();
                        String email = studentData[2].trim();
                        String enrollmentDateString = studentData[3].trim();
                        String birthDateString = studentData[4].trim();
                        boolean isGraduated = Boolean.parseBoolean(studentData[5].trim());
                        String facultyName = studentData[6].trim();

                        Date enrollmentDate = handler.handleDateInput(enrollmentDateString);
                        Date birthDate = handler.handleDateInput(birthDateString);


                        Student student = new Student(firstName, lastName, email, enrollmentDate, birthDate);
                        student.setIsEnrolled(!isGraduated);

                        Faculty faculty = university.getFacultyByAbbreviation(scanner, facultyName);

                        if (faculty != null) {
                            faculty.addStudent(student);
                        } else {
                            System.out.println("| FOR STUDENT: " + firstName + " " + lastName + " FACULTY NOT FOUND! |");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("| BATCH FAILED! NO FACULTIES FOUND!  |");
        }
    }
}
