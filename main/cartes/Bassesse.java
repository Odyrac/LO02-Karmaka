public class Bassesse extends Carte{

        public static final int NB_CARTES = 2;

        public Bassesse() {
            this.couleur = EnumCouleur.rouge;
            this.points = 3;
            this.nom = "Bassesse";
            this.description = "Défaussez au hasard 2 cartes de la Main d'un rival.";
        }
}
