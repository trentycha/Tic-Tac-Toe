public class Plateau {

    private Case[][] plateauJeu;

    public Plateau() {
        plateauJeu = new Case[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                plateauJeu[i][j] = new Case();
            }
        }
    }

    public boolean placerSymbole(int ligne, int colonne, char symbole) {
        if (plateauJeu[ligne][colonne].estVide()) {
            plateauJeu[ligne][colonne].setSymbole(symbole);
            return true;
        }
        return false;
    }

    public boolean victoire(char symbole) {
        for (int i = 0; i < 3; i++) {
            if (plateauJeu[i][0].getSymbole() == symbole && plateauJeu[i][1].getSymbole() == symbole && plateauJeu[i][2].getSymbole() == symbole) {
                return true;
            }
        }

        for (int j = 0; j < 3; j++) {
            if (plateauJeu[0][j].getSymbole() == symbole && plateauJeu[1][j].getSymbole() == symbole && plateauJeu[2][j].getSymbole() == symbole) {
                return true;
            }
        }

        if (plateauJeu[0][0].getSymbole() == symbole && plateauJeu[1][1].getSymbole() == symbole && plateauJeu[2][2].getSymbole() == symbole) {
            return true;
        }

        if (plateauJeu[0][2].getSymbole() == symbole && plateauJeu[1][1].getSymbole() == symbole && plateauJeu[2][0].getSymbole() == symbole) {
            return true;
        }
        return false;
    }

    public boolean estPlein() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (plateauJeu[i][j].estVide()) {
                    return false;
                }
            }
        }
        return true;
    }

    public Case getCase(int ligne, int colonne) {
        return plateauJeu[ligne][colonne];
    }
}
