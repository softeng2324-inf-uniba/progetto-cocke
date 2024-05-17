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
    private Output() { }
    /**
     * Metodo che stampa la prima riga di caratteri che compone il campo.
     * @param dim dimensione del campo.
     */
    private static void printFirstLine(final int dim) {
        System.out.print(" " + "\u203e" + " " + "\u2016" + "\u200a");
        for (int i = 0; i < dim - 1; i++) {
            System.out.print("\u203e" + "\u203e" + "\u203e" + "\u203e" + "\u203e" + "\u203e" + "\u203e");
            System.out.print("\u007c");
        }
        System.out.print("\u203e" + "\u203e" + "\u203e" + "\u203e" + "\u203e" + "\u203e" + "\u203e");
        System.out.print("\u200a");
        System.out.print("\u2016");
        System.out.print(" " + "\u203e");
        System.out.print("\n");
    }
    /**
     * Metodo che stampa la riga di caratteri contenente il numero che identifica la riga di caselle.
     * @param dim dimensione del campo.
     * @param num numero di riga.
     * */
    private static void printNumberLine(final int dim, final int num) {
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
        System.out.print(" " + num + " ");
        System.out.print("\n");
    }
    /**
     * Metodo che stampa una riga vuota del campo.
     * @param dim dimensione del campo.
     */
    private static void printSpaceLine(final int dim) {
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
     * Metodo che stampa la riga contenente le lettere che identificano le colonne.
     * @param dim dimensione del campo
     */
    private static void printLetters(final int dim) {
        System.out.print("   " + "\u200a" + "\u007c");
        char c = '\u0041';
        for (int i = 0; i < dim; i++) {
            System.out.print("   " + c + "   " + "\u007c");
            c++;
        }
        System.out.print("\n");
    }
    /**
     * Metodo che mostra a video il campo vuoto su cui giocare.
     */
    static void printEmptyField() {
        final int dim = Field.DEFAULT_DIM;
        printLetters(dim);
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
        System.out.print(" " + "\u203e");
        System.out.print("\n");
        for (int i = 0; i < dim - 1; i++) {
            printNumberLine(dim, i + 1);
            printSpaceLine(dim);
            printFirstLine(dim);
        }
        printNumberLine(dim, dim);
        printSpaceLine(dim);
        System.out.print(" " + "\u203e" + " ");
        System.out.print("\u200a");
        System.out.print("\u033f");
        for (int i = 0; i < dim - 1; i++) {
            System.out.print("\u033f" + "\u033f" + "\u033f" + "\u033f" + "\u033f" + "\u033f" + "\u033f");
            System.out.print(" ");
        }
        System.out.print("\u033f" + "\u033f" + "\u033f" + "\u033f" + "\u033f" + "\u033f" + "\u033f");
        System.out.print("\u033f");
        System.out.print(" " + "\u203e");
        System.out.print("\n");
        printLetters(dim);
    }
}
