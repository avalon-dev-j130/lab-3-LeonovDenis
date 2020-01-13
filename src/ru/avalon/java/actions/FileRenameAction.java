package ru.avalon.java.actions;

import ru.avalon.java.Commands;
import ru.avalon.java.console.Logger;
import java.io.File;
import java.nio.file.Path;

public class FileRenameAction implements Action {
    private File source;
    private File name;

    public FileRenameAction(Path source, String name) {
        this.source = source.toFile();
        name = source.getParent().toString() + "\\" + name;
        this.name = new File(name);
    }

    @Override
    public void run() {
        source.renameTo(name);
        //логирование потока
        list.add(new Logger(Thread.currentThread().getName(), Commands.rename));
    }

    @Override
    public void close() {
        System.out.println("Поток \"" + Thread.currentThread().getName() + "\" создал дополнительный поток.");
    }

}
