public class OeuvresJoueur extends Pile {

    public Carte getOeuvreExposee() {
        return cartes[cartes.length - 1];
    }

    public int compterPoints() {
        int pointsRouge = 0;
        int pointsBleu = 0;
        int pointsVert = 0;
        int pointsMosaique = 0;

        for (Carte carte : cartes) {
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

        if (pointsRouge >= pointsBleu && pointsRouge >= pointsVert) {
            return pointsRouge + pointsMosaique;
        } else if (pointsBleu >= pointsRouge && pointsBleu >= pointsVert) {
            return pointsBleu + pointsMosaique;
        } else {
            return pointsVert + pointsMosaique;
        }
    }

}
