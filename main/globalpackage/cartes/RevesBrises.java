public class RevesBrises extends Carte {
    public RevesBrises() {
        //disponible dans le jeu en quantité de 3
        this.couleur = EnumCouleur.bleu;
        this.points = 1;
        this.nom = "Rêves Brisés";
        this.description = "Placez la première carte de la Vie Future d'un rival sur la vôtre.";
    }
}
