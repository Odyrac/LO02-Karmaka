package cards;
import game.*;

/**
 * Classe Incarnation qui implémente le pouvoir de la carte Incarnation
 * Permet de copier le pouvoir d'une de ses oeuvres
 * Hérite de la classe game.Carte
 *
 * @see Carte
 */
public class Incarnation extends Carte {

    /**
     * Nombre de cartes dans le jeu
     */
    public static final int NB_CARTES = 5;

    /**
     * Ìnstancie une carte Incarnation avec les valeurs par défaut
     */
    public Incarnation() {
        this.couleur = EnumCouleur.mosaique;
        this.points = 1;
        this.nom = "Incarnation";
        this.description = "Choisissez une de vos Oeuvres. Copiez son pouvoir.";
    }

    /**
     * Utilise le pouvoir de la carte
     */
    public void utiliserPouvoir() {
        // on récupère le joueur actuel
        Joueur joueurActuel = Partie.getInstance().getJoueurActuel();
        // on récupère la main du joueur actuel
        Pile main = joueurActuel.getMain();
        // on récupère les oeuvres du joueur actuel
        Pile oeuvres = joueurActuel.getOeuvres();
        // on ajoute la carte incarnation aux cartes jouées pour pouvoir
        if (main.contientCarte(this)) {
            Partie.getInstance().getJoueurActuel().getCartesJoueesPourPouvoir().ajouterCarte(this);
        }
        // on demande au joueur quelle carte il veut copier parmi ses oeuvres
        if (oeuvres.getNbCartes() > 0 && oeuvres != null) {
            Pile.cartesToString(oeuvres, true, true);
            Utils.println("Quelle carte voulez-vous copier ? (1-" + oeuvres.getNbCartes() + ")", "vert");
            // on récupère le choix du joueur en repetant la question tant qu'il ne choisit pas une carte valide avec les exceptions
            int choixCarte = 0;
            boolean carteValide = false;
            while (!carteValide) {
                try {
                    choixCarte = Utils.inputInt("Choix : ", "jaune", true, oeuvres.getNbCartes());
                    // on récupère la carte choisie
                    Carte carteChoisie = oeuvres.getCarte(choixCarte - 1);
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
                    carteValide = true;
                } catch (Exception e) {
                    Utils.println("Erreur : choix invalide", "rouge");
                }
            }
        } else {
            Utils.println("Vous n'avez pas d'oeuvre à copier", "vert");
        }
    }
}
