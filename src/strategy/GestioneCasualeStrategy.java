package strategy;

import caselle.*;
import partita.Board;

import java.util.Random;

public class GestioneCasualeStrategy implements GestioneBoardStrategy {
    Random random = new Random();

    @Override
    public void gestioneBoard(Board board, int numeroCaselle) {
        if (numeroCaselle <= 1) {
            throw new IllegalArgumentException("Il numero di caselle deve essere maggiore di 1!");
        }

        int numeroSerpenti = 2+random.nextInt(2);
        int numeroScale = 2+random.nextInt(2);
        int larghezza = calcolaLarghezza(numeroCaselle);

        System.out.println("Numero di serpenti da inserire: " + numeroSerpenti);
        for(int i = 0; i < numeroSerpenti; i++) {
            int testa;
            int coda;
            do {
                testa = 1+random.nextInt(numeroCaselle-1);
                coda = random.nextInt(testa);
            } while(testa==0 || testa ==numeroCaselle-1 || coda==0 || coda==numeroCaselle-1 || testa<=coda || board.getCaselleUsate().contains(testa) || board.getCaselleUsate().contains(coda) || stessaRiga(testa, coda, larghezza));
            board.getCaselle()[testa] = board.getCasellaFactory().creaCasella(CaselleSpeciali.SERPENTE, testa, coda);
            board.getCaselleUsate().add(testa);
            board.getCaselleUsate().add(coda);
            board.aggiungiCasella(CaselleSpeciali.SERPENTE, testa, coda);
            System.out.println("La coda del serpente è alla casella: " + coda);
            System.out.println("La testa del serpente è alla casella: " + testa);
        }

        System.out.println("Numero di scale da inserire: " + numeroScale);
        for(int i = 0; i < numeroScale; i++) {
            int cima;
            int fine;
            do {
                cima = 1+random.nextInt(numeroCaselle-1);
                fine = random.nextInt(cima);
            } while(cima<=fine || board.getCaselleUsate().contains(cima) || board.getCaselleUsate().contains(fine) || stessaRiga(cima, fine, larghezza));
            board.getCaselle()[fine] = board.getCasellaFactory().creaCasella(CaselleSpeciali.SCALA, fine, cima);
            board.getCaselleUsate().add(cima);
            board.getCaselleUsate().add(fine);
            board.aggiungiCasella(CaselleSpeciali.SCALA, fine, cima);
            System.out.println("La fine della scala è alla casella: " + fine);
            System.out.println("La cima della scala è alla casella: " + cima);
        }

        aggiungiCasellaSpeciale(CaselleSpeciali.PANCHINA, numeroCaselle, board);
        aggiungiCasellaSpeciale(CaselleSpeciali.LOCANDA, numeroCaselle, board);
        aggiungiCasellaSpeciale(CaselleSpeciali.DADI, numeroCaselle, board);
        aggiungiCasellaSpeciale(CaselleSpeciali.MOLLA, numeroCaselle, board);

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
            board.aggiungiCasella(CaselleSpeciali.PESCA_UNA_CARTA, pos, 0);
        }
    }

    private boolean stessaRiga(int pos1, int pos2, int larghezza) {
        return (pos1/larghezza == pos2/larghezza);
    }

    private void aggiungiCasellaSpeciale(CaselleSpeciali tipo, int numeroCaselle, Board board) {
        int pos;
        do {
            pos = random.nextInt(numeroCaselle-1);
        } while(board.getCaselleUsate().contains(pos));
        board.getCaselle()[pos] = board.getCasellaFactory().creaCasella(tipo, pos, 0);
        System.out.println("La casella " + tipo.toString() + " è stata aggiunta nella posizione: " + pos);
        board.aggiungiCasella(tipo, pos, 0);
    }

    /* private boolean esisteSerpenteNellaStessaRiga(int coda, int testa, int larghezza, Board board) {
        for (Integer pos : board.getCaselleUsate()) {
            AbstractCasella casella = board.getCasella(pos);

            // Verifica se la casella è una Scala
            if (casella != null && casella.getTipo()==CaselleSpeciali.SERPENTE) {
                CasellaSerpente serpente = (CasellaSerpente) casella;

                // Ottieni le posizioni di "cima" e "fine" della Scala esistente
                int codaSerpenteEsistente = serpente.getCoda();
                int testaSerpenteEsistente = serpente.getTesta();

                // Controlla se la nuova cima o fine è sulla stessa riga della cima o fine esistente
                if (stessaRiga(coda, testaSerpenteEsistente, larghezza) ||
                        stessaRiga(coda, codaSerpenteEsistente, larghezza) ||
                        stessaRiga(testa, testaSerpenteEsistente, larghezza) ||
                        stessaRiga(testa, codaSerpenteEsistente, larghezza)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean esisteScalaNellaStessaRiga(int fine, int cima, int larghezza, Board board) {
        for (Integer pos : board.getCaselleUsate()) {
            AbstractCasella casella = board.getCasella(pos);

            // Verifica se la casella è una Scala
            if (casella != null && casella.getTipo()==CaselleSpeciali.SCALA) {
                CasellaScala scala = (CasellaScala) casella;

                // Ottieni le posizioni di "cima" e "fine" della Scala esistente
                int cimaScalaEsistente = scala.getFine();
                int fineScalaEsistente = scala.getInizio();

                // Controlla se la nuova cima o fine è sulla stessa riga della cima o fine esistente
                if (stessaRiga(fine, cimaScalaEsistente, larghezza) ||
                        stessaRiga(fine, fineScalaEsistente, larghezza) ||
                        stessaRiga(cima, cimaScalaEsistente, larghezza) ||
                        stessaRiga(cima, fineScalaEsistente, larghezza)) {
                    return true;
                }
            }
        }
        return false;
    } */

    private int calcolaLarghezza(int numeroCaselle) {
        int num = (int) Math.sqrt(numeroCaselle); // Partiamo dalla radice quadrata
        while (numeroCaselle % num != 0) {
            num++; // Trova il primo valore che divide esattamente numeroCaselle
        }
        return numeroCaselle/num;
    }
}
