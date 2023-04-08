
import java.util.Random;
import javax.swing.*;

public class Turn {
    double winAmt;
    double looseAmt;

    public Turn(){
        winAmt = 1000;
        looseAmt = 100;
    }
    public boolean takeTurn(Players player, Hosts host) {
        
        String guess;
        boolean correct = false;
        Random rand = new Random();
        int prize;
        Phrases phrase = new Phrases();

        
        guess = JOptionPane.showInputDialog(host.getFirstName() + " " + host.getLastName() + " says " + player.getFirstName() + " " + player.getLastName() + ", guess a letter.");
        while (!guess.matches("[a-zA-Z]")) {
            JOptionPane.showMessageDialog(null, "Please guess a letter only");
            guess = JOptionPane.showInputDialog(host.getFirstName() + " " + host.getLastName() + " says " + player.getFirstName() + " " + player.getLastName() + ", guess a letter.");
        }
        try {
            correct = phrase.findLetters(guess);
        }
        catch (MultipleLettersException mle)
        {
            System.out.println(mle);
        }
        prize = rand.nextInt(1);

        if (prize == 0) {
            Money mon = new Money();
            player.setPlayersMoney(player.getPlayersMoney() + mon.displayWinnings(player, correct));

        }
        else {
            Physical phys = new Physical();
            player.setPlayersMoney(player.getPlayersMoney() + phys.displayWinnings(player, correct));
        }

        
        JOptionPane.showMessageDialog(null, player);
        return correct;
    }
}
