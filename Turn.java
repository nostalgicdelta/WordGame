import java.util.Scanner;
import java.util.Random;

public class Turn {
    double winAmt;
    double looseAmt;

    public Turn(){
        winAmt = 1000;
        looseAmt = 100;
    }
    public boolean takeTurn(Players player, Hosts host) {
        Scanner scanner = new Scanner(System.in);
        String guess;
        boolean correct = false;
        Random rand = new Random();
        int prize;
        Phrases phrase = new Phrases();

        System.out.println("The phrase to guess is: " + phrase.getPlayingPhrase());
        System.out.println(host.getFirstName() + " " + host.getLastName() + " says " + player.getFirstName() + " " + player.getLastName() + ", guess what Phrase I picked.");
        guess = scanner.nextLine();
        while (!guess.matches("[a-zA-Z]")) {
            System.out.println("Please guess a letter only");
            guess = scanner.nextLine();
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

        
        System.out.println(player);
        return correct;
    }
}
