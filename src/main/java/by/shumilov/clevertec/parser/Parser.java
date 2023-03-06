package by.shumilov.clevertec.parser;

public interface Parser {

    Object deSerialize(String json);

    String serialize(Object object);
}
