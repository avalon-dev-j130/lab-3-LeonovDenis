package ru.avalon.java.console;

import ru.avalon.java.Commands;

public class Logger {
    private String threadName;
    private Commands commands;

    public Logger(String threadName, Commands commands) {
        this.threadName = threadName;
        this.commands = commands;
    }

    @Override
    public String toString() {

        return String.format("Поток \"%s\" выполнил команду \"%s\".", threadName, commands);
    }
}
