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
     * Se non vi è una partita in corso, ne viene inizializzata una nuova e viene stampato il campo
     * da gioco, con le pedine in posizione iniziale.
     */
    private void startNewGame() {
        if (getGame() == null) {
            setGame(new Game());
            getGame().setStartingPosition();
            printField(getGame().getGameField());
        }
    }

    /**
     * Gestisce le flag passate come argomenti al programma (tramite CLI).
     * Se viene passata la flag -h o --help, viene stampato l'help del programma.
     * Se viene passata una flag non riconosciuta, viene stampato un messaggio di errore.
     * Se non vengono passate flag, il programma prosegue normalmente.
     */
    private void manageFlag(final String[] args) {
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
    private void manageHelp() {
        Output.printFile(filePath);
    }

    /**
     * Gestisce l'uscita dal gioco.
     */
    private void manageExit() {
        System.out.println("Sicuro di voler uscire? (s/n)"); //spostare in Output
        do {
            String answer = Input.getCommand();
            if (Input.getCommand().equals("s")) {
                setStillPlaying(false);
            }
        } while (!answer.equals("s") || !answer.equals("n"));
    }

    private void leaveGame() {
        System.out.println("Sei sicuro di voler abbandonare la partita? (s/n)"); // spostare in Output
        String answer;
        do {
            answer = Input.getCommand();
            if (answer.equals("s")) {
                setGame(null);
                Player currentPlayer = game.whoIsPlaying();
                Player winner = game.getPlayer((currentPlayer == game.getPlayer(0)) ? 1 : 0);
                int piecesRemaining = game.getGameField().countPieces(winner.getColor());
                System.out.println("Il giocatore " + winner.getName()
                        + " ha vinto per abbandono dell'avversario, il punteggio è " + piecesRemaining + "a 0.");
                System.out.println("");
            } else if (answer.equals("n")) {
                System.out.println("Continua la partita."); // spostare in Output
            } else {
                System.out.println("Errore, inserire 's' per abbandonare o 'n' per annullare."); // Metti in Output
            }
        } while (!answer.equals("s") || !answer.equals("n"));
    }


    /**
     * Gestisce il flusso di esecuzione in base al comando ricevuto.
     * @param args array di argomenti passati da command line.
     */
    public static void ataxxCommand(final String[] args) {
        Ataxx ataxx = new Ataxx();
        ataxx.manageFlag(args);
        do {
            String command = Input.getCommand();
            switch (command) {
                case "/help":
                    ataxx.manageHelp();
                    break;
                case "/gioca":
                    ataxx.startNewGame();
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
                    ataxx.leaveGame();
                    break;
                case "/esci":
                    ataxx.manageExit();
                    break;
                default:
                    System.out.println("Comando sconosciuto");
                    break;
            }
        } while (ataxx.getStillPlaying());
    }
}
