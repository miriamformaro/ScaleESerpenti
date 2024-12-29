package caselle;

public class Casella implements CasellaFactory {

    @Override
    public AbstractCasella creaCasella(CaselleSpeciali tipo, int posizione, int fine) {
        switch (tipo) {
            case SCALA:
                return new CasellaScala(posizione, fine);
            case SERPENTE:
                return new CasellaSerpente(posizione, fine);
            case PANCHINA:
                return new CasellaSosta(posizione, CaselleSpeciali.PANCHINA);
            case LOCANDA:
                return new CasellaSosta(posizione, CaselleSpeciali.LOCANDA);
            case DADI:
                return new CasellaPremio(posizione, CaselleSpeciali.DADI);
            case MOLLA:
                return new CasellaPremio(posizione, CaselleSpeciali.MOLLA);
            case PESCA_UNA_CARTA:
                return new CasellaPescaUnaCarta(posizione, CaselleSpeciali.PESCA_UNA_CARTA);
            default:
                throw new  IllegalArgumentException("Tipo non valido");
        }
    }
}
