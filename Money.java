

public class Money implements Award {
    double winAmt = 1000;
    double looseAmt = 100;

    public int displayWinnings(Players player, boolean correct, GUI gui) {
        if (correct) {
            gui.playCorrectAnswer();
            gui.showMessage( player.getFirstName() + ", That is Correct! You won $" + winAmt);
            return (int)winAmt;
        } 
        else {
           gui.showMessage( player.getFirstName() + ", Sorry you lost $" + looseAmt);
           return -(int)looseAmt;
        }

    }
}
