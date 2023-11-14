public class Voyage extends Carte{

        public static final int NB_CARTES = 2;

        public Voyage() {
            this.couleur = EnumCouleur.vert;
            this.points = 3;
            this.nom = "Voyage";
            this.description = "Puisez 3 cartes à la Source. Vous pouvez ensuite jouer une autre carte.";
        }
}
