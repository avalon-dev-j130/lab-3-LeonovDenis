package ru.avalon.java.actions;

import ru.avalon.java.console.Logger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Абстрактное представление о действии, как функциональном
 * элементе приложения.
 * <p>
 * Действие является потоковым объектом, что позволяет
 * исполнять несколько действий параллельно и не блокировать
 * основной поток исполнения.
 */
public interface Action extends Runnable, AutoCloseable {

    ExecutorService pool = Executors.newWorkStealingPool();//пул потоков, число потоков = числу ядер
    Collection<Logger> list = new ArrayList<>();//коллекция выполненных дополнительных потоков

    /**
     * Запускает потоковый объект на исполнение в отдельном
     * потоке исполнения.
     */
    default void start() {
        /*
         * TODO №1 Реализуйте метод start интерфейса Action.
         */
        pool.submit(this);//добавляем задачу в пул потоков
    }

}
