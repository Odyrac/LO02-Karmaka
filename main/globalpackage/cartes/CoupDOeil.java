public class CoupDOeil extends Carte {

    public static final int NB_CARTES = 3;
    
    public CoupDOeil() {
        this.couleur = EnumCouleur.bleu;
        this.points = 1;
        this.nom = "Coup d'Oeil";
        this.description = "Regardez la Main d’un rival. Vous pouvez ensuite jouer une autre carte.";
    }
}
