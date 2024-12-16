package strategy;

import caselle.CasellaNormale;
import caselle.CaselleSpeciali;
import partita.Board;

import java.util.Random;

public class GestioneCasualeStrategy implements GestioneBoardStrategy {
    private Random random = new Random();
    @Override
    public void gestioneBoard(Board board, int numeroCaselle) {
        int numSerpenti = 2+random.nextInt(3);
        int numScale = 2+random.nextInt(3);
        int numCaselleSosta = 1+random.nextInt(3);
        int numCasellePescaUnaCarta = 1+random.nextInt(2);

        int lunghezzaMassima = 5;
        int lunghezzaMinima = 2;

        //aggiungo i serpenti (sulla stessa riga)
        for(int i = 0; i < numSerpenti; i++) {
            int testa, coda;
            do {
                testa = 1+random.nextInt(numeroCaselle-1);
                coda = random.nextInt(testa);
            } while(testa-coda<lunghezzaMinima || testa-coda>lunghezzaMassima || board.getCaselleUsate().contains(coda) || board.getCaselleUsate().contains(testa));
            board.getCaselle()[testa] = board.getCasellaFactory().creaCasella(CaselleSpeciali.SERPENTE, testa, coda);
            for(int j = coda; j<=testa; j++) {
                board.getCaselleUsate().add(j);
            }
            System.out.println("La coda del serpente è alla casella: " + coda);
            System.out.println("La testa del serpente è alla casella: " + testa);
        }

        //aggiungo le scale (sulla stessa riga)
        for(int i = 0; i < numScale; i++) {
            int inizio, fine;
            do {
                inizio = 1+random.nextInt(numeroCaselle-1);
                fine = lunghezzaMinima+inizio+random.nextInt(lunghezzaMassima-lunghezzaMinima+1);
            } while(fine>=numeroCaselle|| board.getCaselleUsate().contains(inizio) || board.getCaselleUsate().contains(fine));
            board.getCaselle()[inizio] = board.getCasellaFactory().creaCasella(CaselleSpeciali.SCALA, inizio, fine);
            for(int j = inizio; j<=fine; j++) {
                board.getCaselleUsate().add(j);
            }
            System.out.println("La scala si trova dalla casella " + inizio + " alla casella " + fine);
        }

        //aggiungo le caselle di sosta
        int panchina;
        do {
            panchina = random.nextInt(numeroCaselle-1);
        } while(board.getCaselleUsate().contains(panchina));
        board.getCaselle()[panchina] = board.getCasellaFactory().creaCasella(CaselleSpeciali.PANCHINA, panchina, 0);
        board.getCaselleUsate().add(panchina);
        System.out.println("La panchina si trova alla casella " + panchina);
        int locanda;
        do {
            locanda = random.nextInt(numeroCaselle-1);
        } while(locanda==panchina || board.getCaselleUsate().contains(locanda));
        board.getCaselle()[locanda] = board.getCasellaFactory().creaCasella(CaselleSpeciali.LOCANDA, locanda, 0);
        board.getCaselleUsate().add(locanda);
        System.out.println("La locanda si trova alla casella " + locanda);

        //aggiungo le caselle premio
        int dadi;
        do {
            dadi = random.nextInt(numeroCaselle-1);
        } while(board.getCaselleUsate().contains(dadi));
        board.getCaselle()[dadi] = board.getCasellaFactory().creaCasella(CaselleSpeciali.DADI, dadi, 0);
        board.getCaselleUsate().add(dadi);
        System.out.println("La casella dadi si trova alla casella " + dadi);
        int molla;
        do {
            molla = random.nextInt(numeroCaselle-1);
        } while(molla==dadi || board.getCaselleUsate().contains(molla));
        board.getCaselle()[molla] = board.getCasellaFactory().creaCasella(CaselleSpeciali.MOLLA, molla, 0);
        board.getCaselleUsate().add(molla);
        System.out.println("La casella molla si trova alla casella " + molla);

        //aggiungo le caselle pesca una carta
        for(int i=0; i<numCasellePescaUnaCarta; i++) {
            int pos;
            do {
                pos = random.nextInt(numeroCaselle-1);
            } while(board.getCaselleUsate().contains(pos));
            board.getCaselle()[pos] = board.getCasellaFactory().creaCasella(CaselleSpeciali.PESCA_UNA_CARTA, pos, 0);
            board.getCaselleUsate().add(pos);
            System.out.println("Alla casella " + pos + " si abbiamo pescato una carta " + board.getCaselle()[pos].toString());
        }

        for(int i=0; i<numeroCaselle; i++) {
            if(board.getCaselle()[i] == null)
                board.getCaselle()[i] = new CasellaNormale(i);
        }
    }
}
