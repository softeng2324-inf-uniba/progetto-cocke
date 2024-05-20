package it.uniba.app.controller;

import it.uniba.app.model.Coordinate;
import it.uniba.app.model.Slot;
import it.uniba.app.utils.Color;
import it.uniba.app.model.Field;
import it.uniba.app.model.Game;
import it.uniba.app.model.Player;
import it.uniba.app.utils.Message;
import it.uniba.app.views.Input;
import it.uniba.app.views.Output;

/**
 * GameController è la classe che gestisce il gioco.
 */
public class GameController {
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
        tempXY[0] = 0;
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
            //aggiungere questo messaggio nella funzione printMessages
            System.out.println("Sei sicuro di voler abbandonare la partita? (s/n)");

            String answer = "";
            do {
                answer = Input.getCommand();
                if (answer.equals("s")) {
                    Player winner = getGame().nextPlayer();
                    int remainingPieces = getGame().countPieces(winner.getColor());

                    System.out.println("Il giocatore " + winner.getName()
                            + " ha vinto per abbandono dell'avversario, il punteggio è " + remainingPieces + "a 0.");

                    game = null;
                } else if (!answer.equals("n")) {
                    Output.printMessages(Message.BAD_CONFIRMATION_LEAVE);
                }
            } while (!(answer.equals("s") || answer.equals("n")));
        } else {
            Output.printMessages(Message.NO_GAME);
        }
    }

}
