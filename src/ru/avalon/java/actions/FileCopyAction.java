package ru.avalon.java.actions;

import ru.avalon.java.Commands;
import ru.avalon.java.console.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Действие, которое копирует файлы в пределах дискового пространства.
 */
public class FileCopyAction implements Action {

    private Path source;
    private Path target;

    public FileCopyAction(Path source, Path target) {
        this.source = source;
        this.target = target;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        /*
         * TODO №2 Реализуйте метод run класса FileCopyAction
         */
        createDir();
        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            System.err.println("Ошибка копирования файла " + ex.getMessage() + "\n> ");
        }
        //логирование потока
        list.add(new Logger(Thread.currentThread().getName(), Commands.copy));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        /*
         * TODO №3 Реализуйте метод close класса FileCopyAction
         */
        System.out.println("Поток \"" + Thread.currentThread().getName() + "\" создал дополнительный поток.");
    }

    private void createDir() {

        try {
            if (source.getFileName().toString().equals(target.getFileName().toString())) {
                Files.createDirectory(target.getParent());
            } else {
                Files.createDirectory(target);
                target = Paths.get(target.toString(), source.getFileName().toString());

            }
        } catch (IOException ignore) {
            System.out.println("Ошибка создания несуществующей директории");
        }
    }
}
