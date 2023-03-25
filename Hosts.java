import java.util.Random;

public class Hosts extends Person{
    private static int randomNum;

    private static String[] gamePhrases = new String[]{"wheel of fortune", "have a nice day", "piece of cake", "bread and butter"};

    public Hosts() {
        randomNum = 0;
    }

    public Hosts(String first, String last){
        firstName = first;
        lastName = last;
    }
    public void setRandomNum(int number) {
        randomNum = number;
    }

    public int getRandomNum() {
        return randomNum;
    }

    ////
    public void generateNumber() {
        Random rand = new Random();
        randomNum = rand.nextInt(4);
        setRandomNum(randomNum);
    }

    public void pickPhrase() {
        generateNumber();
        Phrases phrase = new Phrases();
        phrase.setGamePhrase(gamePhrases[randomNum]);
    }

    public boolean compareNumber(int guess) {
        if (randomNum == guess) {
            System.out.println("Congratulations, you guessed the number!");
            return true;
        }
        else if (randomNum < guess) {
            System.out.println("I'm sorry.  That guess was too high.");
            return false;
        }
        else if (randomNum > guess) {
            System.out.println("I'm sorry.  That guess was too low.");
            return false;
        }
    return false;
    }

    
}
