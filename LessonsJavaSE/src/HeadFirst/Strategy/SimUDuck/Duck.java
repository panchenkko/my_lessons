package HeadFirst.Strategy.SimUDuck;

import HeadFirst.Strategy.SimUDuck.Fly.FlyBehavior;
import HeadFirst.Strategy.SimUDuck.Quack.QuackBehavior;

/**
 * Паттерн Стратегия определяет семейство алгоритмов,
 * инкапсулирует каждый из них и обеспечивает их взаимозаменяемость.
 * Он позволяет модифицировать алгоритмы независимо от их использования на стороне клиента.
 */
public abstract class Duck {
    public FlyBehavior flyBehavior;
    public QuackBehavior quackBehavior;

    public Duck() {
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public abstract void display();

    public void performFly() {
        flyBehavior.fly();
    }

    public void performQuack() {
        quackBehavior.quack();
    }

    public void swim() {
        System.out.println("All ducks float, even decoys!");
    }
}
