package Lab1.behavior;

import Lab1.models.Faculty;
import Lab1.models.Student;
import Lab1.models.StudyField;
import Lab1.models.University;

import java.util.List;
import java.util.Scanner;

public class Printer {
    private University university;
    private Scanner scanner;
    public Printer(University university, Scanner scanner) {
        this.university = university;
        this.scanner = scanner;
    }
    public void printFaculties() {
        System.out.print(university.toString());
        System.out.println("+---------------------------------------------+");
    }

    void printFaculties(StudyField studyField) {
        System.out.print(university.toString(studyField));
        System.out.println("+---------------------------------------------+");
    }
    public void printFacultiesByField() {
        System.out.println("| INPUT STUDY FIELD:                          |");
        for (StudyField studyField : StudyField.values()) {
            System.out.println(studyField.ordinal() + 1 + ". " + studyField);
        }
        int indexInt = university.getFacultyFieldIndex(this.scanner);
        StudyField facultyField = StudyField.values()[indexInt - 1];

        printFaculties(facultyField);
    }

    public void printFacultiesByField(String[] commandsList) {
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