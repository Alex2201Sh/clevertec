package by.shumilov.clevertec.bean;

/**
 * Class Item uses like a base for imported from file items.
 */
public abstract class Item {

    /**
     * Common field for all inherited objects.
     */
    private int id;

    public int getId() {
        return id;
    }

    public void setId(final int newId) {
        this.id = newId;
    }
}
