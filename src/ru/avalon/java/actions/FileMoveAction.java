package ru.avalon.java.actions;

import ru.avalon.java.Commands;
import ru.avalon.java.console.Logger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Действие, которое перемещает файлы в пределах дискового
 * пространства.
 */
public class FileMoveAction implements Action {
    private Path source;
    private Path target;

    public FileMoveAction(Path source, Path target) {
        this.source = source;
        this.target = Paths.get(target.toString(),source.getFileName().toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        /*
         * TODO №4 Реализуйте метод run класса FileMoveAction
         */
        try {
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            System.err.println("Ошибка перемещения файла " + ex.getMessage()+"\n> ");
        }
        //логирование потока
        list.add(new Logger(Thread.currentThread().getName(), Commands.move));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        /*
         * TODO №5 Реализуйте метод close класса FileMoveAction
         */
        System.out.println("Поток \"" + Thread.currentThread().getName() + "\" создал дополнительный поток.");
    }

}
