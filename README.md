<details>
  <summary>Тестовое задание</summary>

**Тестовое задание Clevertec,
реализующее функционал формирования чека в магазине.**

Выполнены задачи с 1 по 13. Процент покрытия кода юнит-тестами 89%.

Вывод данных осуществляется в консоль и в файл
в текстовый файл в папке resources/outputData.

Паттерн Factory применён в пакете by/shumilov/clevertec/dao
в классе DAOFactory.

Паттерн Builder применён в пакете by/shumilov/clevertec/bean
в классах DiscountCard, Product, ReceiptLine.

Паттерн Decorator применён в пакете by/shumilov/clevertec/dao/impl
в классах ItemDAO>ItemDAODecorator>ProductDAO,DiscountCardDAO.

Сделана обработка исключений при попытке поиска товара
или дисконтной карты по неверному идентификатору.


Для **запуска** использовать класс CheckRunner в пакете by/shumilov/clevertec/controller.

В данном классе происходит запуск метода main класса Runner со следующими аргументами:

1.Имя файла в папке resources/inputData с исходным списком продуктов,
формат данных о продукте в каждой строке: id name price isPromotion;

2.Имя файла в папке resources/inputData с исходным списком скидочных карт,
формат данных о продукте в каждой строке: id discountPercentage;

3.Имя файла в папке resources/inputData с исходным списком покупок и скидочной карты,
формат данных прописывается в одной строке:
productId-productQuantity ... productId-productQuantity card-discountCardId

Для **запуска** с чтением из базы данных использовать закомментированную строку в классе CheckRunner
`Runner.main(new String[]{"db", "smallDataInput",});`,
где "db" - информация о том, что дынные будут взяты из базы данных;
"smallDataInput" - файл в папке resources/inputData с исходным списком покупок и скидочной карты.

DDL команды для создания и заполнения базы данных находятся в папке resources/sql.

Стек: Java 17, Gradle 7.5, Junit 5.7, Postgres 13.
</details>

<details>
  <summary>Reflection Home Task</summary>

1. Реализация кэша находится в папке src/main/java/by/shumilov/clevertec/cache.

2. Алгоритм и размер кэша читаются из resources/application.jml.

3. Коллекция объектов User инициализируется через фабрику. 
Фабрика находится в папке src/main/java/by/shumilov/clevertec/dao_user.

4. Javadoc and README.md included.

5. Тесты покрывают 88% кода.
6. Entity для работы это класс User с полями id, name, password, email.
password, email проверяются валидатором через класс Pattern.

7. service, dao присутствуют.

8. proxy реализован в папке src/main/java/by/shumilov/clevertec/proxy.

9. *** Получение информации из xml формата реализовано в папке src/main/java/by/shumilov/clevertec/builder.
Реализация сделана DOM-XML парсером.

</details>

<details>
  <summary>String/Json</summary>

Библиотека находится в папке src/main/java/by/shumilov/clevertec/parser.

Возможности: преобразование объекта класса Receipt в формат JSON и обратно.

В тестах проведена проверка работы парсера и сравнение результатов с парсером Jackson.

</details>