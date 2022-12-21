**Тестовое задание Clevertec, 
реализующее функционал формирования чека в магазине.**

Выполнены задачи с 1 по 12. Процент покрытия кода юнит-тестами 78%.

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


Для **запуска** можно использовать класс CheckRunner в пакете by/shumilov/clevertec/controller.

В данном классе происходит запуск метода main класса Runner со следующими аргументами:

1.Имя файла в папке resources/inputData с исходным списком продуктов,
формат данных о продукте в каждой строке: id name price isPromotion;

2.Имя файла в папке resources/inputData с исходным списком скидочных карт,
формат данных о продукте в каждой строке: id discountPercentage;

3.Имя файла в папке resources/inputData с исходным списком покупок и скидочной карты,
формат данных прописывается в одной строке: 
productId-productQuantity ... productId-productQuantity card-discountCardId

Стек: Java 17, Gradle 7.5, Junit 5.7, Postgres 13.

_P.S. Добавлена поддержка базы данных PostgreSQL. Однако при подключении есть ошибка.
Пока проблема не решена, но ddl команды и другие классы, связанные с базой данных дабавляю._