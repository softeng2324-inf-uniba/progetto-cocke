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
     * Stringa contenente il comando inserito dall'utente.
     */
    private static String command;

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
     * Gestisce la mossa inserita dall'utente.
     * @param game gestisce il flusso di gioco.
     * @return la mossa inserita dall'utente, null se la mossa non è valida.
     */
    private Move manageMove(final GameController game) {
        String[] nextMove = Input.getNextMove(command);
        if (nextMove != null) {
            Coordinate start = new Coordinate(Integer.parseInt(nextMove[0].substring(1)) - 1,
                    nextMove[0].charAt(0) - 'a');
            Coordinate destination = new Coordinate(Integer.parseInt(nextMove[1].substring(1)) - 1,
                    nextMove[1].charAt(0) - 'a');
            return new Move(start, destination);
        }
        return null;
    }

    /**
     * Gestisce il caso /mosse nel metodo ataxxCommand.
     * @param game gestisce il flusso di gioco.
     */

    private void manageMoveHistory(final GameController game) {
        if (game.getGame() == null) {
            Output.printMessages(Message.NO_GAME);
        } else {
            game.moveHistory();
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
            command = Input.getCommand();
            switch (command) {
                case "/help":
                    commands.manageHelp();
                    break;
                case "/gioca":
                    ataxx.startNewGame();
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
                case "/mosse":
                    commands.manageMoveHistory(ataxx);
                    break;
                case "/abbandona":
                    ataxx.leaveGame();
                    break;
                case "/esci":
                    commands.manageExit(ataxx);
                    break;
                default:
                    Move move = commands.manageMove(ataxx);
                    if (move != null && ataxx.getGame() != null) {
                        ataxx.movePiece(move);
                    } else if (move != null) {
                        Output.printMessages(Message.NO_GAME);
                    } else {
                        Output.printMessages(Message.UNKNOWN_COMMAND);
                    }
                    break;
            }
        } while (ataxx.getStillPlaying());
    }
}
