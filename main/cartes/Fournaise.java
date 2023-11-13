public class Fournaise extends Carte{

        public static final int NB_CARTES = 3;

        public Fournaise() {
            this.couleur = EnumCouleur.rouge;
            this.points = 2;
            this.nom = "Fournaise";
            this.description = "Défaussez les 2 premières cartes de la Vie Future d'un rival.";
        }
}
