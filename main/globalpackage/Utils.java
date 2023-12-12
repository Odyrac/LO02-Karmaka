import java.util.Scanner;

public class Utils {
    // on ajoute les fonctions réutilisables style input de texte qu'on évite qu'il
    // y en ait partout et que pour le rendre plus beau dans la console on ait juste
    // à modifier ici

    public static String appliquerCouleur(String text, String color) {
        if (color == null) {
            color = "";
        }

        if (color.equals("gris")) {
            color = "\u001B[90m";
        } else if (color.equals("rouge")) {
            color = "\u001B[31m";
        } else if (color.equals("orange")) {
            color = "\u001B[38;5;208m";
        } else if (color.equals("bleu")) {
            color = "\u001B[34m";
        } else if (color.equals("vert")) {
            color = "\u001B[32m";
        } else if (color.equals("jaune")) {
            color = "\u001B[33m";
        } else if (color.equals("rose")) {
            color = "\u001B[35m";
        } else if (color.equals("cyan")) {
            color = "\u001B[36m";
        } else if (color.equals("mosaique")) {
            // on modife le text pour que chaque lettre soit d'une couleur différente entre
            // rouge, bleu et vert
            StringBuilder textMosaique = new StringBuilder();
            for (int i = 0; i < text.length(); i++) {
                if (i % 3 == 0) {
                    textMosaique.append("\u001B[31m").append(text.charAt(i));
                } else if (i % 3 == 1) {
                    textMosaique.append("\u001B[34m").append(text.charAt(i));
                } else {
                    textMosaique.append("\u001B[32m").append(text.charAt(i));
                }
            }
            return textMosaique.toString();
        } else {
            color = "\u001B[37m"; // Blanc par défaut
        }

        return color + text;
    }

    // system.out.println, mais qui accepte une couleur en paramètre
    public static void println(String text, String color) {
        String coloredText = appliquerCouleur(text, color);
        System.out.println(coloredText + "\u001B[0m");
    }

    public static void printlnImportant(String text, String color) {
        Utils.println("--------------------------------------------------", "gris");
        Utils.println(text, color);
        Utils.println("--------------------------------------------------", "gris");
    }

    public static void printLigne() {
        Utils.println("--------------------------------------------------", "gris");
    }

    public static int inputInt(String text, String color, boolean enJeu, int nombreChoix) {
        Scanner sc = new Scanner(System.in);
        Utils.println(text, color);

        // si c'est un input de type enJeu et que joueur actuel est de type
        // JoueurVirtuel alors on fait un choix aléatoire
        if (enJeu && Partie.getInstance().getJoueurActuel() instanceof JoueurVirtuel) {
            int choix = (int) (Math.random() * nombreChoix) + 1;
            Utils.println("[BOT] Choix du JoueurVirtuel : " + choix, "rose");
            return choix;
        }

        boolean choixValide = false;
        int input = 0;

//        while (!sc.hasNextInt()) {
//            Utils.println("Veuillez entrer un entier valide.", "rouge");
//            sc.next(); // consommer l'entrée invalide
//            Utils.println(text, color);
//        }

        while (!choixValide) {
            try {
                while (!sc.hasNextInt()) {
                    Utils.println("Veuillez entrer un entier valide.", "rouge");
                    sc.next(); // consommer l'entrée invalide
                    Utils.println(text, color);
                }
                input = sc.nextInt();
                if (input >= 1 && input <= nombreChoix) {
                    choixValide = true;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                Utils.println("Erreur : choix invalide", "rouge");
            }
        }
        return input;
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void waitEnter() {
        System.out.print("\n");
        Utils.println("Appuyez sur entrée...", "jaune");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

    public static String inputString(String text, String color) {
        // si c'est un bot qui joue, on fait un choix aléatoire entre o et n
        if (Partie.getInstance().getJoueurActuel() instanceof JoueurVirtuel) {
            String[] choix = {"o", "n"};
            String choixAleatoire = choix[(int) (Math.random() * 2)];
            Utils.println("[BOT] Choix du JoueurVirtuel : " + choixAleatoire, "rose");
            return choixAleatoire;
        }
        Scanner sc = new Scanner(System.in);
        Utils.println(text, color);

        String input = sc.nextLine();
        return input;
    }

    public static String center(String text, int length) {
        if (text.length() >= length) {
            return text.substring(0, length);
        }

        int padding = (length - text.length()) / 2;
        StringBuilder centeredText = new StringBuilder();

        for (int i = 0; i < padding; i++) {
            centeredText.append(" ");
        }

        centeredText.append(text);

        while (centeredText.length() < length) {
            centeredText.append(" ");
        }

        return centeredText.toString();
    }

    public static String construirePaquet(String titre, int nombreCartes, String couleur, String dernierElement) {
        String couleurTexte = appliquerCouleur("", couleur);

        String bordureHautBas = couleurTexte + "+-------------+" + "\u001B[0m";
        String bordureCote = couleurTexte + "|             |" + "\u001B[0m";
        String contenu = couleurTexte + "|" + Utils.center(titre, 13) + "|" + "\u001B[0m";
        String nombreCartesTexte = couleurTexte + "|" + Utils.center(nombreCartes + " cartes", 13) + "|"
                + "\u001B[0m";
        String contenuDernierElement = (dernierElement != "")
                ? couleurTexte + "|" + Utils.center("Dernière : ", 13) + "|" + "\u001B[0m"
                : bordureCote;

        String contenuDernierElement2 = couleurTexte + "|" + Utils.center(dernierElement, 13) + "|" + "\u001B[0m";

        StringBuilder paquet = new StringBuilder();
        paquet.append(bordureHautBas).append("\n");
        paquet.append(bordureCote).append("\n");
        paquet.append(contenu).append("\n");
        paquet.append(bordureCote).append("\n");
        paquet.append(bordureCote).append("\n");
        paquet.append(nombreCartesTexte).append("\n");
        paquet.append(contenuDernierElement).append("\n");

        if (dernierElement != "") {
            paquet.append(contenuDernierElement2).append("\n");
        } else {
            paquet.append(bordureCote).append("\n");
        }

        paquet.append(bordureHautBas).append("\n");

        return paquet.toString();
    }

    public static String concatenerPaquets(String paquet1, String paquet2) {
        String[] lignesPaquet1 = paquet1.split("\n");
        String[] lignesPaquet2 = paquet2.split("\n");

        StringBuilder resultat = new StringBuilder();
        int maxLignes = Math.max(lignesPaquet1.length, lignesPaquet2.length);

        for (int i = 0; i < maxLignes; i++) {
            String lignePaquet1 = (i < lignesPaquet1.length) ? lignesPaquet1[i] : "";
            String lignePaquet2 = (i < lignesPaquet2.length) ? lignesPaquet2[i] : "";

            resultat.append(lignePaquet1).append("    ").append(lignePaquet2).append("\n");
        }

        return resultat.toString();
    }

    public static void infosPlateau(Partie partie, String pseudo) {
        Utils.printLigne();
        int nombreDeCartesSource = partie.getPlateau().getLaSource().getNbCartes();
        int nombreDeCartesFosse = partie.getPlateau().getLaFosse().getNbCartes();
        String dernierElement;
        if (partie.getPlateau().getLaFosse().getDerniereCarte() == null) {
            dernierElement = "/";
        } else {
            dernierElement = partie.getPlateau().getLaFosse().getDerniereCarte().getNom();
        }

        Utils.println("Plateau (actuellement le tour de " + pseudo + ") :", "orange");

        System.out.println(Utils.concatenerPaquets(Utils.construirePaquet("LaSource", nombreDeCartesSource, "cyan", ""),
                Utils.construirePaquet("LaFosse", nombreDeCartesFosse, "rose", dernierElement)));

        Utils.printLigne();
    }

    public static void infosJoueur(Joueur joueur) {
        int nombreDeCartesVieFuture = joueur.getVieFuture().getNbCartes();
        int nombreDeCartesPile = joueur.getPile().getNbCartes();

        Utils.println("Oeuvres :", "gris");
        Pile.cartesToString(joueur.getOeuvres(), false, false);

        Utils.println("Anneaux Karmiques : " + joueur.getAnneauxKarmiques(), "gris");

        Utils.println("Position Echelle Karmique : " + joueur.getPositionEchelleKarmique(), "gris");

        System.out.println(
                Utils.concatenerPaquets(Utils.construirePaquet("Vie Future", nombreDeCartesVieFuture, "gris", ""),
                        Utils.construirePaquet("Pile", nombreDeCartesPile, "gris", "")));

    }

}