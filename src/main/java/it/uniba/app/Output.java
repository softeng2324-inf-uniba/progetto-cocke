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
     * Metodo che mostra a video il campo vuoto su cui giocare.
     */
    static void printEmptyField() {
        final int dim = 7;
        System.out.print("   ");
        for (int i = 1; i <= dim; i++) {
            System.out.print("____ ");
        }
        System.out.print("\n");
        System.out.print(" _|");
        char c = '\u0041';
        for (int i = 1; i <= dim; i++) {
            System.out.print("_" + c + "__|");
            c++;
        }
        System.out.print("_");
        System.out.print("\n");
        for (int i = 1; i <= dim; i++) {
            System.out.print("|" + i + "|");
            for (int j = 1; j <= dim; j++) {
                System.out.print("    |");
            }
            System.out.print(i + "|");
            System.out.print("\n");
            System.out.print("|_|");
            for (int j = 1; j <= dim; j++) {
                System.out.print("____|");
            }
            System.out.print("_|");
            System.out.print("\n");
        }
        System.out.print("  |");
        c = '\u0041';
        for (int i = 1; i <= dim; i++) {
            System.out.print("_" + c + "__|");
            c++;
        }
    }
}
