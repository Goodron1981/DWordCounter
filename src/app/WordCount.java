package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Класс обеспечивающий функционал для определения уникальных слов и колчества из появлений в тексте.
 */
public class WordCount {

    // Массив для хранения всех уникальных слов.
    private static int N = 500;
    // Массив для хранения всех уникальных слов.
    private static String[] words = new String[N];
    // Позиция, куда писать новое уникальное слово.
    private static int top = 0;
    /* Массив для хранения количества уникальных слов. */
    private static int[] counts = new int[N];

    /**
     * Метод, вычисляющий, на какой позиции находиться искуемое слово.
     * @param word искуемое слово.
     * @return индекс (позиция) слова в массиве уникальных слов.
     */
    private static int getWordIndex(String word) {
        for (int i = 0; i < top; i++) {
            if (words[i].equals(word)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Метод для проверки уникальности слова и соответствующих дейтствий - записи в массив, отметки количества.
     */
    private static void processWord(String word) {
        int indx = getWordIndex(word);
        //если слова нет, то добавить его
        if (indx == -1) {
            words[top] = word;
            top++;
            indx = top;
        }
        //увеличить счетчик
        counts[indx]++;
    }

    /**
     * Метод для чтения входного текста из файла.
     */
    public static void countWords(String inputPath, String outputPath) {
        try {
            BufferedReader in = null;
            FileWriter out = null;
            try{
                in = new BufferedReader(new FileReader(inputPath));
                out = new FileWriter(outputPath);

                //читаем входящий файл построчно и обрабатываем слова
                String line = in.readLine();
                while (line != null) {
                    String [] lineWords = line.split(" ");
                    for (String lineWord : lineWords) {
                        processWord(lineWord.trim());
                    }
                    line = in.readLine();
                }
                //вывод результата в консоль и в исходящий файл
                for (int i = 0; i < top; i++) {
                    String message = words[i] + " : " + counts[i];
                    System.out.println(message);
                    out.write(message + "\n");
                }
            } finally {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
