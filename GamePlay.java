import java.util.Scanner;


public class GamePlay {
    
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstName;
        String lastName;
        boolean correct = false;
        boolean keepPlaying = true;
        String response;
        Players[] currentPlayers = new Players[3];

        for (int i = 0; i < 3; i++) {
            System.out.println("What is your first name?");
            firstName = scanner.nextLine();
            System.out.println("What is your last name?"); 
            lastName = scanner.nextLine();
            currentPlayers[i] = new Players(firstName, lastName);
        }

        Turn turn = new Turn();
        Hosts host = new Hosts("Bob", "Barker");

        while(keepPlaying){
            host.generateNumber();
            int i = 0;
            while (!correct) {
                correct = turn.takeTurn(currentPlayers[i], host);
                i = (i + 1) % currentPlayers.length;
            } 
            if (correct) {
                System.out.println("Play another game? (y or n)");
                response = scanner.next();
                if (response.charAt(0) == 'n') {
                    keepPlaying = false;
                }
                correct = false;
            }           
        }
        scanner.close();
    }
}