import java.util.Scanner;

public class Jeu {

    private Plateau plateau;
    private Joueur joueurUn;
    private Joueur joueurDeux;
    private Joueur joueurCourant;

    private Scanner scanner = new Scanner(System.in);

    public Jeu() {
        plateau = new Plateau();
        joueurUn = new Joueur('X');
        joueurDeux = new Joueur('O');
        joueurCourant = joueurUn;
    }

    public void jouer() {
        boolean jeuFini = false;

        while (!jeuFini) {
            afficherPlateau();
            System.out.println("Joueur " + joueurCourant.getId() + ", entrez la ligne (0-2) :");
            int ligne = scanner.nextInt();
            System.out.println("Entrez la colonne (0-2) :");
            int colonne = scanner.nextInt();

            if (plateau.placerSymbole(ligne, colonne, joueurCourant.getId())) {
                if (plateau.victoire(joueurCourant.getId())) {
                    afficherPlateau();
                    System.out.println("Le joueur " + joueurCourant.getId() + " a gagné !");
                    jeuFini = true;
                } else if (plateau.estPlein()) {
                    afficherPlateau();
                    System.out.println("Match nul !");
                    jeuFini = true;
                } else {
                    changerTour();
                }
            } else {
                System.out.println("Case déjà occupée ! Réessayez.");
            }
        }

        scanner.close();
    }

    private void changerTour() {
        joueurCourant = (joueurCourant == joueurUn) ? joueurDeux : joueurUn;
    }

    private void afficherPlateau() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char s = plateau.getCase(i, j).getSymbole();
                System.out.print(s == ' ' ? "_" : s);
                if (j < 2) System.out.print("|");
            }
            System.out.println();
        }
    }
}
