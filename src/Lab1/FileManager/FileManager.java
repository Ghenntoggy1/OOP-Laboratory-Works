package Lab1.FileManager;

import Lab1.behavior.Handler;
import Lab1.models.Faculty;
import Lab1.models.Student;
import Lab1.models.StudyField;
import Lab1.models.University;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class FileManager {
    private static final String PATH = "C:\\IT Roma\\OOP Labs\\Labs\\src\\Lab1\\DataBases\\";
    private static final String UNIVERSITY_FILE = PATH + "university.txt";
    private static final String FACULTY_FILE = PATH + "faculty.txt";
    private static final String STUDENT_FILE = PATH + "student.txt";
    private static Handler handler;

    public FileManager(Handler handler) {
        this.handler = handler;
    }

    public static University loadUniversityData() {
        University university = new University();
        try (BufferedReader facultiesReader = new BufferedReader(new FileReader(FACULTY_FILE))) {
            String line;
            while ((line = facultiesReader.readLine()) != null) {
                String[] facultyData = line.split(",");
                String facultyName = facultyData[0].trim();
                String facultyAbbreviation = facultyData[1].trim();
                StudyField studyField = StudyField.valueOf(facultyData[2].trim());

                ArrayList<Student> students = new ArrayList<Student>();
                try (BufferedReader studentsReader = new BufferedReader(
                    new FileReader(PATH + facultyAbbreviation + ".txt"))) {
                    String studentLine;
                    while ((studentLine = studentsReader.readLine()) != null) {
                        String[] studentData = studentLine.split(",");
                        String firstName = studentData[0].trim();
                        String lastName = studentData[1].trim();
                        String email = studentData[2].trim();
                        Date enrollmentDate = handler.handleDateInput(studentData[3].trim());
                        Date birthDate = handler.handleDateInput(studentData[4].trim());
                        boolean isGraduated = Boolean.parseBoolean(studentData[5].trim());
                        Student student = new Student(firstName, lastName, email, enrollmentDate, birthDate);
                        student.setIsEnrolled(isGraduated);
                        students.add(student);
                    }
                }
                Faculty faculty = new Faculty(facultyName, facultyAbbreviation, studyField);
                faculty.setStudents(students);
                university.addFaculty(faculty);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return university;
    }

    public static void saveUniversityData(University university) {
        try {
            FileWriter facultiesFile = new FileWriter(FACULTY_FILE);
            for (Faculty faculty : university.getFacultyList()) {
                facultiesFile
                        .write(faculty.getName() + "," + faculty.getAbbreviation() + "," + faculty.getStudyField() + "\n");
                FileWriter studentsFile = new FileWriter(PATH + faculty.getAbbreviation() + ".txt");
                for (Student student : faculty.getStudents()) {
                    studentsFile
                            .write(student.getFirstName() + "," + student.getLastName() + "," + student.getEmail() + ","
                                    + student.getEnrollmentDate() + "," + student.getDateOfBirth() + "," + student.isEnrolled() + "\n");
                }
                studentsFile.close();
            }
            facultiesFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
