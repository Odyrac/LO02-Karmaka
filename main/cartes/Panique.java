public class Panique extends Carte{

        public static final int NB_CARTES = 3;

        public Panique() {
            this.couleur = EnumCouleur.rouge;
            this.points = 1;
            this.nom = "Panique";
            this.description = "Défaussez la première carte de la Pile d'un joueur. Vous pouvez ensuite jouer une autre carte ";
        }
}
