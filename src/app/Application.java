package app;

public class Application {

    private static final String INPUT = "src/resources/input.txt";
    private static final String OUTPUT = "src/resources/output.txt";


    public static void main(String[] args) {
        WordCount.countWords(INPUT, OUTPUT);
    }

}
