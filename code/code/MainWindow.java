package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class MainWindow extends JFrame {
    private JTextField textField;
    private JButton submitButton;
    private ButtonGroup radioButtonGroup;
    
    public MainWindow() {
        setTitle("Recherche");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(6, 7));
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2));
        
        JLabel label3 = new JLabel("Choisissez le mode de recherche:");
        JLabel label4 = new JLabel("    ");
        radioButtonGroup = new ButtonGroup();
        JRadioButton radioButton1 = new JRadioButton("Par titre de film");
        JRadioButton radioButton2 = new JRadioButton("Par acteur");
        radioButtonGroup.add(radioButton1);
        radioButtonGroup.add(radioButton2);
        panel1.add(label3);
        panel1.add(label4);
        panel1.add(radioButton1);
        panel1.add(new JLabel()); 
        panel1.add(radioButton2);
        panel1.add(new JLabel());
        
        ActionListener radioListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitButton.setEnabled(true);
            }
        };

        radioButton1.addActionListener(radioListener);
        radioButton2.addActionListener(radioListener);
     
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        submitButton = new JButton("Recherche");
        submitButton.setEnabled(false);
        panel2.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openResultWindow();
            }
        });
        
        //JLabel label1 = new JLabel("");
        textField = new JTextField();
        //inputPanel.add(label1);
        inputPanel.add(textField);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2));
        mainPanel.add(panel1);
        mainPanel.add(inputPanel);
        mainPanel.add(panel2);
        
        add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private String getSelectedRadioButton() {
        for (Enumeration<AbstractButton> buttons = radioButtonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    
    private void openResultWindow() {
    	// Vérifie si le champ de texte est vide
        if (textField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un texte avant de rechercher.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return; // Arrête le traitement si le champ de texte est vide
        }
        String selectedRadioButton = getSelectedRadioButton();
        if (selectedRadioButton == "Par titre de film") {
        	String titreFilm = textField.getText();
            
            System.out.println("Résultat pour le film : " + titreFilm);
            
            Film film = DBconnection.getFilmByTitre(titreFilm);

            List<String> actors = DBpedia.getActeursByTitre(titreFilm);
            film.setActeurs(actors);
            
            List<String> directors = DBpedia.getDirectorByTitre(titreFilm);
            film.setRealisateurs(directors);
            
            List<String> producers = DBpedia.getProducerByTitre(titreFilm);
            film.setProducteurs(producers);
            
            Omdb omdb = new Omdb();
            omdb.setResume(titreFilm);
            
            film.setResume(omdb.getResume());
            
			film.afficherDetails();
			ResultatParTitreWindow resultWindow = new ResultatParTitreWindow(film);
            resultWindow.displayMovieDetails(film);
        }else {
        	String nomActeur = textField.getText();
            List<String> titre_films = DBpedia.getFilmsParActeur(nomActeur);
            List<Film> films = new ArrayList<>();
            if (titre_films.isEmpty()) {
                System.out.println("Aucun film trouvé pour l'acteur : " + nomActeur);
            } else {
                System.out.println("Liste des films pour l'acteur " + nomActeur + " :");
                System.out.println(titre_films);
                System.out.println("");
                for (String titre_film : titre_films) {
                    System.out.println("Titre: " + titre_film);
                    Film film2 = DBconnection.getFilmByTitre(titre_film);
                    List<String> directors2 = DBpedia.getDirectorByTitre(titre_film);
                    film2.setRealisateurs(directors2);
                    
                    List<String> producers2 = DBpedia.getProducerByTitre(titre_film);
                    film2.setProducteurs(producers2);
                    film2.setTitre(titre_film);
                    film2.afficherDetails2();
                    
                    films.add(film2);
                 }
            }
            ResultatParActeurWindow resultWindow = new ResultatParActeurWindow(films, nomActeur);
            resultWindow.displayMovieDetails(films, nomActeur);
        }
       
    }
    
    public static void main(String[] args) {
        new MainWindow();
    }
}


