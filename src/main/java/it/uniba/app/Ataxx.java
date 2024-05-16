package it.uniba.app;
import java.nio.file.Paths;

/**
 * Ataxx è la classe principale del gioco che gestisce l'intero flusso di gioco.
 */
public class Ataxx {
    /**
     * Stringa contenente il percorso relativo del file da leggere.
     */
    private static String relativePath = "/src/main/java/it/uniba/app/help.txt";
    /**
     * Workspace attuale.
     */
    private static String filePath = Paths.get(System.getProperty("user.dir"), relativePath).toString();

    /**
     * Rappresenta il gioco attualmente in esecuzione.
     */
    private Game game = null;

    /**
     * Indica se il gioco è ancora in esecuzione.
     */
    private static boolean stillPlaying = true;

    /**
     * Restituisce il gioco attualmente in esecuzione.
     * @return il gioco attualmente in esecuzione.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Imposta il gioco attualmente in esecuzione.
     * @param newGame il nuovo gioco da impostare.
     */
    public void setGame(final Game newGame) {
        game = newGame;
    }

    /**
     * Restituisce se il gioco è ancora in esecuzione.
     * @return (true) se il gioco è ancora in esecuzione, (false) altrimenti.
     */
    public boolean getStillPlaying() {
        return stillPlaying;
    }

    /**
     * Imposta se il gioco è ancora in esecuzione.
     * @param isStillPlaying valore booleano per indicare se il gioco è ancora in esecuzione.
     */
    public void setStillPlaying(final boolean isStillPlaying) {
        stillPlaying = isStillPlaying;
    }

    /**
     * Gestisce le flag passate come argomenti al programma (tramite CLI).
     * Se viene passata la flag -h o --help, viene stampato l'help del programma.
     * Se viene passata una flag non riconosciuta, viene stampato un messaggio di errore.
     * Se non vengono passate flag, il programma prosegue normalmente.
     */
    private static void manageFlag(final String[] args) {
        boolean help = false;

        for (String arg : args) {
            switch (arg) {
                case "-h":
                case "--help":
                    manageHelp();
                    break;
                default:
                    System.err.println("Flag non riconosciuta: " + arg);
                    break;
            }
        }
    }

    /**
     * Gestisce il file da stampare a video.
     */
    private static void manageHelp() {
        Output.printFile(filePath);
    }


    /**
     * Gestisce il flusso di esecuzione in base al comando ricevuto.
     * @param args array di argomenti passati da command line.
     */
    public static void ataxxCommand(final String[] args) {
        manageFlag(args);
        String command = "";  //da eliminare dopo implementazione di getcommand
        do {
            //String command = Input.getCommand();
            switch (command) {
                case "/help":
                    manageHelp();
                    break;
                case "/gioca":
                    System.out.println("/gioca");
                    break;
                case "/vuoto":
                    System.out.println("/vuoto");
                    break;
                case "/tavoliere":
                    System.out.println("/tavoliere");
                    break;
                case "/qualimosse":
                    System.out.println("/qualimosse");
                    break;
                case "/abbandona":
                    System.out.println("/abbandona");
                    break;
                case "/esci":
                    System.out.println("/esci");
                    break;
                default:
                    System.out.println("Comando sconosciuto");
                    break;
            }
        } while (!stillPlaying);
    }
}
