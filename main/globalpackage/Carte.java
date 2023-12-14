/**
 * La classe Carte représente une carte générique avec des attributs tels que le nom, la description, les points,
 * la couleur et le nombre total de cartes.
 */
public class Carte {

    /** Le nom de la carte. */
    protected String nom;

    /** La description de la carte. */
    protected String description;

    /** Les points associés à la carte. */
    protected int points;

    /** La couleur de la carte, représentée par une énumération EnumCouleur. */
    protected EnumCouleur couleur;

    /** Le nombre total de cartes, partagé entre toutes les instances de Carte. */
    protected int nbCartes = 0;

    /**
     * Retourne une représentation textuelle de la carte, comprenant le nom, la description, les points et la couleur.
     *
     * @return Une chaîne de caractères représentant la carte.
     */
    public String toString() {
        return "Nom: " + this.nom + "\nDescription: " + this.description + "\nPoints: " + this.points + "\nCouleur: " + this.couleur;
    }

    /**
     * Méthode vide représentant le pouvoir associé à la carte.
     * Cette méthode peut être étendue par les sous-classes pour définir un pouvoir spécifique à la carte.
     */
    public void utiliserPouvoir() {}

    /**
     * Retourne la couleur de la carte.
     *
     * @return L'énumération EnumCouleur représentant la couleur de la carte.
     */
    public EnumCouleur getCouleur() {
        return couleur;
    }

    /**
     * Retourne le nom de la carte.
     *
     * @return Le nom de la carte.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne la description de la carte.
     *
     * @return La description de la carte.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retourne les points associés à la carte.
     *
     * @return Les points de la carte.
     */
    public int getPoints() {
        return points;
    }
}
