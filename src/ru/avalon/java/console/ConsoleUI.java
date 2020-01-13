package ru.avalon.java.console;

import ru.avalon.java.actions.Action;

import java.io.IOException;

/**
 * Класс описывает текстовый человеко-машинный интерфейс.
 *
 * @param <E> тип данных, описывающий возможные команды.
 *            Дожен быть перечислением
 * @author Daniel Alpatov <danial.alpatov@gmail.com>
 */
public class ConsoleUI<E extends Enum<E>> extends EnumReader<E> implements Runnable {
    /**
     * Флаг, указывающий на то, должен ли интерфейс
     * продолжать обрабатывать команды.
     * <p>
     * Переменная должна содержать значение false, чтобы
     * интрефейс продолжал получать команды из потомка.
     */
    private boolean exit;

    /**
     * Основной конструктор класса.
     *
     * @param cls описатель перечисления, которое отражает
     *            набор команд, обрабатываемых интерфейсом
     */
    public ConsoleUI(Class<E> cls) {
        super(System.in, cls);
    }

    /**
     * Выполняет обработку следующей команды из потока.
     * <p>
     * Следующая команда, содержащаяся в потоке, связанном
     * с текущим объектом, передаётся на обрабтку в метод
     * onCommand.
     */
    protected void processCommand() {
        try {
            System.out.print("\nВведите комманду {copy|delete|move|rename|exit}\n> ");
            onCommand(next());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Алгоритм обработки команд.
     */
    @Override
    public void run() {
        while (!exit) processCommand();
    }

    /**
     * Метод жизненного цикла класса.
     * <p>
     * Вызывается, когда от пользозвателя была получена
     * следующая команда.
     *
     * @param command экземпляр перечисления E. Описывает
     *                пользовательскую команду.
     * @throws IOException в случае ощибки ввода вывода
     */
    protected void onCommand(E command) throws IOException {
    }

    /**
     * {@inheritDoc}
     *
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        exit = true;
        onClose();
    }

    /**
     * Закрытие пула потоков
     * Отчет о выполненных потоках
     */
    public void onClose() {
        Action.pool.shutdown();
        if (!Action.list.isEmpty()) {
            System.out.println("\nВыполнялись дополнительные потоки:");
            Action.list.forEach(System.out::println);
        } else System.out.println("\nДополнительных потоков небыло");
    }
}
