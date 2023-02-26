package by.shumilov.clevertec.cache;

import by.shumilov.clevertec.bean.Property;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class CacheCreator {

    public static Cache createCache(String propertyLocation) {
        Cache cache = null;
        String algorithm = getProperty(propertyLocation).getAlgorithm();
        int maxLength = getProperty(propertyLocation).getMaxLength();
        int initCapacity = getProperty(propertyLocation).getInitCapacity();
        switch (algorithm) {
            case "lfu" -> cache = new LFUCache<>(initCapacity);
            case "lru" -> cache = new LRUCache<>(maxLength);
        }
        return cache;
    }

    private static Property getProperty(String propertyLocation) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Property property = new Property();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            File file = new File(Objects.requireNonNull(classLoader.getResource(propertyLocation)).toURI());
            property = mapper.readValue(file, Property.class);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return property;
    }


}
