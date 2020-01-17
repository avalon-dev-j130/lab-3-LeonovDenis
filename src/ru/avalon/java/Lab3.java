package ru.avalon.java;

import ru.avalon.java.actions.*;
import ru.avalon.java.console.ConsoleUI;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Лабораторная работа №3
 * <p>
 * Курс: "Программирование на платформе Java. Разработка многоуровневых
 * приложений"
 * <p>
 * Тема: "Потоки исполнения (Threads) и многозадачность"
 *
 * @author Daniel Alpatov <danial.alpatov@gmail.com>
 */
public class Lab3 extends ConsoleUI<Commands> {

    private Path source;
    private Path target;

    /**
     * Точка входа в приложение.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Lab3().run();
    }

    /**
     * Конструктор класса.
     * <p>
     * Инициализирует экземпляр базового типа с использоавнием перечисления
     * {@link Commands}.
     */
    Lab3() {
        super(Commands.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCommand(Commands command) {
        switch (command) {
            case copy:
                /*
                 * TODO №6 Обработайте команду copy
                 */
                try {
                    System.out.println("Введите путь откуда копировать файл.");
                    source = Paths.get(in.readLine());
                    System.out.println("Введите путь куда копировать файл.");
                    target = Paths.get(in.readLine());
                    isExist(source);
                    try (Action act = new FileCopyAction(source, target)) {
                        act.start();
                    }
                } catch (IOException ex) {
                    System.err.println("Ошибка чтения строки " + ex.getMessage() + "\n> ");
                } catch (Exception ex) {
                    System.err.println("Ошибка закрытия FileCopyAction " + ex.getMessage() + "\n> ");
                }
                break;
            case move:
                /*
                 * TODO №7 Обработайте команду move
                 */
                try {
                    System.out.println("Введите путь откуда переместить файл.");
                    source = Paths.get(in.readLine());
                    System.out.println("Введите ДИРЕКТОРИЮ куда переместить файл.");
                    target = Paths.get(in.readLine());
                    try (Action act = new FileMoveAction(source, target)) {
                        act.start();
                    }
                } catch (IOException ex) {
                    System.err.println("Ошибка чтения строки " + ex.getMessage() + "\n> ");
                } catch (Exception ex) {
                    System.err.println("Ошибка закрытия FileMoveAction " + ex.getMessage() + "\n> ");
                }
                break;
            case exit:
                try {
                    System.out.println("Введена команда выхода из программы");

                    close();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                break;
            /*
             * TODO №9 Обработайте необработанные команды
             */
            case delete:
                try {
                    System.out.println("Введите путь откуда удалить файл.");
                    source = Paths.get(in.readLine());
                    try (Action act = new FileDeleteAction(source)) {
                        act.start();
                    }
                } catch (IOException ex) {
                    System.err.println("Ошибка чтения строки " + ex.getMessage() + "\n> ");
                } catch (Exception ex) {
                    System.err.println("Ошибка закрытия FileDeleteAction " + ex.getMessage() + "\n> ");
                }
                break;
            case rename:
                try {
                    System.out.println("Введите путь файла для переименовки.");
                    source = Paths.get(in.readLine());
                    System.out.println("Введите новое ИМЯ файла.");
                    target = Paths.get(in.readLine());
                    try (Action act = new FileRenameAction(source, target.toString())) {
                        act.start();
                    }
                } catch (IOException ex) {
                    System.err.println("Ошибка чтения строки " + ex.getMessage() + "\n> ");
                } catch (Exception ex) {
                    System.err.println("Ошибка закрытия FileRenameAction " + ex.getMessage() + "\n> ");
                }
                break;
        }
    }
    private void isExist(Path p){
        if(!p.toFile().exists())System.out.println("Файла нет");
    }
}
