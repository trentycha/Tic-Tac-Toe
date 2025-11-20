import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class TicTacToeGUI extends JFrame {

    private JButton[][] boutons;
    private Jeu jeu;
    private JLabel titreLabel;
    private JLabel tourLabel;

    private Color couleurFond = new Color(26, 32, 44);
    private Color couleurCarte = new Color(45, 55, 72);
    private Color couleurBouton = new Color(74, 85, 104);
    private Color couleurSurvol = new Color(90, 103, 125);
    private Color couleurTexte = new Color(237, 242, 247);
    private Color couleurX = new Color(99, 179, 237);
    private Color couleurO = new Color(245, 101, 101);
    private Color couleurVert = new Color(72, 187, 120);

    public TicTacToeGUI() {
        jeu = new Jeu();
        boutons = new JButton[3][3];

        setTitle("Tic Tac Toe");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(20, 20));
        panelPrincipal.setBackground(couleurFond);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JPanel panelHaut = new JPanel();
        panelHaut.setLayout(new BoxLayout(panelHaut, BoxLayout.Y_AXIS));
        panelHaut.setBackground(couleurFond);

        titreLabel = new JLabel("TIC TAC TOE");
        titreLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titreLabel.setForeground(couleurTexte);
        titreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        tourLabel = new JLabel("Tour du joueur X");
        tourLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        tourLabel.setForeground(couleurX);
        tourLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        tourLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        panelHaut.add(titreLabel);
        panelHaut.add(tourLabel);

        JPanel conteneurPlateau = new JPanel();
        conteneurPlateau.setLayout(new BorderLayout());
        conteneurPlateau.setBackground(couleurCarte);
        conteneurPlateau.setBorder(new CompoundBorder(
                new LineBorder(new Color(74, 85, 104), 2, true),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JPanel panelPlateau = new JPanel();
        panelPlateau.setLayout(new GridLayout(3, 3, 10, 10));
        panelPlateau.setBackground(couleurCarte);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton btn = new JButton("");
                btn.setFont(new Font("Arial", Font.BOLD, 72));
                btn.setBackground(couleurBouton);
                btn.setForeground(couleurTexte);
                btn.setFocusPainted(false);
                btn.setBorderPainted(false);
                btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btn.setBorder(new EmptyBorder(10, 10, 10, 10));

                boutons[i][j] = btn;
                panelPlateau.add(btn);

                int lig = i;
                int col = j;

                btn.addMouseListener(new MouseAdapter() {
                    public void mouseEntered(MouseEvent e) {
                        if (btn.isEnabled()) {
                            btn.setBackground(couleurSurvol);
                        }
                    }
                    public void mouseExited(MouseEvent e) {
                        if (btn.isEnabled()) {
                            btn.setBackground(couleurBouton);
                        }
                    }
                });

                btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jouer(lig, col);
                    }
                });
            }
        }

        conteneurPlateau.add(panelPlateau, BorderLayout.CENTER);

        JPanel panelBas = new JPanel();
        panelBas.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelBas.setBackground(couleurFond);

        JButton boutonReset = new JButton("Nouvelle Partie");
        boutonReset.setFont(new Font("Arial", Font.BOLD, 14));
        boutonReset.setBackground(couleurVert);
        boutonReset.setForeground(Color.WHITE);
        boutonReset.setFocusPainted(false);
        boutonReset.setBorderPainted(false);
        boutonReset.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boutonReset.setPreferredSize(new Dimension(160, 45));

        boutonReset.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                boutonReset.setBackground(couleurVert.brighter());
            }
            public void mouseExited(MouseEvent e) {
                boutonReset.setBackground(couleurVert);
            }
        });

        boutonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                recommencer();
            }
        });

        panelBas.add(boutonReset);

        panelPrincipal.add(panelHaut, BorderLayout.NORTH);
        panelPrincipal.add(conteneurPlateau, BorderLayout.CENTER);
        panelPrincipal.add(panelBas, BorderLayout.SOUTH);

        add(panelPrincipal);
        setVisible(true);
    }

    private void jouer(int ligne, int colonne) {
        if (jeu.getJoueurCourant().jouerCoup(jeu.getPlateau(), ligne, colonne)) {
            char id = jeu.getJoueurCourant().getId();
            boutons[ligne][colonne].setText("" + id);
            boutons[ligne][colonne].setEnabled(false);

            if (id == 'X') {
                boutons[ligne][colonne].setForeground(couleurX);
            } else {
                boutons[ligne][colonne].setForeground(couleurO);
            }

            if (jeu.getPlateau().victoire(id)) {
                Timer t = new Timer(300, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        montrerVictoire(id);
                    }
                });
                t.setRepeats(false);
                t.start();
            } else if (jeu.getPlateau().estPlein()) {
                Timer t = new Timer(300, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        montrerNul();
                    }
                });
                t.setRepeats(false);
                t.start();
            } else {
                jeu.changerTour();
                changerLabel();
            }
        }
    }

    private void changerLabel() {
        char j = jeu.getJoueurCourant().getId();
        tourLabel.setText("Tour du joueur " + j);
        if (j == 'X') {
            tourLabel.setForeground(couleurX);
        } else {
            tourLabel.setForeground(couleurO);
        }
    }

    private void montrerVictoire(char joueur) {
        UIManager.put("OptionPane.background", couleurCarte);
        UIManager.put("Panel.background", couleurCarte);
        UIManager.put("OptionPane.messageForeground", couleurTexte);

        String msg = "🎉 Victoire du joueur " + joueur + " ! 🎉";
        String[] choix = {"Rejouer", "Quitter"};

        int resultat = JOptionPane.showOptionDialog(
                this,
                msg,
                "Partie terminée",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                choix,
                choix[0]
        );

        if (resultat == 0) {
            recommencer();
        } else {
            System.exit(0);
        }
    }

    private void montrerNul() {
        UIManager.put("OptionPane.background", couleurCarte);
        UIManager.put("Panel.background", couleurCarte);
        UIManager.put("OptionPane.messageForeground", couleurTexte);

        String msg = "🤝 Match nul ! 🤝\nBien joué à tous les deux !";
        String[] choix = {"Rejouer", "Quitter"};

        int resultat = JOptionPane.showOptionDialog(
                this,
                msg,
                "Partie terminée",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                choix,
                choix[0]
        );

        if (resultat == 0) {
            recommencer();
        } else {
            System.exit(0);
        }
    }

    private void recommencer() {
        jeu = new Jeu();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boutons[i][j].setText("");
                boutons[i][j].setEnabled(true);
                boutons[i][j].setBackground(couleurBouton);
                boutons[i][j].setForeground(couleurTexte);
            }
        }
        changerLabel();
    }
}