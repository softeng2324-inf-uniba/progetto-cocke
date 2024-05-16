package it.uniba.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Output è la classe che si occupa delle stampe a video.
 * <p>Questa classe gestisce la stampa degli elementi del gioco Ataxx,
 * come la stampa del campo da gioco e dell'interfaccia grafica.</p>
 */
public class Output {
    public static void printFile(final String filePath) {
        if (new File(filePath).exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println("Errore durante la lettura del file: " + e.getMessage());
            }
        } else {
            System.out.println("File non trovato");
        }
    }
}