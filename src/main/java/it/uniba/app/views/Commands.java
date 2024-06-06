package it.uniba.app.views;
import java.nio.file.Paths;
import java.util.Vector;
import it.uniba.app.controller.GameController;
import it.uniba.app.model.Coordinate;
import it.uniba.app.model.Field;
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
     * Dimensione massima consentita per il vettore di coordinate da bloccare.
     */
    private static final int COORDSTOLOCK_DIM = 9;

    /**
     * Lista contenente le coordinate degli slot da bloccare.
     */
    private static Vector<Coordinate> coordsToLock = new Vector<Coordinate>();

    /**
     * Metodo che determina la presenza di una coordinata nella lista coordsToLock.
     * @param c coordinata di cui determinare la presenza nella lista coordsToLock.
     * @return esito del controllo.
     */
    public static boolean isInCoordsToLock(final Coordinate c) {
        boolean isThere = false;
        for (int i = 0; i < coordsToLock.size(); i++) {
           if ((coordsToLock.get(i).getRow() == c.getRow()) && (coordsToLock.get(i).getColumn() == c.getColumn())) {
               isThere = true;
               return isThere;
           }
        }
        return isThere;
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
     * Gestisce il caso /tempo del metodo ataxxCommand.
     * @param game gestisce il flusso di gioco.
     */
    private void manageTime(final GameController game) {
        if (game.getGame() == null) {
            Output.printMessages(Message.NO_GAME);
        } else {
            game.getGame().elapsedTime();
        }
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
     * Gestisce il caso /blocca xn del metodo ataxxCommand.
     * @param s comando inserito dall'utente.
     */
    private void manageBlocca(final String s, final GameController gg) {
        if (gg.getGame() == null) {
            int column = (int) s.charAt(8) - 97;
            int row = (int) s.charAt(9) - 49;
            if ((row >= 0 && row < Field.DEFAULT_DIM) && (column >= 0 && column < Field.DEFAULT_DIM)) {
                int distance = 2;
                boolean a = row >= distance && row <= Field.DEFAULT_DIM - distance;
                boolean b = column >= distance && column <= Field.DEFAULT_DIM - distance;
                if (a || b) {
                    if (coordsToLock.size() < COORDSTOLOCK_DIM) {
                        Coordinate coord = new Coordinate(row, column);
                        System.out.println(isInCoordsToLock(coord));
                        if (!isInCoordsToLock(coord)) {
                            coordsToLock.add(coord);
                        } else {
                            Output.printMessages(Message.CANTDO, "casella già bloccata.");
                            return;
                        }
                    } else {
                        Output.printMessages(Message.CANTDO, "numero massimo di slot bloccabili raggiunto.");
                        return;
                    }
                } else {
                    String msg = "lo slot scelto si trova entro una distanza due da quelli di partenza.";
                    Output.printMessages(Message.CANTDO, msg);
                    return;
                }
            } else {
                Output.printMessages(Message.COORD_ERR, "");
                return;
            }
        } else {
            Output.printMessages(Message.GAME_IS_PLAYING, "");
            return;
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
                case "/tempo":
                    commands.manageTime(ataxx);
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
                    } else if (command.startsWith("/blocca ") && command.length() == 10) {
                        commands.manageBlocca(command, ataxx);
                    } else {
                        Output.printMessages(Message.UNKNOWN_COMMAND);
                    }
                    break;
            }
        } while (ataxx.getStillPlaying());
    }
}
