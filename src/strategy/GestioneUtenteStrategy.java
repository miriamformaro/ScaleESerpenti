package strategy;

import caselle.CaselleSpeciali;
import partita.Board;

import java.util.Scanner;

public class GestioneUtenteStrategy implements GestioneBoardStrategy {
    private Scanner sc = new Scanner(System.in);
    @Override
    public void gestioneBoard(Board board, int numeroCaselle) {
        int larghezza = (int) Math.ceil(Math.sqrt(numeroCaselle));
        int numeroSerpenti = ottieniNumero("Quanti serpenti vuoi inserire? ");
        for(int i=0; i<numeroSerpenti; i++) {
            int testa = 0;
            int coda = 0;
            do {
                testa = ottieniPosizione("Inserisci la posizione della testa del serpente: ");
                coda = ottieniPosizione("Inserisci la posizione della coda del serpente: ");

                if(testa <= coda || board.getCaselleUsate().contains(testa) || board.getCaselleUsate().contains(coda) || stessaRiga(testa, coda, larghezza)) {
                    System.out.println("Serpente invalido! Inserirlo in altre posizioni!");
                }
            } while(testa <= coda || board.getCaselleUsate().contains(testa) || board.getCaselleUsate().contains(coda) || stessaRiga(testa, coda, larghezza));
            board.aggiungiCasella(CaselleSpeciali.SERPENTE, testa, coda);
            board.getCaselleUsate().add(testa);
            board.getCaselleUsate().add(coda);
        }

        int numeroScale = ottieniNumero("Quanti scale vuoi inserire? ");
        for(int i=0; i<numeroScale; i++) {
            int inizio = 0;
            int fine = 0;
            do {
                inizio = ottieniPosizione("Inserisci l'inizio della scala: ");
                fine = ottieniPosizione("Inserisci la fine della scala: ");

                if(fine <= inizio || board.getCaselleUsate().contains(inizio) || board.getCaselleUsate().contains(fine) || stessaRiga(inizio, fine, larghezza)) {
                    System.out.println("Scala invalida! Inserirla in altre posizioni!");
                }
            } while(fine <= inizio || board.getCaselleUsate().contains(inizio) || board.getCaselleUsate().contains(fine) || stessaRiga(inizio, fine, larghezza));
            board.aggiungiCasella(CaselleSpeciali.SCALA, inizio, fine);
            board.getCaselleUsate().add(inizio);
            board.getCaselleUsate().add(fine);
        }

        aggiungi(CaselleSpeciali.PANCHINA, board);
        aggiungi(CaselleSpeciali.LOCANDA, board);
        aggiungi(CaselleSpeciali.DADI, board);
        aggiungi(CaselleSpeciali.MOLLA, board);
        aggiungi(CaselleSpeciali.PESCA_UNA_CARTA, board);

        board.caselleNormali();
    }

    private int ottieniNumero(String domanda) {
        System.out.print(domanda);
        return sc.nextInt();
    }

    private int ottieniPosizione(String domanda) {
        System.out.print(domanda);
        return sc.nextInt();
    }

    private void aggiungi(CaselleSpeciali tipo, Board board) {
        int pos;
        do {
            pos = ottieniPosizione("Inserisci la posizione per la casella " + tipo.toString() + " ");
        } while (board.getCaselleUsate().contains(pos));
        board.aggiungiCasella(tipo, pos, 0);
        board.getCaselleUsate().add(pos);
    }

    private boolean stessaRiga(int pos1, int pos2, int larghezza) {
        return (pos1/larghezza == pos2/larghezza);
    }
}
