package it.uniba.app.controller;

import it.uniba.app.model.Coordinate;
import it.uniba.app.model.Field;
import it.uniba.app.model.Game;
import it.uniba.app.model.Move;
import it.uniba.app.model.Player;
import it.uniba.app.model.Slot;
import it.uniba.app.utils.Color;
import it.uniba.app.utils.Message;
import it.uniba.app.views.Input;
import it.uniba.app.views.Output;
import java.util.ArrayList;

/**
 * {@literal <<Control>>}
 * GameController è la classe che gestisce il gioco.
 */
public class GameController {
    /**
     * Distanza massima tra le coordinate delle caselle coinvolte in una mossa.
     */
    static final int MAX_DISTANCE = 2;

    /**
     * Rappresenta il gioco attualmente in esecuzione.
     */
    private Game game = null;

    /**
     * Indica se il gioco è ancora in esecuzione.
     */
    private boolean stillPlaying = true;

    /**
     * Restituisce il gioco attualmente in esecuzione.
     * @return il gioco attualmente in esecuzione.
     */
    public Game getGame() {
        if (game == null) {
            return null;
        }
        return new Game(game);
    }

    /**
     * Imposta il gioco attualmente in esecuzione.
     * @param newGame il nuovo gioco da impostare.
     */
    public void setGame(final Game newGame) {
        game = new Game(newGame);
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
    public void startNewGame() {
        if (game == null) {
            setGame(new Game());
            setStartingPosition();
            Output.printField(getGame().getGameField());
        }
    }


    /**
     * Inizializza la posizione iniziale delle pedine sul campo da gioco a inizio partita.
     */
    void setStartingPosition() {
        int[] tempXY = new int[2];
        tempXY[1] = Field.DEFAULT_DIM - 1;

        Game tempGame = getGame();
        Field tempField = tempGame.getGameField();
        Coordinate tempCoordinate;

        for (int row = 0; row < tempXY.length; row++) {
            for (int column = 0; column < tempXY.length; column++) {
                tempCoordinate = new Coordinate(tempXY[row], tempXY[column]);
                if ((row + column) % 2 != 0) {
                    tempField.getSlot(tempCoordinate).setColorState(Color.WHITE);
                } else {
                    tempField.getSlot(tempCoordinate).setColorState(Color.BLACK);
                }
            }
        }
        tempGame.setGameField(tempField);
        setGame(tempGame);
    }

    /**
     * Stampa il campo con le mosse consentite per il giocatore di turno.
     * <p>Il metodo stampa:
     * <ul>
     *     <li>in giallo le caselle in cui è possibile effettuare duplicarsi;</li>
     *     <li>in arancione le caselle in cui è possibile spostarsi;</li>
     *     <li>in rosa le caselle in cui è possibile effettuare entrambe le azioni.</li>
     * </ul>
     */
    public void legalMoves() {
        Field legalMovesField = new Field(game.getGameField());
        convertField(legalMovesField, game.whoIsPlaying().getColor());
        Output.printField(legalMovesField);
    }

    /**
     * Stampa a video il log delle mosse effettuate fino ad ora.
     */
    public void moveHistory() {
        if (game.getMoveList().isEmpty()) {
            Output.printMessages(Message.NO_MOVES);
        } else {
            StringBuilder moveList = new StringBuilder();
            int i = 1;
            for (Move move : game.getMoveList()) {
                int playerColor = i % 2;
                moveList.append(i).append(". ").append(move).append(" ");
                if (playerColor == 0) {
                    moveList.append("(B); ");
                } else {
                    moveList.append("(N); ");
                }
                i++;
            }
            Output.printMessages(Message.MOVE_LIST, moveList.toString());
        }
    }

    /**
     * Converte il campo per mostrare le mosse consentite al giocatore di turno.
     * <p>Il <code>field</code> passato come parametro
     * è convertito in un campo con le caselle colorate a seconda delle mosse consentite.
     * @param field il campo da convertire.
     * @param playerColor il colore del giocatore di cui mostrare le mosse.
     */
    void convertField(final Field field, final Color playerColor) {
        Coordinate coordinate = new Coordinate(0, 0);
        Slot currentSlot;
        for (int x = 0; x < field.length(); x++) {
            for (int y = 0; y < field.length(); y++) {
                coordinate.setRow(x);
                coordinate.setColumn(y);
                currentSlot = field.getSlot(coordinate);
                if (currentSlot.getColorState() == playerColor) {
                    markNeighboringSlot(field, coordinate);
                }
            }
        }
    }


    /**
     * Evidenzia le caselle adiacenti alla pedina.
     * @param field il campo in cui evidenziare le caselle.
     * @param coordinate la posizione della casella da cui evidenziare le caselle.
     */
    void markNeighboringSlot(final Field field, final Coordinate coordinate) {
        Coordinate markCoordinate = new Coordinate(0, 0);
        for (int distance = 1; distance <= 2; distance++) {
            for (int row = (coordinate.getRow() - distance); row <= (coordinate.getRow() + distance); row++) {
                if ((row == (coordinate.getRow() - distance)) || (row == (coordinate.getRow() + distance))) {
                    for (int column = (coordinate.getColumn() - distance);
                         column <= (coordinate.getColumn() + distance);
                         column++) {
                        if ((((row >= 0) && (row < field.length())) && ((column >= 0) && (column < field.length())))) {
                            markCoordinate.setRow(row);
                            markCoordinate.setColumn(column);
                            field.getSlot(markCoordinate).markSlot(distance);
                        }
                    }
                } else {
                    int column = coordinate.getColumn() - distance;
                    if ((((row >= 0) && (row < field.length())) && ((column >= 0) && (column < field.length())))) {
                        markCoordinate.setRow(row);
                        markCoordinate.setColumn(column);
                        field.getSlot(markCoordinate).markSlot(distance);
                    }
                    column = coordinate.getColumn() + distance;
                    if ((((row >= 0) && (row < field.length())) && ((column >= 0) && (column < field.length())))) {
                        markCoordinate.setRow(row);
                        markCoordinate.setColumn(column);
                        field.getSlot(markCoordinate).markSlot(distance);
                    }
                }
            }
        }
    }

    /**
     * Gestisce l'uscita dalla partita.
     */
    public void leaveGame() {
        if (getGame() != null) {
            Output.printMessages(Message.CONFIRM_ABANDONMENT);

            String answer;
            do {
                answer = Input.getCommand();
                if (answer.equals("s")) {
                    Player winner = getGame().nextPlayer();
                    int remainingPieces = getGame().countPieces(winner.getColor());

                    Output.printMessages(Message.WINNER_PLAYER, winner.getName(),
                            Message.PLAYER_WON.getMessageText(), Integer.toString(remainingPieces),
                            Message.SCORE_0.getMessageText());

                    game = null;
                } else if (!answer.equals("n")) {
                    Output.printMessages(Message.BAD_CONFIRMATION_LEAVE);
                }
            } while (!(answer.equals("s") || answer.equals("n")));
        } else {
            Output.printMessages(Message.NO_GAME);
        }
    }

    /**
     * Controlla se è il colore della casella di partenza del giocatore che deve effettuare il turno.
     * @param startSlot casella di partenza
     * @return (true) se il colore della casella di partenza è corretto, (false) altrimenti.
     */
    private boolean checkStartSlot(final Slot startSlot) {
        return startSlot.getColorState() == game.whoIsPlaying().getColor();
    }

    /**
     * Controlla se la casella di destinazione è una casella vuota.
     * @param destinationSlot casella di destinazione
     * @return (true) se la casella di destinazione è vuota, (false) altrimenti.
     */
    private boolean checkDestinationSlot(final Slot destinationSlot) {
        return destinationSlot.getColorState() == Color.GREY;
    }

    /**
     * Gestisce la mossa del giocatore.
     * @param move la mossa da effettuare.
     */
    public void movePiece(final Move move) {
        Field tempField = new Field(game.getGameField());
        ArrayList<Move> tempMoveList = game.getMoveList();
        Slot startSlot = tempField.getSlot(move.getStartingSlot());
        Slot destinationSlot = tempField.getSlot(move.getChosenSlot());
        if (startSlot != null && destinationSlot != null && destinationSlot.getColorState() != Color.DARK_GREY) {
            int distance = move.getDistance();
            if (checkStartSlot(startSlot) && checkDestinationSlot(destinationSlot) && checkDistance(distance)) {
                if (distance == MAX_DISTANCE) {
                    startSlot.setColorState(Color.GREY);
                }
                destinationSlot.setColorState(game.whoIsPlaying().getColor());
                game.setGameField(tempField);
                game.captureSlot(move.getChosenSlot());
                Output.printField(game.getGameField());
                tempMoveList.add(move);
                game.setMoveList(tempMoveList);
                checkTurn();
            } else {
                Output.printField(game.getGameField());
                Output.printMessages(Message.ILLEGAL_MOVE);
            }
        } else {
            Output.printField(game.getGameField());
            Output.printMessages(Message.ILLEGAL_MOVE);
        }
    }

    /**
     * Verifica la correttezza della distanza tra le coordinate delle caselle coinvolte in una mossa.
     * @param distance distanza tra le caselle sul campo da gioco.
     * @return (true) se la distanza è consentita, (false) in caso contrario.
     */
    boolean checkDistance(final int distance) {
        return distance > 0 && distance <= MAX_DISTANCE;
    }

    /**
     * Verifica che il giocatore di turno abbia mosse disponibili.
     *
     */
    public void checkTurn() {
        Player currentPlayer = game.whoIsPlaying();
        if (!isLegalMoves(currentPlayer)) {
            Move emptyMove = new Move(new Coordinate(0, 0), new Coordinate(0, 0));
            Output.printMessages(Message.UNAVAILABLE_MOVE,
                    currentPlayer.getName(), ". ", Message.PASS_TURN.getMessageText());
            ArrayList<Move> tempMoveList = getGame().getMoveList();
            tempMoveList.add(emptyMove);
            game.setMoveList(tempMoveList);
            if (containsEmptyMove(tempMoveList)) {
                Output.printMessages(Message.UNAVAILABLE_MOVE, "entrambi i giocatori.");
                Output.printWinner(game);
                game = null;
            }
        }
    }
    /**
     * Verifica che non sia stata fatta una mossa vuota in precedenza.
     *
     *  @param moveList la lista delle mosse effettuate nel gioco
     *  @return true se l'ultima mossa nella lista è una mossa vuota,altrimenti false
     */
    public boolean containsEmptyMove(final ArrayList<Move> moveList) {
        Coordinate emptyCoordinate = new Coordinate(0, 0);
        Move lastMove = moveList.get(moveList.size() - 1);
        return lastMove.getStartingSlot().equalsCoordinate(emptyCoordinate)
                && lastMove.getChosenSlot().equalsCoordinate(emptyCoordinate);
    }

    /**
     * Verifica che ci siano mosse disponibili per l'utente.
     *
     * @param player il giocatore per il quale verificare la disponibilità di mosse.
     * @return true se ci sono mosse legali disponibili per il giocatore, false altrimenti.
     */
    private boolean isLegalMoves(final Player player) {
        Field field = getGame().getGameField();
        convertField(field, player.getColor());
        Coordinate coordinate = new Coordinate(0, 0);
        for (int row = 0; row < field.length(); row++) {
            for (int column = 0; column < field.length(); column++) {
                coordinate.setRow(row);
                coordinate.setColumn(column);
                Slot currentSlot = field.getSlot(coordinate);
               if (currentSlot.getColorState() == Color.YELLOW
                        || currentSlot.getColorState() == Color.ORANGE || currentSlot.getColorState() == Color.PINK) {
                    return true;
                }
            }
        }
        return false;
    }
}
