package org.example.task3;


public class Main {
    static final String FILE_PATH = "java-4course-main\\pr2-2\\src\\main\\java\\org\\example\\task3\\total.txt";

    public static void main(String[] args) {
        CalculateCheckSumService calculateCheckSumService = new CalculateCheckSumService();

        calculateCheckSumService.execute(FILE_PATH);
    }
}
