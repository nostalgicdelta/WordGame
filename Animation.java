import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.*;

public class Animation extends JPanel implements ActionListener{
    Image money;
    Timer timer;
    int xVelocity = 2;
    int yVelocity = 1;
    int x = 0;
    int y = 0;

    Animation() {
        this.setPreferredSize(new Dimension(640, 480));
        this.setBackground(Color.white);
        this.setVisible(true);
        
        
        money = new ImageIcon("Multimedia\\money.jpg").getImage();
        timer = new Timer(10, this);
        timer.start();

     
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(money, x, y, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x = x + xVelocity;
        repaint();
    }
}
