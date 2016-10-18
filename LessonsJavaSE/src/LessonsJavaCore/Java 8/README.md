## Нововведения в Java 8
_**Какие же новшества предлагает последняя версия Java 8?** В языке
появились **лямбда-выражения**, **методы интерфейсов по умолчанию**,
**ссылки на методы** и **повторяемые аннотации**. Это основные новинки
релиза._

_Рассмотрим подробнее и на простых примерах каждый новый инструмент языка._

#### Методы интерфейсов по умолчанию
Теперь Java позволяет добавлять неабстрактные реализации методов в интерфейс, 
или так называемые методы расширения. Для этого используется ключевое слово `default`.

_Рассмотрим пример такого добавления:_

    interface Formula (
        double calculate(int a);
        default double sqrt(int a) {
            return Math.sqrt(a);
        }
    }

Помимо абстрактного метода `calculate` в интерфейсе `Formula` так же по умолчанию 
определяется метод `sqrt`. Классы, реализующие этот интерфейс, должны переопределить 
только абстрактный метод `calculate`. **Метод по умолчанию `sqrt` будет доступен 
без переопределения.**

    Formula formula = new Formula() {
        @0verride
        public double calculate(int a) {
            return sqrt(a * 100);
        }
    };
    formula.calculate(100); // 100.0
    formula.sqrt(16); //4.0
    
Здесь объект `formula` реализован как анонимный объект. Кроме того, код в этом примере 
достаточно избыточен: целых шесть строчек для такого простого вычисления, как 
`sqrt(а * 100)`. Немного позже мы рассмотрим более изящный способ реализации объектов 
с одним методом.

#### Лямбда-выражения
Прежде чем мы приступим к объяснению, что такое лямбда-выражения, давайте рассмотрим 
пример сортировки массива строк в предыдущих версиях языка. Код такой сортировки 
будет выглядит примерно так:

    List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
    Collections.sort(names, new Comparator<String>() {
        @0verride
        public int compare(String a, String b) {
            return b.compareTo(a);
        }
    });

Java 8 предоставляет гораздо более короткий синтаксис — лямбда-выражения. Они добавлены 
в язык для того, чтобы вам не приходилось тратить время на создание анонимных объектов.

    Collections.sort(names, (String a, String b) -> (
        return b.compareTo(a);
    });

Теперь код стал гораздо короче и более читаем. Давайте сделаем его еще короче и удобнее:

    Collections.sort(names, (String a, String b) -> b.compareTo (a));
    
_**Для однострочных методов позволяется опустить фигурные скобки и ключевое слово `return`.
Поскольку компилятору известны типы параметров, здесь их можно тоже опустить.**
Вот так код стал еще короче:_

    Collections.sort(names, (a, b) -> b.compareTo(a));
    
Рассмотрим несколько способов применения лямбда-выражений. 

#### Функциональные интерфейсы
Как лямбда-выражения соответствуют системе типов языка Java? Каждой лямбде соответствует тип, 
представленный интерфейсом. Так называемый функциональный интерфейс должен содержать ровно
один абстрактный метод. Каждое лямбда-выражение этого типа будет сопоставлено объявленному 
методу. Поскольку методы по умолчанию не являются абстрактными, вы можете добавлять 
в функциональный интерфейс сколько угодно таких методов.

Для лямбда-выражений разрешается использовать любые интерфейсы, содержащие только один 
абстрактный класс. Чтобы гарантировать, что написанный вами интерфейс соответствует этому 
требованию, следует использовать аннотацию `@FunctionalInterface`. Компилятор «поймет»,
что означает эта аннотация, и выдаст ошибку, если вас что-то отвлечет и вы добавите второй 
абстрактный метод в функциональный интерфейс.

Рассмотрим пример:

    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }
    Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
    Integer converted = converter.convert("123");
    System.out.println(converted); // 123

Обратите внимание, что даже если убрать аннотацию `@FunctionalInterface`, код в примере 
по-прежнему останется корректным.

#### Ссылки на методы и конструкторы
Предыдущий пример можно несколько упростить, если использовать статические ссылки на методы. 
Тогда он будет иметь следующий вид:

    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }
    Converter<String, Integer> converter = Integer::valueOf;
    Integer converted = converter.convert("123");
    System.out.println(converted); // 123

Java 8 позволяет вам передавать ссылки на методы или конструкторы. Для этого используется 
комбинация из двух символов двоеточия `::`.

В предыдущем примере продемонстрирована передача ссылки на статический метод.

Однако аналогичным образом можно ссылаться и на экземплярный метод.

Рассмотрим пример:

    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }
    class Something {
        String startsWith(String s) (
            return String.valueOf(s.charAt(0));
        }
    }
    Something something = new Something();
    // Присваиваем интерфейсу конкретную реализацию класса
    Converter<String, String> converter = something::startsWith;
    // Теперь когда мы обращаемся к методу интерфейса, он в свою очередь 
    // обращается к методу класса, какой мы передали
    String converted = converter.convert("Java");
    System.out.println(converted); // "J"
    
Теперь рассмотрим пример передачи ссылки на конструктор. Сначала создадим класс с несколькими 
конструкторами:

    class Person {
        String firstName;
        String lastName;
        Person () {}
        Person (String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }
    
Теперь определим интерфейс для «фабрики», которая будет использоваться для создания новых «персон»:

    interface PersonFactory<P extends Person» {
        P create(String firstName, String lastName);
    }
    
Теперь вместо реализации интерфейса соединим все вместе при помощи ссылки па конструктор:

    PersonFactory<Person> personFactory = Person::new;
    Person person = personFactory.create("Peter", "Parker");

Здесь ссылка на конструктор создается с помощью `Person::new`. Компилятор автоматически выбирает 
подходящий конструктор, сигнатура которого совпадает с сигнатурой `PersonFactory.create`.

#### Области действия лямбд
Доступ к переменным внешней области действия из лямбда-выражения очень схож с доступом из 
анонимных объектов.

Вы можете ссылаться на переменные, объявленные как `final`, на экземплярные поля класса и 
статические переменные.

Рассмотрим пример:

    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }
    int num = 1;
    Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num);
    stringConverter.convert (2); // 3

Однако переменная num не должна изменять значение. Следующий пример кода не будет скомпилирован:

    int num = 1;
    Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num);
    num = 3;

Запись в переменную num в пределах лямбда-выражения также запрещена.

#### Доступ к полям и статическим переменным
В отличие от локальных переменных, мы можем записывать значения в экземплярные поля класса и 
статические переменные внутри лямбдавыражений. Это поведение хорошо знакомо по анонимным объектам.

Рассмотрим следующий пример:

    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }
    class Lambda4 {
        static int outerStaticNum;
        int outerNum;
        void testScopes() {
            Converter<Integer, String> stringConverter1 = (from) -> {
                outerNum = 23;
                return String.valueOf(from) ;
            };
            Converter<Integer, String> stringConverter2 = (from) -> {
                outerStaticNum = 72;
                return String.valueOf(from);
            };
        }
    }

#### Доступ к методам интерфейсов по умолчанию
Рассмотрим еще раз самый первый пример главы. В интерфейсе `Formula` определен метод по умолчанию 
`sqrt`, который доступен из каждой реализации интерфейса, включая анонимные объекты. Однако это
не сработает в лямбда-выражениях.

Внутри лямбда-выражений запрещено обращаться к методам по умолчанию. Обратите внимание на 
приведенный в следующем примере код — этот код не будет скомпилирован.

    Formula formula = (а) -> sqrt( а * 100);

## Встроенные функциональные интерфейсы

#### Предикаты
_**Предикаты** — это функции, принимающие один аргумент и возвращающие значение логического 
типа `boolean`._

Интерфейс содержит различные методы по умолчанию, позволяющие строить сложные инструкции 
(например, «И», «ИЛИ», «НЕ»).

Рассмотрим пример:

    Predicate<String> predicate = (s) -> s.length() > 0;
    predicate.test("foo"); // true
    predicate.negated.test("foo"); // false
    Predicate<Boolean> nonNull = Objects::nonNull;
    Predicate<Boolean> isNull = Objects::isNull;
    Predicate<String> isEmpty = String::isEmpty;
    Predicate<String> isNotEmpty = isEmpty.negate();

#### Функции
_Функции принимают один аргумент и возвращают некоторый результат. Методы по умолчанию могут 
использоваться для построения цепочек вызовов (`compose`, `andThen`)._

    Function<String, Integer> toInteger = Integer::valueOf;
    Function<String, String> backToString = toInteger.
    andThen(String::valueOf);
    backToString.apply("123"); // "123"

#### Поставщики
_Поставщики возвращают результат заданного типа. В отличие от функций, поставщики не принимают 
никаких аргументов._ Пример:

    Supplier<Person> personSupplier = Person::new;
    personSupplier.get(); // new Person

#### Потребители
_Потребители представляют собой операции, которые производятся над одним переданным в потребитель 
аргументом._

    Consumer<Person> greeter = (р) -> System.out.println("Hello, " + p.firstName);
    greeter.accept(new Person("Luke", "Skywalker"));

#### Компараторы
_Компараторы описаны для предыдущих версий Java и хорошо известны. Java 8 добавляет в интерфейс 
различные методы по умолчанию._ Рассмотрим пример:

    Comparator<Person> comparator = (pi, р2) -> pl.firstName.compareTo(p2.firstName);
    Person pi = new Person("John", "Doe");
    Person p2 = new Person("Alice", "Wonderland");
    comparator.compare(pi, p2); // > 0
    comparator.reversed)).compare(pi, p2); // < 0
    
#### Опциональные значения
Опциональные значения (`optionals`) не являются функциональными интерфейсами, однако являются 
удобным средством предотвращения ошибок типа `NullPointerException`. Это важная концепция, поэтому 
давайте разберемся, как работают опциональные значения.

Опциональные значение — это, по сути, контейнер для значения, которое может быть равно `null`. 
Например, вам нужен метод, который обычно возвращает некоторое значение, однако иногда он должен 
возвращать пустое значение. Вместо того чтобы возвращать `null`, в Java 8 вы можете вернуть 
опциональное значение.

Рассмотрим пример кода:

    Optional<String> optional = Optional.of("bam");
    optional.isPresent(); // true
    optional.get(); // "bam"
    optional.orElse("fallback"); // "bam"
    optional.ifPresent((s) -> System.out.println(s.charAt(0))); // "b"

### Потоки
Потоковый тип `java,util.Stream` представляет собой последовательность элементов, над которой 
можно производить различные операции. Операции над потоками могут быть промежуточными (`intermediate`) 
или конечными (`terminal`). Конечные операции возвращают результат определенного типа, а промежуточные 
операции возвращают тот же поток. Таким образом, вы можете строить цепочки из несколько операций
над одним и тем же потоком. Потоки создаются на основе источников, например типов, реализующих 
коллекции `java.util.Collection`, такие как списки или множества (обратите внимание, что ассоциативные 
массивы здесь не поддерживаются). Операции над потоками могут выполняться как последовательно, 
так и параллельно.

Сначала давайте посмотрим, как работать с потоком последовательно. Для начала создадим источник 
в виде списка строк:

    List<String> stringCollection = new ArrayList<>();
    stringCollection.add("ddd2");
    stringCollection.add("aaa2");
    stringCollection.add("bbbl");
    stringCollection.add("aaal");
    stringCollection.add("bbb3");
    stringCollection.add("ccc");
    stringCollection.add("bbb2");
    stringCollection.add("dddl");

В Java 8 вы можете быстро создавать потоки, используя вызовы методов Collection.stream() или 
Collection.parallelStream(). Далее рассмотрим подробнее наиболее распространенные операции над потоками.

#### Фильтр
Операция фильтра (`Filter`) принимает предикат, который фильтрует все элементы потока. Эта операция 
является промежуточной, иными словами, она позволяет вызвать другую операцию (например, `forEach`),
которая будет использоваться для обработки результата промежуточной операции. `ForEach` принимает 
функцию, которая вызывается для каждого элемента в уже отфильтрованном потоке. `ForEach` является 
конечной операцией. Она не возращает никакого значения, поэтому дальнейший вызов потоковых операций 
для данного потока невозможен.

Рассмотрим пример кода:

    stringCollection.stream()
                    .filter ((s) -> s.startsWith("a"))
                    .forEach(System.out::println); // "aaa2", "aaal"

#### Сортировка
Операция сортировки (`Sorted`) является промежуточной операцией, которая возвращает отсортированное 
представление потока. Элементы сортируются в обычном порядке, если не передается дополнительный
компаратор:

    stringCollection.stream()
                    .sorted()
                    .filter((s) -> s.startsWith("a"))
                    .forEach(System.out::println); // "aaal", "aaa2"
                    
Следует помнить, что `sorted` создает всего лишь отсортированное представление и не влияет на 
порядок элементов в исходной коллекции. Порядок строк в `stringCollection` остается нетронутым:                  

    System.out.println(stringCollection); // ddd2, aaa2, bbbl, aaal, bbb3, ccc, bbb2, dddl
    
#### Преобразование `map`
Операция преобразования `map` является промежуточной. Она преобразует каждый элемент в другой 
объект при помощи переданной функции. Следующий пример преобразует каждую строку в строку из 
символов в верхнем регистре. Однако вы также можете использовать `mар` для преобразования каждого 
объекта в объект другого типа. Тип результирующего потока зависит от типа функции, которая 
передается при вызове `mар`.

Рассмотрим пример:

    stringCollection.stream()
                    .map(String::toUpperCase)
                    .sorted((a, b) -> b.compareTo(a))
                    .forEach(System.out::println); // "DDD2", "DDD1", "CCC", "BBB3", "BBB2", "AAA2", "AAA1"

#### Соответствие
Для проверки, удовлетворяет ли поток заданному предикату, используются различные операции 
сопоставления (`match`). Все операции сопоставления являются конечными и возвращают результат 
логического типа `boolean`, который может иметь значение `true` или `false`, соответственно 1 или 0.

Пример:

    // anyMatch - любое совпадение. Если хоть один элемент начинается на "a", то вернет true
    boolean anyStartsWithA = stringCollection.stream().anyMatch((s) -> s.startsWith("a"));
    System.out.println(anyStartsWithA); // true
    
    // allMatch - совпадение по всем. Если все элементы начинаются на "a", то вернет true
    boolean allStartsWithA = stringCollection.stream().allMatch((s) -> s.startsWith("a"));
    System.out.println(allStartsWithA); // false
    
    // noneMatch - ни одно совпадение. Если ни один элемент не начинается на "z", то вернет true
    boolean noneStartsWithZ = stringCollection.stream().noneMatch((s) -> s.startsWith("z"));
    System.out.println(noneStartsWithZ); // true

#### Количество элементов
Операция `Count` является конечной и возвращает количество элементов в потоке. Возвращаемое значение 
имеет числовой тип `long`.

    long startsWithB = stringCollection.stream()
                                       .filter((s) -> s.startsWith("b"))
                                       .count(); // Возвращает кол-во элементов, какие начинаются на "b"
                                       
    System.out.println(startsWithB); // 3

#### Сокращение
Эта операция производит свертку элементов потока по заданной функции, то есть все значения сворачивает 
воедино, она является конечной. Результат выполнения функции опциональное значение.

Рассмотрим пример:

    Optional<String> reduced = stringCollection.stream()
                                               .sorted()
                                               .reduce((s1, s2) -> s1 + "#" + s2);
                                               
    // ifPresent - если присутствует хоть какое-то значение, то выведем его.                                               
    reduced.ifPresent(System.out::println); // "aaa1#aaa2#bbbl#bbb2#bbb3#ccc#dddl#ddd2"

### Параллельные потоки
Как говорилось ранее, потоки могут быть последовательными и параллельными. Операции над последовательными 
потоками выполняются в одном потоке процессора, операции над параллельными потоками используют несколько 
потоков процессора.

Следующий пример демонстрирует, как можно легко увеличить скорость работы, используя параллельные потоки.

Для начала создадим большой список из уникальных элементов:

    int max = 1000000;
    List<String> values = new ArrayList<>(max);
    for (int i = 0; i < max; i++) {
        UUID uuid = UUID.randomUUID();
        values.add(uuid.toString()) ;
    }

Теперь измерим время сортировки этого списка используя различные методы сортировки.

***Последовательная сортировка***

    long t0 = System.nanoTime();
    long count = values.stream().sorted().count();
    System.out.println(count);
    long tl = System.nanoTime();
    
    long millis = TimeUnit.NANOSECONDS.toMillis(tl - t0); 
    System.out.println(String.format("sequential sort took: %d ms", millis));

Время сортировки последовательным способом заняло 899 мс.

***Параллельная сортировка***

    long t0 = System.nanoTime();
    long count = values.parallelStream().sorted().count();
    System.out.println(count);
    long tl = System.nanoTime();
    
    long millis = TimeUnit.NANOSECONDS.toMillis(tl - t0); 
    System.out.println(String.format("parallel sort took: %d ms", millis));

Время сортировки параллельным способом заняло 472 мс.

Очевидно, что оба примера кода практически идентичны, однако параллельная сортировка почти 
в два раза быстрее. Все, что нужно сделать, это заменить вызов `stream()` на `parallelStream()`.

### Ассоциативные массивы
Ранее уже говорилось, что ассоциативные массивы (`maps`) не поддерживают потоки. Вместо этого 
ассоциативные массивы в новом релизе Java поддерживают различные полезные методы, которые решают 
часто встречающиеся при разработке приложений задачи.

    Map<Integer, String> map = new HashMap<>();
    for (int i = 0; i < 10; i++) (
        map.putIfAbsent(i, "val" + i); // putIfAbsent - добавить, если отсутствует
    }
    map.forEach((id, val) -> System.out.println(val));

Давайте добавим несколько комментариев к приведенному выше примеру. Итак, `putIfAbsent` позволяет нам 
не писать дополнительные проверки на `null`, `forEach` принимает потребителя, который производит операцию 
над каждым элементом массива. Следующий пример демонстрирует, как код используется для вычислений при 
помощи различных функций:

    map.computeIfPresent(3, (num, val) -> val + num); // computeIfPresent - вычислить, если присутствует
    map.get(3); // val33
    
    map.computeIfPresent(9, (num, val) -> null);
    map.containsKey(9); // false
    
    шар.computeIfPresent(23, num -> "val" + num);
    map.containsKey(23); // true
    
    map.computeIfPresent(3, num -> "bam”);
    map.get(3); // val33

Чтобы удалить объект по ключу, в случае когда объект ассоциирован с ключом, можно использовать 
следующий код:

    map.remove(3, "val3");
    map.get(3); // val33
    map.remove(3, "val33");
    map.get(3); // null

И еще один полезный метод: 

    map.getOrDefault(42, "not found"); // not found
    
Если нужно объединить записи двух массивов, используйте следующий пример:

    map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
    map.get(9); // val9
    map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
    map.get(9); // val9concat
    
В случае отсутствия ключа функция Merge создает новую пару «ключ/зиачеиие». В противном случае 
вызывает функцию объединения для существующего значения.
    