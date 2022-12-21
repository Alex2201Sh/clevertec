**Тестовое задание Clevertec, 
реализующее функционал формирования чека в магазине.**

Для запуска можно использовать класс CheckRunner 
в пакете by/shumilov/clevertec/controller. 
В данном классе происходит запуск метода main класса Runner со следующими аргументами:
1.Имя файла в папке resources/inputData с исходным списком продуктов,
формат данных о продукте в каждой строке: id name price isPromotion;
2.Имя файла в папке resources/inputData с исходным списком скидочных карт,
формат данных о продукте в каждой строке: id discountPercentage;
3.Имя файла в папке resources/inputData с исходным списком покупок и скидочной карты,
формат данных прописывается в одной строке: 
productId-productQuantity ... productId-productQuantity card-discountCardId

Стек: Java 17, Gradle 7.5, Junit 5.7