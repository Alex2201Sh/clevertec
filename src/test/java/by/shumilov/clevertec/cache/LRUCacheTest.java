package by.shumilov.clevertec.cache;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LRUCacheTest {

    private LRUCache<Integer, String> cache;

    @ParameterizedTest
    @MethodSource("getArgumentsForGet")
    void get(int capacity, int key, String result) {
        cache = new LRUCache<>(capacity);
        IntStream.range(0,capacity).forEach(value -> cache.put(value,"xxx"));
        cache.get(1);
        cache.get(2);
        cache.put(100, "yyy");
        assertThat(cache.get(key)).isEqualTo(result);
        System.out.println(cache.toString());
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForPut")
    void put(int key, String value, String result) {
        cache = new LRUCache<>(3);
        cache.put(key, value);
        assertThat(cache.get(key)).isEqualTo(result);
    }

    static Stream<Arguments> getArgumentsForGet() {
        return Stream.of(
                Arguments.of(3,3,null),
                Arguments.of(3,100,"yyy"));
    }

    static Stream<Arguments> getArgumentsForPut() {
        return Stream.of(
                Arguments.of(1,"aaa","aaa"),
                Arguments.of(1,"bbb","bbb"),
                Arguments.of(2,"ccc","ccc")
        );
    }
}