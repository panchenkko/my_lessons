# ПРИНЦИПЫ ПРОЕКТИРОВАНИЯ
- Инкапсулируйте то, что изменяется.
- Предпочитайте композицию наследованию.
- Программируйте на уровне интерфейсов.
- Стремитесь к слабой связанности взаимодействующих объектов.
- Классы должны быть открыты для расширения, но закрыты для изменения.
- Код должен зависеть от абстракции, а не от конкретных классов.
- Взаимодействуйте только с "друзьями".
- Не вызывайте нас - мы вас сами вызовем.
- Класс должен иметь только одну причину для изменений.

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

![CC0](https://github.com/Panchenko-Vlad/java-lessons/blob/master/LessonsJavaSE/src/HeadFirst/Screenshots/manual1.png)

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
![CC0](https://github.com/Panchenko-Vlad/java-lessons/blob/master/LessonsJavaSE/src/HeadFirst/Strategy_1/Screenshots/07_strategy.png)

Наблюдатель/Observer
--------------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/blob/master/LessonsJavaSE/src/HeadFirst/Observer_2/Screenshots/03_observer.png)

Декоратор/Decorator
-------------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/blob/master/LessonsJavaSE/src/HeadFirst/Decorator_3/Screenshots/18_decorator.png)

Фабричный Метод/Factory Method
------------------------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/blob/master/LessonsJavaSE/src/HeadFirst/Factory_4/Screenshots/19_factorymethod.png)

Абстрактная Фабрика/Abstract Factory
------------------------------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/blob/master/LessonsJavaSE/src/HeadFirst/Factory_4/Screenshots/14_abstractfactory.png)

Одиночка/Singleton
------------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/blob/master/LessonsJavaSE/src/HeadFirst/Singleton_5/Screenshots/23_singleton.png)

Команда/Command
---------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/blob/master/LessonsJavaSE/src/HeadFirst/Command_6/Screenshots/04_command.png)

Адаптер/Adapter
---------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/blob/master/LessonsJavaSE/src/HeadFirst/Adapter_7/Screenshots/12_adapter.png)

Фасад/Facade
------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/blob/master/LessonsJavaSE/src/HeadFirst/Facade_8/Screenshots/21_facade.png)

Шаблонный метод/Template Method
-------------------------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/blob/master/LessonsJavaSE/src/HeadFirst/TemplateMethod_9/Screenshots/09_templatemethod.png)

Итератор/Iterator
-----------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/blob/master/LessonsJavaSE/src/HeadFirst/Iterator_10/Screenshots/08_iterator.png)

Компоновщик/Composite
---------------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/blob/master/LessonsJavaSE/src/HeadFirst/Composite_11/Screenshots/16_composite.png)

Состояние/State
---------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/blob/master/LessonsJavaSE/src/HeadFirst/State_12/Screenshots/05_state.png)

Заместитель/Proxy
-----------------
![CC0](https://github.com/Panchenko-Vlad/java-lessons/blob/master/LessonsJavaSE/src/HeadFirst/Proxy_13/Screenshots/13_proxy.png)

