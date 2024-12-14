package caselle;

import Gioco.Player;

public class CasellaScala extends AbstractCasella {
    private int fine;

    public CasellaScala(int inizio, int fine) {
        super(inizio);
        if(fine<=inizio)
        {
            throw new RuntimeException();
        }
        this.fine = fine;
    }

    public int getInizio() {
        return getPosizione();
    }

    public int getFine() { return fine; }
    public void setFine(int fine) { this.fine = fine; }

    public void esegui(Player player) {
        System.out.println("La casella contiene la fine di una scala, avanza fino alla cima della scala!");
        player.setPosizione(fine);
        System.out.println("Nuova posizione: " + fine);
    }
}
