package ru.naumen.collection.task3;

import java.nio.file.Path;
import java.util.*;

/**
 * <p>Написать консольное приложение, которое принимает на вход произвольный текстовый файл в формате txt.
 * Нужно собрать все встречающийся слова и посчитать для каждого из них количество раз, сколько слово встретилось.
 * Морфологию не учитываем.</p>
 * <p>Вывести на экран наиболее используемые (TOP) 10 слов и наименее используемые (LAST) 10 слов</p>
 * <p>Проверить работу на романе Льва Толстого “Война и мир”</p>
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class WarAndPeace {

    private static final Path WAR_AND_PEACE_FILE_PATH = Path.of("src/main/resources",
            "Лев_Толстой_Война_и_мир_Том_1,_2,_3,_4_(UTF-8).txt");

    public static void main(String[] args) {
        printFrequentAndRare10Words(countWordsAndSortByValue());
    }

    private static List<Map.Entry<String, Long>> countWordsAndSortByValue() {
        Map<String, Long> wordsMap = new HashMap<>();
        // Подсчёт слов
        new WordParser(WAR_AND_PEACE_FILE_PATH).forEachWord(x -> {
            if (wordsMap.containsKey(x)) {
                wordsMap.replace(x, wordsMap.get(x) + 1);
            } else wordsMap.put(x, 1L);
        });
        // Сортировка по значениям
        List<Map.Entry<String, Long>> listWords = new ArrayList<>(wordsMap.entrySet());
        Collections.sort(listWords, new Comparator<Map.Entry<String, Long>>() {
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        return listWords;
    }

    private static void printFrequentAndRare10Words(List<Map.Entry<String, Long>> listWords) {
        System.out.println("Топ 10 самых частых слов:");
        for (var i = 0; i < 10 && listWords.size() > i; i++)
            System.out.println(listWords.get(i).getKey());

        System.out.println("Топ 10 самых редких слов:");
        for (var i = listWords.size() - 1; i >= 0 && i > listWords.size() - 11; i--)
            System.out.println(listWords.get(i).getKey());
    }
}
