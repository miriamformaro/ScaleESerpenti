package caselle;

import partita.Player;

public class CasellaPremio extends AbstractCasella {
    private CaselleSpeciali tipo;
    private int avanzamento = 0;

    public CasellaPremio(int posizione, CaselleSpeciali tipo) {
        super(posizione);
        this.tipo = tipo;
    }

    public CaselleSpeciali getTipo() { return tipo; }

    @Override
    public void esegui(Player p) {
        if (tipo == null) {
            System.out.println("Errore: Tipo di casella non definito!");
            return;
        }
        if (tipo == CaselleSpeciali.DADI) {
            //System.out.println("Il giocatore è finito sulla casella premio 'DADI' farà un altro turno, rilancia i dadi!");
            avanzamento = p.getD().eseguiLancio();
            int nuovaPosizione = p.getPosizione() + avanzamento;
            p.setPosizione(nuovaPosizione);
            //System.out.println("Il giocatore avanzerà di " + avanzamento + " caselle!");
            //System.out.println("Nuova posizione: " + p.getPosizione());
        } else if (tipo == CaselleSpeciali.MOLLA) {
            int ultimoAvanzo = p.getAvanzo();  // Recupera l'ultimo avanzo
            if (ultimoAvanzo > 0) {
                p.setPosizione(p.getPosizione() + ultimoAvanzo);  // Avanza di nuovo sulla base dell'ultimo movimento
                //System.out.println("Il giocatore avanza ancora del punteggio ottenuto con l’ultimo lancio di dadi!");
                //System.out.println("Nuova posizione: " + p.getPosizione());
            }
        }
    }

    public int getAvanzamento() { return avanzamento; }

    public void setAvanzamento(int avanzamento) {
        this.avanzamento = avanzamento;
    }
}
