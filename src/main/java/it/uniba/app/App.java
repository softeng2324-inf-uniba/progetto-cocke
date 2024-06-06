package it.uniba.app;

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

        Output.switchBackgroundColor(Output.DEFAULT_BACKGROUND);
        Output.switchCharColor(Output.DEFAULT_CHAR);
        Commands.ataxxCommand(args);
    }
}
