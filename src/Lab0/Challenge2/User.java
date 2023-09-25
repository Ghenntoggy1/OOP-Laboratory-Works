package Lab0.Challenge2;

// Challenge 1: ABBREVIATE THE NAME:
// We have a class User with two fields: firstName and lastName of type String.
// The client asked us to display the initials at the bottom of a business-related document, so we need to
// create a method inside User class that will convert:
// Ion Doe -> I. D.
// Miley Cyrus -> M. C.
public class User {
    private String firstName;
    private String lastName;

    private String abreviatedName;
    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void abreviate() {
        abreviatedName = "";
        abreviatedName += getFirstName().charAt(0) + ". " + getLastName().charAt(0) + ".";
        setAbreviatedName(abreviatedName);
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getFirstName() {
        return this.firstName;
    }
    private void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return this.lastName;
    }
    private void setAbreviatedName(String abreviatedName) {
        this.abreviatedName = abreviatedName;
    }
    public String getAbreviatedName() {
        return this.abreviatedName;
    }
}

