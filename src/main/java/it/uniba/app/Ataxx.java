package it.uniba.app;
import org.apache.commons.cli;
import java.text.ParseException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;



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
	 * Gestisce il file da stampare a video
	 * @param help file da leggere e stampare a video
	 */
	private void manageHelp(File help) {
		BufferedReader reader = null; //inizializzo a null reader per leggere il file help
		try {
			reader = new BufferedReader(new FileReader(help)); //istanza che punta al file da leggere
			String currentLine = reader.readLine(); //leggo la prima riga del file help e la salvo in currentLine
			while ((currentLine = reader.readLine()) != null) { //stampo tutte le righe del file help fino a che non arriva alla fine
				System.out.println(currentLine);
			}
		}
		catch (IOException e) { //se non riesce a leggere il file help
			System.err.println("Errore durante la lettura del file di help: " + e.getMessage());
		}
		finally { //in ogni caso finisco qui e chiudo il file help per liberare risorse
			try {
				if (reader != null) {
					reader.close();
				}
			}
			catch (IOException e) { //se non riesce a chiudere il file help
				System.err.println("Errore durante la chiusura del file di help: " + e.getMessage());
			}
		}
	}


	/**
	 * Gestisce il flusso di esecuzione in base al comando ricevuto.
	 * @param command il comando da gestire
	 */
	public static void ataxxCommand(String command, String[] args) {
		switch (command){
			case "/help":
				File help = new File(help.txt);
				manageHelp(help);
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
