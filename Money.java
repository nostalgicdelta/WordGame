import javax.swing.*;

public class Money implements Award {
    double winAmt = 1000;
    double looseAmt = 100;

    public int displayWinnings(Players player, boolean correct) {
        if (correct) {
            JOptionPane.showMessageDialog(null, player.getFirstName() + " That is Correct! You won $" + winAmt);
            return (int)winAmt;
        } 
        else {
           JOptionPane.showMessageDialog(null, player.getFirstName() + " Sorry you lost $" + looseAmt);
           return -(int)looseAmt;
        }

    }
}
