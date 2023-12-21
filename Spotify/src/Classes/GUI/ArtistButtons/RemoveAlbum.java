package Classes.GUI.ArtistButtons;

import Classes.Album;
import Classes.GUI.jframes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveAlbum implements ActionListener {

    jframes removeAlbumPage=new jframes("Remove Album");
    JTextField albNameT=new JTextField();
    JButton delete=new JButton("Remove Album");

    public RemoveAlbum(){

        removeAlbumPage.setLayout(new BorderLayout());

        JPanel panel=new JPanel(new GridBagLayout());
        GridBagConstraints loc=new GridBagConstraints();
        loc.insets=new Insets(20,20,20,20);
        loc.gridx=0;

        Dimension size=new Dimension(120,30);

        JLabel albumName = new JLabel("Album Name:");
        loc.gridy = 0;
        panel.add(albumName, loc);
        albNameT.setPreferredSize(size);
        loc.gridx = 1;
        panel.add(albNameT, loc);

        //button
        delete.setFocusable(false);
        delete.addActionListener(this);
        delete.setSize(200, 200);
        loc.gridx = 0;
        loc.gridy = 1;
        panel.add(delete, loc);


        //Add to frame
        removeAlbumPage.add(panel,BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==delete){
            String albumNameEntered = albNameT.getText();

            Album albumToDelete = new Album();
            albumToDelete.setAlbumName(albumNameEntered);

            if (albumToDelete.isAlbumSaved(albumNameEntered)) {
                Album.deleteAlbumFromFile(albumToDelete);
                JOptionPane.showMessageDialog(null, "Album Removed Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                removeAlbumPage.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Album Not Found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
