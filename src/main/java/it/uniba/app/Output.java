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
    private static void printFirstLine(int dim) {
        System.out.print(" " + "\u203e" + " " + "\u2016" + "\u200a");
        for (int i = 0; i < dim - 1; i++) {
            System.out.print("\u203e" + "\u203e" + "\u203e" + "\u203e" + "\u203e" + "\u203e" + "\u203e");
            System.out.print("\u007c");
        }
        System.out.print("\u203e" + "\u203e" + "\u203e" + "\u203e" + "\u203e" + "\u203e" + "\u203e");
        System.out.print("\u200a");
        System.out.print("\u2016");
        System.out.print("\n");
    }
    private static void printNumberLine(int dim, int num) {
        System.out.print(" " + num + " ");
        System.out.print("\u2016");
        System.out.print("\u200a");
        for (int i = 0; i < dim - 1; i++) {
            System.out.print("       ");
            System.out.print("\u007c");
        }
        System.out.print("       ");
        System.out.print("\u200a");
        System.out.print("\u2016");
        System.out.print("\n");
    }
    private static void printSpaceLine(int dim) {
        System.out.print("   ");
        System.out.print("\u2016");
        System.out.print("\u200a");
        for (int i = 0; i < dim - 1; i++) {
            System.out.print("       ");
            System.out.print("\u007c");
        }
        System.out.print("       ");
        System.out.print("\u200a");
        System.out.print("\u2016");
        System.out.print("\n");
    }
    /**
     * Metodo che mostra a video il campo vuoto su cui giocare.
     */
    static void printEmptyField() {
        final int dim = 7;
        System.out.print("   " + "\u200a" + "\u007c");
        char c = '\u0041';
        for (int i = 0; i < dim; i++) {
            System.out.print("   " + c + "   " + "\u007c");
            c++;
        }
        System.out.print("\n");
        System.out.print(" " + "\u203e" + " ");
        System.out.print("\u2016");
        System.out.print("\u200a");
        for (int i = 0; i < dim - 1; i++) {
            System.out.print("\u033f" + "\u033f" + "\u033f" + "\u033f" + "\u033f" + "\u033f" + "\u033f");
            System.out.print("\u007c");
        }
        System.out.print("\u033f" + "\u033f" + "\u033f" + "\u033f" + "\u033f" + "\u033f" + "\u033f");
        System.out.print("\u200a");
        System.out.print("\u2016");
        System.out.print("\n");

        printNumberLine(dim, 1);
        printSpaceLine(dim);
        printFirstLine(dim);
    }
}
