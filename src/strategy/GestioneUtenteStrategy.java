package strategy;

import caselle.CaselleSpeciali;
import partita.Board;

import java.util.Scanner;

public class GestioneUtenteStrategy implements GestioneBoardStrategy {
    @Override
    public void gestioneBoard(Board board, int numeroCaselle) {
        int numeroSerpenti = ottieniNumero("Quanti serpenti vuoi inserire? ");
        for(int i=0; i<numeroSerpenti; i++) {
            int testa = ottieniPosizione("Inserisci la posizione della testa del serpente: ");
            int coda = ottieniPosizione("Inserisci la posizione della coda del serpente: ");
            if(testa > coda && !board.getCaselleUsate().contains(testa) && !board.getCaselleUsate().contains(coda)) {
                board.aggiungiCasella(CaselleSpeciali.SERPENTE, testa, coda);
            } else {
                System.out.println("Impossibile aggiungere il serpente!");
            }
        }

        int numeroScale = ottieniNumero("Quanti scale vuoi inserire? ");
        for(int i=0; i<numeroScale; i++) {
            int inizio = ottieniPosizione("Inserisci la casella di inizio della scala: ");
            int fine = ottieniPosizione("Inserisci la casella di fine della scala: ");
            if(fine > inizio && !board.getCaselleUsate().contains(fine) && !board.getCaselleUsate().contains(inizio)) {
                board.aggiungiCasella(CaselleSpeciali.SCALA, inizio, fine);
            } else {
                System.out.println("Impossibile aggiungere la scala!");
            }
        }

        int posizionePanchina = ottieniPosizione("In che casella vuoi inserire la panchina? ");
        if(!board.getCaselleUsate().contains(posizionePanchina)) {
            board.aggiungiCasella(CaselleSpeciali.PANCHINA, posizionePanchina, 0);
        } else {
            System.out.println("Impossibile aggiungere la panchina!");
        }

        int posizioneLocanda = ottieniPosizione("In che casella vuoi inserire la locanda? ");
        if(!board.getCaselleUsate().contains(posizioneLocanda)) {
            board.aggiungiCasella(CaselleSpeciali.LOCANDA, posizioneLocanda, 0);
        } else {
            System.out.println("Impossibile aggiungere la locanda!");
        }

        int posizioneDadi = ottieniPosizione("Dove vuoi inserire la casella dadi? ");
        if(!board.getCaselleUsate().contains(posizioneDadi)) {
            board.aggiungiCasella(CaselleSpeciali.DADI, posizioneDadi, 0);
        } else {
            System.out.println("Impossibile aggiungere la casella dadi!");
        }

        int posizioneMolla = ottieniPosizione("Dove vuoi inserire la casella molla? ");
        if(!board.getCaselleUsate().contains(posizioneMolla)) {
            board.aggiungiCasella(CaselleSpeciali.MOLLA, posizioneMolla, 0);
        } else {
            System.out.println("Impossibile aggiungere la casella molla!");
        }

        int posizionePescaUnaCarta = ottieniPosizione("Dove vuoi inserire la casella per pescare una carta? ");
        if(!board.getCaselleUsate().contains(posizionePescaUnaCarta)) {
            board.aggiungiCasella(CaselleSpeciali.PESCA_UNA_CARTA, posizionePescaUnaCarta, 0);
        } else {
            System.out.println("Impossibile aggiungere la casella pesca una carta!");
        }

        board.caselleNormali();
    }

    private int ottieniNumero(String domanda) {
        Scanner sc = new Scanner(System.in);
        System.out.print(domanda);
        int numero = sc.nextInt();
        return numero;
    }

    private int ottieniPosizione(String domanda) {
        Scanner sc = new Scanner(System.in);
        System.out.print(domanda);
        int posizione = sc.nextInt();
        return posizione;
    }
}
