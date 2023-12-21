package Classes.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class jframes extends JFrame implements ActionListener {

    public jframes(String title) {
        this.setTitle(title); // Window Title
        this.setSize(500, 600);
        this.getContentPane().setBackground(new Color(68,134,74)); // background color

        // Window Icon
        ImageIcon icon = new ImageIcon("spotify.png"); //create imagIcon
        this.setIconImage(icon.getImage()); //change ImageIcon

        this.setVisible(true); //Show Window
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
