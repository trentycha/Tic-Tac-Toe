public class Case {

    private char symbole;

    public Case(char symbole) {
        this.symbole = symbole;
    }

    public boolean estVide() {
        if(symbole == ' ') {
            return true;
        } else {
            return false;
        }
    }

    public void setSymbole(char symbole) {
        this.symbole = symbole;
    }

    public char getSymbole() {
        return symbole;
    }

}
