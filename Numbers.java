import java.util.Random;

public class Numbers {
    private static int randomNum;

    public void setRandomNum(int number) {
        randomNum = number;
    }

    public int getRandomNum() {
        return randomNum;
    }

    ////
    public void generateNumber() {
        Random rand = new Random();
        randomNum = rand.nextInt(100);
        setRandomNum(randomNum);
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