package swing;

import Observer.Observer;
import Observer.Subject;
import caselle.*;
import partita.Board;
import partita.Player;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TabellonePanel extends JPanel {
    private Board board;
    private List<JLabel> pedine;
    private List<Player> players;
    private int numeroGiocatori;
    private boolean unDado;
    private Random random = new Random();

    public TabellonePanel(Board board, int numeroGiocatori, boolean unDado) {
        this.board = board;
        this.numeroGiocatori = numeroGiocatori;
        this.unDado = unDado;
        int size = (int) Math.sqrt(board.getCaselle().length);
        setLayout(new GridLayout(size, size));  // Imposta un layout a griglia
        mostraTabellone();
        aggiungiPedine();
    }

    private void mostraTabellone() {
        for (int i = 0; i < board.getLenght(); i++) {
            JPanel casellaPanel = new JPanel();
            casellaPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            //casellaPanel.setPreferredSize(new Dimension(50, 50));

            JLabel casellaLabel = new JLabel(String.valueOf(i+1), SwingConstants.CENTER);
            casellaPanel.setOpaque(true);
            casellaPanel.add(casellaLabel, BorderLayout.CENTER);

            if (board.getCasella(i) instanceof CasellaSerpente) {
                casellaPanel.setBackground(Color.GREEN);
            }

            if (board.getCasella(i) instanceof CasellaScala) {
                casellaPanel.setBackground(Color.GRAY);
            }

            if (board.getCasella(i) instanceof CasellaPremio) {
                if (((CasellaPremio) board.getCasella(i)).getTipo() == CaselleSpeciali.DADI) {
                    casellaPanel.setBackground(Color.YELLOW);
                } else {
                    casellaPanel.setBackground(Color.PINK);
                }
            }

            if (board.getCasella(i) instanceof CasellaSosta) {
                if (((CasellaSosta) board.getCasella(i)).getTipo() == CaselleSpeciali.PANCHINA) {
                    casellaPanel.setBackground(Color.ORANGE);
                } else {
                    casellaPanel.setBackground(Color.RED);
                }
            }

            if (board.getCasella(i) instanceof CasellaPescaUnaCarta) {
                casellaPanel.setBackground(Color.CYAN);
            }

            casellaPanel.setOpaque(true);
            add(casellaPanel);
        }
    }

    private void aggiungiPedine() {
        pedine = new LinkedList<>();
        for (int i = 0; i < numeroGiocatori; i++) {
            Color colore = colorePedina();
            JLabel pedina = new JLabel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.setColor(colore);
                    g.fillOval(0, 0, getWidth(), getHeight()); // Disegna un cerchio pieno
                }
            };

            pedina.setPreferredSize(new Dimension(20, 20)); // Dimensione della pedina
            pedine.add(pedina);
        }

        this.revalidate();
        this.repaint();
    }

    private Color colorePedina() {
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    public List<JLabel> getPedine() {
        return pedine;
    }

    public Board getBoard() {
        return board;
    }
}
