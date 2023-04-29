import java.util.Random;


public class Physical implements Award{
    String[] prizes = {"Piano", "Car", "Vacation", "Beach House", "Spa Day"};
    String[] prizeImages = {".\\Multimedia\\piano.jpg", ".\\Multimedia\\car.jpg", ".\\Multimedia\\vacation.jpg", ".\\Multimedia\\beachhouse.jpg", ".\\Multimedia\\spaday.jpg"};

    int getRandomPrize() {
        Random rand = new Random();
        return rand.nextInt(prizes.length);
    }

    public int displayWinnings(Players player, boolean correct, GUI gui) {
        int prizeNum = getRandomPrize();
        if (correct) {
            gui.showMessage( "Congratulations " + player.getFirstName() + " You Won a" + prizes[prizeNum]);
            gui.playCorrectAnswer();
            gui.showPrizeImage(prizeImages[prizeNum]);
        }
        else {
            gui.showMessage( "Sorry " + player.getFirstName() + "You could have won" + prizes[prizeNum]);
        }
        return 0;
    }
}
