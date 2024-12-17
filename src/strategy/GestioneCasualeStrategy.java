package strategy;

import caselle.CasellaNormale;
import caselle.CaselleSpeciali;
import partita.Board;

import java.util.Random;

public class GestioneCasualeStrategy implements GestioneBoardStrategy {
    Random random = new Random();

    @Override
    public void gestioneBoard(Board board, int numeroCaselle) {
        int numeroSerpenti = 2+random.nextInt(3);
        int numeroScale = 2+random.nextInt(3);
        int larghezza = (int) Math.ceil(Math.sqrt(numeroCaselle));

        System.out.println("Numero di serpenti da inserire: " + numeroSerpenti);
        for(int i = 0; i < numeroSerpenti; i++) {
            int testa;
            int coda;
            do {
                testa = random.nextInt(numeroCaselle-1);
                coda = random.nextInt(testa);
            } while(testa<=coda || board.getCaselleUsate().contains(testa) || board.getCaselleUsate().contains(coda) || stessaRiga(testa, coda, larghezza));
            board.getCasellaFactory().creaCasella(CaselleSpeciali.SERPENTE, testa, coda);
            board.getCaselleUsate().add(testa);
            board.getCaselleUsate().add(coda);
            System.out.println("La coda del serpente è alla casella: " + coda);
            System.out.println("La testa del serpente è alla casella: " + testa);
        }

        System.out.println("Numero di scale da inserire: " + numeroScale);
        for(int i = 0; i < numeroScale; i++) {
            int cima;
            int fine;
            do {
                cima = random.nextInt(numeroCaselle-1);
                fine = random.nextInt(cima);
            } while(cima<=fine || board.getCaselleUsate().contains(cima) || board.getCaselleUsate().contains(fine) || stessaRiga(cima, fine, larghezza));
            board.getCasellaFactory().creaCasella(CaselleSpeciali.SCALA, fine, cima);
            board.getCaselleUsate().add(cima);
            board.getCaselleUsate().add(fine);
            System.out.println("La fine della scala è alla casella: " + fine);
            System.out.println("La cima della scala è alla casella: " + cima);
        }

        aggiungiCasella(CaselleSpeciali.PANCHINA, numeroCaselle, board);
        aggiungiCasella(CaselleSpeciali.LOCANDA, numeroCaselle, board);
        aggiungiCasella(CaselleSpeciali.DADI, numeroCaselle, board);
        aggiungiCasella(CaselleSpeciali.MOLLA, numeroCaselle, board);

        int numCasellePescaUnaCarta = 1+random.nextInt(2);
        System.out.println("Numero caselle pesca una carta: " + numCasellePescaUnaCarta);
        for(int i=0; i<numCasellePescaUnaCarta; i++) {
            int pos;
            do {
                pos = random.nextInt(numeroCaselle-1);
            } while(board.getCaselleUsate().contains(pos));
            board.getCaselle()[pos] = board.getCasellaFactory().creaCasella(CaselleSpeciali.PESCA_UNA_CARTA, pos, 0);
            board.getCaselleUsate().add(pos);
            System.out.println("Alla casella " + pos + " si abbiamo pescato una carta " + board.getCaselle()[pos].toString());
        }
    }

    private boolean stessaRiga(int pos1, int pos2, int larghezza) {
        return (pos1/larghezza == pos2/larghezza);
    }

    private void aggiungiCasella(CaselleSpeciali tipo, int numeroCaselle, Board board) {
        int pos;
        do {
            pos = random.nextInt(numeroCaselle-1);
        } while(board.getCaselleUsate().contains(pos));
        board.getCaselle()[pos] = board.getCasellaFactory().creaCasella(tipo, pos, 0);
        System.out.println("La casella " + tipo.toString() + " è stata aggiunta nella posizione: " + pos);
    }
}
