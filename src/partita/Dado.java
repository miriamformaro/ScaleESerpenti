package partita;

import java.util.Random;

public class Dado {
    private boolean dado;
    private Random rand = new Random();
    private int dado1;
    private int dado2;

    public Dado(boolean dado) {
        this.dado = dado;
    }

    public int eseguiLancio() {
        dado1 = rand.nextInt(6) + 1;
        if(dado) {
            System.out.println("Dado: " + dado1);
            return dado1;
        }
        dado2 = rand.nextInt(6) + 1;
        System.out.println("Dado1: " + dado1 + ", Dado2: " + dado2);
        return dado1 + dado2;
    }

    public int eseguiLancioSingolo() {
        dado1 = rand.nextInt(6) + 1;
        System.out.println("Dado: " + dado1);
        return dado1;
    }

    public int getDado1() {
        return dado1;
    }

    public int getDado2() {
        return dado2;
    }
}
