package io.sarl.akka.bic;

import io.sarl.akka.AkkaAgent;
import io.sarl.core.Logging;
import io.sarl.lang.core.Skill;

import java.util.function.Supplier;
import java.util.logging.Logger;

public class LoggingSkill extends Skill implements Logging {

    private String loggingName = getID().toString();

    public LoggingSkill() {
        //
    }

    @Override
    public void setLoggingName(String loggingName) {
        this.loggingName = loggingName;
    }

    @Override
    public void println(Object message) {
        System.out.println("[" + getID().toString() + "] " + message); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public boolean isErrorLogEnabled() {
        return true;
    }

    @Override
    public boolean isWarningLogEnabled() {
        return true;
    }

    @Override
    public boolean isInfoLogEnabled() {
        return true;
    }

    @Override
    public boolean isDebugLogEnabled() {
        return true;
    }

    @Override
    public int getLogLevel() {
        return 0;
    }

    @Override
    public void setLogLevel(int level) {
        //
    }

    @Override
    public Logger getLogger() {
        return null;
    }

    @Override
    public void error(Object message, Throwable exception, Object... parameters) {
        System.out.println("[" + this.loggingName + "] ERROR: " + message); //$NON-NLS-1$ //$NON-NLS-2$
        if (exception != null) {
            exception.printStackTrace(System.out);
        }
    }

    @Override
    public void error(Supplier<String> messageProvider) {
        System.out.println("[" + this.loggingName + "] ERROR: " + messageProvider.get()); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public void warning(Object message, Throwable exception, Object... parameters) {
        System.out.println("[" + this.loggingName + "] WARNING: " + message); //$NON-NLS-1$ //$NON-NLS-2$
        if (exception != null) {
            exception.printStackTrace(System.out);
        }
    }

    @Override
    public void warning(Supplier<String> messageProvider) {
        System.out.println("[" + this.loggingName + "] WARNING: " + messageProvider.get()); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public void info(Object message, Object... parameters) {
        System.out.println("[" + this.loggingName + "] INFO: " + message); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public void info(Supplier<String> messageProvider) {
        System.out.println("[" + this.loggingName + "] INFO: " + messageProvider.get()); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public void debug(Object message, Object... parameters) {
        System.out.println("[" + this.loggingName + "] DEBUG: " + message); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public void debug(Supplier<String> messageProvider) {
        System.out.println("[" + this.loggingName + "] DEBUG: " + messageProvider.get()); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public void error(Object message, Object... parameters) {
        System.out.println("[" + this.loggingName + "] ERROR: " + message); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public void warning(Object message, Object... parameters) {
        System.out.println("[" + this.loggingName + "] WARNING: " + message); //$NON-NLS-1$ //$NON-NLS-2$
    }

}