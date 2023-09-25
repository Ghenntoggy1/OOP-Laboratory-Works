package Lab1.models;

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
        text.append("Faculties:\n");
        for (Faculty faculty : this.facultyList) {
            text.append(faculty).append("\n");
        }
        return text.toString();
    }
}
