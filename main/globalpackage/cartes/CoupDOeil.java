public class CoupDOeil extends Carte {
    public CoupDOeil() {
        //disponible dans le jeu en quantité de 3
        this.couleur = EnumCouleur.bleu;
        this.points = 1;
        this.nom = "Coup d'Oeil";
        this.description = "Regardez la Main d’un rival. Vous pouvez ensuite jouer une autre carte.";
    }
}
