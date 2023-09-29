package Lab1.models;

import jdk.javadoc.doclet.StandardDoclet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class University {
    private List<Faculty> facultyList;

    public University() {
        facultyList = new ArrayList<>();
    }

    public void addFaculty(Faculty faculty) {
        this.facultyList.add(faculty);
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append("FACULTIES:\n");
        for (Faculty faculty : this.facultyList) {
            text.append(faculty).append("\n");
        }
        return text.toString();
    }

    public String toString(StudyField studyField) {
        StringBuilder text = new StringBuilder();
        text.append("FACULTIES (STUDY FIELD: ").append(studyField).append("):\n");
        for (Faculty faculty : this.facultyList) {
            if (faculty.getStudyField().equals(studyField)) {
                text.append(faculty).append("\n");
            }
        }
        return text.toString();
    }

    public int getFacultyFieldIndex(Scanner scanner) {
        int indexInt = 0;
        boolean flag = true;

        while (flag) {
            try {
                String index = scanner.nextLine();
                indexInt = Integer.parseInt(index);

                flag = false;
                if (indexInt > StudyField.values().length || indexInt <= 0) {
                    System.out.println("| INVALID CHOICE OF FACULTY FIELD! CHOOSE FROM BELOW (INDEX): |");
                    for (StudyField studyField : StudyField.values()) {
                        System.out.println(studyField.ordinal() + 1 + ". " + studyField);
                    }
                    flag = true;
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("| INVALID CHOICE OF FACULTY FIELD! CHOOSE FROM BELOW (INDEX): |");
                for (StudyField studyField : StudyField.values()) {
                    System.out.println(studyField.ordinal() + 1 + ". " + studyField);
                }
            }
        }
        return indexInt;
    }
}
