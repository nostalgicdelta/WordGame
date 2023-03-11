public class Players extends Person {
    private double playersMoney;
    

    public Players() {
        playersMoney = 1000;
    }

    public Players (String userFirstName) {
        firstName = userFirstName;
        lastName = "";
    }

    public Players (String userFirstName, String userLastName) {
        firstName = userFirstName;
        lastName = userLastName;
        playersMoney = 1000;
    }

    public void setPlayersMoney(double number) {
        playersMoney = number;
    }

    public double getPlayersMoney() {
        return playersMoney;
    }

    @Override
    public String toString() {
        return  super.toString() + "\n" + "Money: " + playersMoney;
    }
}
