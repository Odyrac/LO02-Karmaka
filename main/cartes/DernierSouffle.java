public class DernierSouffle extends Carte{

        public static final int NB_CARTES = 3;

        public DernierSouffle() {
            this.couleur = EnumCouleur.rouge;
            this.points = 1;
            this.nom = "Dernier Souffle";
            this.description = "Le joueur de votre choix défausse une carte de sa Main.";
        }
}
