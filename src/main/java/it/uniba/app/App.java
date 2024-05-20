package it.uniba.app;

import it.uniba.app.utils.Color;
import it.uniba.app.views.Commands;
import it.uniba.app.views.Output;

/**
 * {@literal <<Boundary>>}
 * Main class of the application.
 */
public final class App {

    /**
     * Get a greeting sentence.
     *
     * @return the "Hello World!" string.
     */
    public String getGreeting() {
        return "Hello World!!!";
    }

    /**
     * Entrypoint of the application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        System.out.println(new App().getGreeting());

        Output.switchBackgroundColor(Color.GREY);
        Output.switchCharColor(Color.WHITE);
        Commands terminal = new Commands();
        terminal.ataxxCommand(args);
    }
}
