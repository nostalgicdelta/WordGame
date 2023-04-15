
import java.util.Random;
import javax.swing.*;

public class Turn {
    double winAmt;
    double looseAmt;

    public Turn(){
        winAmt = 1000;
        looseAmt = 100;
    }
    public boolean takeTurn(Players player, Hosts host, GUI gui) {
        
        String guess;
        boolean correct = false;
        Random rand = new Random();
        int prize;
        Phrases phrase = new Phrases();

        
        guess = JOptionPane.showInputDialog(host.getFirstName() + " " + host.getLastName() + " says " + player.getFirstName() + " " + player.getLastName() + ", guess a letter.");
        while (!guess.matches("[a-zA-Z]")) {
            gui.showMessage( "Please guess a letter only");
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
        
        gui.clearMessage();
        if (prize == 0) {
            Money mon = new Money();
            player.setPlayersMoney(player.getPlayersMoney() + mon.displayWinnings(player, correct, gui));

        }
        else {
            Physical phys = new Physical();
            player.setPlayersMoney(player.getPlayersMoney() + phys.displayWinnings(player, correct, gui));
        }
        
        
        gui.showMessage(player.toString());
        return correct;
    }
}
