package partita;

import caselle.*;
import strategy.GestioneBoardStrategy;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Board {
    private AbstractCasella[] caselle;
    private Set<Integer> caselleUsate = new HashSet<>();
    private Casella casellaFactory = new Casella();
    private GestioneBoardStrategy strategy;
    private List<CasellaSerpente> listaSerpenti = new LinkedList<>();
    private List<CasellaScala> scaleLista = new LinkedList<>();

    public Board(int numeroCaselle, GestioneBoardStrategy strategy) {
        caselle = new AbstractCasella[numeroCaselle];
        this.strategy = strategy;
    }

    public void applicaStrategia() {
        this.strategy.gestioneBoard(this, caselle.length);
    }

    public void aggiungiCasella(CaselleSpeciali tipo, int posizione, int fine) {
        if(posizione > 0 && posizione < caselle.length-1 && !caselleUsate.contains(posizione)) {
            caselle[posizione] = casellaFactory.creaCasella(tipo, posizione, fine);
            caselleUsate.add(posizione);
        }
    }

    public int getUltimaCasella() {
        return caselle.length - 1;  // Ultima casella del tabellone
    }

    public AbstractCasella[] getCaselle() {
        return caselle;
    }

    public AbstractCasella getCasella(int posizione) {
        if (posizione >= 0 && posizione < caselle.length) {
            return caselle[posizione];  // Restituisce la casella corrispondente
        }
        return null;
    }

    public int getLenght() {
        return caselle.length;
    }

    public Set<Integer> getCaselleUsate() {
        return caselleUsate;
    }

    public Casella getCasellaFactory() {
        return casellaFactory;
    }

    public void aggiungiSerpente(CasellaSerpente serpente) {
        listaSerpenti.add(serpente);
    }

    public String getSerpente() {
        StringBuilder sb = new StringBuilder();
        for(CasellaSerpente serpente : listaSerpenti) {
            sb.append(serpente.toString()).append("\n");
        }
        return sb.toString();
    }

    public void aggiungiScala(CasellaScala scala) {
        scaleLista.add(scala);
    }

    public String getScala() {
        StringBuilder sb = new StringBuilder();
        for(CasellaScala scala : scaleLista) {
            sb.append(scala.toString()).append("\n");
        }
        return sb.toString();
    }
}