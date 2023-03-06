package by.shumilov.clevertec.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

class CacheCreatorTest {

    private Cache cacheLFUWithCapacity4;
    private Cache cacheLRUWithCapacity5MaxLength10;

    @BeforeEach
    void init() {
        cacheLFUWithCapacity4 = CacheCreator.createCache("application-lfu-4.yml");
        cacheLRUWithCapacity5MaxLength10 = CacheCreator.createCache("application-lru-10-5.yml");
    }

    @Test
    void createLFUCacheCheckClass() throws NoSuchFieldException, IllegalAccessException {
        Cache cache = cacheLFUWithCapacity4;
        String simpleClassName = cache.getClass().getSimpleName();
        String expectedClassName = "LFUCache";
        assertThat(simpleClassName).isEqualTo(expectedClassName);
    }

    @Test
    void createLFUCacheCheckCapacity() throws NoSuchFieldException, IllegalAccessException {
        Cache cache = cacheLFUWithCapacity4;
        Field capacity = cache.getClass().getDeclaredField("capacity");
        capacity.setAccessible(true);
        System.out.println(capacity.get(cache));
        int expectedCapacity = 4;
        assertThat(capacity.get(cache)).isEqualTo(expectedCapacity);
    }

    @Test
    void createLRUCacheCheckClass() throws NoSuchFieldException, IllegalAccessException {
        Cache cache = cacheLRUWithCapacity5MaxLength10;
        String simpleClassName = cache.getClass().getSimpleName();
        String expectedClassName = "LRUCache";
        assertThat(simpleClassName).isEqualTo(expectedClassName);
    }

    @Test
    void createLRUCacheCheckCapacity() throws NoSuchFieldException, IllegalAccessException {
        Cache cache = cacheLRUWithCapacity5MaxLength10;
        Field capacity = cache.getClass().getDeclaredField("maxCapacity");
        capacity.setAccessible(true);
        System.out.println(capacity.get(cache));
        int expectedMaxCapacity = 10;
        assertThat(capacity.get(cache)).isEqualTo(expectedMaxCapacity);
    }


}