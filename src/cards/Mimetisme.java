package cards;
import game.*;

/**
 * Classe Mimetisme qui implémente le pouvoir de la carte Mimetisme
 * Permet de copier le pouvoir d'une des œuvres exposées d'un joueur
 * Hérite de la classe game.Carte
 *
 * @see Carte
 */
public class Mimetisme extends Carte {

    public static final int NB_CARTES = 2;

    public Mimetisme() {
        this.couleur = EnumCouleur.mosaique;
        this.points = 1;
        this.nom = "Mimétisme";
        this.description = "Choisissez un Rival. Copiez le pouvoir de son Oeuvre Exposée.";
    }

    /**
     * Utilise le pouvoir de la carte
     */
    public void utiliserPouvoir() {
        // on récupère le joueur actuel
        Joueur joueurActuel = Partie.getInstance().getJoueurActuel();
        // on récupère la main du joueur actuel
        Pile main = joueurActuel.getMain();
        // on récupère les œuvres du joueur adverse
        Joueur joueurAdverse = Partie.getInstance().getJoueurAdverse(joueurActuel);
        Pile oeuvres = joueurAdverse.getOeuvres();
        // on ajoute la carte mimetisme aux cartes jouées pour pouvoir
        if (main.contientCarte(this)) {
            Partie.getInstance().getJoueurActuel().getCartesJoueesPourPouvoir().ajouterCarte(this);
        }
        // on copie le pouvoir de la dernière œuvre exposée du joueur adverse
        if (oeuvres.getNbCartes() > 0 && oeuvres != null) {
            // on récupère la dernière carte de la pile d'œuvres du joueur adverse
            Carte carteChoisie = oeuvres.getCarte(oeuvres.getNbCartes() - 1);
            //on crée une instance temporaire de la carte choisie pour ne pas rajouter et on utilise son pouvoir
            Carte carteTemp = null;
            try {
                carteTemp = carteChoisie.getClass().getDeclaredConstructor().newInstance();
                carteTemp.utiliserPouvoir();
                // on supprime la carte temporaire
                carteTemp = null;
            } catch (Exception e) {
                Utils.println("Erreur : impossible de copier la carte", "rouge");
            }
        } else {
            Utils.println("Vous n'avez pas d'oeuvre à copier", "vert");
        }
    }
}
