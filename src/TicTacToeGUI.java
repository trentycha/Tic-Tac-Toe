import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGUI extends JFrame {

    private JButton[][] boutons;
    private Jeu jeu;

    public TicTacToeGUI() {
        jeu = new Jeu();
        boutons = new JButton[3][3];

        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        add(panel);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton bouton = new JButton("");
                bouton.setFont(new Font("Montserrat", Font.BOLD, 60));
                boutons[i][j] = bouton;
                panel.add(bouton);

                final int ligne = i;
                final int colonne = j;

                bouton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jouerCoup(ligne, colonne);
                    }
                });
            }
        }

        setVisible(true);
    }

    private void jouerCoup(int ligne, int colonne) {
        if (jeu.getJoueurCourant().jouerCoup(jeu.getPlateau(), ligne, colonne)) {
            boutons[ligne][colonne].setText("" + jeu.getJoueurCourant().getId());
            boutons[ligne][colonne].setEnabled(false);

            if (jeu.getPlateau().victoire(jeu.getJoueurCourant().getId())) {
                JOptionPane.showMessageDialog(this, "Le joueur " + jeu.getJoueurCourant().getId() + " a gagné !");
                resetGame();
            } else if (jeu.getPlateau().estPlein()) {
                JOptionPane.showMessageDialog(this, "Match nul !");
                resetGame();
            } else {
                jeu.changerTour();
            }
        }
    }

    private void resetGame() {
        jeu = new Jeu();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boutons[i][j].setText("");
                boutons[i][j].setEnabled(true);
            }
        }
    }
}
