import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.*;
import javax.sound.sampled.AudioSystem;

public class GUI implements ActionListener{
    Players[] currentPlayers = new Players[3];
    String gamePhrase;
    String displayHost;
    Hosts currentHost = new Hosts();
    int numPlayers;
    JFrame aFrame;
    JPanel aPanel;
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
    JMenuItem attribution = new JMenuItem("Attribution");
    JTextArea messageText = new JTextArea(10,20);
    JCheckBox saveMessages = new JCheckBox("Save Messages");

    public void playGame() {
        
        numPlayers = 0;
        aFrame = new JFrame();
        aPanel = new Animation();
        JMenuBar mainBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenu about = new JMenu("About");
        JScrollPane scrollable = new JScrollPane(messageText,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        messageText.setEditable(false);

        aFrame.setVisible(true);
        aFrame.add(aPanel);
        aFrame.pack();

        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            System.out.println(e);
        }
        aFrame.remove(aPanel);
        
        aFrame.setLayout(new GridLayout(2,3, 20, 20));
        about.setMnemonic('A');
        gameMenu.setMnemonic('g');
        aFrame.setJMenuBar(mainBar);
        saveMessages.setToolTipText("If checked all messages will be saved in the text area.");
        mainBar.add(gameMenu);
        mainBar.add(about);
        gameMenu.add(addPlayer);
        gameMenu.add(addHost);
        about.add(layout);
        about.add(attribution);
        layout.addActionListener(this);
        attribution.addActionListener(this);
        addPlayer.addActionListener(this);
        addHost.addActionListener(this);
        players = new JLabel("Please input 3 Players");
        host = new JLabel("Please add a Host");
        currentPhrase = new JLabel("Please add a Host and phrase");
        
        
        startGame.addActionListener(this);
        aFrame.add(players);
        aFrame.add(host);
        aFrame.add(currentPhrase);
        aFrame.add(scrollable);
        aFrame.add(saveMessages);

        aFrame.add(startGame);
        aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aFrame.pack();
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
        else if(e.getSource() == attribution) {
            JOptionPane.showMessageDialog(aFrame, "Pictures: \nTitle: Piano-1731467, Creator: ColiNOOB, URL: https://pixabay.com/photos/piano-instrument-music-piano-keys-1731467/ \nTitle: ameland-5651866, Creator: gabedejong, URL: https://pixabay.com/photos/ameland-the-netherlands-house-beach-5651866/ \nTitle: car-604019, Creator: jarmoluk, URL: https://pixabay.com/photos/car-audi-auto-automotive-vehicle-604019/ \nTitle: wellness-1021131, Creator: sonja_paetow, URL: https://pixabay.com/photos/wellness-massage-relaxation-1021131/ \nTitle: polynesia-3021072, Creator: julius_Silver, URL: https://pixabay.com/photos/polynesia-french-polynesia-tahiti-3021072/ \nTitle: dollars-499481, Creator: geralt, URL:https://pixabay.com/photos/dollars-currency-money-us-dollars-499481/\n\nSound: \nTitle: Correct answer tone, URL: https://mixkit.co/free-sound-effects/tones/ \nTitle: Wrong long buzzer, URL: https://mixkit.co/free-sound-effects/wrong/");
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

    public void showPrizeImage(String fileName) {
        try{
            ImageIcon icon = new ImageIcon(fileName);
            JOptionPane.showMessageDialog(aFrame, "", "You Won!", JOptionPane.INFORMATION_MESSAGE, icon);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void playSound (String soundFile) {
        try {
            File f = new File(soundFile);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());  
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    public void playWrongAnswer() {
        playSound("Multimedia\\wrongAnswer.wav");
    }

    public void playCorrectAnswer() {
        playSound("Multimedia\\correctAnswer.wav");
    }
}
