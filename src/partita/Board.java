package partita;

import caselle.AbstractCasella;
import caselle.Casella;
import caselle.CasellaNormale;
import caselle.CaselleSpeciali;
import strategy.GestioneBoardStrategy;

import java.util.HashSet;
import java.util.Set;

public class Board {
    private AbstractCasella[] caselle;
    private Set<Integer> caselleUsate = new HashSet<>();
    private Casella casellaFactory = new Casella();
    private GestioneBoardStrategy strategy;

    public Board(int numeroCaselle, GestioneBoardStrategy strategy) {
        caselle = new AbstractCasella[numeroCaselle];
        this.strategy = strategy;
    }

    public void applicaStrategia() {
        this.strategy.gestioneBoard(this, caselle.length);
    }

    public void aggiungiCasella(CaselleSpeciali tipo, int posizione, int fine) {
        if(posizione > 0 && posizione < caselle.length && !caselleUsate.contains(posizione)) {
            caselle[posizione] = casellaFactory.creaCasella(tipo, posizione, fine);
            caselleUsate.add(posizione);
        }
    }

   public void caselleNormali() {
        for(int i = 0; i < caselle.length; i++) {
            if(caselle[i] == null) {
                caselle[i] = new CasellaNormale(i);
            }
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
}