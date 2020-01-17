package ru.avalon.java.actions;

import ru.avalon.java.Commands;
import ru.avalon.java.console.Logger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileDeleteAction implements Action {

    private Path source;

    public FileDeleteAction(Path source) {
        this.source = source;
    }

    @Override
    public void run() {
        try {
            Files.delete(source);
        } catch (IOException ex) {
            System.err.println("Ошибка удаления файла " + ex.getMessage()+"\n> ");
        }
        //логирование потока
        list.add(new Logger(Thread.currentThread().getName(), Commands.delete));
    }

    @Override
    public void close() {
        System.out.println("Поток \"" + Thread.currentThread().getName() + "\" создал дополнительный поток.");
    }

}
