package Lab1.models;

import java.util.Date;

public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private Date enrollmentDate;
    private Date dateOfBirth;

    private boolean isEnrolled;
    public Student(String firstName, String lastName, String email, Date enrollmentDate, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enrollmentDate = enrollmentDate;
        this.dateOfBirth = dateOfBirth;
        this.isEnrolled = true;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isEnrolled() {
        return this.isEnrolled;
    }

    public void setIsEnrolled(boolean isEnrolled) {
        this.isEnrolled = isEnrolled;
    }

    @Override
    public String toString() {
        return "{" +
                "First Name: '" + firstName + '\'' +
                ", Last Name: '" + lastName + '\'' +
                ", Email: '" + email + '\'' +
                ", Enrollment Date: " + enrollmentDate +
                ", Date of Birth: " + dateOfBirth +
                ", Is Enrolled: " + isEnrolled +
                '}';
    }
}
