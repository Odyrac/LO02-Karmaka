public class Carte {
    protected String nom;
    protected String description;
    protected int points;
    protected EnumCouleur couleur;
    protected int nbCartes = 0;



//    toString method detailing name, desc, points and color
    public String toString() {
        return "Nom: " + this.nom + "\nDescription: " + this.description + "\nPoints: " + this.points + "\nCouleur: " + this.couleur;
    }

//    fonction pour le pouvoir de la carte
    public void utiliserPouvoir() {}


    public EnumCouleur getCouleur() {
        return couleur;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public int getPoints() {
        return points;
    }
}


