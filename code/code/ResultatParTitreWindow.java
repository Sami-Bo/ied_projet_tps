package code;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ResultatParTitreWindow extends JFrame {
    public ResultatParTitreWindow(Film movie) {
        setTitle("Détails du film " + movie.getTitre());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(10, 1));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(createSectionPanel("Titre", movie.getTitre()));
        mainPanel.add(createSectionPanel("Date de sortie", movie.getDateSortie()));
        mainPanel.add(createSectionPanel("Genre", movie.getGenre()));
        mainPanel.add(createSectionPanel("Résumé", movie.getResume()));
        mainPanel.add(createSectionPanel("Distributeur", movie.getDistributeur()));
        mainPanel.add(createSectionPanel("Budget", String.valueOf(movie.getBudget())));
        mainPanel.add(createSectionPanel("Revenus aux États-Unis", String.valueOf(movie.getRevenusUs())));
        mainPanel.add(createSectionPanel("Revenus mondiaux", String.valueOf(movie.getRevenusMonde())));
        mainPanel.add(createSectionPanel("Réalisateurs", formatList(movie.getRealisateur())));
        mainPanel.add(createSectionPanel("Acteurs", formatList(movie.getActeurs())));

   
        add(mainPanel);
    }

    
    private JPanel createSectionPanel(String title, String info) {
        JPanel sectionPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(title + " : ");
        JLabel infoLabel = new JLabel(info);
        sectionPanel.add(titleLabel, BorderLayout.WEST);
        sectionPanel.add(infoLabel, BorderLayout.CENTER);
        return sectionPanel;
    }

  
    private String formatList(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String item : list) {
            sb.append(item).append(" , ");
        }
       
        return sb.length() > 0 ? sb.substring(0, sb.length() - 2) : "";
    }

   
    public void displayMovieDetails(Film movie) {
        SwingUtilities.invokeLater(() -> {
        	ResultatParTitreWindow frame = new ResultatParTitreWindow(movie);
        	frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}



