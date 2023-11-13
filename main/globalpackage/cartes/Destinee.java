public class Destinee extends Carte {

    public static final int NB_CARTES = 3;
    
    public Destinee() {
        this.couleur = EnumCouleur.bleu;
        this.points = 2;
        this.nom = "Destinée";
        this.description = "Regardez les 3 premières cartes de la Source ; ajoutez-en jusqu’à 2 à votre Vie Future. Replacez le reste dans l'ordre souhaité.";
    }
}