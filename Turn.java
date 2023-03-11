import java.util.Scanner;

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

        
        System.out.println(host.getFirstName() + " " + host.getLastName() + " says " + player.getFirstName() + " " + player.getLastName() + ", guess what number I picked between 0 and 100.");
        guess = scanner.nextInt();
        correct = host.compareNumber(guess);
        if (correct) {
            System.out.println("You win $" + winAmt);
            player.setPlayersMoney(player.getPlayersMoney() + winAmt);
        }
        else {
            System.out.println("You lose $" + looseAmt);
            player.setPlayersMoney(player.getPlayersMoney() - looseAmt);
        }
        System.out.println(player);
        return correct;
    }
}
