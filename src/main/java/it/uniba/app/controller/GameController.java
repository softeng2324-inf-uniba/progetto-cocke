package it.uniba.app.controller;

import it.uniba.app.model.Coordinate;
import it.uniba.app.model.Slot;
import it.uniba.app.utils.Color;
import it.uniba.app.model.Field;
import it.uniba.app.model.Game;
import it.uniba.app.model.Player;
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
            Output.printField(getGame().getGameField());
        }
    }


    /**
     * Inizializza la posizione iniziale delle pedine sul campo da gioco a inizio partita.
     */
    void setStartingPosition(final Game game) {
        int[] tempXY = new int[2];
        tempXY[0] = 0;
        tempXY[1] = Field.DEFAULT_DIM - 1;

        Field tempField = game.getGameField();

        for (int row = 0; row < tempXY.length; row++) {
            for (int column = 0; column < tempXY.length; column++) {
                Coordinate tempCoordinate = new Coordinate(tempXY[row], tempXY[column]);
                if ((row + column) % 2 != 0) {
                    tempField.getSlot(tempCoordinate).setColorState(Color.WHITE);
                } else {
                    tempField.getSlot(tempCoordinate).setColorState(Color.BLACK);
                }
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
                coordinate.setCol(y);
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
                    for (int column = (coordinate.getCol() - distance); column <= (coordinate.getCol() + distance);
                         column++) {
                        if ((((row >= 0) && (row < field.length())) && ((column >= 0) && (column < field.length())))) {
                            markCoordinate.setRow(row);
                            markCoordinate.setCol(column);
                            field.getSlot(markCoordinate).markSlot(distance);
                        }
                    }
                } else {
                    int column = coordinate.getCol() - distance;
                    if ((((row >= 0) && (row < field.length())) && ((column >= 0) && (column < field.length())))) {
                        markCoordinate.setRow(row);
                        markCoordinate.setCol(column);
                        field.getSlot(markCoordinate).markSlot(distance);
                    }
                    column = coordinate.getCol() + distance;
                    if ((((row >= 0) && (row < field.length())) && ((column >= 0) && (column < field.length())))) {
                        markCoordinate.setRow(row);
                        markCoordinate.setCol(column);
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

        //aggiungere questo messaggio nella funzione printMessages
        System.out.println("Sei sicuro di voler abbandonare la partita? (s/n)");

        String answer = "";
        do {
            //answer = Input.getCommand();
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
        } while (!(answer.equals("s") || answer.equals("n")));
    }

}
