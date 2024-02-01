package game;

/**
 * La classe game.OeuvresJoueur représente la pile d'œuvres exposées par un joueur.
 * Cette classe hérite de la classe game.Pile.
 */
public class OeuvresJoueur extends Pile {

    /**
     * Retourne la dernière œuvre exposée par le joueur.
     * @return La dernière œuvre exposée.
     */
    public Carte getOeuvreExposee() {
        return cartes[cartes.length - 1];
    }

    /**
     * Compte le total des points des œuvres exposées par le joueur.
     * Les points sont calculés en fonction de la couleur des cartes.
     * @return Le total des points des œuvres exposées.
     */
    public int compterPoints() {
        int pointsRouge = 0;
        int pointsBleu = 0;
        int pointsVert = 0;
        int pointsMosaique = 0;

        // Parcours toutes les cartes dans la pile d'œuvres exposées
        for (Carte carte : cartes) {
            // Calcule les points en fonction de la couleur de la carte
            if (carte.getCouleur() == EnumCouleur.rouge) {
                pointsRouge += carte.getPoints();
            } else if (carte.getCouleur() == EnumCouleur.bleu) {
                pointsBleu += carte.getPoints();
            } else if (carte.getCouleur() == EnumCouleur.vert) {
                pointsVert += carte.getPoints();
            } else if (carte.getCouleur() == EnumCouleur.mosaique) {
                pointsMosaique += carte.getPoints();
            }
        }

        // Compare les points de chaque couleur et retourne le total
        if (pointsRouge >= pointsBleu && pointsRouge >= pointsVert) {
            return pointsRouge + pointsMosaique;
        } else if (pointsBleu >= pointsRouge && pointsBleu >= pointsVert) {
            return pointsBleu + pointsMosaique;
        } else {
            return pointsVert + pointsMosaique;
        }
    }
}
