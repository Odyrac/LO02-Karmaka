
public class Main {
    public static void main(String[] args) {

        Utils.clearConsole();

        Utils.println("Ladies and Gentlemen, bienvenue sur :\n", "cyan");
        Utils.println("██╗  ██╗ █████╗ ██████╗ ███╗   ███╗ █████╗ ██╗  ██╗ █████╗ ", "mosaique");
        Utils.println("██║ ██╔╝██╔══██╗██╔══██╗████╗ ████║██╔══██╗██║ ██╔╝██╔══██╗", "mosaique");
        Utils.println("█████╔╝ ███████║██████╔╝██╔████╔██║███████║█████╔╝ ███████║", "mosaique");
        Utils.println("██╔═██╗ ██╔══██║██╔══██╗██║╚██╔╝██║██╔══██║██╔═██╗ ██╔══██║", "mosaique");
        Utils.println("██║  ██╗██║  ██║██║  ██║██║ ╚═╝ ██║██║  ██║██║  ██╗██║  ██║", "mosaique");
        Utils.println("╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝", "mosaique");

        // menu avec nouvelle partie, charger une partie, et crédits
        // on demande au joueur de choisir

        System.out.print("\n");
        Utils.println("1. Nouvelle partie", "vert");
        Utils.println("2. Charger une partie", "vert");
        Utils.println("3. Crédits", "vert");
        Utils.println("4. Quitter", "vert");
        System.out.print("\n");
        int choix = Utils.inputInt("Choix : ", "jaune", false, 4);

        if (choix == 1) {
            // on lance une nouvelle partie
            Partie.getInstance().debutPartie(null);
  

        } else if (choix == 2) {
            // on charge une partie

        } else if (choix == 3) {
            // on affiche les crédits
            credits();
        } else if (choix == 4) {
            // on quitte le jeu
            System.exit(0);
        } else {
            // on affiche un message d'erreur
            Utils.clearConsole();
            Utils.println("Erreur : choix invalide", "rouge");
            Utils.waitEnter();
            main(null);
        }

    }

    public static void credits() {
        Utils.clearConsole();
        Utils.println("Ce projet est développé dans le cadre de l'UE LO02.", "vert");
        Utils.println("Il est réalisé par :", "vert");
        Utils.println(" - Thomas MATAMBA", "mosaique");
        Utils.println(" - Louis HALLEY", "mosaique");

        Utils.waitEnter();
        main(null);

    }

}
