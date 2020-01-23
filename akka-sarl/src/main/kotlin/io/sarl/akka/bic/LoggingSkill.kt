package io.sarl.akka.bic

import io.sarl.core.Logging
import io.sarl.lang.core.Agent
import io.sarl.lang.core.Skill
import java.util.function.Supplier
import java.util.logging.Logger

class LoggingSkill(owner: Agent) : Skill(owner), Logging {

    private var loggingName = id.toString()
    private var logger = Logger.getLogger(this.loggingName)

    override fun setLoggingName(loggingName: String) {
        this.loggingName = loggingName
        this.logger = Logger.getLogger(this.loggingName)
    }

    override fun println(message: Any) {
        System.out.println("[$id] $message") //$NON-NLS-1$ //$NON-NLS-2$
    }

    override fun isErrorLogEnabled(): Boolean {
        return true
    }

    override fun isWarningLogEnabled(): Boolean {
        return true
    }

    override fun isInfoLogEnabled(): Boolean {
        return true
    }

    override fun isDebugLogEnabled(): Boolean {
        return true
    }

    override fun getLogLevel(): Int {
        return 0
    }

    override fun setLogLevel(level: Int) {
        //
    }

    override fun getLogger(): Logger {
        return this.logger
    }

    override fun error(message: Any, exception: Throwable?, vararg parameters: Any) {
        //        System.out.println("[" + this.loggingName + "] ERROR: " + message); //$NON-NLS-1$ //$NON-NLS-2$
        this.logger.severe(message.toString())
        exception?.printStackTrace(System.out)
    }

    override fun error(messageProvider: Supplier<String>) {
        //        System.out.println("[" + this.loggingName + "] ERROR: " + messageProvider.get()); //$NON-NLS-1$ //$NON-NLS-2$
        this.logger.severe(messageProvider.get())
    }

    override fun error(message: Any, vararg parameters: Any) {
        //        System.out.println("[" + this.loggingName + "] ERROR: " + message); //$NON-NLS-1$ //$NON-NLS-2$
        this.logger.severe(message.toString())
    }

    override fun warning(message: Any, exception: Throwable?, vararg parameters: Any) {
        //        System.out.println("[" + this.loggingName + "] WARNING: " + message); //$NON-NLS-1$ //$NON-NLS-2$
        this.logger.warning(message.toString())
        exception?.printStackTrace(System.out)
    }

    override fun warning(messageProvider: Supplier<String>) {
        //        System.out.println("[" + this.loggingName + "] WARNING: " + messageProvider.get()); //$NON-NLS-1$ //$NON-NLS-2$-
        this.logger.warning(messageProvider.get())
    }

    override fun warning(message: Any, vararg parameters: Any) {
//        println("[" + this.loggingName + "] WARNING: " + message) //$NON-NLS-1$ //$NON-NLS-2$
        this.logger.warning(message.toString())
    }

    override fun info(message: Any, vararg parameters: Any) {
        //        System.out.println("[" + this.loggingName + "] INFO: " + message); //$NON-NLS-1$ //$NON-NLS-2$
        this.logger.info(message.toString())
    }

    override fun info(messageProvider: Supplier<String>) {
        //        System.out.println("[" + this.loggingName + "] INFO: " + messageProvider.get()); //$NON-NLS-1$ //$NON-NLS-2$
        this.logger.info(messageProvider.get())
    }

    override fun debug(message: Any, vararg parameters: Any) {
        //        System.out.println("[" + this.loggingName + "] DEBUG: " + message); //$NON-NLS-1$ //$NON-NLS-2$
        this.logger.fine(message.toString())
    }

    override fun debug(messageProvider: Supplier<String>) {
        //        System.out.println("[" + this.loggingName + "] DEBUG: " + messageProvider.get()); //$NON-NLS-1$ //$NON-NLS-2$
        this.logger.fine(messageProvider.get())
    }

}