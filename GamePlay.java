import java.util.Scanner;


public class GamePlay {
    
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstName;
        String lastName;
        boolean correct = false;
        boolean keepPlaying = true;
        String response;

        System.out.println("What is your first name?");
        firstName = scanner.nextLine();
        System.out.println("What is your last name?"); 
        lastName = scanner.nextLine();
        
        Players player = new Players(firstName, lastName);
        Turn turn = new Turn();
        Hosts host = new Hosts("Bob", "Barker");

        while(keepPlaying){
            host.generateNumber();
            while (!correct) {
                correct = turn.takeTurn(player, host);
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