package it.uniba.app.views;

import it.uniba.app.model.Field;
import it.uniba.app.model.Game;
import it.uniba.app.utils.Color;
import it.uniba.app.model.Coordinate;
import it.uniba.app.utils.Message;

import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.time.Duration;


/**
 * {@literal <<Boundary>>}
 * Output è la classe che si occupa delle stampe a video.
 * <p>Questa classe gestisce la stampa degli elementi del gioco Ataxx,
 * come la stampa del campo da gioco e dell'interfaccia grafica.</p>
 */
public final class Output {

    /**
     * Stringa contenente il percorso relativo del file Winner.txt.
     */
    private static final String RELATIVE_WINNER_PATH = "/src/main/java/it/uniba/app/Winner.txt";
    /**
     * Workspace attuale.
     */
    private static final String WINNER_PATH = Paths.get(
            System.getProperty("user.dir"), RELATIVE_WINNER_PATH).toString();



    /**
     * Costruttore privato per evitare che la classe Output venga istanziata.
     */
    private Output() { }

    /**
     * Colore di background di default.
     */
    public static final Color DEFAULT_BACKGROUND = Color.GREY;

    /**
     * Colore di background di default.
     */
    public static final Color DEFAULT_CHAR = Color.WHITE;

    /**
     * Stampa le linee interne del campo di gioco.
     */
    private static void printCrossedHoLine() {
        for (int i = 0; i < Field.DEFAULT_DIM - 1; i++) {
            System.out.print("═════╬");
        }
        System.out.print("═════");
    }

    /**
     * Metodo che stampa la riga contenente le lettere che identificano le colonne.
     */
    private static void printLetters() {
        System.out.print("    ║");
        char c = 'A';
        for (int i = 0; i < Field.DEFAULT_DIM; i++) {
            System.out.print("  " + c);
            Output.switchCharColor(DEFAULT_BACKGROUND);
            System.out.print("⛂ ");
            Output.switchCharColor(DEFAULT_CHAR);
            System.out.print("║");
            c++;
        }
        System.out.print("\n");
    }

    /**
     * Metodo che stampa a video la riga num del campo di gioco, eventualmente con le pedine nelle caselle occupate.
     * @param num riga da stampare a video.
     * @param field1 campo di gioco da stampare a video.
     */
    private static void printStuffedLine(final int num, final Field field1) {
        final int limitDim = 10;
        if (num < limitDim) {
            System.out.print("║ " + num + " ║");
        } else {
            System.out.print("║" + "\u2009" + "\u200a" + num + "\u200a" + "\u2009" + "║");
        }
        for (int i = 0; i < Field.DEFAULT_DIM; i++) {
            Coordinate c = new Coordinate(num - 1, i);
            if (field1.getSlot(c).getColorState() == Color.WHITE || field1.getSlot(c).getColorState() == Color.BLACK) {
                switchCharColor(field1.getSlot(c).getColorState());
                System.out.print("  " + "⛂");
                System.out.print(" ");
                switchCharColor(DEFAULT_CHAR);
                System.out.print(" ║");
            } else {
                switchBackgroundColor(field1.getSlot(c).getColorState());
                switchCharColor(field1.getSlot(c).getColorState());
                System.out.print("  ⛂  ");
                switchBackgroundColor(DEFAULT_BACKGROUND);
                switchCharColor(DEFAULT_CHAR);
                System.out.print("║");
            }
        }
        if (num < limitDim) {
            System.out.print(" " + num + " ║");
            System.out.print("\n");
        } else {
            System.out.print("\u2009" + "\u200a" + num + "\u200a" + "\u2009" + "║");
            System.out.print("\n");
        }
    }

    /**
     * Metodo che stampa la riga num del campo di gioco.
     * @param num riga da stampare.
     */
    private static void printNumLine(final int num) {
        final int limitDim = 10;
        if (num < limitDim) {
            System.out.print("║ " + num + " ║");
        } else {
            System.out.print("║" + "\u2009" + "\u200a" + num + "\u200a" + "\u2009" + "║");
        }
        for (int i = 0; i < Field.DEFAULT_DIM; i++) {
            System.out.print("     ║");
        }
        if (num < limitDim) {
            System.out.print(" " + num + " ║");
            System.out.print("\n");
        } else {
            System.out.print("\u2009" + "\u200a" + num + "\u200a" + "\u2009" + "║");
            System.out.print("\n");
        }
    }

    /**
     * Metodo che mostra a video il campo vuoto su cui giocare.
     */
    public static void printEmptyField() {
        printTopField();
        int i;
        for (i = 0; i < Field.DEFAULT_DIM - 1; i++) {
            printNumLine(i + 1);
            System.out.print("╠═══╬");
            printCrossedHoLine();
            System.out.print("╬═══╣");
            System.out.print("\n");
        }
        printNumLine(i + 1);
        printBottomField();
    }

    /**
     * Metodo che stampa la parte superiore del campo.
     */
    private static void printTopField() {
        System.out.print("    ╔");
        for (int i = 0; i < Field.DEFAULT_DIM - 1; i++) {
            System.out.print("═════╦");
        }
        System.out.print("═════╗");
        System.out.print("\n");
        printLetters();

        System.out.print("╔═══╬");
        printCrossedHoLine();
        System.out.print("╬═══╗");
        System.out.print("\n");
    }

    /**
     * Metodo che stampa la parte inferiore del campo.
     */
    private static void printBottomField() {
        System.out.print("╚═══╬");
        printCrossedHoLine();
        System.out.print("╬═══╝");
        System.out.print("\n");
        printLetters();
        System.out.print("    ╚");

        for (int i = 0; i < Field.DEFAULT_DIM - 1; i++) {
            System.out.print("═════╩");
        }
        System.out.println("═════╝");
    }

    /**
     * Metodo che stampa a video il campo di gioco con le pedine nella situazione attuale.
     * @param f campo di gioco da visualizzare.
     */
    public static void printField(final Field f) {
        printTopField();

        int i;
        for (i = 0; i < Field.DEFAULT_DIM - 1; i++) {
            printStuffedLine(i + 1, f);
            System.out.print("╠═══╬");
            printCrossedHoLine();
            System.out.print("╬═══╣");
            System.out.print("\n");
        }
        printStuffedLine(i + 1, f);
        printBottomField();
    }

    /** Prende in input un percorso di un file e ne stampa il suo contenuto.
     * @param filePath percorso del file da stampare,
     */
    public static void printFile(final String filePath) {
        if (new File(filePath).exists()) {
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            Output.printMessages(Message.FILE_NOT_FOUND);
        }
    }

    /**
     * Imposta il colore dei caratteri del terminale.
     * @param color il colore da impostare.
     */
    public static void switchCharColor(final Color color) {
        System.out.printf("\033[38:5:%dm", color.getColorValue());
    }

    /**
     * Imposta il colore di sfondo del terminale.
     * @param color il colore da impostare.
     */
    public static void switchBackgroundColor(final Color color) {
        System.out.printf("\033[48:5:%dm", color.getColorValue());
    }

    /**
     * Stampa i messaggi assieme ad eventuali parametri extra.
     * @param message messaggio che si vuole mostrare.
     * @param extra stringhe da aggiungere al messaggio standard.
     */
    public static void printMessages(final Message message, final String... extra) {
        System.out.print(message.getMessageText());
        for (String string:extra) {
            System.out.print(string);
        }
        System.out.print("\n");
    }

    /**
     * Stampa il file Winner.txt contenente la schermata di vittoria nel fine partita.
     * @param game la partita in corso.
     */
    public static void printWinner(final Game game) {
        if (new File(WINNER_PATH).exists()) {
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(WINNER_PATH), StandardCharsets.UTF_8))) {
                StringBuilder completeTextBuilder = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    completeTextBuilder.append(line).append('\n');
                }
                String completeText = completeTextBuilder.toString();
                Character actualCharacter;
                Output.switchCharColor(game.colorWinner());
                    for (int character = 0; character < completeText.length(); character++) {
                        actualCharacter = completeText.charAt(character);
                        switch (actualCharacter.toString()) {
                            case "#":
                                Output.switchCharColor(Color.BLACK);
                                System.out.print(String.format("%2s", game.countPieces(Color.BLACK)).
                                        replace(' ', '0'));
                                Output.switchCharColor(game.colorWinner());
                                break;
                            case "*":
                                Output.switchCharColor(Color.WHITE);
                                System.out.print(String.format("%2s", game.countPieces(Color.WHITE)).
                                        replace(' ', '0'));
                                Output.switchCharColor(game.colorWinner());
                                break;
                            case "@":
                                String output = getString(game);
                                System.out.print(output);
                                break;
                            case "<":
                                Output.switchCharColor(Color.BLACK);
                                if (game.colorWinner() == Color.BLACK) {
                                    System.out.print("\033[21m");
                                    Output.switchBackgroundColor(Color.GOLD);
                                }
                                break;
                            case ">":
                                Output.switchCharColor(Color.WHITE);
                                if (game.colorWinner() == Color.WHITE) {
                                    System.out.print("\033[21m");
                                    Output.switchBackgroundColor(Color.GOLD);
                                }
                                break;
                            case "+":
                                System.out.print("\033[0m");
                                Output.switchCharColor(game.colorWinner());
                                Output.switchBackgroundColor(DEFAULT_BACKGROUND);
                                break;
                            default:
                                System.out.print(actualCharacter);
                                break;
                        }
                    }
                    Output.switchCharColor(DEFAULT_CHAR);
                    System.out.println();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            Output.printMessages(Message.FILE_NOT_FOUND);
        }
    }

    private static String getString(final Game game) {
        Duration duration = game.getElapsedTime();
        return String.
                format("%2s", duration.toHours()).
                replace(' ', '0')
                + ":" + String.
                        format("%2s", duration.minusHours(duration.toHours()).toMinutes()).
                        replace(' ', '0')
                + ":" + String.
                        format("%2s", duration.minusMinutes(duration.toMinutes()).toSeconds()).
                        replace(' ', '0');
    }

    /**
     * Stampa nel formato ore:minuti:secondi un dato arco temporale.
     * @param elapsedTime l'arco temporale da stampare.
     */
    public static void printElapsedTime(final Duration elapsedTime) {
        String output = elapsedTime.toHours()
                + ":" + elapsedTime.minusHours(elapsedTime.toHours()).toMinutes()
                + ":" + elapsedTime.minusMinutes(elapsedTime.toMinutes()).toSeconds();
        Output.printMessages(Message.ELAPSED_TIME, output);
    }
}

