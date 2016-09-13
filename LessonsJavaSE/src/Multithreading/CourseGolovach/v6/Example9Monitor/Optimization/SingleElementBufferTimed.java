package Multithreading.CourseGolovach.v6.Example9Monitor.Optimization;

import java.util.concurrent.TimeoutException;

/**
 * Текущая оптимизация состоит в том, что мы добавили "время ожидания". Мы вносим время, сколько должен спать поток
 * прежде чем он выкинет себя из программы из-за ненадобности.
 *
 * Например:
 * У нас есть 2 производителя и 5 потребителей. Каждому потребителю мы дали время, чтобы он своей помощи ждал
 * максимум 10 миллисекунд. Если за 1000 миллисекунд он не успел принять данные от производителя он выбрасывает
 * исключение и выкижывает себя из программы. Также для удобства мы изменили имя каждому потоку, чтобы могли наглядно
 * увидеть какой именно поток прекратил работу.
 * Реализацию можно посмотреть в классе Runner этого пакета
 *
 * В результате 4 потребителя рано или поздно выбрасывают исключение, так как с двумя производителями может
 * справиться и один потребитель
 */

public class SingleElementBufferTimed {
    private Integer elem = null;

    public synchronized void put(Integer newElem, long timeout) throws InterruptedException, TimeoutException {
        long waitTime = timeout;
        while (this.elem != null && waitTime > 0) {
            long t0 = System.currentTimeMillis();
            this.wait(waitTime);
            long t1 = System.currentTimeMillis();
            long elapsedTime = t1 - t0;
            waitTime -= elapsedTime;
        }
        //TODO Optimization
        if (this.elem != null && waitTime <= 0)
            throw new TimeoutException();
        //TODO Optimization end
        this.elem = newElem;
        this.notifyAll();
    }

    public synchronized Integer get(long timeout) throws InterruptedException, TimeoutException {
        long waitTime = timeout;
        while (this.elem == null && waitTime > 0) {
            long t0 = System.currentTimeMillis();
            this.wait(timeout);
            long t1 = System.currentTimeMillis();
            long elapsedTime = t1 - t0;
            waitTime -= elapsedTime;
        }
        //TODO Optimization
        if (this.elem == null && waitTime <= 0)
            throw new TimeoutException();
        //TODO Optimization end
        int result = this.elem;
        this.elem = null;
        this.notifyAll();
        return result;
    }
}
