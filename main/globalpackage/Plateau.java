import java.io.Serializable;

/**
 * La classe Plateau représente le plateau de jeu.
 * Elle contient la source, la fosse et les joueurs.
 */
public class Plateau implements Serializable {
    /**
     * La pile représentant la source du jeu.
     */
    private Pile laSource;

    /**
     * La pile représentant la fosse du jeu.
     */
    private Pile laFosse;

    /**
     * Tableau des joueurs sur le plateau.
     */
    private Joueur[] joueurs;

    /**
     * Constructeur de la classe Plateau.
     * Initialise les piles et le tableau de joueurs.
     */
    public Plateau() {
        joueurs = new Joueur[2];
        laSource = new Pile();
        laFosse = new Pile();
    }

    /**
     * Définit les joueurs sur le plateau.
     * @param joueurs Le tableau des joueurs.
     */
    public void setJoueurs(Joueur[] joueurs) {
        this.joueurs = joueurs;
    }

    /**
     * Obtient le tableau des joueurs sur le plateau.
     * @return Le tableau des joueurs.
     */
    public Joueur[] getJoueurs() {
        return joueurs;
    }

    /**
     * Définit la pile représentant la source du jeu.
     * @param laSource La pile représentant la source.
     */
    public void setLaSource(Pile laSource) {
        this.laSource = laSource;
    }

    /**
     * Définit la pile représentant la fosse du jeu.
     * @param laFosse La pile représentant la fosse.
     */
    public void setLaFosse(Pile laFosse) {
        this.laFosse = laFosse;
    }

    /**
     * Obtient la pile représentant la source du jeu.
     * @return La pile représentant la source.
     */
    public Pile getLaSource() {
        return laSource;
    }

    /**
     * Obtient la pile représentant la fosse du jeu.
     * @return La pile représentant la fosse.
     */
    public Pile getLaFosse() {
        return laFosse;
    }
}
