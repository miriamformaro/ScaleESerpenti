package caselle;

import partita.Player;

public abstract class AbstractCasella {
    private int posizione;

    public AbstractCasella(int posizione) {
        this.posizione = posizione;
    }

    public int getPosizione() { return posizione; }

    public void setPosizione(int posizione) { this.posizione = posizione; }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null) return false;
        if(!(o instanceof AbstractCasella)) return false;
        AbstractCasella c = (AbstractCasella) o;
        return c.posizione == this.posizione;
    }

    public abstract void esegui(Player p);
    public abstract CaselleSpeciali getTipo();

    @Override
    public String toString() {
        return CaselleSpeciali.values().toString();
    }
}
