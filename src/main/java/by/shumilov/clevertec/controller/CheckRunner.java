package by.shumilov.clevertec.controller;

public class CheckRunner {
    public static void main(String[] args) {

        Runner.main(new String[]{"src/main/resources/inputData/inputProducts.txt",
                "src/main/resources/inputData/inputDiscountCards.txt",
                "src/main/resources/inputData/smallDataInput.txt"});

        Runner.main(new String[]{"db",
                "src/main/resources/inputData/smallDataInput.txt",});
    }

}
