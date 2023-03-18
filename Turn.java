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
        int guess;
        boolean correct;
        Random rand = new Random();
        int prize;

        
        System.out.println(host.getFirstName() + " " + host.getLastName() + " says " + player.getFirstName() + " " + player.getLastName() + ", guess what number I picked between 0 and 100.");
        guess = scanner.nextInt();
        correct = host.compareNumber(guess);

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
