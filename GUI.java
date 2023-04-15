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
    
    JButton startGame = new JButton("Start Guessing");
    Phrases phrase = new Phrases();
    JMenuItem addPlayer = new JMenuItem("Add Player");
    JMenuItem addHost = new JMenuItem("Add Host");
    JMenuItem layout = new JMenuItem("Layout");
    JTextArea messageText = new JTextArea(10,20);
    JCheckBox saveMessages = new JCheckBox("Save Messages");

    public void playGame() {
        
        numPlayers = 0;
        aFrame = new JFrame();
        JMenuBar mainBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenu about = new JMenu("About");
        JScrollPane scrollable = new JScrollPane(messageText,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        messageText.setEditable(false);
        
        about.setMnemonic('A');
        gameMenu.setMnemonic('g');
        aFrame.setJMenuBar(mainBar);
        saveMessages.setToolTipText("If checked all messages will be saved in the text area.");
        mainBar.add(gameMenu);
        mainBar.add(about);
        gameMenu.add(addPlayer);
        gameMenu.add(addHost);
        about.add(layout);
        layout.addActionListener(this);
        addPlayer.addActionListener(this);
        addHost.addActionListener(this);
        players = new JLabel("Please input 3 Players");
        host = new JLabel("Please add a Host");
        currentPhrase = new JLabel("Please add a Host and phrase");
        aFrame.setLayout(new GridLayout(2,3, 20, 20));
        
        
        startGame.addActionListener(this);
        aFrame.add(players);
        aFrame.add(host);
        aFrame.add(currentPhrase);
        aFrame.add(scrollable);
        aFrame.add(saveMessages);

        aFrame.add(startGame);
        aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aFrame.pack();
        aFrame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addPlayer) {
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
        else if (e.getSource() == addHost) {
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
        else if(e.getSource() == layout) {
            JOptionPane.showMessageDialog(aFrame, "I chose a Gridlayout so I can place my 3 JLabels on top to display the names of the host, players, and phrase. \n I can then place the text area, checkbox, and button on the bottom row. \n While this may not be perfect I prefer this layout over others.");
        }
        else if (e.getSource() == startGame){
            int i = 0;
            Turn turn = new Turn();
            keepPlaying = true;

            for (int j =0; j< currentPlayers.length; j++) {
                if (currentPlayers[j] == null) {
                    showMessage("Please input the Players. ");
                    break;
                }
            }

            if (phrase.getPlayingPhrase().indexOf("_") < 0) {
                messageText.append("Please input a new phrase \n");
            }
            
            else {
                while (keepPlaying) {
                    while (!correct) {
                        correct = turn.takeTurn(currentPlayers[i], currentHost, this);
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

    public void clearMessage () {
        if(!saveMessages.isSelected()) {
        messageText.setText(null);
        }
    }

    public void showMessage(String message) {
        messageText.append(message + "\n");
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
