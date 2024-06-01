package it.uniba.app.views;
import java.nio.file.Paths;
import it.uniba.app.controller.GameController;
import it.uniba.app.model.Coordinate;
import it.uniba.app.model.Move;
import it.uniba.app.utils.Message;

/**
 * {@literal <<noECB>>}
 * Commands è la classe principale del gioco che gestisce l'intero flusso di gioco.
 */
public class Commands {
    /**
     * Stringa contenente il percorso relativo del file da leggere.
     */
    private static String relativePath = "/src/main/java/it/uniba/app/help.txt";
    /**
     * Workspace attuale.
     */
    private static String filePath = Paths.get(System.getProperty("user.dir"), relativePath).toString();

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
                    Output.printMessages(Message.UNKNOWN_FLAG, arg);
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
     * @param game gestisce il flusso di gioco.
     */
    private void manageExit(final GameController game) {
        Output.printMessages(Message.CONFIRM_EXIT);
        String answer = "";
        do {
            answer = Input.getCommand();
            if (answer.equals("s")) {
                game.setStillPlaying(false);
            } else if (!answer.equals("n")) {

                //aggiungere questo messaggio nella funzione printMessages
                Output.printMessages(Message.BAD_CONFIRMATION_EXIT);

            }
        } while (!(answer.equals("s") || answer.equals("n")));
    }

    /**
     * Gestisce il caso /qualimosse del metodo ataxxCommand.
     * @param game gestisce il flusso di gioco.
     */
    private void manageLegalMoves(final GameController game) {
        if (game.getGame() == null) {
            Output.printMessages(Message.NO_GAME);
        } else {
            game.legalMoves();
        }
    }
    /**
     * Gestisce il caso /tavoliere del metodo ataxxCommand.
     * @param game gestisce il flusso di gioco.
     */
    private void manageGameField(final GameController game) {
        if (game.getGame() == null) {
            Output.printMessages(Message.NO_GAME);
        } else {
            Output.printField(game.getGame().getGameField());
        }
    }

    /**
     * Controlla l'indice della riga e lo trasforma in un intero.
     * @param row la riga da controllare.
     * @return
     */
    private static int checkRowIndex(String row) {
        switch (row.charAt(0)) {
            case 'a':
                return 0;
            case 'b':
                return 1;
            case 'c':
                return 2;
            case 'd':
                return 3;
            case 'e':
                return 4;
            case 'f':
                return 5;
            case 'g':
                return 6;
            case 'h':
                return 7;
            default:
                return -1;
        }
    }

    /**
     * Gestisce il caso /mossa del metodo ataxxCommand.
     * @param game gestisce il flusso di gioco.
     */
    private void manageMove(final GameController game) {
        if (game.getGame() == null) {
            Output.printMessages(Message.NO_GAME);
        } else {
            String[] nextMove = Input.getNextMove();
            Coordinate start = new Coordinate(checkRowIndex(nextMove[0].toLowerCase()), Integer.parseInt(nextMove[0].substring(1)));
            Coordinate destination = new Coordinate(checkRowIndex(nextMove[1].toLowerCase()), Integer.parseInt(nextMove[1].substring(1)));
            game.movePiece(start, destination);
        }
    }

    /**
     * Gestisce il flusso di esecuzione in base al comando ricevuto.
     * @param args array di argomenti passati da command line.
     */
    public static void ataxxCommand(final String[] args) {
        Commands commands = new Commands();
        GameController ataxx = new GameController();
        commands.manageFlag(args);
        do {
            String command = Input.getCommand();
            switch (command) {
                case "/help":
                    commands.manageHelp();
                    break;
                case "/gioca":
                    ataxx.startNewGame();
                    break;
                case "/mossa":
                    commands.manageMove(ataxx);
                    break;
                case "/vuoto":
                    Output.printEmptyField();
                    break;
                case "/tavoliere":
                    commands.manageGameField(ataxx);
                    break;
                case "/qualimosse":
                    commands.manageLegalMoves(ataxx);
                    break;
                case "/abbandona":
                    ataxx.leaveGame();
                    break;
                case "/esci":
                    commands.manageExit(ataxx);
                    break;
                default:
                    Output.printMessages(Message.UNKNOWN_COMMAND);
                    break;
            }
        } while (ataxx.getStillPlaying());
    }
}
