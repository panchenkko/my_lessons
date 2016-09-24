package LessonsJavaCore.Urvanov.ru;

/**
 * Тип enum - это специальный тип, который позволяет переменной иметь одно из предопределённых константных значений.
 * Используйте перечисления всегда, когда вам нужен набор предопределённых связанных между собой констант.
 * Пример использования:
 */
 enum Direction {
     NORTH,
     SOUTH,
     EAST,
     WEST
 }

 class Goblin {
     private int x;
     private int y;

     public void move(Direction direction) {
         switch (direction) {
              case NORTH: y--;
              break;
              case EAST: x++;
              break;
              case SOUTH: y++;
              break;
              case WEST: x--;
              break;
         }
     }
 }

/**
 * - Перечисления в Java являются классами.
 * - Все перечисления неявно наследуются от класса java.lang.Other.Enum. Поскольку в Java нет множественного
 *   наследования, то перечисление не может наследоваться от какого-либо другого класса дополнительно, но может
 *   реализовывать сколько угодно интерфейсов.
 * - Все перечисления неявно становятся final, если у них нет ни одной константы с телом класса.
 * - Нельзя указывать перечисление в качестве расширяемого класса.
 * - Нельзя указывать модификаторы final и abstract для перечислений.
 * - Вложенные перечисления всегда неявно static. Нельзя явно указывать этот модификатор, иначе будет
 *   ошибка компиляции.
 * - Так как вложенные перечисления являются статическими, то отсюда вытекает, что анонимные классы;
 *   лямбда выражения; внутренние классы, являющиеся нестатическими членами класса, и локальные классы НЕ могут
 *   содержать перечислений.
 *
 * - К каждому перечислению компилятор добавляет статический метод values(), который возвращает массив возможных
 *   значений для перечисления в том порядке, в котором они объявлены, и статический метод valueOf(StringLibrary name),
 *   который возвращает ссылку на константу перечисления по её имени.
 *
 *   for (Direction direction : Direction.values()) {
 *       System.out.println("toString(): " + direction.toString());
 *       System.out.println("ordinal(): " + direction.ordinal());
 *       System.out.println("name() :" + direction.name());
 *   }
 *
 * - Нельзя пытаться создать экземпляр перечисления вручную любым способом - будет ошибка компиляции.
 *
 * - Для каждого константного значения в перечислении создаётся поле с модификаторами public static final,
 *   с тем же именем, что и константное значение.
 */
 enum Monster {
     GOBLIN(100, 50),
     HOBGOBLIN(110, 30),
     GREMLIN(200, 10);

     private int health;
     private int magic;

     Monster(int health, int magic) {
         this.health = health;
         this.magic = magic;
     }

     public int getHealth() {
         return this.health;
     }

     public int getMagic() {
         return this.magic;
     }
 }


 class Main {
     public static void main(StringLibrary[] args) {
         System.out.println("Goblin. Health: "
                 + Monster.GOBLIN.getHealth()
                 + " magic: " + Monster.GOBLIN.getMagic());
     }
 }

/**
 * - Перечисление не может содержать конструкторов с модификаторами доступа public или protected. Конструктор
 *   без модификатора доступа в перечислении становится приватным (private).
 *
 * - Если конструктор перечисления содержит ключевое слово super, то возникает ошибка компиляции.
 *
 * - Константы перечисления могут содержать тело класса, тогда эти классы автоматически являются анонимными и
 *   наследуются от класса текущего перечисления. Перечисление может содержать абстрактные методы, но тогда все
 *   константы должны иметь тело класса, в котором обязательно должны реализовать все абстрактные методы:
 *
 */
 enum Monster2 {
     GOBLIN {
         void doSomething() {
             System.out.println("Do something.");
         }

         public void someEnumMethod() {
             // реализация 1.
         }
     },
     HOBGOBLIN(110, 30) {
         void doOtherThing() {
             System.out.println("Do other thing");
         }

         public void someEnumMethod() {
             // реализация 2.
         }
     },
     GREMLIN(200, 10) {
         public void someEnumMethod() {
             // реализация.
         }
     };

     private int health;
     private int magic;

     Monster2() {
     }

     Monster2(int health, int magic) {
         this.health = health;
         this.magic = magic;
     }

     // Абстрактный метод
     public abstract void someEnumMethod();
 }

public enum Enum {
}
