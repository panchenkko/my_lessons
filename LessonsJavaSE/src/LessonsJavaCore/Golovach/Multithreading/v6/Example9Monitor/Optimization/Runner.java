package LessonsJavaCore.Golovach.Multithreading.v6.Example9Monitor.Optimization;

public class Runner {
    public static void main(String[] args) {
        SingleElementBufferTimed buffer = new SingleElementBufferTimed();

        Thread producer1 = new Thread(new ProducedTimed(1, 100, buffer, 1));
        Thread producer2 = new Thread(new ProducedTimed(1, 300, buffer, 1));

        Thread consumer1 = new Thread(new ConsumerTimed(buffer, 1000));
        Thread consumer2 = new Thread(new ConsumerTimed(buffer, 1000));
        Thread consumer3 = new Thread(new ConsumerTimed(buffer, 1000));
        Thread consumer4 = new Thread(new ConsumerTimed(buffer, 1000));
        Thread consumer5 = new Thread(new ConsumerTimed(buffer, 1000));

        producer1.setName("Producer 1");
        producer2.setName("Producer 2");

        consumer1.setName("Consumer 1");
        consumer2.setName("Consumer 2");
        consumer3.setName("Consumer 3");
        consumer4.setName("Consumer 4");
        consumer5.setName("Consumer 5");

        producer1.start();
        producer2.start();

        consumer1.start();
        consumer2.start();
        consumer3.start();
        consumer4.start();
        consumer5.start();
    }
}
