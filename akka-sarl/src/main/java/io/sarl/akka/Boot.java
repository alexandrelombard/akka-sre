package io.sarl.akka;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.google.common.base.Strings;
import io.sarl.lang.core.Agent;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Iterator;

/**
 * This is the class that permits to boot the Akka SRE platform.
 *
 * <p>This class provides the "main" function for the platform. The list of the parameters is composed of a list of options, the
 * classname of an agent to launch, and the parameters to pass to the launched agent.
 *
 * @author Alexandre Lombard <alexandre.lombard@utbm.fr>
 */
public class Boot {

    /** Short command-line option for "embedded".
     */
    public static final String CLI_OPTION_EMBEDDED_SHORT = "e"; //$NON-NLS-1$

    /** Long command-line option for "embedded".
     */
    public static final String CLI_OPTION_EMBEDDED_LONG = "embedded"; //$NON-NLS-1$

    /** Short command-line option for "boot agent id".
     */
    public static final String CLI_OPTION_BOOTID_SHORT = "B"; //$NON-NLS-1$

    /** Long command-line option for "boot agent id".
     */
    public static final String CLI_OPTION_BOOTID_LONG = "bootid"; //$NON-NLS-1$

    /** Short command-line option for "random id".
     */
    public static final String CLI_OPTION_RANDOMID_SHORT = "R"; //$NON-NLS-1$

    /** Long command-line option for "random id".
     */
    public static final String CLI_OPTION_RANDOMID_LONG = "randomid"; //$NON-NLS-1$

    /** Short command-line option for "Janus world id".
     */
    public static final String CLI_OPTION_WORLDID_SHORT = "W"; //$NON-NLS-1$

    /** Long command-line option for "Janus world id".
     */
    public static final String CLI_OPTION_WORLDID_LONG = "worldid"; //$NON-NLS-1$

    /** Short command-line option for "file".
     */
    public static final String CLI_OPTION_FILE_SHORT = "f"; //$NON-NLS-1$

    /** Long command-line option for "file".
     */
    public static final String CLI_OPTION_FILE_LONG = "file"; //$NON-NLS-1$

    /** Short command-line option for "help".
     */
    public static final String CLI_OPTION_HELP_SHORT = "h"; //$NON-NLS-1$

    /** Long command-line option for "help".
     */
    public static final String CLI_OPTION_HELP_LONG = "help"; //$NON-NLS-1$

    /** Long command-line option for "nologo".
     */
    public static final String CLI_OPTION_NOLOGO_LONG = "nologo"; //$NON-NLS-1$

    /** Short command-line option for "offline".
     */
    public static final String CLI_OPTION_OFFLINE_SHORT = "o"; //$NON-NLS-1$

    /** Long command-line option for "offline".
     */
    public static final String CLI_OPTION_OFFLINE_LONG = "offline"; //$NON-NLS-1$

    /** Short command-line option for "be quiet".
     */
    public static final String CLI_OPTION_QUIET_SHORT = "q"; //$NON-NLS-1$

    /** Long command-line option for "be quiet".
     */
    public static final String CLI_OPTION_QUIET_LONG = "quiet"; //$NON-NLS-1$

    /** Short command-line option for "be more verbose".
     */
    public static final String CLI_OPTION_VERBOSE_SHORT = "v"; //$NON-NLS-1$

    /** Long command-line option for "be more verbose".
     */
    public static final String CLI_OPTION_VERBOSE_LONG = "verbose"; //$NON-NLS-1$

    /** Long command-line option for "display the version".
     */
    public static final String CLI_OPTION_VERSION = "version"; //$NON-NLS-1$

    /** Short command-line option for "change log level".
     */
    public static final String CLI_OPTION_LOG_SHORT = "l"; //$NON-NLS-1$

    /** Long command-line option for "change log level".
     */
    public static final String CLI_OPTION_LOG_LONG = "log"; //$NON-NLS-1$

    /** Short command-line option for "env. variable definition".
     */
    public static final String CLI_OPTION_DEFINE_SHORT = "D"; //$NON-NLS-1$

    /** Long command-line option for "env. variable definition".
     */
    public static final String CLI_OPTION_DEFINE_LONG = "define"; //$NON-NLS-1$

    /** Short command-line option for "show defaults".
     */
    public static final String CLI_OPTION_SHOWDEFAULTS_SHORT = "s"; //$NON-NLS-1$

    /** Long command-line option for "show defaults".
     */
    public static final String CLI_OPTION_SHOWDEFAULTS_LONG = "showdefaults"; //$NON-NLS-1$

    /** Short command-line option for "show the classpath".
     */
    public static final String CLI_OPTION_SHOWCLASSPATH = "showclasspath"; //$NON-NLS-1$

    /** Short command-line option for "show CLI arguments".
     */
    public static final String CLI_OPTION_SHOWCLIARGUMENTS_LONG = "cli"; //$NON-NLS-1$

    /** Short command-line option for "classpath".
     */
    public static final String CLI_OPTION_CLASSPATH_SHORT = "cp"; //$NON-NLS-1$

    /** Long command-line option for "classpath".
     */
    public static final String CLI_OPTION_CLASSPATH_LONG = "classpath"; //$NON-NLS-1$

    private static final int ERROR_EXIT_CODE = 255;

    private static PrintStream consoleLogger;

    /**
     * Parse the command line.
     * @param args the CLI arguments given to the program.
     * @return the arguments that are not recognized as CLI options.
     */
    @SuppressWarnings({"checkstyle:cyclomaticcomplexity", "checkstyle:npathcomplexity"})
    public static String[] parseCommandLine(String[] args) {
        final CommandLineParser parser = new DefaultParser();
        try {
            final CommandLine cmd = parser.parse(getOptions(), args, false);

            final Iterator<Option> optIterator = cmd.iterator();
            while (optIterator.hasNext()) {
                final Option opt = optIterator.next();
                String optName = opt.getLongOpt();
                if (Strings.isNullOrEmpty(optName)) {
                    optName = opt.getOpt();
                }
                switch (optName) {
                    // TODO Manage option
                    default:
                        break;
                }
            }

            return cmd.getArgs();
        } catch (org.apache.commons.cli.ParseException e) {
            // TODO showError(e.getLocalizedMessage(), e);
            // Event if showError never returns, add the return statement for
            // avoiding compilation error.
            return null;
        }
    }

    /**
     * Replies the command line options supported by this boot class.
     * @return the command line options.
     */
    public static Options getOptions() {
        final Options options = new Options();

        return options;
    }

    /**
     * Show an error message, and exit.
     * <p>This function never returns.</p>
     * @param message the description of the error.
     * @param exception the cause of the error.
     */
    @SuppressWarnings("checkstyle:regexp")
    public static void showError(String message, Throwable exception) {
        try (PrintWriter logger = new PrintWriter(getConsoleLogger())) {
            if (message != null && !message.isEmpty()) {
                logger.println(message);
            } else if (exception != null) {
                exception.printStackTrace(logger);
            }
            logger.println();
            logger.flush();
        }
    }

    /**
     * Replies the console stream for logging messages from the boot mechanism.
     * <p>The console stream is independent of the stream used by the logging service of the platform. Indeed,
     * the console stream is used for displaying information, warnings and messages before the Janus platform is realy launched.</p>
     * @return the console logger.
     */
    public static PrintStream getConsoleLogger() {
        return consoleLogger == null ? System.out : consoleLogger;
    }

    /**
     * Replies the console stream for logging messages from the boot mechanism.
     * <p>The console stream is independent of the stream used by the logging service of the platform. Indeed,
     * the console stream is used for displaying information, warnings and messages before the Janus platform is realy launched.</p>
     * @param stream the stream to use for the console logging.
     */
    public static void setConsoleLogger(PrintStream stream) {
        consoleLogger = stream;
    }

    private static Class<? extends Agent> loadAgentClass(String fullyQualifiedName) {
        final Class<?> type;
        try {
            type = Boot.class.getClassLoader().loadClass(fullyQualifiedName);   // FIXME Originally getCurrentClassLoader()
        } catch (Exception e) {
            showError("Error Boot_1", e);       // TODO Show explicit error message
            // Even if showError never returns, add the return statement for
            // avoiding compilation error.
            return null;
        }
        // The following test is needed because the
        // cast to Class<? extends Agent> is not checking
        // the Agent type (it is a generic type, not
        // tested at runtime).
        if (Agent.class.isAssignableFrom(type)) {
            return type.asSubclass(Agent.class);
        }

        showError("Error Boot_2", null);
        // Event if showError never returns, add the return statement for
        // avoiding compilation error.
        return null;
    }

    /**
     * Main function that is parsing the command line and launching the first agent.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        final CommandLineParser parser = new DefaultParser();
        try {
            final CommandLine cmd = parser.parse(getOptions(), args, false);
            Object[] freeArgs = cmd.getArgs();

            if (freeArgs.length == 0) {
                showError("You must give the fully qualified name of the agent to launch.", null);
                // Event if showError never returns, add the return statement for
                // avoiding compilation error.
                return;
            }

            final String agentToLaunch = freeArgs[0].toString();
            freeArgs = Arrays.copyOfRange(freeArgs, 1, freeArgs.length, String[].class);

            // Load the agent class
            final Class<? extends Agent> agent = loadAgentClass(agentToLaunch);
            assert agent != null;

            // Spawn the agent
            system = ActorSystem.create();
            final ActorRef actorRef = system.actorOf(AkkaAgent.props(agent));
        } catch (ParseException e) {
            // TODO
            e.printStackTrace();
        }


        // Load the agent class
        // TODO

        // Start the platform
        // TODO
    }

    private static ActorSystem system;
}
