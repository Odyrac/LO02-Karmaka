/**
 * Classe Vol qui hérite de la classe Carte
 *
 * @see Carte
 */

public class Vol extends Carte {

    /**
     * Nombre de cartes vol dans le jeu
     */
    public static final int NB_CARTES = 2;

    /**
     * Constructeur de la classe Vol
     */
    public Vol() {
        this.couleur = EnumCouleur.bleu;
        this.points = 3;
        this.nom = "Vol";
        this.description = "Placez dans votre Main l'Oeuvre Exposée d'un rival.";
    }

    /**
     * Utilisation du pouvoir de la carte
     */
    @Override
    public void utiliserPouvoir() {
        // on récupère le joueur actuel
        Joueur joueurActuel = Partie.getInstance().getJoueurActuel();
        // on récupère sa main pour ajouter la carte vol aux cartes jouees pour pouvoir
        Pile main = joueurActuel.getMain();
        if (main.contientCarte(this)) {
            Partie.getInstance().getJoueurActuel().getCartesJoueesPourPouvoir().ajouterCarte(this);
        }
        // on récupère le joueur cible (on a toujours 2 joueurs donc c'est forcément l'autre)
        Joueur joueurAdverse = Partie.getInstance().getJoueurAdverse(joueurActuel);
        // on récupère ses œuvres
        Pile oeuvres = joueurAdverse.getOeuvres();
        // on ajoute la première carte de la pile d'oeuvres du joueur adverse à la main du joueur actuel
        try {
            // on récupère la carte
            Carte carte = oeuvres.getCarte(0);
            // on l'ajoute à la main
            main.ajouterCarte(carte);
            // on la retire des oeuvres
            int index = joueurAdverse.getOeuvres().getCarteIndex(carte);
            joueurAdverse.getOeuvres().supprimerCarte(index);
            Utils.println("Vous avez placé l'Oeuvre Exposée de " + joueurAdverse.getPseudo() + ": " + carte.getNom() + " dans votre main", "vert");
        } catch (Exception e) {
            Utils.println("Erreur : la pile d'oeuvres de " + joueurAdverse.getPseudo() + " est vide", "rouge");
        }
    }
}
