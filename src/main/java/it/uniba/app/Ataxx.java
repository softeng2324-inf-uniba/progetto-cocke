package it.uniba.app;

/**
 * Ataxx è la classe principale del gioco che gestisce l'intero flusso di gioco.
 */
public class Ataxx {
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
		return game;
	}

	/**
	 * Imposta il gioco attualmente in esecuzione.
	 * @param newGame il nuovo gioco da impostare.
	 */
	public void setGame(Game newGame) {
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
	public void setStillPlaying(boolean isStillPlaying) {
		stillPlaying = isStillPlaying;
	}

	/**
	 * Gestisce il flusso di esecuzione in base al comando ricevuto.
	 * @param command il comando da gestire
	 */
	public static void ataxxCommand(String command){
		switch (command){
			case "/help":

				break;
			case "/gioca":

				break;
			case "/vuoto":
				Output.printEmptyField();
				break;
			case "/tavoliere":

				break;
			case "/qualimosse":

				break;
			case "/abbandona":

				break;
			case "/esci":

				break;
		}
	}
}
