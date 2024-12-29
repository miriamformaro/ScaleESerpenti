package swing;

import observer.Observer;
import observer.Subject;
import caselle.*;
import partita.Board;
import partita.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GUI extends JFrame implements Observer {
    private DialogConfiguration dialog = new DialogConfiguration(this);
    private TabellonePanel tabellonePanel;
    private JTextArea areaMovimenti;
    private List<Player> players;
    private List<JLabel> pedine;
    private String messaggio = ""; // Inizializzazione di messaggio
    private CaselleSpeciali tipo;

    public GUI() {
        super("Scale e serpenti");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        dialog.mostraDialog();
    }

    @Override
    public void update(Subject subject) {
        if (subject instanceof Player) {
            Player player = (Player) subject;

            // Inizia il messaggio del turno corrente
            StringBuilder messaggioTurno = new StringBuilder();

            // Messaggio iniziale (dado o dadi)
            if (dialog.getUnDado()) {
                messaggioTurno.append("\nDado: ").append(player.getD().getDado1()).append("\n");
            } else {
                if (player.getPosizioneIniziale() >= tabellonePanel.getBoard().getLenght() - 6) {
                    dialog.getUnDado();
                    messaggioTurno.append("\nDado: ").append(player.getD().getDado1()).append("\n");
                } else {
                    messaggioTurno.append("\nDado1: ").append(player.getD().getDado1()).append(", Dado2: ").append(player.getD().getDado2()).append("\n");
                }
            }

            // Messaggio posizione iniziale
            int posizione = player.getPosizione();
            messaggioTurno.append("Giocatore ").append(player.getId() + 1).append(" è arrivato sulla casella ").append(posizione + 1).append("\n");

            // Sincronizza movimenti e messaggi per eventuali azioni a catena
            sincronizzaMovimenti(player, messaggioTurno);

            if ((posizione + 1) == (tabellonePanel.getBoard().getUltimaCasella() + 1)) {
                messaggioTurno.append("Il giocatore ").append(player.getId() + 1).append(" ha vinto!");
            } else if ((posizione + 1) > tabellonePanel.getBoard().getUltimaCasella()) {
                int overflow = (posizione + 1) - tabellonePanel.getBoard().getUltimaCasella();
                posizione = 1 + (tabellonePanel.getBoard().getUltimaCasella() - overflow);
                player.setPosizione(posizione);
                messaggioTurno.append("Il giocatore ").append(player.getId() + 1).append(" deve indietreggiare di ").append(overflow).append(" caselle e arriva alla casella ").append(posizione + 1).append("\n");

                // Sincronizza i movimenti anche quando il giocatore indietreggia e arriva su una casella speciale
                sincronizzaMovimenti(player, messaggioTurno);
            }

            if (!dialog.getUnDado() && player.getD().getDado1() == 6 && player.getD().getDado2() == 6 && (posizione+1)<(tabellonePanel.getBoard().getUltimaCasella()+1)) {
                messaggioTurno.append("Il giocatore ").append(player.getId() + 1).append(" ha ottenuto un doppio 6 e ha diritto a un altro turno!\n");
                sincronizzaMovimenti(player, messaggioTurno);
            }

            // Aggiungi il messaggio all'area di testo
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    areaMovimenti.append(messaggioTurno.toString() + "\n");
                }
            });
        }
    }

    private void sincronizzaMovimenti(Player player, StringBuilder messaggioTurno) {
        boolean azioneInCorso = true;

        while (azioneInCorso) {
            azioneInCorso = false;

            int posizioneCorrente = player.getPosizione();
            AbstractCasella casella = tabellonePanel.getBoard().getCasella(posizioneCorrente);

            if (casella instanceof CasellaSerpente) {
                // Gestione casella serpente
                posizioneCorrente = ((CasellaSerpente) casella).getCoda();
                player.setPosizione(posizioneCorrente);
                messaggioTurno.append(" - Casella serpente! Torna indietro alla casella: ").append(posizioneCorrente + 1).append("\n");
                azioneInCorso = true;
            } else if (casella instanceof CasellaScala) {
                // Gestione casella scala
                posizioneCorrente = ((CasellaScala) casella).getFine();
                player.setPosizione(posizioneCorrente);
                messaggioTurno.append(" - Casella scala! Avanza fino alla casella: ").append(posizioneCorrente + 1).append("\n");
                azioneInCorso = true;
            } else if (casella instanceof CasellaPremio) {
                // Gestione casella premio
                if (casella.getTipo() == CaselleSpeciali.DADI) {
                    casella.esegui(player);
                    messaggioTurno.append(" - Casella premio 'DADI'. Rilancia i dadi e avanza di " + ((CasellaPremio) casella).getAvanzamento() + " caselle!\n");
                    azioneInCorso = true;
                } else if(casella.getTipo() == CaselleSpeciali.MOLLA){
                    casella.esegui(player);
                    messaggioTurno.append(" - Casella premio 'MOLLA'. Avanza ancora del punteggio ottenuto con l'ultimo lancio di dadi! Arriva alla casella " + (player.getPosizione()+1) + "!\n");
                    azioneInCorso = true;
                }
            } else if (casella instanceof CasellaSosta) {
                if (((CasellaSosta) casella).getTipo() == CaselleSpeciali.PANCHINA) {
                    if (player.getMettereCartaDaParte() > 0) {
                        messaggioTurno.append(" - Casella panchina. Il giocatore ha una carta 'DIVIETO DI SOSTA' quindi non sosta al prossimo turno!\n");
                    } else {
                        messaggioTurno.append(" - Casella panchina. Sosta per un turno!\n");
                    }

                } else {
                    messaggioTurno.append(" - Casella locanda. Sosta per tre turni!\n");
                }
            } else if (casella instanceof CasellaPescaUnaCarta) {
                // Gestione casella pesca una carta
                messaggioTurno.append(" - Casella pesca una carta!\n");
                if (casella.getTipo() == CaselleSpeciali.DADI) {
                    messaggioTurno.append("- Carta pescata: 'DADI'. Si ha diritto ad un nuovo turno, si avanza del nuovo lancio dei dadi");
                } else if (casella.getTipo() == CaselleSpeciali.MOLLA) {
                    messaggioTurno.append("- Carta pescata: 'MOLLA'. Avanza ancora del punteggio ottenuto con l'ultimo lancio di dadi!\n");
                } else if (casella.getTipo() == CaselleSpeciali.PANCHINA) {
                    if (player.getMettereCartaDaParte() > 0) {
                        messaggioTurno.append("- Carta pescata: 'PANCHINA'. Il giocatore ha una carta 'DIVIETO DI SOSTA' quindi non sosta al prossimo turno!\n");
                    } else {
                        messaggioTurno.append("- Carta pescata: 'PANCHINA'. Sosta per un turno!\n");
                    }
                } else if (casella.getTipo() == CaselleSpeciali.LOCANDA) {
                    if(player.getMettereCartaDaParte() > 0) {
                        messaggioTurno.append("- Carta pescata: 'LOCANDA'. Il giocatore ha una carta 'DIVIETO DI SOSTA' quindi sosta un turno in meno!\n");
                    } else {
                        messaggioTurno.append("- Carta pescata: 'LOCANDA'. Sosta per tre turni!\n");
                    }
                } else if (casella.getTipo()== CaselleSpeciali.DIVIETO_DI_SOSTA){
                    messaggioTurno.append("- Carta pescata: 'DIVIETO DI SOSTA'. Questa carta ti fa sostare un turno in meno se vai sulla casella 'PANCHINA' o 'LOCANDA'!\n");
                } else if(casella.getTipo()== CaselleSpeciali.PESCA_UNA_CARTA) {
                    messaggioTurno.append("Pesca una carta!\n");
                }
            } else {
                // Casella semplice
                messaggioTurno.append(" - Casella semplice!\n");
                azioneInCorso = false;
            }

            // Se la posizione è cambiata, aggiorna la casella
            if (azioneInCorso) {
                int nuovaPosizione = player.getPosizione();
                casella = tabellonePanel.getBoard().getCasella(nuovaPosizione);
            }
        }
    }

    public void avviaPartita(Board board) {
        players = new LinkedList<>();
        for(int i = 0; i < dialog.getNumeroGiocatori(); i++) {
            Player player = new Player(i, dialog.getUnDado());
            player.registraOsservatore(this); // Registra la GUI come osservatore per ogni giocatore
            players.add(player);
        }

        tabellonePanel = new TabellonePanel(board, dialog.getNumeroGiocatori(), dialog.getUnDado());
        add(tabellonePanel, BorderLayout.CENTER);

        // Crea l'area di testo per visualizzare i movimenti
        areaMovimenti = new JTextArea();
        areaMovimenti.setEditable(false);
        areaMovimenti.setSize(new Dimension(250, 600)); // Imposta una dimensione preferita
        areaMovimenti.setLineWrap(true); // Attiva il ritorno a capo automatico
        areaMovimenti.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(areaMovimenti);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.WEST);

        messaggio = "Numero di giocatori scelti: " + dialog.getNumeroGiocatori() +
                "\nNumero di dadi scelti: " + dialog.getNumeroDadi() + "\n";
        areaMovimenti.append(messaggio);

        revalidate();
        repaint();

        this.pedine = tabellonePanel.getPedine();

        avviaGioco(board);
    }

    public void avviaGioco(Board board) {
        Timer timer = new Timer(1000, new ActionListener() {
            private int turnoCorrente = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                Player player = players.get(turnoCorrente);
                player.avviaGiocatore(board);

                int nuovaPosizione = player.getPosizione();
                JLabel pedina = pedine.get(turnoCorrente);

                try {
                    TimeUnit.SECONDS.sleep(0);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                // Rimuovi la pedina dalla sua casella precedente
                for (Component c : tabellonePanel.getComponents()) {
                    if (c instanceof JPanel) {
                        for (Component comp : ((JPanel) c).getComponents()) {
                            if (comp == pedina) {
                                ((JPanel) c).remove(pedina);
                            }
                        }
                    }
                }

                // Aggiungi la pedina alla nuova casella
                JPanel nuovaCasella = (JPanel) tabellonePanel.getComponent(nuovaPosizione);
                nuovaCasella.add(pedina);

                tabellonePanel.revalidate();
                tabellonePanel.repaint();

                if (nuovaPosizione == board.getUltimaCasella()) {
                    ((Timer) e.getSource()).stop();
                    JOptionPane.showMessageDialog(GUI.this, "Il giocatore " + (turnoCorrente + 1) + " ha vinto!");
                }

                turnoCorrente = (turnoCorrente + 1) % players.size();
            }
        });

        timer.start();
    }

    public static void main(String[] args) {
        GUI gioco = new GUI();
        gioco.setVisible(true);
    }
}