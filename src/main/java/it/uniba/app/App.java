package it.uniba.app;

import it.uniba.app.utils.Color;
import it.uniba.app.views.Commands;
import it.uniba.app.views.Output;

/**
 * {@literal <<Boundary>>}
 * Main class of the application.
 */
public final class App {

    private App() { }

    /**
     * Entrypoint of the application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {

        Output.switchBackgroundColor(Color.GREY);
        Output.switchCharColor(Color.WHITE);
        Commands.ataxxCommand(args);
    }
}
