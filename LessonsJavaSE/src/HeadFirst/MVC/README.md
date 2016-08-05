СОСТАВНОЙ ПАТТЕРН "МОДЕЛЬ-ПРЕДСТАВЛЕНИЕ-КОНТРОЛЛЕР/MVC"
=======================================================
**Составной паттерн** объединяет два и более базовых паттерна в решении типичной или общей задачи. 

КЛЮЧЕВЫЕ МОМЕНТЫ
----------------
- Паттерн Модель-Представление-Контроллер (MVC) - составной паттерн, состоящий из паттерноа 
  Наблюдатель, Стратегия и Компоновщик.
- Модель использует паттерн Наблюдатель, чтобы наблюдатели оповещались об изменениях состояния, 
  без формирования сильных связей.
- Контроллер определяет стратегию для представления. Представление может использовать разные 
  реализации контроллера для обеспечения разного поведения.
- Представление использует паттерн Компоновщик для реализации пользовательского интерфейса, 
  который обычно состоит из иерархии компонентов (панели, кнопки и т. д.).
- Совместная работа паттернов обеспечивает слабую связанность всех трех компонентов модели MVC,
  благодаря чему архитектура сохраняет гибкость и четкость.
- Модель 2 является адаптацией MVC для веб-приложений.
- В Модели 2 контроллер реализуется в форме сервлета, а код JSP и HTML формирует представление.

Описание паттерна "MVC"
-----------------------

![CC0](https://github.com/Panchenko-Vlad/java-lessons/blob/master/LessonsJavaSE/src/HeadFirst/MVC/Screenshots/combined1.png)

![CC0](https://github.com/Panchenko-Vlad/java-lessons/blob/master/LessonsJavaSE/src/HeadFirst/MVC/Screenshots/combined2.png)

![CC0](https://github.com/Panchenko-Vlad/java-lessons/blob/master/LessonsJavaSE/src/HeadFirst/MVC/Screenshots/combined3.png)

![CC0](https://github.com/Panchenko-Vlad/java-lessons/blob/master/LessonsJavaSE/src/HeadFirst/MVC/Screenshots/combined4.png)

![CC0](https://github.com/Panchenko-Vlad/java-lessons/blob/master/LessonsJavaSE/src/HeadFirst/MVC/Screenshots/combined5.png)

![CC0](https://github.com/Panchenko-Vlad/java-lessons/blob/master/LessonsJavaSE/src/HeadFirst/MVC/Screenshots/combined6.png)

![CC0](https://github.com/Panchenko-Vlad/java-lessons/blob/master/LessonsJavaSE/src/HeadFirst/MVC/Screenshots/combined7.png)

![CC0](https://github.com/Panchenko-Vlad/java-lessons/blob/master/LessonsJavaSE/src/HeadFirst/MVC/Screenshots/combined8.png)
