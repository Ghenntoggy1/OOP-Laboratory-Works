package Lab1.Managers;

import Lab1.behavior.Handler;
import Lab1.models.Faculty;
import Lab1.models.Student;
import Lab1.models.University;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class BatchManager {
    private Handler handler;
    private University university;
    private Scanner scanner;

    private static final String PATH = "C:\\IT Roma\\OOP Labs\\Labs\\src\\Lab1\\DataBases\\";
    private static final String BATCH_ENROLLMENT_FILE_PATH = PATH + "batchEnrollment.txt";
    private static final String BATCH_GRADUATION_FILE_PATH = PATH + "batchGraduation.txt";

    public BatchManager(Handler handler, University university, Scanner scanner) {
        this.handler = handler;
        this.university = university;
        this.scanner = scanner;
    }

    public void batchEnrollStudents() {
        if (!this.university.getFacultyList().isEmpty()) {
            try (BufferedReader batchReader = new BufferedReader(new FileReader(BATCH_ENROLLMENT_FILE_PATH))) {
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
                            boolean isDuplicate = false;
                            for (Student existingStudent : faculty.getStudents()) {
                                if (existingStudent.getEmail().equalsIgnoreCase(email)) {
                                    System.out.println("| FOR STUDENT: " + firstName + " " + lastName + " ALREADY ENROLLED IN THIS FACULTY! |");
                                    isDuplicate = true;
                                    break;
                                }
                            }
                            if (!isDuplicate) {
                                faculty.addStudent(student);
                                System.out.println("| FOR STUDENT: " + firstName + " " + lastName + " FACULTY FOUND! + " + faculty.getAbbreviation() + " |");
                            }
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
            System.out.println("| BATCH FAILED! NO FACULTIES FOUND!           |");
        }
    }

    public void batchGraduation() {
        if (!this.university.getFacultyList().isEmpty()) {
            try (BufferedReader graduationReader = new BufferedReader(new FileReader(BATCH_GRADUATION_FILE_PATH))) {
                String line;
                while ((line = graduationReader.readLine()) != null) {
                    try {
                        int studentId = Integer.parseInt(line.trim());
                        performGraduation(studentId);
                        System.out.println("| STUDENT WITH ID: " + studentId + " IS GRADUATED |");
                    } catch (NumberFormatException e) {
                        System.out.println("| INVALID ID IN BATCH FILE! " + line + " |");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("| BATCH FAILED! NO FACULTIES FOUND!           |");
        }
    }

    private void performGraduation(int studentId) {
        for (Faculty faculty : university.getFacultyList()) {
            List<Student> students = faculty.getStudents();
            for (Student student : students) {
                if (student.getId() == studentId) {
                    student.setIsEnrolled(false);
                    return;
                }
            }
        }
        System.out.println("| STUDENT WITH ID: " + studentId + " NOT FOUND! |");
    }
}
