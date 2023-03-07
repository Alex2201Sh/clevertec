package by.shumilov.clevertec.bean;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * Class Item uses like a base for imported from file items.
 */
@Data
@SuperBuilder(setterPrefix = "super", toBuilder = true)
public abstract class Item {

    /**
     * Common field for all inherited objects.
     */
    private int id;
}
