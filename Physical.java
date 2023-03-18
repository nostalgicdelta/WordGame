import java.util.Random;

public class Physical implements Award{
    String[] prizes = {"Piano", "Car", "Vacation", "Beach House", "Spa Day"};

    int getRandomPrize() {
        Random rand = new Random();
        return rand.nextInt(prizes.length);
    }

    public int displayWinnings(Players player, boolean correct) {
        if (correct) {
            System.out.println("Congratulations " + player.getFirstName() + " You Won " + prizes[getRandomPrize()]);
        }
        else {
            System.out.println("Sorry " + player.getFirstName() + "You could have won" + prizes[getRandomPrize()]);
        }
        return 0;
    }
}
