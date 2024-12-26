package caselle;

import partita.Player;

public class CasellaNormale extends AbstractCasella {
    public CasellaNormale(int posizione) {
        super(posizione);
    }

    @Override
    public void esegui(Player p) {
        System.out.println("La casella è semplice!");
    }

    @Override
    public CaselleSpeciali getTipo() {
        return null;
    }


}
