/**
 * La classe Pile représente une pile de cartes.
 * Elle est utilisée pour la main, la pile et la fosse des joueurs.
 */
public class Pile {

    /**
     * Tableau de cartes représentant la pile.
     */
    protected Carte[] cartes;

    /**
     * Constructeur de la classe Pile.
     * Initialise la pile comme une pile vide.
     */
    public Pile() {
        cartes = new Carte[0];
    }

    /**
     * Ajoute une carte à la pile.
     * @param carte La carte à ajouter.
     */
    public void ajouterCarte(Carte carte) {
        Carte[] newCartes = new Carte[cartes.length + 1];
        System.arraycopy(cartes, 0, newCartes, 0, cartes.length);
        newCartes[cartes.length] = carte;
        cartes = newCartes;
    }

    /**
     * Pioche la carte du dessus de la pile.
     * @return La carte piochée.
     */
    public Carte piocherCarte() {
        Carte cartePiochee = cartes[cartes.length - 1];
        Carte[] newCartes = new Carte[cartes.length - 1];
        System.arraycopy(cartes, 0, newCartes, 0, cartes.length - 1);
        cartes = newCartes;
        return cartePiochee;
    }

    /**
     * Supprime une carte de la pile à l'index spécifié.
     * @param index L'index de la carte à supprimer.
     */
    public void supprimerCarte(int index) {
        Carte[] newCartes = new Carte[cartes.length - 1];
        if (index >= 0) System.arraycopy(cartes, 0, newCartes, 0, index);
        if (cartes.length - 1 - index >= 0)
            System.arraycopy(cartes, index + 1, newCartes, index, cartes.length - 1 - index);
        cartes = newCartes;
    }

    /**
     * Mélange les cartes dans la pile.
     */
    public void melangerCartes() {
        for (int i = 0; i < cartes.length; i++) {
            int random = (int) (Math.random() * cartes.length);
            Carte temp = cartes[i];
            cartes[i] = cartes[random];
            cartes[random] = temp;
        }
    }

    /**
     * Obtient la dernière carte de la pile.
     * @return La dernière carte de la pile.
     */
    public Carte getDerniereCarte() {
        if (cartes.length > 0) {
            return cartes[cartes.length - 1];
        } else {
            return null;
        }
    }

    /**
     * Obtient une carte à l'index spécifié dans la pile.
     * @param index L'index de la carte.
     * @return La carte à l'index spécifié.
     */
    public Carte getCarte(int index) {
        return cartes[index];
    }

    /**
     * Affiche les cartes de la pile avec ou sans description et avec ou sans indice.
     * @param p La pile à afficher.
     * @param avecDescription Indique si la description des cartes doit être affichée.
     * @param avecIndice Indique si les indices des cartes doivent être affichés.
     */
    public static void cartesToString(Pile p, boolean avecDescription, boolean avecIndice) {
        for (int i = 0; i < p.getNbCartes(); i++) {
            if (avecIndice) {
                Utils.println(i + 1 + ") " + p.getCarte(i).getNom() + " (" + p.getCarte(i).getPoints() + "p)",
                        p.getCarte(i).getCouleur().toString());
            } else {
                Utils.println(p.getCarte(i).getNom() + " (" + p.getCarte(i).getPoints() + "p)",
                        p.getCarte(i).getCouleur().toString());
            }

            if (avecDescription) {
                Utils.println(p.getCarte(i).getDescription(), "gris");
            }
        }
    }

    /**
     * Obtient le nombre de cartes dans la pile.
     * @return Le nombre de cartes dans la pile.
     */
    public int getNbCartes() {
        return cartes.length;
    }

    /**
     * Obtient le nombre de cartes bleues dans la pile.
     * @return Le nombre de cartes bleues.
     */
    public int getNbCartesBleues() {
        int nbCartesBleues = 0;
        for (Carte carte : cartes) {
            if (carte.getCouleur() == EnumCouleur.bleu) {
                nbCartesBleues++;
            }
        }
        return nbCartesBleues;
    }

    /**
     * Obtient le nombre de cartes rouges dans la pile.
     * @return Le nombre de cartes rouges.
     */
    public int getNbCartesRouges() {
        int nbCartesRouges = 0;
        for (Carte carte : cartes) {
            if (carte.getCouleur() == EnumCouleur.rouge) {
                nbCartesRouges++;
            }
        }
        return nbCartesRouges;
    }

    /**
     * Obtient le nombre de cartes vertes dans la pile.
     * @return Le nombre de cartes vertes.
     */
    public int getNbCartesVertes() {
        int nbCartesVertes = 0;
        for (Carte carte : cartes) {
            if (carte.getCouleur() == EnumCouleur.vert) {
                nbCartesVertes++;
            }
        }
        return nbCartesVertes;
    }

    /**
     * Obtient le nombre de cartes mosaiques dans la pile.
     * @return Le nombre de cartes mosaiques.
     */
    public int getNbCartesMosaiques() {
        int nbCartesMosaiques = 0;
        for (Carte carte : cartes) {
            if (carte.getCouleur() == EnumCouleur.mosaique) {
                nbCartesMosaiques++;
            }
        }
        return nbCartesMosaiques;
    }

    // Méthodes utiles pour le pouvoir des cartes

    /**
     * Obtient une carte aléatoire de la pile.
     * @return Une carte aléatoire.
     */
    public Carte getCarteAleatoire() {
        int random = (int) (Math.random() * cartes.length);
        return cartes[random];
    }

    /**
     * Défausse une carte de la pile.
     * Ajoute la carte à la fosse et la supprime de la pile.
     * @param carte La carte à défausser.
     */
    public void defausserCarte(Carte carte) {
        // Ajoute la carte à la fosse
        Pile fosse = Partie.getInstance().getPlateau().getLaFosse();
        fosse.ajouterCarte(carte);

        // Supprime la carte de la pile
        for (int i = 0; i < cartes.length; i++) {
            if (cartes[i] == carte) {
                supprimerCarte(i);
                break;
            }
        }
    }

    /**
     * Obtient l'index d'une carte dans la pile.
     * @param carte La carte dont on veut obtenir l'index.
     * @return L'index de la carte, ou -1 si la carte n'est pas trouvée.
     */
    public int getCarteIndex(Carte carte) {
        for (int i = 0; i < cartes.length; i++) {
            if (cartes[i] == carte) {
                return i;
            }
        }
        return -1; // Carte non trouvée
    }

    /**
     * Vérifie si la pile contient une carte donnée.
     * @param c La carte à vérifier.
     * @return true si la carte est présente dans la pile, sinon false.
     */
    public boolean contientCarte(Carte c) {
        for (Carte carte : cartes) {
            if (carte == c) {
                return true;
            }
        }
        return false;
    }
}
