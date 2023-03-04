public class Person {
    private String firstName; //Person first name
    private String lastName; //Person Last name

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

    public void createPerson (String userFirstName) {
        firstName = userFirstName;
        lastName = "";
    }

    public void createPerson (String userFirstName, String userLastName) {
        firstName = userFirstName;
        lastName = userLastName;
    }
}
