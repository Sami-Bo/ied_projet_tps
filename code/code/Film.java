package code;

import java.util.List;

public class Film {
    private String titre;
    private String dateSortie;
    private String genre;
    private String distributeur;
    private double budget;
    private double revenusUs;
    private double revenusMonde;
    private List<String> realisateurs;
    private List<String> acteurs;
    private List<String> producteurs;
    private String resume;
    
    public void afficherDetails() {
        System.out.println("Titre: " + this.getTitre());
        System.out.println("Date de sortie: " + this.getDateSortie());
        System.out.println("Genre: " + this.getGenre());
        System.out.println("Résumé: " + this.getResume());
        System.out.println("Distributeur: " + this.getDistributeur());
        System.out.println("Budget: " + this.getBudget());
        System.out.println("Revenus aux états-Unis: " + this.getRevenusUs());
        System.out.println("Revenus dans le monde: " + this.getRevenusMonde());
        System.out.println("Realisteurs: " + this.getRealisateur());
        System.out.println("Acteurs: " + this.getActeurs());
        System.out.println("Producteurs: " + this.getProducteurs());
        System.out.println("");

    }
    
    public Film() {
	}

	public void afficherDetails2() {
        System.out.println("Date de sortie: " + this.getDateSortie());
        System.out.println("Genre: " + this.getGenre());
        System.out.println("Distributeur: " + this.getDistributeur());
        System.out.println("Realisteurs: " + this.getRealisateur());
        System.out.println("Producteurs: " + this.getProducteurs());
        System.out.println("");

    }
    
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDateSortie() {
		return dateSortie;
	}
	public void setDateSortie(String dateSortie) {
		this.dateSortie = dateSortie;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getDistributeur() {
		return distributeur;
	}
	public void setDistributeur(String distributeur) {
		this.distributeur = distributeur;
	}
	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
	public double getRevenusUs() {
		return revenusUs;
	}
	public void setRevenusUs(double revenusUs) {
		this.revenusUs = revenusUs;
	}
	public double getRevenusMonde() {
		return revenusMonde;
	}
	public void setRevenusMonde(double revenusMonde) {
		this.revenusMonde = revenusMonde;
	}
	public List<String> getRealisateur() {
		return realisateurs;
	}
	public void setRealisateurs(List<String> realisateurs) {
		this.realisateurs = realisateurs;
	}
	public List<String> getActeurs() {
		return acteurs;
	}
	public void setActeurs(List<String> acteurs) {
		this.acteurs = acteurs;
	}
	public List<String> getProducteurs() {
		return producteurs;
	}
	public void setProducteurs(List<String> producteurs) {
		this.producteurs = producteurs;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}

    
    
    
}
