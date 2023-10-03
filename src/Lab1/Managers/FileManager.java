package Lab1.Managers;

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
    private static final String FACULTY_FILE = PATH + "faculty.txt";
    private Handler handler;
    private University university;

    public FileManager(Handler handler, University university) {
        this.handler = handler;
        this.university = university;
    }

    public University loadUniversityData() {
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
                        int studentId = Integer.parseInt(studentData[0].trim());
                        String firstName = studentData[1].trim();
                        String lastName = studentData[2].trim();
                        String email = studentData[3].trim();
                        Date enrollmentDate = handler.handleDateReading(studentData[4].trim());
                        Date birthDate = handler.handleDateReading(studentData[5].trim());
                        boolean isGraduated = Boolean.parseBoolean(studentData[6].trim());
                        Student student = new Student(firstName, lastName, email, enrollmentDate, birthDate);
                        student.setIsEnrolled(isGraduated);
                        student.setId(studentId);
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

    public void saveUniversityData(University university) {
        try {
            FileWriter facultiesFile = new FileWriter(FACULTY_FILE);
            for (Faculty faculty : university.getFacultyList()) {
                facultiesFile.write(faculty.getName() + "," + faculty.getAbbreviation() + "," + faculty.getStudyField() + "\n");
                FileWriter studentsFile = new FileWriter(PATH + faculty.getAbbreviation() + ".txt");
                for (Student student : faculty.getStudents()) {
                    studentsFile.write(student.getId() + ", " + student.getFirstName() + "," + student.getLastName() + "," + student.getEmail() + ","
                                    + student.getEnrollmentDate() + "," + student.getDateOfBirth() + "," + student.isEnrolled() + "\n");
                }
                studentsFile.close();
            }
            facultiesFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resetDatabases() {
        File dataDirectory = new File(PATH);
        if (dataDirectory.exists() && dataDirectory.isDirectory()) {
            File[] files = dataDirectory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".txt") && !file.getName().equals("batchEnrollment.txt")
                            && !file.getName().equals("batchGraduation.txt")) {
                        file.delete();
                    }
                }
            }
        }

        try {
            FileWriter facultyFile = new FileWriter(FACULTY_FILE);
            facultyFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
