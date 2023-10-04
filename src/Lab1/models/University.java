package Lab1.models;

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

    public Faculty getFaculty(Scanner scanner) {
        List<Faculty> facultyList = this.getFacultyList();
        System.out.println("| CHOOSE FACULTY (INDEX):                |");
        for (Faculty faculty : facultyList) {
            System.out.println(facultyList.indexOf(faculty) + 1 + ". " + faculty);
        }
        int indexInt = this.getFacultyIndex(scanner);
        return facultyList.get(indexInt - 1);
    }

    public int getFacultyIndex(Scanner scanner) {
        int indexInt = 0;
        boolean flag = true;

        while (flag) {
            try {
                String index = scanner.nextLine();
                indexInt = Integer.parseInt(index);

                flag = false;
                if (indexInt > facultyList.size() || indexInt <= 0) {
                    System.out.println("| INVALID CHOICE OF FACULTY! CHOOSE FROM BELOW (INDEX): |");
                    for (Faculty faculty : facultyList) {
                        System.out.println(facultyList.indexOf(faculty) + 1 + ". " + faculty);
                    }
                    flag = true;
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("| INVALID CHOICE OF FACULTY! CHOOSE FROM BELOW (INDEX): |");
                for (Faculty faculty : facultyList) {
                    System.out.println(facultyList.indexOf(faculty) + 1 + ". " + faculty);
                }
            }
        }
        return indexInt;
    }

    public Faculty getFacultyByName(Scanner scanner, String facultyAbbrev) {
        boolean flag = true;

        Faculty faculty = null;
        while (flag) {
            for (Faculty currFaculty : this.facultyList) {
                if (currFaculty.getAbbreviation().equals(facultyAbbrev)) {
                    faculty = currFaculty;
                    flag = false;
                    break;
                }
            }
            if (faculty == null) {
                System.out.println("| INVALID CHOICE OF FACULTY! CHOOSE FROM BELOW (INDEX): |");
                for (Faculty currFaculty2 : facultyList) {
                    System.out.println(facultyList.indexOf(currFaculty2) + 1 + ". " + currFaculty2);
                }
                int index = getFacultyIndex(scanner);
                faculty = facultyList.get(index - 1);
                flag = false;
            }
        }
        return faculty;
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

    public List<Faculty> getFacultyList() {
        return facultyList;
    }

    public Faculty getFacultyByAbbreviation(Scanner scanner, String abbreviation) {
        boolean flag = true;

        Faculty faculty = null;
        while (flag) {
            for (Faculty currFaculty : this.facultyList) {
                if (currFaculty.getAbbreviation().equals(abbreviation)) {
                    faculty = currFaculty;
                    flag = false;
                    break;
                }
            }
            if (faculty == null) {
                break;
            }
        }
        return faculty;
    }
}
