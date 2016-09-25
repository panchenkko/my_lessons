# ПРИНЦИПЫ ПРОЕКТИРОВАНИЯ
- **Инкапсулируйте то, что изменяется.**
_Выделите аспекты приложения, которые могут изменяться, и отделите их от тех, которые всегда остаются постоянными. 
 То что изменяется заменить абстракцией и под него создавать конкретную реализацию. Как показано в паттерне "Стратегия"._
![CC0](https://github.com/Panchenko-Vlad/java-lessons/tree/master/LessonsJavaSE/src/Books/HeadFirst/Screenshots/principles1.png)
 
- **Отдавайте предпочтение композиции перед наследованием.**
_Отношение СОДЕРЖИТ гораздо лучше отношения ЯВЛЯЕТСЯ._

- **Программируйте на уровне интерфейсов, а не на уровне реализации.**
_Принцип гласит, чтобы зависимость была только от интерфейсов и под него уже создавать конкретную реализацию._

- **Стремитесь к слабой связанности взаимодействующих объектов.**
![CC0](https://github.com/Panchenko-Vlad/java-lessons/tree/master/LessonsJavaSE/src/Books/HeadFirst/Screenshots/principles2.png)

- **Классы должны быть открыты для расширения, но закрыты для изменения.**  ***SOLID***
![CC0](https://github.com/Panchenko-Vlad/java-lessons/tree/master/LessonsJavaSE/src/Books/HeadFirst/Screenshots/principles3.png)

- **Код должен зависеть от абстракции, а не от конкретных классов.**        ***SOLID***

- **Взаимодействуйте только с "друзьями".**
![CC0](https://github.com/Panchenko-Vlad/java-lessons/tree/master/LessonsJavaSE/src/Books/HeadFirst/Screenshots/principles4.png)

- **Не вызывайте нас - мы вас сами вызовем.**
![CC0](https://github.com/Panchenko-Vlad/java-lessons/tree/master/LessonsJavaSE/src/Books/HeadFirst/Screenshots/principles5.png)

- **Класс должен иметь только одну причину для изменений.**                 ***SOLID***

## КЛЮЧЕВЫЕ МОМЕНТЫ
- Решение об использовании паттернов должно естественно следовать из архитектуры. Не используйте 
  паттерны только ради паттернов.
- Паттерны проектирования - не догма; адаптируйте и подстраивайте их для своих потребностей.
- Всегда выбирайте самое простое решение, соответствующее вашим потребностям, даже если в нем 
  не используются паттерны.
- Изучайте каталоги паттернов, чтобы поближе познакомиться с паттернами и отношениями между ними.
- Классификация паттернов обеспечивает их логическую группировку.
- Для создания новых паттернов необходимо время и терпение. Приготовьтесь вносить многократные 
  изменения в свою работу.
- Большинство "новых" паттернов, с которыми вы столкнетесь, будет представлять собой модификации 
  существующих паттернов.
- Формируйте общую терминологию вашей рабочей группы. Это одно из важэнейших преимуществ паттернов.
- В сообществе паттернов, как и в любом другом сообществе, существует свой жаргон. Пусть он вас 
  не пугает - после прочтения этой книги вы знаете большую часть терминов.

# РУКОВОДСТВО ПО ЭФФЕКТИВНОМУ ИСПОЛЬЗОВАНИЮ ПАТТЕРНОВ

-------------------------------------------

***Паттерн - решение задачи в контексте.***

-------------------------------------------

**Контекстом** называется ситуация, в которой применяется паттерн. Ситуация должна быть достаточно 
типичной и распространенной.

**Задачей** называется цель, которой вы хотите добиться в контексте, в совокупности со всеми ограничениями, 
присущими контексту.

**Решением** называется обобщенная архитектура, которая достигает заданной цели при соблюдении набора 
ограничений.

![CC0](https://github.com/Panchenko-Vlad/java-lessons/tree/master/LessonsJavaSE/src/Books/HeadFirst/Screenshots/manual1.png)

## Классификация паттернов проектирования

#### Классификация по категориям: Порождающие, Поведенческие, Структурные.

- **Порождающие паттерны** связаны с созданием экземпляров объектов; все они обеспечивают средства 
логической изоляции клиента от создаваемых объектов.

- Паттерны, относящиеся к **поведенческой категории**, относятся к взаимодействиям и распределению 
обязанностей между классами и объектами.

- **Структурные паттерны** объединяют классы или объекты в более крупные структуры.

#### Классификация паттернов по другому аттрибуту: в зависимости от того, относится паттерн к классам или объектам.

- **Паттерны классов** описывают определение отношенрий между классами посредством наследования. 
  Отношения в паттернах классов определяются на стадии компиляции.
- **Паттерны объектов** описывают отношения между объектами, прежде всего относящиеся к композиции. 
  Отношения в паттернах объектов обычно определяются на стадии выполнения, а следовательно, 
  обладают большей динамичностью и гибкостью.

КРАТКОЕ ПОЯСНЕНИЕ ВСЕХ ПАТТЕРНОВ
================================

Стратегия/Strategy
------------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/tree/master/LessonsJavaSE/src/Books/src/HeadFirst/Strategy_1/Screenshots/07_strategy.png)

Наблюдатель/Observer
--------------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/tree/master/LessonsJavaSE/src/Books/src/HeadFirst/Observer_2/Screenshots/03_observer.png)

Декоратор/Decorator
-------------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/tree/master/LessonsJavaSE/src/Books/HeadFirst/Decorator_3/Screenshots/18_decorator.png)

Фабричный Метод/Factory Method
------------------------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/tree/master/LessonsJavaSE/src/Books/HeadFirst/Factory_4/Screenshots/19_factorymethod.png)

Абстрактная Фабрика/Abstract Factory
------------------------------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/tree/master/LessonsJavaSE/src/Books/HeadFirst/Factory_4/Screenshots/14_abstractfactory.png)

Одиночка/Singleton
------------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/tree/master/LessonsJavaSE/src/Books/HeadFirst/Singleton_5/Screenshots/23_singleton.png)

Команда/Command
---------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/tree/master/LessonsJavaSE/src/Books/HeadFirst/Command_6/Screenshots/04_command.png)

Адаптер/Adapter
---------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/tree/master/LessonsJavaSE/src/Books/HeadFirst/Adapter_7/Screenshots/12_adapter.png)

Фасад/Facade
------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/tree/master/LessonsJavaSE/src/Books/HeadFirst/Facade_8/Screenshots/21_facade.png)

Шаблонный Метод/Template Method
-------------------------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/tree/master/LessonsJavaSE/src/Books/HeadFirst/TemplateMethod_9/Screenshots/09_templatemethod.png)

Итератор/Iterator
-----------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/tree/master/LessonsJavaSE/src/Books/HeadFirst/Iterator_10/Screenshots/08_iterator.png)

Компоновщик/Composite
---------------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/tree/master/LessonsJavaSE/src/Books/HeadFirst/Composite_11/Screenshots/16_composite.png)

Состояние/State
---------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/tree/master/LessonsJavaSE/src/Books/HeadFirst/State_12/Screenshots/05_state.png)

Заместитель/Proxy
-----------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/tree/master/LessonsJavaSE/src/Books/HeadFirst/Proxy_13/Screenshots/13_proxy.png)

Мост/Bridge
-----------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/tree/master/LessonsJavaSE/src/Books/HeadFirst/Screenshots/15_bridge.png)

Строитель/Builder
-----------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/tree/master/LessonsJavaSE/src/Books/HeadFirst/Screenshots/17_builder.png)

Цепочка Обязанностей/Chain Of Responsibility
--------------------------------------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/tree/master/LessonsJavaSE/src/Books/HeadFirst/Screenshots/02_chain.png)

Приспособленец/Flyweight
------------------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/tree/master/LessonsJavaSE/src/Books/HeadFirst/Screenshots/22_flyweight.png)

Интерпретатор/Interpreter
-------------------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/tree/master/LessonsJavaSE/src/Books/HeadFirst/Screenshots/06_interpreter.png)

Посредник/Mediator
------------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/tree/master/LessonsJavaSE/src/Books/HeadFirst/Screenshots/11_mediator.png)

Хранитель/Memento
-----------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/tree/master/LessonsJavaSE/src/Books/HeadFirst/Screenshots/01_memento.png)

Прототип/Prototype
------------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/tree/master/LessonsJavaSE/src/Books/HeadFirst/Screenshots/20_prototype.png)

Посетитель/Visitor
------------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/tree/master/LessonsJavaSE/src/Books/HeadFirst/Screenshots/10_visitor.png)