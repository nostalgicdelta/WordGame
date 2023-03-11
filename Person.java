public class Person {
    protected String firstName; //Person first name
    protected String lastName; //Person Last name

    public void setFirstName(String userFirstName) {
        firstName = userFirstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String userLastName) {
        lastName = userLastName;
    }

    public String getLastName() {
        return lastName;
    }

    public Person() {
        firstName = "";
        lastName = "";
    }

    public Person (String userFirstName) {
        firstName = userFirstName;
        lastName = "";
    }

    public Person (String userFirstName, String userLastName) {
        firstName = userFirstName;
        lastName = userLastName;
    }

    public String toString() {
        if (lastName == "") {
            return "Players Name: " + firstName;
        }
        else {
            return "Players Name: " + firstName + " " + lastName;
        }
    }
}
