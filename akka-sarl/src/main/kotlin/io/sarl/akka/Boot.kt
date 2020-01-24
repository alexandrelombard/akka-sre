package io.sarl.akka

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import com.google.common.base.Strings
import com.typesafe.config.ConfigFactory
import io.sarl.lang.core.Agent
import org.apache.commons.cli.*

import java.io.File
import java.io.IOException
import java.io.PrintStream
import java.io.PrintWriter
import java.text.MessageFormat
import java.util.Arrays

/**
 * This is the class that permits to boot the Akka SRE platform.
 *
 *
 * This class provides the "main" function for the platform. The list of the parameters is composed of a list of options, the
 * classname of an agent to launch, and the parameters to pass to the launched agent.
 *
 * @author Alexandre Lombard <alexandre.lombard></alexandre.lombard>@utbm.fr>
 */
object Boot {

    /** Short command-line option for "embedded". */
    val CLI_OPTION_EMBEDDED_SHORT = "e" //$NON-NLS-1$

    /** Long command-line option for "embedded". */
    val CLI_OPTION_EMBEDDED_LONG = "embedded" //$NON-NLS-1$

    /** Short command-line option for "boot agent id". */
    val CLI_OPTION_BOOTID_SHORT = "B" //$NON-NLS-1$

    /** Long command-line option for "boot agent id". */
    val CLI_OPTION_BOOTID_LONG = "bootid" //$NON-NLS-1$

    /** Short command-line option for "random id". */
    val CLI_OPTION_RANDOMID_SHORT = "R" //$NON-NLS-1$

    /** Long command-line option for "random id". */
    val CLI_OPTION_RANDOMID_LONG = "randomid" //$NON-NLS-1$

    /** Short command-line option for "Janus world id". */
    val CLI_OPTION_WORLDID_SHORT = "W" //$NON-NLS-1$

    /** Long command-line option for "Janus world id". */
    val CLI_OPTION_WORLDID_LONG = "worldid" //$NON-NLS-1$

    /** Short command-line option for "file". */
    val CLI_OPTION_FILE_SHORT = "f" //$NON-NLS-1$

    /** Long command-line option for "file". */
    val CLI_OPTION_FILE_LONG = "file" //$NON-NLS-1$

    /** Short command-line option for "help". */
    val CLI_OPTION_HELP_SHORT = "h" //$NON-NLS-1$

    /** Long command-line option for "help". */
    val CLI_OPTION_HELP_LONG = "help" //$NON-NLS-1$

    /** Long command-line option for "nologo". */
    val CLI_OPTION_NOLOGO_LONG = "nologo" //$NON-NLS-1$

    /** Short command-line option for "offline". */
    val CLI_OPTION_OFFLINE_SHORT = "o" //$NON-NLS-1$

    /** Long command-line option for "offline".
     */
    val CLI_OPTION_OFFLINE_LONG = "offline" //$NON-NLS-1$

    /** Short command-line option for "be quiet".
     */
    val CLI_OPTION_QUIET_SHORT = "q" //$NON-NLS-1$

    /** Long command-line option for "be quiet".
     */
    val CLI_OPTION_QUIET_LONG = "quiet" //$NON-NLS-1$

    /** Short command-line option for "be more verbose".
     */
    val CLI_OPTION_VERBOSE_SHORT = "v" //$NON-NLS-1$

    /** Long command-line option for "be more verbose".
     */
    val CLI_OPTION_VERBOSE_LONG = "verbose" //$NON-NLS-1$

    /** Long command-line option for "display the version".
     */
    val CLI_OPTION_VERSION = "version" //$NON-NLS-1$

    /** Short command-line option for "change log level".
     */
    val CLI_OPTION_LOG_SHORT = "l" //$NON-NLS-1$

    /** Long command-line option for "change log level".
     */
    val CLI_OPTION_LOG_LONG = "log" //$NON-NLS-1$

    /** Short command-line option for "env. variable definition".
     */
    val CLI_OPTION_DEFINE_SHORT = "D" //$NON-NLS-1$

    /** Long command-line option for "env. variable definition".
     */
    val CLI_OPTION_DEFINE_LONG = "define" //$NON-NLS-1$

    /** Short command-line option for "show defaults".
     */
    val CLI_OPTION_SHOWDEFAULTS_SHORT = "s" //$NON-NLS-1$

    /** Long command-line option for "show defaults".
     */
    val CLI_OPTION_SHOWDEFAULTS_LONG = "showdefaults" //$NON-NLS-1$

    /** Short command-line option for "show the classpath".
     */
    val CLI_OPTION_SHOWCLASSPATH = "showclasspath" //$NON-NLS-1$

    /** Short command-line option for "show CLI arguments".
     */
    val CLI_OPTION_SHOWCLIARGUMENTS_LONG = "cli" //$NON-NLS-1$

    /** Short command-line option for "classpath".
     */
    val CLI_OPTION_CLASSPATH_SHORT = "cp" //$NON-NLS-1$

    /** Long command-line option for "classpath".
     */
    val CLI_OPTION_CLASSPATH_LONG = "classpath" //$NON-NLS-1$

    private val ERROR_EXIT_CODE = 255

    /**
     * Replies the console stream for logging messages from the boot mechanism.
     *
     * The console stream is independent of the stream used by the logging service of the platform. Indeed,
     * the console stream is used for displaying information, warnings and messages before the Janus platform is realy launched.
     * @return the console logger.
     */
    /**
     * Replies the console stream for logging messages from the boot mechanism.
     *
     * The console stream is independent of the stream used by the logging service of the platform. Indeed,
     * the console stream is used for displaying information, warnings and messages before the Janus platform is realy launched.
     * @param stream the stream to use for the console logging.
     */
    var consoleLogger: PrintStream? = null
        get() = if (field == null) System.out else field

    /**
     * Replies the command line options supported by this boot class.
     * @return the command line options.
     */
    val options: Options
        get() {
            val options = Options()

            return options
        }

    lateinit var system: ActorSystem

    /**
     * Parse the command line.
     * @param args the CLI arguments given to the program.
     * @return the arguments that are not recognized as CLI options.
     */
    fun parseCommandLine(args: Array<String>): Array<String>? {
        val parser = DefaultParser()
        try {
            val cmd = parser.parse(options, args, false)

            val optIterator = cmd.iterator()
            while (optIterator.hasNext()) {
                val opt = optIterator.next()
                var optName = opt.longOpt
                if (Strings.isNullOrEmpty(optName)) {
                    optName = opt.opt
                }
                when (optName) {
                    // TODO Manage option
                    else -> {
                    }
                }
            }

            return cmd.args
        } catch (e: ParseException) {
            // TODO showError(e.getLocalizedMessage(), e);
            // Event if showError never returns, add the return statement for
            // avoiding compilation error.
            return null
        }

    }

    /**
     * Show an error message, and exit.
     *
     * This function never returns.
     * @param message the description of the error.
     * @param exception the cause of the error.
     */
    fun showError(message: String?, exception: Throwable?) {
        PrintWriter(consoleLogger).use { logger ->
            if (message != null && !message.isEmpty()) {
                logger.println(message)
            } else exception?.printStackTrace(logger)
            logger.println()
            logger.flush()
        }
    }

    private fun loadAgentClass(fullyQualifiedName: String): Class<out Agent>? {
        val type: Class<*>
        try {
            type = Boot::class.java.classLoader.loadClass(fullyQualifiedName)   // FIXME Originally getCurrentClassLoader()
        } catch (e: Exception) {
            showError("Error Boot_1", e)       // TODO Show explicit error message
            // Even if showError never returns, add the return statement for
            // avoiding compilation error.
            return null
        }

        // The following test is needed because the cast to Class<? extends Agent> is not checking
        // the Agent type (it is a generic type, not tested at runtime).
        if (Agent::class.java.isAssignableFrom(type)) {
            return type.asSubclass(Agent::class.java)
        }

        showError("Error Boot_2", null)
        // Event if showError never returns, add the return statement for avoiding compilation error.
        return null
    }

    /**
     * Main function that is parsing the command line and launching the first agent.
     * @param args command line arguments
     */
    @JvmStatic
    fun main(args: Array<String>) {
        val parser = DefaultParser()
        try {
            val cmd = parser.parse(options, args, false)
            var freeArgs = cmd.args

            if (freeArgs.size == 0) {
                showError("You must give the fully qualified name of the agent to launch.", null)
                // Event if showError never returns, add the return statement for
                // avoiding compilation error.
                return
            }

            val agentToLaunch = freeArgs[0].toString()
            freeArgs = Arrays.copyOfRange<String, Any>(freeArgs, 1, freeArgs.size, Array<String>::class.java)

            // Load the agent class
            val agent = loadAgentClass(agentToLaunch)

            // Spawn the agent
            // FIXME merged-reference.conf should be automatically merged from Akka configuration files when the JAR is being built
            val referenceConfigText = javaClass::class.java.getResourceAsStream("/io/sarl/akka/merged-reference.conf").bufferedReader().readText()
            val config = ConfigFactory
                    .parseResources(javaClass, "sre.conf")
                    .withFallback(ConfigFactory.parseString(referenceConfigText))
//                    .withFallback(ConfigFactory.defaultReference(javaClass::class.java.classLoader))
                    .resolve()


            system = ActorSystem.create("sre-akka", config)
//            system = ActorSystem.create("sre-akka")
            val actorRef = system.actorOf(AkkaAgent.props(agent!!))
        } catch (e: ParseException) {
            // TODO
            e.printStackTrace()
        }

    }
}
