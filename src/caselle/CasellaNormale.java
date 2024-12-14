package caselle;

import Gioco.Player;

public class CasellaNormale extends AbstractCasella {
    public CasellaNormale(int posizione) {
        super(posizione);
    }

    @Override
    public void esegui(Player p) {
        System.out.println("La casella è semplice!");
    }


}
