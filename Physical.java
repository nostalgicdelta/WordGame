import java.util.Random;
import javax.swing.*;


public class Physical implements Award{
    String[] prizes = {"Piano", "Car", "Vacation", "Beach House", "Spa Day"};

    int getRandomPrize() {
        Random rand = new Random();
        return rand.nextInt(prizes.length);
    }

    public int displayWinnings(Players player, boolean correct) {
        if (correct) {
            JOptionPane.showMessageDialog(null, "Congratulations " + player.getFirstName() + " You Won " + prizes[getRandomPrize()]);
        }
        else {
            JOptionPane.showMessageDialog(null, "Sorry " + player.getFirstName() + "You could have won" + prizes[getRandomPrize()]);
        }
        return 0;
    }
}
