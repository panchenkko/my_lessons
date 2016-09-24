package LessonsJavaCore.Other.Iterator;

import java.util.*;

/**
 * Итератор — это объект, который позволяет программисту пробежать по элементам коллекции.
 * * * «Ну и что?» - скажут некоторые - «Я могу это сделать с помощью обычного цикла!».
 * * *
 * * * Да, действительно, большинство стандартных коллекций из пакета java.util предоставляют возможность выборки
 * * * элемента по его индексу, вот так:
 */
class WithCycle {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("One");
        list.add("Two");
        list.add("Three");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
/**
 * Но таким образом можно поступить далеко не со всеми коллекциями. Возьмём, к примеру  Set. Если внимательно
 * * * посмотреть на описание интерфейса java.util.Set, то станет понятно, что в нём нет ни одного метода,
 * * * который бы возвращал элемент данного множества.
 */

/**
 * Как обойти элементы Set’а?
 * * * Все коллекции из java.util реализуют интерфейс Collection, который, в свою очередь, расширяет интерфейс Iterable.
 * * * Вот оно, наше решение! В интерфейсе Iterable описан только один метод.
 */
class CollectionSet {
    public static void main(String[] args) {
        Set set = new HashSet();
        set.add("One");
        set.add("Two");
        set.add("Three");

        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

/**
 * Рассмотрим подробнее методы возвращаемого класса Iterator.
 * * * - boolean hasNext() - метод возвращает true, если в коллекции ещё остались элементы и false, если достигнут
 * * *   конец коллекции.
 * * * - E next() - метод возвращает текущий элемент. Т.к. итератор является параметризованным классом, то этот метод
 * * *   нам будет возвращает не Object, а сразу тот тип, которы нам нужен.
 */

public class Runner {
}
