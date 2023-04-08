import java.util.Random;

public class Hosts extends Person{
    private static int randomNum;

    public Hosts() {
        randomNum = 0;
    }

    public Hosts(String first) {
        firstName = first;
        lastName = "";
    }

    public Hosts(String first, String last){
        firstName = first;
        lastName = last;
    }

    public String getHostName() {
        return firstName + " " + lastName;
    }
    public void setRandomNum(int number) {
        randomNum = number;
    }

    public int getRandomNum() {
        return randomNum;
    }

    public void generateNumber() {
        Random rand = new Random();
        randomNum = rand.nextInt(4);
        setRandomNum(randomNum);
    }

    public void pickNewPhrase(String newGamePhrase) {
        Phrases phrase = new Phrases();
        phrase.setGamePhrase(newGamePhrase);
    }
   
    
}
