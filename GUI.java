import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener{
    Players[] currentPlayers = new Players[3];
    String gamePhrase;
    String displayHost;
    Hosts currentHost = new Hosts();
    int numPlayers;
    JFrame aFrame;
    JLabel currentPhrase;
    JLabel players;
    JLabel host;
    boolean correct = false;
    boolean keepPlaying = true;
    JButton enterHost = new JButton("Add host");
    JButton enterPlayers = new JButton("Add Player");
    JButton startGame = new JButton("Start Guessing");
    Phrases phrase = new Phrases();
    
    public void playGame() {
        
        numPlayers = 0;
        aFrame = new JFrame();
        players = new JLabel("Please input 3 Players");
        host = new JLabel("Please add a Host");
        currentPhrase = new JLabel("Please add a Host and phrase");
        aFrame.setLayout(new GridLayout());
        
        enterPlayers.addActionListener(this);
        enterHost.addActionListener(this);
        startGame.addActionListener(this);
        aFrame.add(enterPlayers);
        aFrame.add(players);
        aFrame.add(enterHost);
        aFrame.add(host);
        aFrame.add(currentPhrase);
        aFrame.add(startGame);
        aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aFrame.pack();
        aFrame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enterPlayers) {
            String name = JOptionPane.showInputDialog("What is your name?");
            String [] firstLast = name.split(" ", 2);
            
            if (firstLast.length < 2) {
                currentPlayers[numPlayers] = new Players(firstLast[0], "");
            }
            else {
                currentPlayers[numPlayers] = new Players(firstLast[0], firstLast[1]);
            }
            ++numPlayers;
            refreshPlayers();
        }
        else if (e.getSource() == enterHost) {
            String hostsName = JOptionPane.showInputDialog("What is the host's name?");
            displayHost = hostsName;
            String [] firstLast = hostsName.split(" ", 2);
            if (firstLast.length < 2) {
                currentHost = new Hosts(firstLast[0], "");
            }
            else {
                currentHost = new Hosts(firstLast[0], firstLast[1]);
            }
            refreshHost();
            gamePhrase = JOptionPane.showInputDialog("What is the game Phrase?");
            currentHost.pickNewPhrase(gamePhrase); 
            refreshCurrentPhrase();  
        }
        else if (e.getSource() == startGame){
            int i = 0;
            Turn turn = new Turn();
            keepPlaying = true;

            for (int j =0; j< currentPlayers.length; j++) {
                if (currentPlayers[j] == null) {
                    JOptionPane.showMessageDialog(aFrame, "Please input the Players.");
                    break;
                }
            }

            if (phrase.getPlayingPhrase().indexOf("_") < 0) {
                JOptionPane.showMessageDialog(aFrame, "Please input a new phrase");
            }
            
            else {
                while (keepPlaying) {
                    while (!correct) {
                        correct = turn.takeTurn(currentPlayers[i], currentHost);
                        i = (i + 1) % currentPlayers.length;
                        refreshCurrentPhrase();
                    } 
                    if (correct) {
                        int answer = JOptionPane.showConfirmDialog(aFrame, "Would you like to play again?");
                        if (answer == JOptionPane.YES_OPTION) {
                            keepPlaying = false;
                        }
                        else if (answer == JOptionPane.NO_OPTION){
                            System.exit(0);
                        }
                        correct = false;
                    }
                }
            }

        }
    }

    public void refreshPlayers() {
        String allPlayers = "";

        for (int i=0; i < numPlayers; ++i) {
            allPlayers += currentPlayers[i].getFullName();
            if (i < numPlayers - 1) {
                allPlayers += ", ";
            }
        }
        players.setText(allPlayers);
    }

    public void refreshHost() {
        host.setText(displayHost);;
    }

    public void refreshCurrentPhrase() {
        currentPhrase.setText("Phrase: " + phrase.getPlayingPhrase());
    }
}
