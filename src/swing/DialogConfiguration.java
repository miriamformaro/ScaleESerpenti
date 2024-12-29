package swing;

import caselle.CasellaScala;
import caselle.CasellaSerpente;
import partita.Board;
import strategy.GestioneBoardStrategy;
import strategy.GestioneCasualeStrategy;
import strategy.GestioneUtenteStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DialogConfiguration extends JFrame{
    private JFrame parentFrame;
    private JDialog dialog;
    private JLabel labGiocatori;
    private JLabel labelCaselle;
    private JLabel labelStrategia;
    private JLabel labelNumeroDiDadi;
    private JLabel labelSer;
    private JLabel labelScale;
    private JLabel labelPanchina;
    private JLabel labelLocanda;
    private JLabel labelDadi;
    private JLabel labelMolla;
    private JLabel labelPescaUnaCarta;
    private JTextField textFieldGiocatori;
    private JTextField textFieldCaselle;
    private JTextField posizioneCodaSerpente;
    private JTextField posizioneTestaSerpente;
    private JTextField posizioneFineScala;
    private JTextField posizioneCimaScala;
    private JTextField posizionePanchina;
    private JTextField posizioneLocanda;
    private JTextField posizioneDadi;
    private JTextField posizioneMolla;
    private JTextField posizionePescaUnaCarta;
    private JComboBox comboStrategia;
    private JComboBox comboNumeroDiDadi;
    private JComboBox serpenti;
    private JComboBox scale;
    private JButton avviaGioco;
    private JPanel panelSerpenti;
    private JPanel panelScale;
    private JScrollPane scrollPaneSerpenti;
    private JScrollPane scrollPaneScale;

    private int numeroGiocatori;
    private boolean unDado;
    private Board board;
    private int numeroCaselle;
    private int panchina;
    private int locanda;
    private int dadi;
    private int molla;
    private int pescaUnaCarta;

    public DialogConfiguration(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        dialog = new JDialog(parentFrame, "Opzioni di gioco");
        dialog.setSize(600, 600);
        dialog.setLayout(new GridBagLayout());
        dialog.setLocationRelativeTo(null);
    }

    public void mostraDialog() {
        //giocatori
        labGiocatori = new JLabel("Giocatori: ");
        textFieldGiocatori = new JTextField();

        //dimensione board
        labelCaselle = new JLabel("Numero di caselle: ");
        textFieldCaselle = new JTextField();

        //dadi
        labelNumeroDiDadi = new JLabel("Numero dadi: ");
        comboNumeroDiDadi = new JComboBox(new String[]{"1", "2"});

        //strategia
        labelStrategia = new JLabel("Stragetia: ");
        comboStrategia = new JComboBox(new String[]{"casuale", "utente"});

        //serpenti
        labelSer = new JLabel("Serpenti: ");
        serpenti = new JComboBox(new String[]{"1", "2", "3", "4", "5"});
        panelSerpenti = new JPanel();
        scrollPaneSerpenti = new JScrollPane(panelSerpenti);
        posizioneCodaSerpente = new JTextField();
        posizioneTestaSerpente = new JTextField();
        labelSer.setEnabled(false);
        serpenti.setEnabled(false);
        panelSerpenti.setEnabled(false);
        scrollPaneSerpenti.setEnabled(false);

        //scale
        labelScale = new JLabel("Scale: ");
        scale = new JComboBox(new String[]{"1", "2", "3", "4", "5"});
        panelScale = new JPanel();
        scrollPaneScale = new JScrollPane(panelScale);
        posizioneFineScala = new JTextField();
        posizioneCimaScala = new JTextField();
        labelScale.setEnabled(false);
        scale.setEnabled(false);
        panelScale.setEnabled(false);
        scrollPaneScale.setEnabled(false);

        //panchina
        labelPanchina = new JLabel("Panchina: ");
        posizionePanchina = new JTextField();
        labelPanchina.setEnabled(false);
        posizionePanchina.setEnabled(false);

        //locanda
        labelLocanda = new JLabel("Locanda: ");
        posizioneLocanda = new JTextField();
        labelLocanda.setEnabled(false);
        posizioneLocanda.setEnabled(false);

        //dadi
        labelDadi = new JLabel("Dadi: ");
        posizioneDadi = new JTextField();
        labelDadi.setEnabled(false);
        posizioneDadi.setEnabled(false);

        //molla
        labelMolla = new JLabel("Molla: ");
        posizioneMolla = new JTextField();
        labelMolla.setEnabled(false);
        posizioneMolla.setEnabled(false);

        //pesca una carta
        labelPescaUnaCarta = new JLabel("Pesca Una Carta: ");
        posizionePescaUnaCarta = new JTextField();
        labelPescaUnaCarta.setEnabled(false);
        posizionePescaUnaCarta.setEnabled(false);

        avviaGioco = new JButton("Avvia Gioco");

        // Aggiungi gli elementi al dialog
        GridBagConstraints gbc = new GridBagConstraints();

        // Configurazione base di default per gbc
        gbc.insets = new Insets(5, 5, 5, 5); // Margini uniformi per ogni componente
        gbc.weightx = 1.0; // Distribuzione uniforme dello spazio orizzontale
        gbc.weighty = 0.0; // Nessuna distribuzione verticale predefinita
        gbc.fill = GridBagConstraints.HORIZONTAL; // Allarga i componenti orizzontalmente

        // Giocatori
        gbc.gridx = 0; // Prima colonna
        gbc.gridy = 0; // Prima riga
        gbc.anchor = GridBagConstraints.LINE_END; // Allinea a destra
        dialog.add(labGiocatori, gbc);

        gbc.gridx = 1; // Seconda colonna
        gbc.anchor = GridBagConstraints.LINE_START; // Allinea a sinistra
        dialog.add(textFieldGiocatori, gbc);

        // Numero di caselle
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        dialog.add(labelCaselle, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        dialog.add(textFieldCaselle, gbc);

        //numero di dadi
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        dialog.add(labelNumeroDiDadi, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        dialog.add(comboNumeroDiDadi, gbc);

        // Strategia
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        dialog.add(labelStrategia, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        dialog.add(comboStrategia, gbc);

        // Serpenti
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        dialog.add(labelSer, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        dialog.add(serpenti, gbc);

        // Pannello scrollabile per i serpenti
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; // Occupa entrambe le colonne
        gbc.fill = GridBagConstraints.BOTH; // Occupa lo spazio disponibile
        gbc.weighty = 1.0; // Distribuzione verticale
        dialog.add(scrollPaneSerpenti, gbc);

        // Scale
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1; // Torna a una singola colonna
        gbc.weighty = 0.0; // Resetta la distribuzione verticale
        gbc.anchor = GridBagConstraints.LINE_END;
        dialog.add(labelScale, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        dialog.add(scale, gbc);

        // Pannello scrollabile per le scale
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        dialog.add(scrollPaneScale, gbc);

        // Panchina
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.LINE_END;
        dialog.add(labelPanchina, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        dialog.add(posizionePanchina, gbc);

        // Locanda
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.LINE_END;
        dialog.add(labelLocanda, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        dialog.add(posizioneLocanda, gbc);

        // Dadi
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.LINE_END;
        dialog.add(labelDadi, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        dialog.add(posizioneDadi, gbc);

        // Molla
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.anchor = GridBagConstraints.LINE_END;
        dialog.add(labelMolla, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        dialog.add(posizioneMolla, gbc);

        //pesca una carta
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.LINE_END;
        dialog.add(labelPescaUnaCarta, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        dialog.add(posizionePescaUnaCarta, gbc);

        // Bottone Avvia Gioco
        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.gridwidth = 2; // Centra il bottone su entrambe le colonne
        gbc.anchor = GridBagConstraints.CENTER; // Allinea al centro
        gbc.fill = GridBagConstraints.NONE; // Rimuove l'espansione orizzontale
        dialog.add(avviaGioco, gbc);

        comboStrategia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(comboStrategia.getSelectedItem().toString().equals("utente")) {
                    labelSer.setEnabled(true);
                    serpenti.setEnabled(true);
                    labelScale.setEnabled(true);
                    scale.setEnabled(true);
                    posizioneTestaSerpente.setEnabled(true);
                    posizioneCodaSerpente.setEnabled(true);
                    panelSerpenti.setEnabled(true);
                    scrollPaneSerpenti.setEnabled(true);
                    panelScale.setEnabled(true);
                    posizioneFineScala.setEnabled(true);
                    posizioneCimaScala.setEnabled(true);
                    scrollPaneScale.setEnabled(true);
                    labelPanchina.setEnabled(true);
                    posizionePanchina.setEnabled(true);
                    labelLocanda.setEnabled(true);
                    posizioneLocanda.setEnabled(true);
                    labelDadi.setEnabled(true);
                    posizioneDadi.setEnabled(true);
                    labelMolla.setEnabled(true);
                    posizioneMolla.setEnabled(true);
                    labelPescaUnaCarta.setEnabled(true);
                    posizionePescaUnaCarta.setEnabled(true);
                    aggiornaSerpenti();
                    aggiornaScale();
                } else {
                    labelSer.setEnabled(false);
                    serpenti.setEnabled(false);
                    labelScale.setEnabled(false);
                    scale.setEnabled(false);
                    posizioneTestaSerpente.setEnabled(false);
                    posizioneCodaSerpente.setEnabled(false);
                    panelSerpenti.setEnabled(false);
                    scrollPaneSerpenti.setEnabled(false);
                    posizioneFineScala.setEnabled(false);
                    posizioneCimaScala.setEnabled(false);
                    panelScale.setEnabled(false);
                    scrollPaneScale.setEnabled(false);
                    labelPanchina.setEnabled(false);
                    posizionePanchina.setEnabled(false);
                    labelLocanda.setEnabled(false);
                    posizioneLocanda.setEnabled(false);
                    labelDadi.setEnabled(false);
                    posizioneDadi.setEnabled(false);
                    labelMolla.setEnabled(false);
                    posizioneMolla.setEnabled(false);
                    labelPescaUnaCarta.setEnabled(false);
                    posizionePescaUnaCarta.setEnabled(false);
                }
            }
        });

        serpenti.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aggiornaSerpenti();
            }
        });

        scale.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aggiornaScale();
            }
        });

        avviaGioco.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                avviaGioco();
            }
        });

        dialog.setVisible(true);
    }

    private List<JTextField> listaPosizioneCodaSerpente = new LinkedList<>();
    private List<JTextField> listaPosizioneTestaSerpente = new LinkedList<>();
    private void aggiornaSerpenti() {
        panelSerpenti.removeAll();
        listaPosizioneCodaSerpente.clear();
        listaPosizioneTestaSerpente.clear();

        int numSerpenti = Integer.parseInt(serpenti.getSelectedItem().toString());

        for (int i = 0; i < numSerpenti; i++) {
            panelSerpenti.add(new JLabel("Serpente " + (i + 1) + " - Coda:"));
            JTextField codaField = new JTextField(10);
            listaPosizioneCodaSerpente.add(codaField);
            panelSerpenti.add(codaField);

            panelSerpenti.add(new JLabel("Serpente " + (i + 1) + " - Testa:"));
            JTextField testaField = new JTextField(10);
            listaPosizioneTestaSerpente.add(testaField);
            panelSerpenti.add(testaField);
        }

        panelSerpenti.revalidate();
        panelSerpenti.repaint();
    }

    private List<JTextField> listaPosizioneFineScala = new LinkedList<>();
    private List<JTextField> listaPosizioneCimaScala = new LinkedList<>();
    private void aggiornaScale() {
        panelScale.removeAll();
        listaPosizioneFineScala.clear();
        listaPosizioneCimaScala.clear();

        int numScale = Integer.parseInt(scale.getSelectedItem().toString());

        for (int i = 0; i < numScale; i++) {
            // Etichetta per la base della scala
            panelScale.add(new JLabel("Scala " + (i + 1) + " - Base:"));
            JTextField baseField = new JTextField(10);
            listaPosizioneFineScala.add(baseField);
            panelScale.add(baseField);

            // Etichetta per la cima della scala
            panelScale.add(new JLabel("Scala " + (i + 1) + " - Cima:"));
            JTextField cimaField = new JTextField(10);
            listaPosizioneCimaScala.add(cimaField);
            panelScale.add(cimaField);
        }

        panelScale.revalidate();
        panelScale.repaint();
    }

    private void avviaGioco() {
        try {
            numeroGiocatori = Integer.parseInt(textFieldGiocatori.getText());
            if (numeroGiocatori <= 1 || numeroGiocatori > 6) {
                JOptionPane.showMessageDialog(dialog,
                        "Il numero di giocatori deve essere compreso tra 2 e 6!", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }

            numeroCaselle = Integer.parseInt(textFieldCaselle.getText());
            if (numeroCaselle <= 35 || numeroCaselle > 300) {
                JOptionPane.showMessageDialog(dialog,
                        "Il numero di caselle deve essere compreso tra 36 e 300!", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int numeroDadi = Integer.parseInt(comboNumeroDiDadi.getSelectedItem().toString());
            unDado = numeroDadi == 1;

            int numeroSerpenti = Integer.parseInt(serpenti.getSelectedItem().toString());
            List<CasellaSerpente> listaSerpenti = new LinkedList<>();
            Set<Integer> posizioniUsate = new HashSet<>();
            if (serpenti.isEnabled()) {
                for (int i = 0; i < numeroSerpenti; i++) {
                    int testa = Integer.parseInt(listaPosizioneTestaSerpente.get(i).getText())-1;
                    int coda = Integer.parseInt(listaPosizioneCodaSerpente.get(i).getText())-1;
                    if (testa<=0 || coda<=0 ||testa >= numeroCaselle-1 || coda >= numeroCaselle-1) {
                        JOptionPane.showMessageDialog(dialog, "Errore: La coda o la testa del serpente non può trovarsi nella prima o nell'ultima posizione (" + numeroCaselle + ")!", "Errore Posizione Casella Dadi!", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (coda >= testa) {
                        JOptionPane.showMessageDialog(dialog, "Errore: La coda del serpente deve essere in una posizione inferiore rispetto alla testa!", "Errore Posizione Serpente!", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if(posizioniUsate.contains(coda) || posizioniUsate.contains(testa)) {
                        JOptionPane.showMessageDialog(dialog, "Errore: Due elementi non possono occupare la stessa posizione sulla tabella!", "Errore Posizioni Duplicate!", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    posizioniUsate.add(coda);
                    posizioniUsate.add(testa);
                    listaSerpenti.add(new CasellaSerpente(testa, coda));
                }
            }

            int numeroScale = Integer.parseInt(scale.getSelectedItem().toString());
            List<CasellaScala> listaScale = new LinkedList<>();
            if (scale.isEnabled()) {
                for (int i = 0; i < numeroScale; i++) {
                    int base = Integer.parseInt(listaPosizioneFineScala.get(i).getText())-1;
                    int cima = Integer.parseInt(listaPosizioneCimaScala.get(i).getText())-1;
                    if (base<=0 || cima<=0 || base >= numeroCaselle-1 || cima >= numeroCaselle-1) {
                        JOptionPane.showMessageDialog(dialog,
                                "Errore: La base o la cima della scala non può trovarsi nella prima o nell'ultima posizione (" + numeroCaselle + ")!", "Errore Posizione Casella Dadi!", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (base >= cima) {
                        JOptionPane.showMessageDialog(dialog, "Errore: La base della scala deve essere in una posizione inferiore rispetto alla cima!", "Errore Posizione Scala!", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if(posizioniUsate.contains(base) || posizioniUsate.contains(cima)) {
                        JOptionPane.showMessageDialog(dialog, "Errore: Due elementi non possono occupare la stessa posizione sulla tabella!", "Errore Posizioni Duplicate!", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    posizioniUsate.add(base);
                    posizioniUsate.add(cima);
                    listaScale.add(new CasellaScala(base, cima));
                }
            }

            if (posizionePanchina.isEnabled()) {
                panchina = Integer.parseInt(posizionePanchina.getText())-1;
                if (panchina<=0 || panchina >= numeroCaselle-1) {
                    JOptionPane.showMessageDialog(dialog, "Errore: La casella 'PANCHINA' non deve essere trovarsi nella prima o nell'ultima posizione (" + numeroCaselle + ")!", "Errore Posizione Casella Dadi!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (posizioniUsate.contains(panchina)) {
                    JOptionPane.showMessageDialog(dialog, "Errore: Due elementi non possono occupare la stessa posizione sulla tabella!", "Errore Posizioni Duplicate!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (posizioneLocanda.isEnabled()) {
                locanda = Integer.parseInt(posizioneLocanda.getText())-1;
                if (locanda<=0 || locanda >= numeroCaselle-1) {
                    JOptionPane.showMessageDialog(dialog, "Errore: La casella 'LOCANDA' non deve essere trovarsi nella prima o nell'ultima posizione (" + numeroCaselle + ")!", "Errore Posizione Casella Dadi!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(posizioniUsate.contains(locanda)) {
                    JOptionPane.showMessageDialog(dialog, "Errore: Due elementi non possono occupare la stessa posizione sulla tabella!", "Errore Posizioni Duplicate!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (posizioneDadi.isEnabled()) {
                dadi = Integer.parseInt(posizioneDadi.getText())-1;
                if (dadi <= 0 || dadi >= numeroCaselle-1) {
                    JOptionPane.showMessageDialog(dialog, "Errore: La casella 'DADI' non deve essere trovarsi nella prima o nell'ultima posizione (" + numeroCaselle + ")!", "Errore Posizione Casella Dadi!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(posizioniUsate.contains(dadi)) {
                    JOptionPane.showMessageDialog(dialog, "Errore: Due elementi non possono occupare la stessa posizione sulla tabella!", "Errore Posizioni Duplicate!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (posizioneMolla.isEnabled()) {
                molla = Integer.parseInt(posizioneMolla.getText())-1;
                if (molla<=0 || molla >= numeroCaselle-1) {
                    JOptionPane.showMessageDialog(dialog, "Errore: La casella 'MOLLA' non deve essere trovarsi nella prima o nell'ultima posizione (" + numeroCaselle + ")!", "Errore Posizione Casella Dadi!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(posizioniUsate.contains(molla)) {
                    JOptionPane.showMessageDialog(dialog, "Errore: Due elementi non possono occupare la stessa posizione sulla tabella!", "Errore Posizioni Duplicate!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (posizionePescaUnaCarta.isEnabled()) {
                pescaUnaCarta = Integer.parseInt(posizionePescaUnaCarta.getText())-1;
                if (panchina<=0 || pescaUnaCarta >= numeroCaselle-1) {
                    JOptionPane.showMessageDialog(dialog, "Errore: La casella 'PESCA UNA CARTA' non deve essere trovarsi nella prima o nell'ultima posizione (" + numeroCaselle + ")!", "Errore Posizione Casella Dadi!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(posizioniUsate.contains(pescaUnaCarta)) {
                    JOptionPane.showMessageDialog(dialog, "Errore: Due elementi non possono occupare la stessa posizione sulla tabella!", "Errore Posizioni Duplicate!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            GestioneBoardStrategy strategy;
            if (comboStrategia.getSelectedItem().toString().equals("utente")) {
                strategy = new GestioneUtenteStrategy(listaSerpenti, listaScale, panchina, locanda, dadi, molla, pescaUnaCarta);
            } else {
                strategy = new GestioneCasualeStrategy();
            }

            board = new Board(numeroCaselle, strategy);
            board.applicaStrategia();
            getContentPane().removeAll();

            dialog.dispose();
            ((GUI) parentFrame).avviaPartita(board);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(dialog, "Inserisci valori validi per tutti i campi richiesti.", "Errore", JOptionPane.ERROR_MESSAGE);
        }

    }

    public int getNumeroGiocatori() {
        return this.numeroGiocatori;
    }

    public int getNumeroDadi() {
        return Integer.parseInt(comboNumeroDiDadi.getSelectedItem().toString());
    }

    public boolean getUnDado() {
        return this.unDado;
    }
}
