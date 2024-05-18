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
            Output.printField(getGame().getGameField());
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
                    Output.printMessages(3, arg);
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
        Output.printMessages(2, "");
        String answer;
        do {
            answer = Input.getCommand();
            if (answer.equals("s")) {
                setStillPlaying(false);
            } else if (!answer.equals("n")) {

                //aggiungere questo messaggio nella funzione printMessages
                System.out.println("Errore! Inserire 's' per uscire, 'n' per annullare.");

            }
        } while ( !(answer.equals("s") || answer.equals("n")) );
    }

    /**
     * Gestisce l'uscita dalla partita.
     */
    private void leaveGame() {

        //aggiungere questo messaggio nella funzione printMessages
        System.out.println("Sei sicuro di voler abbandonare la partita? (s/n)");

        String answer;
        do {
            answer = Input.getCommand();
            if (answer.equals("s")) {
                Player winner = getGame().nextPlayer(); //creare il metodo nextPlayer in Game, che restituisce il giocatore successivo a quello attuale
                int remainingPieces = getGame().countPieces(winner.getColor());

                //aggiungere questo messaggio nella funzione printMessages
                System.out.println("Il giocatore " + winner.getName() + " ha vinto per abbandono dell'avversario, il punteggio è " + remainingPieces + "a 0.");

                setGame(null);
            } else if (!answer.equals("n")) {

                //aggiungere questo messaggio nella funzione printMessages
                System.out.println("Errore, inserire 's' per abbandonare o 'n' per annullare.");

            }
        } while ( !(answer.equals("s") || answer.equals("n")) );
    }


    /**
     * Gestisce il caso /qualimosse del metodo ataxxCommand.
     */
    private void manageQualiMosse() {
        if (getGame() == null) {
            Output.printMessages(1, "");
        } else {
            getGame().legalMoves();
        }
    }
    /**
     * Gestisce il caso /tavoliere del metodo ataxxCommand.
     */
    private void manageTavoliere() {
        if (getGame() == null) {
            Output.printMessages(1, "");
        } else {
            Output.printField(getGame().getGameField());
        }
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
                    Output.printEmptyField();
                    break;
                case "/tavoliere":
                    ataxx.manageTavoliere();
                    break;
                case "/qualimosse":
                    ataxx.manageQualiMosse();
                    break;
                case "/abbandona":
                    ataxx.leaveGame();
                    break;
                case "/esci":
                    ataxx.manageExit();
                    break;
                default:
                    Output.printMessages(4, "");
                    break;
            }
        } while (ataxx.getStillPlaying());
    }
}
