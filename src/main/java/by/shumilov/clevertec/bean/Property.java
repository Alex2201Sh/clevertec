package by.shumilov.clevertec.bean;

public class Property {
    private String algorithm;
    private int maxLength;
    private int initCapacity;

    public Property() {
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getInitCapacity() {
        return initCapacity;
    }

    public void setInitCapacity(int initCapacity) {
        this.initCapacity = initCapacity;
    }

    @Override
    public String toString() {
        return "Property{" +
                "algorithm='" + algorithm + '\'' +
                ", maxLength=" + maxLength +
                '}';
    }
}
