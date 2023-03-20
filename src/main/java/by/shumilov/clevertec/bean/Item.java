package by.shumilov.clevertec.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Class Item uses like a base for imported from file items.
 */
@Data
@SuperBuilder(setterPrefix = "super", toBuilder = true)
@NoArgsConstructor
public abstract class Item {

    /**
     * Common field for all inherited objects.
     */
    @JsonProperty(value = "id", index = 0)
    private int id;
}
