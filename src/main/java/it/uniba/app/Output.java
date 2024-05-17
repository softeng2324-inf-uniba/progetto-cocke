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
    private static void printHoLine(final int dim){
        for (int i = 0; i < dim*5+dim-1; i++) {
            System.out.print("═");
        }
    }
    /**
     * Metodo che stampa la riga contenente le lettere che identificano le colonne.
     * @param dim dimensione del campo
     */
    private static void printLetters(final int dim) {
        System.out.print("    ║");
        char c = 'A';
        for (int i = 0; i < dim; i++) {
            System.out.print("  " + c + "  ║");
            c++;
        }
        System.out.print("\n");
    }
    private static void printStuffedLine(final int dim, final int num) {
        System.out.print("║ " + num + " ║");
        for (int i = 0; i < dim; i++) {
            System.out.print("     ║");
        }
        System.out.print(" " + num + " ║");
        System.out.print("\n");
    }
    /**
     * Metodo che mostra a video il campo vuoto su cui giocare.
     */
    static void printEmptyField() {
        final int dim = Field.DEFAULT_DIM;
        System.out.print("    ╔");
        printHoLine(dim);
        System.out.print("╗");
        System.out.print("\n");
        printLetters(dim);

        System.out.print("╔═══╬");
        printHoLine(dim);
        System.out.print("╬═══╗");
        System.out.print("\n");

        int i=0;
        for (i = 0; i < dim-1; i++) {
            printStuffedLine(dim, i+1);
            System.out.print("╠═══╬");
            printHoLine(dim);
            System.out.print("╬═══╣");
            System.out.print("\n");
        }
        printStuffedLine(dim, i+1);
        System.out.print("╚═══╬");
        printHoLine(dim);
        System.out.print("╬═══╝");
        System.out.print("\n");
        printLetters(dim);
        System.out.print("    ╚");
        printHoLine(dim);
        System.out.print("╝");
    }
}
