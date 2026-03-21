package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Enumeration;



import java.util.ArrayList;

import java.util.List;

public class ResultatParActeurWindow extends JFrame {
    public ResultatParActeurWindow (List<Film> films, String acteur) {
        setTitle("Détails des films pour l'acteur " + acteur);
        setSize(900, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JPanel filmsPanel = new JPanel(new GridLayout(films.size(), 1, 0, 10));
        filmsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        
        for (Film film : films) {
            JPanel moviePanel = createMoviePanel(film);
            filmsPanel.add(moviePanel);
        }

        
        JScrollPane scrollPane = new JScrollPane(filmsPanel);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createMoviePanel(Film film) {
        JPanel moviePanel = new JPanel(new BorderLayout());
        moviePanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(), BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        JLabel titleLabel = new JLabel("Titre: " + film.getTitre());
        JLabel dateLabel = new JLabel("Date de sortie: " + film.getDateSortie());
        JLabel genreLabel = new JLabel("Genre: " + film.getGenre());
        
        JLabel DistributeurLabel = new JLabel("Distributeur: " + film.getDistributeur());
        String directorsText = film.getRealisateur() != null ? String.join(", ", film.getRealisateur()) : "";
        String producersText = film.getProducteurs() != null ? String.join(", ", film.getProducteurs()) : "";
        JLabel directorsLabel = new JLabel("Réalisateur(s): " + String.join(", ", directorsText));
        JLabel producersLabel = new JLabel("Producteur(s): " + String.join(", ", producersText));

        JPanel detailsPanel = new JPanel(new GridLayout(7, 1));
        detailsPanel.add(titleLabel);
        detailsPanel.add(dateLabel);
        detailsPanel.add(genreLabel);
        detailsPanel.add(DistributeurLabel);
        detailsPanel.add(directorsLabel);
        detailsPanel.add(producersLabel);

        moviePanel.add(detailsPanel, BorderLayout.CENTER);

        return moviePanel;
    }

    
    public void displayMovieDetails(List<Film> films, String acteur) {
        SwingUtilities.invokeLater(() -> {
        	ResultatParActeurWindow frame = new ResultatParActeurWindow(films, acteur);
        	frame.setLocationRelativeTo(null);
        	frame.setVisible(true);
        });
    }

}