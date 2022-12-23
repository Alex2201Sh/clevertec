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