package LessonsJavaCore.Urvanov.ru;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * По соглашению переменные типа именуются одной буквой в верхнем регистре.
 * * * Наиболее часто используемые имена для параметров типа:
 * * * * * E — элемент (Element, обширно используется Java Collections Framework)
 * * * * * K — Ключ
 * * * * * N — Число
 * * * * * T — Тип
 * * * * * V — Значение
 * * * * * S, U, V и т.п. - 2-й, 3-й, 4-й типы
 *
 * Параметр типа и аргумент типа - это два разных понятия. Когда вы объявляете обобщённый тип Lair<T>, то здесь T     *
 * * * является параметром типа. Когда вы обращаетесь к обобщённому типу, вы передаёте аргумент типа, например Goblin.
 *
 * Обращение к обобщённому типу обычно называется параметризованным типом (parameterized type).
 *
 * Чтобы создать экземпляр класса, используйте ключевое слово new, как обычно, и в дополнение укажите <Goblin>
 * * * между именем класса и скобками с параметрами конструктора:
 * * * * * Lair<Goblin> goblinLair = new Lair<Goblin>();
 *
 * Можно также заменить параметр типа на параметризованный тип:
 * * * PairLair<Goblin, Lair<Genie>> hierarchicalLair = new PairLair<>();
 *
 * Сырой тип (raw type) - это имя обобщённого класса или интерфейса без аргументов типа (type arguments).
 * * * Например, параметризованный тип создаётся так:
 * * * * * Lair<Goblin> goblinLair = new Lair<>();
 * * * Если убрать аргументы типа, то будет создан сырой тип:
 * * * * * Lair lair = new Lair();
 *
 * Синтаксис обобщённого метода включает параметры типа внутри угловых скобок, которые указываются
 * * * перед возвращаемым типом.
 * * * * * class Utils {
 * * * * *     // Обобщённый метод.
 * * * * *     static <T> void setIfNull(Lair<T> lair, T t) {
 * * * * *         if (lair.getInhabitant() == null) {
 * * * * *         lair.setInhabitant(t);
 * * * * *         }
 * * * * *     }
 * * * * * }
 * * *
 * * * Пример вызова обобщённого метода:
 * * * * * Lair<Goblin> goblinsLair = new Lair<>();
 * * * * * Utils.<Goblin>setIfNull(goblinsLair, new Goblin());
 * * *
 * * * Здесь тип Goblin указан явно, но обычно он может быть опущен, и компилятор выведет тип из контекста:
 * * * * * Lair<Goblin> goblinsLair = new Lair<>();
 * * * * * Utils.setIfNull(goblinsLair, new Goblin());
 *
 * Чтобы объявить ограниченный параметр типа нужно после имени параметра указать ключевое слово extends,
 * * * а затем указать верхнюю границу (upper bound), которой в данном примере является класс Monster.
 * * * В этом контексте extends означает как extends, так и implements.
 * * * |
 * * * |
 * * * v
 */
 // У параметра типа указываем верхнюю границу Monster.                                                               *
 class Lair<T extends Monster3> {

     T inhabitant;

     public void setInhabitant(T inhabitant) {
         this.inhabitant = inhabitant;
     }

     public T getInhabitant() {
         return this.inhabitant;
     }

     public void tick() {
         if (inhabitant != null) {
             // Можно вызывать методы интерфейса или класса,                                                          *
             // указанного в качестве верхней границы параметра типа.
             inhabitant.doSomething();
         }
     }

 }

 class Monster3 {
     public void doSomething() {
         System.out.println("Doing something.");
     }
 }

 class Goblin3 extends Monster3 {
 }

 class Main3 {
     public static void main(String[] args) {
         Lair<Goblin3> goblinLair = new Lair<>();
         goblinLair.setInhabitant(new Goblin3());
         goblinLair.tick();
     }
 }

/**
 * * * Можно указать несколько верхних границ, перечисляя их через символ «&», но при этом только один класс          *
 * * * может быть указан в списке верхних границ, и он должен стоять первым:
 */
 class Monster6 { }

 interface Enemy6 { }

 interface Dreadful6 { }

 // Указываем несколько верхних границ.                                                                     *
 // Если в списке верхних границ есть класс, то
 // он обязательно должен идти первым.
 class Lair6<T extends Monster6 & Enemy6 & Dreadful6> { }

/**
 * * * Аналогичным образом можно создавать обобщённые методы с ограничением:
 */
 class Monster4 {
     boolean isSelect() {
         return true; // some selection logic
     }
 }

 interface Enemy4 {}

 interface Dreadful4 {}

 class Utils4 {
     public static <T extends Monster4 & Enemy4 & Dreadful4> T selectMonster(T[] monsters) {
         T result = null;
         for (T obj : monsters) {
             result = obj.isSelect() ? obj : null; // some selection logic
         }
         return result;
     }
 }

 class Goblin4 extends Monster4 implements Enemy4, Dreadful4 {
 }

 class Main4 {
     public static void main(String[] args) {
         Goblin4[] goblins = new Goblin4[10];
         for (int n = 0; n < goblins.length; n++) {
             goblins[n] = new Goblin4();
         }
         Goblin4 selectedMonster = Utils4.selectMonster(goblins);
     }
 }

/**
 * Вы можете осуществить вызов обобщённого типа, передав Number в качестве аргумента типа, и любой дальнейший         *
 * * * вызов будет разрешён, если аргумент совместим с Number:
 * * * * * Box<Number> box = new Box<Number>();
 * * * * * box.add(new Integer(10));   // OK
 * * * * * box.add(new Double(10.1));  // OK
 * * *
 * * * Теперь рассмотрим метод:                                                                                       *
 * * * * * public void boxTest(Box<Number> n) { }
 * * * Какой тип аргумента он будет принимать? Если посмотрите на сигнатуру, то вы можете увидеть, что он принимает
 * * * один аргумент с типом Box<Number>. Но что это означает? Можете ли вы передать Box<Integer> или Box<Double>,
 * * * как вы могли бы ожидать? Нет, не можете, так как Box<Integer> и Box<Double> не являются потомками Box<Number>.
 * * *
 * * * Запомните: Для двух типов A и B (например, Number и Integer), MyClass<A> не имеет никакой связи или            *
 * * * родства с MyClass<B>, независимо от того, как A и B связаны между собой.
 * * * Общий родитель MyClass<A> и MyClass<B> - это Object.
 *
 * Рассмотрим этот пример:
 */
 class Lair5<T> {
     private T inhabitant;

     public void setInhabitant(T t) {
         this.inhabitant = t;
     }

     public T getInhabitant() {
         return this.inhabitant;
     }
 }

 class Goblin5 {
     private String name;

     public Goblin5(String name) {
         this.name = name;
     }

     @Override
     public String toString() {
         return this.name;
     }
 }


 class LairDemo {

     public static <U> void addLair(U u, List<Lair5<U>> lairs) {
         Lair5<U> lair = new Lair5<>();
         lair.setInhabitant(u);
         lairs.add(lair);
     }

     public static <U> void outputLairs(List<Lair5<U>> lairs) {
         int counter = 0;
         for (Lair5<U> lair : lairs) {
             U lairInhabitant = lair.getInhabitant();
             System.out.println("Lair #" + counter + " contains [" + lairInhabitant.toString() + "]");
             counter++;
         }
     }

     public static void main(String[] args) {
         ArrayList<Lair5<Goblin5>> listOfGoblinsLairs = new ArrayList<>();
         LairDemo.<Goblin5>addLair(new Goblin5("Michael"), listOfGoblinsLairs);
         LairDemo.addLair(new Goblin5("Rafael"), listOfGoblinsLairs);
         LairDemo.addLair(new Goblin5("Pushkin"), listOfGoblinsLairs);
         LairDemo.outputLairs(listOfGoblinsLairs);
     }
 }

/**
 * * * Обобщённый метод addLair объявляет один параметр типа U. В большинстве случаев компилятор Java может
 * * * вывести параметры типа вызова обобщённого метода, в результате вам чаще всего вовсе не обязательно их указывать.
 * * * Например, чтобы вызвать обобщённый метод addBox, вы можете указать параметры типа так:
 * * * * * LairDemo.<Goblin>addLair(new Goblin("Michael"), listOfGoblinsLairs);
 * * * Либо можно опустить их, и тогда компилятор Java автоматически выведет тип Goblin из аргументов метода:
 * * * * * LairDemo.addLair(new Goblin("Rafael"), listOfGoblinsLairs);
 */

/**
 * Конструкторы могут быть обобщёнными как в обобщённых, так и в необобщённых классах. Рассмотрим пример:
 * * * class MyClass<X> {
 * * *     <T> MyClass(T t) {
 * * *     // ...
 * * *     }
 * * * }
 * * *
 * * * Рассмотрим создание экземпляра класса MyClass:
 * * * * * MyClass<Integer> myObject = new MyClass<>("");
 * * * В этом примере компилятор выводит Integer для параметра типа X обобщённого класса MyClass<X>. Он выводит
 * * * тип String для параметра T обобщённого конструктора обобщённого класса.
 *
 * Рассмотрим метод:
 * * * void processStringList(List<String> stringList) {
 * * *     // process stringList
 * * * }
 * * *
 * * * Представьте, что вы хотите вызвать метод processStringList с пустым списком. В Java 7 следующий код
 * * * не будет работать:
 * * * * * processStringList(Collections.emptyList());
 * * *
 * * * Компилятору необходимо значение аргумента типа для T, и он начинает с Object. В результате вызов
 * * * Collections.emptyList возвращает тип List<Object>, который несовместим с методом processStringList.
 * * * Таким образом в Java 7 вы должны указать аргумент типа так:
 * * * * * processStringList(Collections.<String>emptyList());
 * * *
 * * * В Java 8 в этом больше нет необходимости.
 *
 * В обобщённом коде знак вопроса (?), называемый подстановочным символом, означает неизвестный тип.
 * * * Подстановочный символ (wildcard) может использоваться в разных ситуациях: как параметр типа, поля, локальной
 * * * переменной, иногда в качестве возвращаемого типа. Подстановочный символ никогда не используется в качестве
 * * * аргумента типа для вызова обобщённого метода, создания экземпляра обобщённого класса или супертипа.
 */

/**
 * Подстановочный символ, ограниченный сверху (Upper bounded wildcard)                                                *
 * * * Чтобы объявить ограниченный сверху подстановочный символ, используйте символ вопроса «?», с последующим
 * * * ключевым словом extends, с последующим ограничением сверху. Запомните, что в этом контексте extends означает
 * * * как расширение класса, так и реализацию интерфейса.
 * * *
 * * * Чтобы написать метод, который работает со списками Number и дочерними типами от Number, например Integer,      *
 * * * Double и Float, вы можете указать List<? extends Number>. List<Number> вводит более жёсткое ограничение,
 * * * чем List<? extends Number>, потому что оно соответствует только списку типа Number, а List<? extends Number>
 * * * соответствует списку типа Number и спискам всех его подклассов.
 * * *
 * * * Рассмотрим следующий метод process:                                                                            *
 * * * * * public static void process(List<? extends Monster> list) { }
 * * *
 * * * Ограниченный сверху подстановочный символ <? extends Monster>, где Monster - любой тип, соответствует Monster
 * * * и любому подтипу Monster. Метод process может обращаться к элементу списка как к типу Monster:
 * * * * * public static void process(List<? extends Monster> list) {
 * * * * *     for (Monster elem : list) {
 * * * * *         // ...
 * * * * *     }
 * * * * * }
 * * *
 * * * Любой метод класса или интерфейса Monster может быть использован у elem.                                       *
 * * *
 * * * Метод sumOfList возвращает сумму чисел в списке:
 * * * * * public static double sumOfList(List<? extends Number> list) {
 * * * * *     double s = 0.0;
 * * * * *     for (Number n : list)
 * * * * *         s += n.doubleValue();
 * * * * *     return s;
 * * * * * }
 * * * Следующий код использует список Integer-ов и выведет: «sum = 6.0»:
 * * * * * // В лист можно задать любой тип, какой является Number или его наследником
 * * * * * List<Integer> li = Arrays.asList(1, 2, 3);
 * * * * * System.out.println("sum = " + sumOfList(li));
 */

/**
 * Неограниченный подстановочный символ (Unbounded wildcard)                                                          *
 * * * Если просто использовать подстановочный символ, то получится подстановочный символ без ограничений.
 * * * List<?> Означает список неизвестного типа.
 * * *
 * * * Неограниченный подстановочный символ полезен в двух случаях:                                                   *
 * * * - Если вы пишете метод, который может быть реализован с помощью функциональности класса Object.
 * * *
 * * * - Когда код использует методы обобщённого класса, которые не зависят от параметра типа.
 * * *   Например, List.size() или List.clear(). В реальности Class<?> используется так часто, потому что
 * * *   большинство методов Class<T> не зависят от T.
 * * *
 * * * Рассмотрите следующий метод printList:                                                                         *
 * * * * * public static void printList(List<Object> list) {
 * * * * *     for (Object elem : list)
 * * * * *         System.out.println(elem + " ");
 * * * * *     System.out.println();
 * * * * * }
 * * * Цель метода printList - вывод в консоль списка любого типа, но сейчас он её не выполняет, так как он
 * * * может вывести в консоль только список объектов типа Object. Он не может принимать в качестве параметра
 * * * List<Integer>, List<String>, List<Double> и какие-либо ещё, так как они не являются дочерними типами для
 * * * List<Object>. Чтобы сделать этот метод более общим, нужно использовать List<?>:
 * * * * * public static void printList(List<?> list) {
 * * * * *     for (Object elem: list)
 * * * * *         System.out.print(elem + " ");
 * * * * *     System.out.println();
 * * * * * }
 * * * List<A> является дочерним типом для List<?> для любого конкретного типа A, поэтому вы можете использовать
 * * * printList для вывода в консоль списков любого типа:
 * * * * * List<Integer> li = Arrays.asList(1, 2, 3);
 * * * * * List<String>  ls = Arrays.asList("one", "two", "three");
 * * * * * printList(li);
 * * * * * printList(ls);
 * * * (Заметка: Метод Arrays.asList конвертирует массив и возвращает список фиксированного размера.)                 *
 */

/**
 * Ограниченный снизу подстановочный символ (Lower bound Wildcard)                                                    *
 * * * Ограниченный снизу подстановочный символ ограничивает неизвестный тип так, чтобы он был либо указанным типом,
 * * * либо одним из его предков.
 * * * (Вы можете указать либо только верхнюю границу для подстановочного символа, либо только нижнюю, но вы не можете
 * * * указать оба ограничения сразу.)
 * * *
 * * * Допустим, что вы хотите написать метод, который добавляет объекты Integer в список. Чтобы максимизировать
 * * * гибкость, вы можете захотеть, чтобы метод работал с List<Integer>, List<Number> и List<Object> - всё,
 * * * что может хранить экземпляры класса Integer.
 * * *
 * * * Чтобы написать метод, который работает со списком Integer-ов и супертипами Integer-а (такими как Integer,
 * * * Number и Object), вы можете указать List<? super Integer>. Вариант List<Integer> более ограничен,
 * * * чем List<? super Integer>, потому что он позволяет использовать только список объектов типа Integer, тогда как
 * * * List<? super Integer> соответствует спискам любого родительского класса от Integer и списку Integer-ов.
 * * *
 * * * Не смотря на то что Integer является подтипом Number, List<Integer> не является подтипом List<Number>.         *
 * * * Это разные типы. Общим предком для List<Number> и List<Integer> является List<?>.
 * * * Для того чтобы создать такую связь между этими классами, чтобы код мог иметь доступ к методам Number через
 * * * элементы List<Integer>, используйте подстановочный символ:
 * * * * * List<? extends Integer> intList = new ArrayList<>();
 * * * * * // List<? extends Integer> дочерний тип от List<? extends Number>
 * * * * * List<? extends Number> numList = intList; // OK.
 */

/**
 * Захват символа подстановки (Wildcard Capture) и вспомогательные методы
 * * * В некоторых случаях компилятор может вывести тип подстановочного символа. Список может быть определён
 * * * как List<?>, но при вычислении выражения компилятор выведет конкретный тип из кода. Этот сценарий называется
 * * * захватом подстановочного символа.
 * * * Следующий код выводит сообщение об ошибке, связанное с захватом подстановочного символа, при компиляции:
 */
 class WildcardError {
     void foo(List<?> i) {
         // i.set(0, i.get(0)); (WARNING)
     }
 }
/**
 * * * В этом примере компилятор обрабатываем параметр i как тип Object. Когда метод foo вызывает List.set(int, E),
 * * * компилятор не может подтвердить тип объекта, который будет вставляться в список, и генерирует ошибку.
 * * * Когда возникает этот тип ошибки, это обычно означает, что компилятор верит, что вы присваиваете неправильный
 * * * тип переменной.
 * * *
 * * * В нашем же примере код пытается выполнить безопасную операцию, тогда как мы можем обойти ошибку компиляции?
 * * * Вы можете исправить её написав приватный вспомогательный метод (private helper method), который захватывает
 * * * подстановочный символ. В этом случае вы можете обойти проблему с помощь создания приватного вспомогательного
 * * * метода fooHelper():
 */
 class WildcardFixed {
     void foo(List<?> i) {
         fooHelper(i);
     }
     // Вспомогательный метод создан так, чтобы подстановочный символ мог быть захвачен.
     // Через выведение типа.
     private <T> void fooHelper(List<T> l) {
         l.set(0, l.get(0));
     }
 }
/**
 * * * Благодаря вспомогательному методу компилятор использует выведение типа для определения, что T является
 * * * CAP#1 (захваченная переменная в вызове). Пример теперь успешно компилируется.
 * * *
 * * * Теперь рассмотрите более сложный пример, WildcardErrorBad:
 */
 class WildcardErrorBad {
     void swapFirst(List<? extends Number> l1, List<? extends Number> l2) {
         Number temp = l1.get(0);
         // l1.set(0, l2.get(0)); (WARNING)   // ожидается CAP#1 extends Number,
                                              // получаем CAP#2 extends Number;
                                              // одинаковые ограничения, но разные типы
         // l2.set(0, temp);	  (WARNING)   // ожидается CAP#1 extends Number,
         // получаем Number
     }
 }
/**
 * * * В этом примере код пытается выполнить небезопасную операцию. Например, рассмотрите следующий вызов метода
 * * * swapFirst:
 * * * * * List<Integer> li = Arrays.asList(1, 2, 3);
 * * * * * List<Double>  ld = Arrays.asList(10.10, 20.20, 30.30);
 * * * * * swapFirst(li, ld);
 * * *
 * * * Не смотря на то что List<Integer> и List<Double> оба удовлетворяют критерию List<? extends Number>,
 * * * это совершенно неверно брать элемент из списка объектов типа Integer и пытаться добавить его в список
 * * * объектов типа Double.
 * * *
 * * * В этом случае обойти проблему уже не удастся, так как код в принципе неверен.
 */

/**
 * Руководство по использованию подстановочного символа                                                               *
 * * * Когда использовать ограниченный сверху подстановочный символ (wildcard), и когда использовать ограниченный
 * * * снизу подстановочный символ, определить зачастую бывает довольно сложно. Здесь собраны советы по выбору
 * * * необходимого ограничения для подстановочного символа.
 * * *
 * * * В этом обсуждении будет полезно думать о переменных, будто они представляют две функции:
 * * * - Входная переменная. Предоставляет данные для кода. Для метода copy(src, dst) параметр src предоставляет
 * * *   данные для копирования, поэтому он считается входной переменной.
 * * * - Выходная переменная. Содержит данные для использования в другом месте. В примере с copy(src, dst)
 * * *   параметр dst принимает данные и является выходной переменной.
 * * *
 * * * Руководство:
 * * * - Входная переменная определяется с ограниченным сверху подстановочным символом, используя ключевое слово extends.
 * * * - Выходная переменная определяется с ограниченным снизу подстановочным символом, используя ключевое слово super.
 * * * - Если к входной переменной можно обращаться только используя методы класса Object, используйте неограниченный
 * * *   подстановочный символ.
 * * * - Если переменная должна использоваться как входная и как выходная одновременно, то НЕ используйте
 * * *   подстановочный символ.
 * * *
 * * * Это руководство не охватывает использование подстановочных символов в возвращаемых из методов типах.
 * * * Не используйте подстановочные символы в возвращаемых типах, потому что это будет принуждать других
 * * * программистов разбираться с подстановочными символами.
 * * *
 * * * Список, объявленный как List<? extends ...> может неформально считаться как только для чтения,
 * * * но это не строгое ограничение.
 */

/**
 * Стирание типа (Type Erasure)
 * * * Обобщения были введены в язык программирования Java для обеспечения более жёсткого контроля типов во время
 * * * компиляции и для поддержки обобщённого программирования. Для реализации обобщения компилятор Java применяет
 * * * стирание типа (type erasure) к:
 * * * - Заменяет все параметры типа в обобщённых типах их границами или Object-ами, если параметры типа не ограничены.
 * * *   Сгенерированный байткод содержит только обычные классы, интерфейсы и методы.
 * * * - Вставляет приведение типов где необходимо, чтобы сохранить безопасность типа.
 * * * - Генерирует связующие методы, чтобы сохранить полиморфизм в расширенных (extended, наследующиеся от других)
 * * *   обобщённых типах.
 * * *
 * * * При объявлении обычного обобщенного типа параметры типа не ограничены. Говоря иначе компилятор заменяет этот тип
 * * * Object-ом. Если обобщенный тип ограничен ключевым словом extends, то компилятор заменяет обобщенный тип
 * * * родительким типом этого ограничения.
 * * * * * MyClass<T extends Monster> { } // Тип T ограничен родительским типом Monster.
 * * * * *                                // Компилятор заменит все T на Monster.
 * * *
 * * * Стирание типа обеспечивает, что никакие новые классы не создаются для параметризованных типов, следовательно
 * * * обобщения не приводят к накладным расходам во время выполнения.
 * * *
 * * * Рассмотрим следующий обобщённый класс, который представляет узел односвязного списка:
 */
 class Node<T> {
     private T data;
     private Node<T> next;

     public Node(T data, Node<T> next) {
         this.data = data;
         this.next = next;
     }

     public T getData() {
         return data;
     }
     // ...
 }
/**
 * * * Так как параметр T неограничен, то компилятор заменяет его Object-ом:
 */
 class Node2 {
     private Object data;
     private Node2 next;

     public Node2(Object data, Node2 next) {
         this.data = data;
         this.next = next;
     }

     public Object getData() {
         return data;
     }
     // ...
 }
/**
 * * * В следующем примере обобщённый класс Node использует ограниченный параметр:
 */
 class Node3<T extends Comparable<T>> {
     private T data;
     private Node3<T> next;

     public Node3(T data, Node3<T> next) {
         this.data = data;
         this.next = next;
     }

     public T getData() {
         return data;
     }
     // ...
 }
/**
 * * * Компилятор Java заменяет ограниченный параметр T первой границей Comparable:
 */
 class Node4 {
     private Comparable data;
     private Node4 next;

     public Node4(Comparable data, Node4 next) {
         this.data = data;
         this.next = next;
     }

     public Comparable getData() {
         return data;
     }
     // ...
 }

/**
 * Методы-мосты (Bridge Methods)
 * * * При компиляции класса или интерфейса, который расширяет параметризованный класс или реализует
 * * * параметризованный интерфейс, компилятор требует создания синтетического метода, называемого методом-мостом
 * * * (bridge method). Обычно вам не придётся беспокоиться об этих методах, но вы можете быть озадачены, если он
 * * * появится в трассировке стека:
 * * *
 * * * Пусть есть два класса:
 */
 class Node7<T> {
     public T data;

     public Node7(T data) { this.data = data; }

     public void setData(T data) {
         System.out.println("Node.setData");
         this.data = data;
     }
 }

 class MyNode7 extends Node7<Integer> {

     public MyNode7(Integer data) { super(data); }

     public void setData(Integer data) {
         System.out.println("MyNode.setData");
         super.setData(data);
     }
 }
/**
 * После стирания типа классы Node и MyNode станут такими:
 */
 class Node8 {
     public Object data;

     public Node8(Object data) { this.data = data; }

     public void setData(Object data) {
         System.out.println("Node.setData");
         this.data = data;
     }
 }

 class MyNode8 extends Node8 {

     public MyNode8(Integer data) { super(data); }

     public void setData(Integer data) {
         System.out.println("MyNode.setData");
         super.setData(data);
     }
 }
/**
 * После стирания типа сигнатуры методов не совпадают. Метод из класса Node становится setData(Object), а метод
 * * * из класса MyNode становится setData(Integer), поэтому метод setData из класса MyNode не переопределяет метод
 * * * setData из класса Node.
 * * *
 * * * Чтобы исправить проблему и сохранить полиморфизм обобщённых типов после стирания типа, компилятор Java
 * * * генерирует методы-мосты, для того чтобы расширение работало как ожидается. Для класса MyNode компилятор
 * * * генерирует следующий метод-мост setData:
 */
 class MyNode9 extends Node8 {

     public MyNode9(Integer data) { super(data); }

     // Метод-мост, сгенерированный компилятором
     public void setData(Object data) {
         setData((Integer) data);
     }

     public void setData(Integer data) {
         System.out.println("MyNode.setData");
         super.setData(data);
     }
 }

/**
 * Нематериализуемые типы (Non-Reifiable Types)
 * * * Я так и не смог нормально перевести слово “Non-Reifiable”, но пусть будет переводиться как «нематериализуемые».
 * * *
 * * * В разделе стирания типов обсуждается процесс, где компилятор удаляет информацию, связанную с параметрами типа
 * * * и аргументами типа. Стирание типа имеет последствия, связанные с произвольным количеством параметров (varargs).
 * * *
 * * * Материализуемые типы (reifiable types) - это типы, информация о которых полностью доступа во время выполнения:
 * * * примитивы, необобщённые типы, сырые типы, обращения к неограниченным подстановочным символам.
 * * *
 * * * Нематериализуемые типы (Non-reifiable types) - это типы, информация о которых удаляется во время компиляции
 * * * стиранием типов: обращения к обобщённым типам, которые не объявлены с помощью неограниченных подстановочных
 * * * символов. Во время выполнения о нематериализуемых типах (Non-reifiable types) нет всей информации. Примеры
 * * * нематериализуемых типов: List<String> и List<Number>. Виртуальная машина Java не может узнать разницу между
 * * * ними во время выполнения. В некоторых ситуациях нематериализуемые типы не могут использоваться, например
 * * * в выражениях instanceof или в качестве элементов массива.
 */

/**
 * Загрязнение кучи (heap pollution) возникает, когда переменная параметризованного типа ссылается на объект,
 * * * который не является параметризованным типом.
 * * * Предупреждение unchecked warning генерируется, если правильность операции, в которую вовлечён
 * * * параметризованный тип (например приведение типа или вызов метода) не может быть проверена. Например,
 * * * загрязнение кучи возникает при смешивании сырых типов и параметризованных типов, или при осуществлении
 * * * непроверяемых преобразований типа.
 * * *
 * * * Если вы обеспечите компиляцию кода без предупреждений, то загрязнение кучи (heap pollution) не сможет произойти.
 */

/**
 * Потенциальные уязвимости методов с произвольным числом параметров с нематериализуемыми формальными параметрами
 * * * Обобщённые методы, которые включают произвольное число параметров могут привести к загрязнению кучи.
 * * *
 * * * Рассмотрите следующий класс ArrayBuilder:
 */
 class ArrayBuilder {
     public static <T> void addToList (List<T> listArg, T... elements) {
         for (T x : elements) {
             listArg.add(x);
         }
     }

     public static void faultyMethod(List<String>... l) {
         Object[] objectArray = l;     // Valid
         objectArray[0] = Arrays.asList(42);
         String s = l[0].get(0);       // Здесь генерируется ClassCastException
     }
 }
/**
 * * * Следующий пример использует класс ArrayBuilder:
 */
class HeapPollutionExample {
    public static void main(String[] args) {
        List<String> stringListA = new ArrayList<>();
        List<String> stringListB = new ArrayList<>();

        ArrayBuilder.addToList(stringListA, "Seven", "Eight", "Nine");
        ArrayBuilder.addToList(stringListB, "Ten", "Eleven", "Twelve");
        List<List<String>> listOfStringLists = new ArrayList<List<String>>();
        ArrayBuilder.addToList(listOfStringLists, stringListA, stringListB);
        ArrayBuilder.faultyMethod(Arrays.asList("Hello!"), Arrays.asList("World!"));
    }
}
/**
 * * * При компиляции возникает следующее предупреждение на определении метода ArrayBuilder.addToList:
 * * * * * warning: [varargs] Possible heap pollution from parameterized vararg type T
 * * *
 * * * Когда компилятор сталкивается с методом с произвольным числом параметров (varargs-метод), он преобразует
 * * * varargs-параметр в массив. Однако Java не запрещает создание массивов с параметризованными типами.
 * * * В методе ArrayBuilder.addToList компилятор преобразует формальный параметр T... elements в формальный
 * * * параметр T[] elements. Но из-за стирания типа компилятор конвертирует формальный параметр в Object[] elements,
 * * * поэтому возникает загрязнение кучи.
 */

/**
 * Подавление предупреждений для методов с произвольным количеством параметров с нематериализуемыми формальными
 * * * параметрами.
 * * * Если вы объявили метод с произвольным числом параметров параметризованного типа и обеспечили то, что тело
 * * * метода не бросает исключение ClassCastException  или другое похожее исключение, связанное с неправильной
 * * * обработкой varargs-параметра, то вы можете отключить предупреждение, которое компилятор генерирует для этих
 * * * методов при помощи добавления аннотации к статическому методу и методу неконструктору:
 * * * * * @SafeVarargs
 * * * Аннотация @SafeVarargs относится к документируемой части объявления метода. Эта аннотация говорит, что эта
 * * * реализация метода корректно обрабатывает varargs-параметр.
 */

/**
 * ОГРАНИЧЕНИЯ ОБОБЩЕНИЙ
 * - Нельзя создавать экземпляры обобщённых типов с примитивными типами в качестве аргументов типа.                   *
 * * * Pair<int, char> p = new Pair<>(8, 'a'); // compile-time error
 *
 * - Нельзя создавать экземпляры параметров типа                                                                      *
 * * * В качестве обходного пути вы можете создать объект параметра типа с помощью отражения (reflection):
 * * * * * public static <E> void append(List<E> list, Class<E> cls) throws Exception {
 * * * * *     E elem = cls.newInstance();   // OK
 * * * * *     list.add(elem);
 * * * * * }
 * * * Вы можете вызвать метод append вот так:
 * * * * * List<String> ls = new ArrayList<>();
 * * * * * append(ls, String.class);
 *
 * - Нельзя объявлять статические поля с типом параметра типа                                                         *
 * * * Статические поля класса являются общими для всех объектов этого класса, поэтому статические поля с типом
 * * * параметра типа запрещены. Рассмотрите следующий класс:
 * * * * * public class MobileDevice<T> {
 * * * * *     private static T os;
 * * * * *     // ...
 * * * * * }
 * * * Если бы статические поля с типом параметра типа были бы разрешены, то следующий код сбивал бы с толку:
 * * * * * MobileDevice<Smartphone> phone = new MobileDevice<>();
 * * * * * MobileDevice<Pager> pager = new MobileDevice<>();
 * * * * * MobileDevice<TabletPC> pc = new MobileDevice<>();
 * * * Так как статическое поле os является общим для phone, pager и pc, то какого типа os? Оно не может быть
 * * * Smartphone, Pager и TabletPC в одно и то же время, поэтому вы не можете создавать статические поля с типом
 * * * параметра типа.
 *
 * - Нельзя использовать приведения типа или instanceof с параметризованными типами                                   *
 * * * Так как компилятор Java стирает все параметры типа из обобщённого кода, то вы не можете проверить во время
 * * * выполнения, какой параметризованный тип используется для обобщённого типа:
 * * * * * public static <E> void rtti(List<E> list) {
 * * * * *     if (list instanceof ArrayList<Integer>) { // compile-time error
 * * * * *         // ...
 * * * * *     }
 * * * * * }
 * * *
 * * * Обычно вы не можете использовать приведение типа к параметризованному типу, если он не использует
 * * * неограниченный подстановочный символ. Например:
 * * * * * List<Integer> li = new ArrayList<>();
 * * * * * List<Number>  ln = (List<Number>) li; // ошибка компиляции
 *
 * - Невозможно создавать массивы параметризованных типов                                                             *
 * * * Вы не можете создавать массивы параметризованных типов. Например, следующий код не будет компилироваться:
 * * * * * List<Integer>[] arrayOfLists = new List<Integer>[2]; // ошибка компиляции
 *
 * - Нельзя создавать, ловить (catch) или бросать (throw) объекты параметризованных типов                             *
 * * * Обобщённый класс не может расширять класс Throwable напрямую или ненапрямую. Например, следующие классы
 * * * не компилируются:
 * * * * * // Расширяет Throwable ненапрямую
 * * * * * class MathException<T> extends Exception { } // ошибка компиляции
 * * * * *
 * * * * * // Расширяет Throwable напрямую
 * * * * * class QueueFullException<T> extends Throwable { } // ошибка компиляции
 * * *
 * * * Метод не может ловить (catch) экземпляр параметра типа:
 * * * * * public static <T extends Exception, J> void execute(List<J> jobs) {
 * * * * *     try {
 * * * * *         for (J job : jobs)
 * * * * *         // ...
 * * * * *     } catch (T e) { // ошибка компиляции
 * * * * *         // ...
 * * * * *     }
 * * * * * }
 * * * Однако вы можете использовать параметр типа в клаузе throws:
 */
 class Parser<T extends Exception> {
     public void parse(File file) throws T {     // OK
         // ...
     }
 }
/**
 * - Нельзя перегружать метод так, чтобы формальные параметры типа стирались в один и тот же сырой тип                *
 * * * Класс не может иметь два перегруженных метода, которые будут иметь одинаковую сигнатуру после стирания типов:
 * * * * * public class Example {
 * * * * *     public void print(Set<String> strSet) { }
 * * * * *     public void print(Set<Integer> intSet) { }
 * * * * * }
 */

public class Generics {
}
