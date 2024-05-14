package it.uniba.app;
import org.apache.commons.cli;
import java.text.ParseException;

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
	 * Gestisce le flag passate come argomenti al programma (tramite CLI).
	 * Se viene passata la flag -h o --help, viene stampato l'help del programma.
	 * Se viene passata una flag non riconosciuta, viene stampato un messaggio di errore.
	 * Se non vengono passate flag, il programma prosegue normalmente.
	 */
	private void manageFlag(){
		Options options = new Options();
        options.addOption("h", "help", false, "Mostra l'aiuto");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("h") || cmd.hasOption("help")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("App", options);
                manageHelp();
                return;
            }

        } catch (ParseException e) {
            System.err.println("Errore durante il parsing degli argomenti: " + e.getMessage());
        }
	}

	/**
	 * Gestisce il flusso di esecuzione in base al comando ricevuto.
	 * @param command il comando da gestire
	 */
	public static void ataxxCommand(String command, String[] args){
		switch (command){
			case "/help":

				break;
			case "/gioca":

				break;
			case "/vuoto":

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
