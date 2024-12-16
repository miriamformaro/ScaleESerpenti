package caselle;

import partita.Player;

public class CasellaSerpente extends AbstractCasella {
    private int coda;

    public CasellaSerpente(int testa, int coda) {
        super(testa);
        if(testa<=coda) {
            throw new RuntimeException();
        }
        this.coda = coda;
    }

    public int getTesta() {
        return getPosizione();
    }

    public int getCoda() { return coda; }

    public void setCoda(int coda) {
        this.coda = coda;
    }

    public void esegui(Player player) {
        player.setPosizione(player.getPosizione());
        System.out.println("La casella contiene la testa del serpente, devi tornare indietro fino alla sua coda!");
        player.setPosizione(coda);
        System.out.println("Nuova posizione: " + coda);
    }
}
