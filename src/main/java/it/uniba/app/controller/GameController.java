package it.uniba.app.controller;

import it.uniba.app.model.Coordinate;
import it.uniba.app.model.Slot;
import it.uniba.app.utils.Color;
import it.uniba.app.model.Field;
import it.uniba.app.model.Game;

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
    public void startNewGame() {
        if (game == null) {
            setGame(new Game());
            setStartingPosition(getGame());
            //printField(getGame().getGameField());
        }
    }

     /**
     * Inizializza la posizione iniziale delle pedine sul campo da gioco a inizio partita.
     */
    static public void setStartingPosition(final Game game) {
        int[] tempXY = new int[2];
        tempXY[0] = 0;
        tempXY[1] = Field.DEFAULT_DIM - 1;

        Field tempField = game.getGameField();

        Slot tempSlot = new Slot();

        for (int x = 0; x < tempXY.length; x++) {

            for (int y = 0; y < tempXY.length; y++) {

                Coordinate tempCoordinate = new Coordinate(tempXY[x], tempXY[y]);

                if ((x + y) % 2 != 0) {
                    tempSlot.setColorState(Color.BIANCO);
                } else {
                    tempSlot.setColorState(Color.NERO);
                }

                tempField.setSlot(tempCoordinate, tempSlot);

            }

        }

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
    public void legalMoves(final Game game) {
        Field legalMovesField = game.getGameField();
        convertField(legalMovesField, game.whoIsPlaying().getColor());
        //Output.printField(legalMovesField);
    }

    /**
     * Converte il campo per mostrare le mosse consentite al giocatore di turno.
     * <p>Il <code>field</code> passato come parametro
     * è convertito in un campo con le caselle colorate a seconda delle mosse consentite.
     * @param field il campo da convertire.
     * @param playerColor il colore del giocatore di cui mostrare le mosse.
     */
    private void convertField(final Field field, final Color playerColor) {
        Coordinate coordinate = new Coordinate(0, 0);
        Slot currentSlot = null;
        for (int x = 0; x < field.length(); x++) {
            for (int y = 0; y < field.length(); y++) {
                coordinate.setX(x);
                coordinate.setY(y);
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
    private void markNeighboringSlot(final Field field, final Coordinate coordinate) {
        Coordinate markCoordinate = new Coordinate(0, 0);
        for (int distance = 1; distance <= 2; distance++) {
            for (int row = (coordinate.getX() - distance); row <= (coordinate.getX() + distance); row++) {
                if ((row == (coordinate.getX() - distance)) || (row == (coordinate.getX() + distance))) {
                    for (int column = (coordinate.getY() - distance); column <= (coordinate.getY() + distance);
                         column++) {
                        if ((((row >= 0) && (row < field.length())) && ((column >= 0) && (column < field.length())))) {
                            markCoordinate.setX(row);
                            markCoordinate.setY(column);
                            field.getSlot(markCoordinate).markSlot(distance);
                        }
                    }
                } else {
                    int column = coordinate.getY() - distance;
                    if ((((row >= 0) && (row < field.length())) && ((column >= 0) && (column < field.length())))) {
                        markCoordinate.setX(row);
                        markCoordinate.setY(column);
                        field.getSlot(markCoordinate).markSlot(distance);
                    }
                    column = coordinate.getY() + distance;
                    if ((((row >= 0) && (row < field.length())) && ((column >= 0) && (column < field.length())))) {
                        markCoordinate.setX(row);
                        markCoordinate.setY(column);
                        field.getSlot(markCoordinate).markSlot(distance);
                    }
                }
            }
        }
    }
}
