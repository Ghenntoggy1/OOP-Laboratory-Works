package Lab1.models;

import jdk.javadoc.doclet.StandardDoclet;

import java.util.ArrayList;
import java.util.List;

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
}
