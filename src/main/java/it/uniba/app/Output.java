package it.uniba.app;

/**
 * Output è la classe che si occupa delle stampe a video.
 * <p>Questa classe gestisce la stampa degli elementi del gioco Ataxx,
 * come la stampa del campo da gioco e dell'interfaccia grafica.</p>
 */
public final class Output {
    /**
     * Costruttore per la classe.
     */
    private Output() {
    }
    /**
     * Metodo che mostra a video il campo vuoto su cui giocare.
     */
    static void printEmptyField() {
        final int dim = 7;
        System.out.print("   " + "\u200a" + "\u007c");
        char c = '\u0041';
        for (int i = 0; i < dim; i++) {
            System.out.print("  " + c + "  " + "\u007c");
            c++;
        }
        System.out.print("\n");
        System.out.print("   ");
        System.out.print("\u2016");
        System.out.print("\u200a");
        for (int i = 0; i < dim - 1; i++) {
            System.out.print("\u033f");
            System.out.print("\u033f");
            System.out.print("\u033f");
            System.out.print("\u033f");
            System.out.print("\u033f");
            System.out.print("\u007c");
        }
        System.out.print("\u033f");
        System.out.print("\u033f");
        System.out.print("\u033f");
        System.out.print("\u033f");
        System.out.print("\u033f");
        System.out.print("\u200a");
        System.out.print("\u2016");
        System.out.print("\n");
    }
}
