import java.util.Scanner;


public class GamePlay {
    
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstName;
        String lastName;
        boolean correct = false;
        int guess;
        Person player = new Person();

        System.out.println("What is your first name?");
        firstName = scanner.nextLine();
        System.out.println("What is your last name?"); 
        lastName = scanner.nextLine();
        if (lastName == "") {
            player.createPerson(firstName);
        }
        else {
            player.createPerson(firstName, lastName);
        }

        
        Numbers number = new Numbers();
        number.generateNumber();
        while (!correct) {
           System.out.println(player.getFirstName() + " " + player.getLastName() + ", guess what number I picked between 0 and 100.");
            guess = scanner.nextInt();
            number.compareNumber(guess);
        }
        scanner.close();
    }
}