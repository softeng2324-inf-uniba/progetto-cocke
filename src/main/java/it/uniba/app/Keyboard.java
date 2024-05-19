package it.uniba.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Utility class per la lettura di diversi tipi di dati da tastiera.
 */
final class Keyboard {
	/**
	 * Costruttore per la classe Keyboard.
	 */
	private Keyboard() { }

	// Sezione per la gestione degli errori.

	private static boolean printErrors = true;

	private static int errorCount = 0;

	/**
	 * Incrementa il counter degli errori e stampa il messaggio di errore se appropriato.
	 * @param str il messaggio di errore da stampare.
	 */
	private static void error(final String str) {
		errorCount++;
		if (printErrors) {
			Output.printMessages(9, str);
		}
	}

	/**
	 * Restituisce il numero corrente di errori.
	 * @return errorCount il numero corrente di errori.
	 */
	public static int getErrorCount() {
		return errorCount;
	}

	/**
	 * Resetta il counter degli errori a zero.
	 * @param count il valore a cui resettare il counter.
	 */
	public static void resetErrorCount(final int count) {
		errorCount = 0;
	}

	/**
	 * Restituisce un booleano che indica se gli errori di input vengono attualmenti stampati in output.
	 * @return printErrors true se gli errori di input vengono attualmente stampati in output, false altrimenti.
	 */
	public static boolean getPrintErrors() {
		return printErrors;
	}

	/**
	 * Imposta un booleano che indica se gli errori di input devono essere stampati in output.
	 * @param flag true se gli errori di input devono essere stampati in output, false altrimenti.
	 */
	public static void setPrintErrors(final boolean flag) {
		printErrors = flag;
	}

	// Sezione per la lettura di input da tastiera.

	/**
	 * Contiene il token corrente.
	 */
	private static String currentToken = null;

	/**
	 * Contiene i token da leggere.
	 */
	private static StringTokenizer reader;

	/**
	 * Contiene l'input da tastiera.
	 */
	private static BufferedReader in = new BufferedReader(
			new InputStreamReader(System.in));


	/**
	 * Restituisce il prossimo token di input senza saltare le linee vuote.
	 */
	private static String getNextToken() {
		return getNextToken(true);
	}

	/**
	 * Restituisce il prossimo token di input derivato dalla funzione getNextInputToken.
	 * @param skip true se vogliamo saltare le linee vuote, false altrimenti.
	 * @return token il prossimo token di input.
	 */
	private static String getNextToken(final boolean skip) {
		String token;

		if (currentToken == null) {
			token = getNextInputToken(skip);
		} else {
			token = currentToken;
			currentToken = null;
		}
		return token;
	}

	/**
	 * Restituisce il prossimo token di input.
	 * @param skip true se vogliamo saltare le linee vuote, false altrimenti.
	 * @return token il prossimo token di input.
	 */
	private static String getNextInputToken(final boolean skip) {
		final String delimiters = " \t\n\r\f";
		String token = null;

		try {
			if (reader == null) {
				reader = new StringTokenizer(in.readLine(), delimiters, true);
			}
			while (token == null || ((delimiters.indexOf(token) >= 0) && skip)) {
				while (!reader.hasMoreTokens()) {
					reader = new StringTokenizer(in.readLine(), delimiters,
							true);
				}
				token = reader.nextToken();
			}
		} catch (Exception exception) {
			token = null;
		}

		return token;
	}

	/**
	 * Restituisce true se non ci sono più token da leggere sulla linea di input corrente.
	 */
	public static boolean endOfLine() {
		return !reader.hasMoreTokens();
	}

	// Sezione per la lettura di diversi tipi di dati da tastiera.

	/**
	 * Legge e restituisce un booleano da tastiera.
	 * @return bool il booleano letto da tastiera.
	 */
	public static boolean readBoolean() {
		String token = getNextToken();
		boolean bool;
		try {
			if (token.toLowerCase().equals("true")) {
				bool = true;
			} else if (token.toLowerCase().equals("false")) {
				bool = false;
			} else {
				error("Error reading boolean data, false value returned.");
				bool = false;
			}
		} catch (Exception exception) {
			error("Error reading boolean data, false value returned.");
			bool = false;
		}
		return bool;
	}

	/**
	 * Legge e restituisce un intero da tastiera.
	 * @return value l'intero letto da tastiera.
	 */
	public static int readInt() {
		String token = getNextToken();
		int value;
		try {
			value = Integer.parseInt(token);
		} catch (Exception exception) {
			error("Error reading int data, MIN_VALUE value returned.");
			value = Integer.MIN_VALUE;
		}
		return value;
	}

	/**
	 * Legge e restituisce un long da tastiera.
	 * @return value il long letto da tastiera.
	 */
	public static long readLong() {
		String token = getNextToken();
		long value;
		try {
			value = Long.parseLong(token);
		} catch (Exception exception) {
			error("Error reading long data, MIN_VALUE value returned.");
			value = Long.MIN_VALUE;
		}
		return value;
	}

	/**
	 * Legge e restituisce un float da tastiera.
	 * @return value il float letto da tastiera.
	 */
	public static float readFloat() {
		String token = getNextToken();
		float value;
		try {
			value = Float.parseFloat(token);
		} catch (Exception exception) {
			error("Error reading float data, NaN value returned.");
			value = Float.NaN;
		}
		return value;
	}

	/**
	 * Legge e restituisce un double da tastiera.
	 * @return value il double letto da tastiera.
	 */
	public static double readDouble() {
		String token = getNextToken();
		double value;
		try {
			value = Double.parseDouble(token);
		} catch (Exception exception) {
			error("Error reading double data, NaN value returned.");
			value = Double.NaN;
		}
		return value;
	}

	/**
	 * Legge e restituisce un carattere da tastiera.
	 * @return value il carattere letto da tastiera.
	 */
	public static char readChar() {
		String token = getNextToken(false);
		char value = '_';
		try {
			if (token.length() > 1) {
				currentToken = token.substring(1, token.length());
			} else {
				currentToken = null;
				value = token.charAt(0);
			}
		} catch (Exception exception) {
			error("Error reading char data, MIN_VALUE value returned.");
			value = Character.MIN_VALUE;
		}

		return value;
	}

	/**
	 * Legge e restituisce una parola da tastiera.
	 * @return word la parola letta da tastiera.
	 */
	public static String readWord() {
		String word;
		try {
			word = getNextToken();
		} catch (Exception exception) {
			error("Error reading String data, null value returned.");
			word = null;
		}
		return word;
	}

	/**
	 * Legge e restituisce una stringa da tastiera.
	 * @return str la stringa letta da tastiera.
	 */
	public static String readString() {
		String str;

		try {
			str = getNextToken(false);
			while (!endOfLine()) {
				str = str + getNextToken(false);
			}
		} catch (Exception exception) {
			error("Error reading String data, null value returned.");
			str = null;
		}
		return str;
	}

}
