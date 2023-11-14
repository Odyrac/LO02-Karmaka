public class Roulette extends Carte{

        public static final int NB_CARTES = 3;

        public Roulette() {
            this.couleur = EnumCouleur.rouge;
            this.points = 2;
            this.nom = "Roulette";
            this.description = "Défaussez jusqu'a 2 cartes de votre Main. Vous pouvez ensuite puiser à la Source autant de carte(s) +1.";
        }
}
