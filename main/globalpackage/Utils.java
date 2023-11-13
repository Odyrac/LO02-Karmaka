import java.util.Scanner;

public class Utils {
    // on ajoute les fonctions réutilisables style input de texte qu'on évite qu'il
    // y en ait partout et que pour le rendre plus beau dans la console on ait juste
    // à modifier ici

    // system.out.println, mais qui accepte une couleur en paramètre
    public static void println(String text, String color) {

        if (color == null) {
            color = "";
        }
        ;

        if (color.equals("gris")) {
            color = "\u001B[90m";
        } else if (color.equals("rouge")) {
            color = "\u001B[31m";
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
            String textMosaique = "";
            for (int i = 0; i < text.length(); i++) {
                if (i % 3 == 0) {
                    textMosaique += "\u001B[31m" + text.charAt(i);
                } else if (i % 3 == 1) {
                    textMosaique += "\u001B[34m" + text.charAt(i);
                } else {
                    textMosaique += "\u001B[32m" + text.charAt(i);
                }
            }
            text = textMosaique;
            color = "";
        } else {
            color = "\u001B[37m"; // blanc par défaut
        }
        ;

        System.out.println(color + text + "\u001B[0m");
    }

    public static int inputInt(String text, String color) {
        Scanner sc = new Scanner(System.in);
        Utils.println(text, color);

        while (!sc.hasNextInt()) {
            Utils.println("Veuillez entrer un entier valide.", "rouge");
            sc.next(); // consommer l'entrée invalide
            Utils.println(text, color);
        }

        int input = sc.nextInt();
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
        Scanner sc = new Scanner(System.in);
        Utils.println(text, color);

        String input = sc.nextLine();
        return input;
    }

}
