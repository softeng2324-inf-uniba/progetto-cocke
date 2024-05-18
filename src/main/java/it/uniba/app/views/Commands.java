package it.uniba.app.views;
import java.nio.file.Paths;
import it.uniba.app.model.Game;
import it.uniba.app.utils.Output;
import it.uniba.app.controller.GameController;

/**
 * Ataxx è la classe principale del gioco che gestisce l'intero flusso di gioco.
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
                    System.err.println("Flag non riconosciuta: " + arg);
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
     */
    private void manageExit() {
        System.out.println("Sicuro di voler uscire? (s/n)");
        /*if (Input.command().equals("s")) {
            setStillPlaying(false);
        }*/
    }

    /**
     * Gestisce il caso /qualimosse del metodo ataxxCommand.
     */
    private void manageQualimosse(final GameController game) {
        if (game.getGame() == null) {
            System.out.println("Non è stata avviata alcuna partita. '/gioca' per avviare una nuova partita.");
        } else {
            game.legalMoves(game.getGame());
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
        String command = "";  //da eliminare dopo implementazione di getcommand
        do {
            //String command = Input.getCommand();
            switch (command) {
                case "/help":
                    commands.manageHelp();
                    break;
                case "/gioca":
                    ataxx.startNewGame();
                    break;
                case "/vuoto":
                    System.out.println("/vuoto");
                    Output.printEmptyField();
                    break;
                case "/tavoliere":
                    System.out.println("/tavoliere");
                    break;
                case "/qualimosse":
                    System.out.println("/qualimosse");
                    commands.manageQualimosse(ataxx);
                    break;
                case "/abbandona":
                    System.out.println("/abbandona");
                    break;
                case "/esci":
                    commands.manageExit();
                    break;
                default:
                    System.out.println("Comando sconosciuto");
                    break;
            }
        } while (ataxx.getStillPlaying());
    }
}
