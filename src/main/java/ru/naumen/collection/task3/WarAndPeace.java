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
        new WarAndPeace().printFirstAndLast10WordsByCount();
    }

    private void printFirstAndLast10WordsByCount() {
        var count = countWordsWithWordParser();

        print10WordsFromQueue(identifyingFirstWords(count), true);
        print10WordsFromQueue(identifyingLastWords(count), false);
    }

    private Map<String, Long> countWordsWithWordParser() {
        Map<String, Long> wordsMap = new LinkedHashMap<>();
        WordParser wordParserToWarAndPeace = new WordParser(WAR_AND_PEACE_FILE_PATH);

        wordParserToWarAndPeace
                .forEachWord(word ->
                        wordsMap.put(
                                word, wordsMap.getOrDefault(word, 0L) + 1L
                        ));

        return wordsMap;
    }

    private void print10WordsFromQueue(
            Queue<Map.Entry<String, Long>> queueWords,
            Boolean increasing
    ) {
        var exitMessage = increasing ? "Топ 10 самых частых слов:" : "Топ 10 самых редких слов:";
        System.out.println(exitMessage);

        while (!queueWords.isEmpty()) {
            Map.Entry<String, Long> entry = queueWords.poll();
            System.out.printf("%s : %s%n", entry.getKey(), entry.getValue());
        }
    }

    private Queue<Map.Entry<String, Long>> identifyingFirstWords(
            Map<String, Long> dictionaryWords
    ) {
        return leaveFirstNWordsInQueueByMap(dictionaryWords, 10,
                Comparator.comparingLong(Map.Entry::getValue));
    }

    private Queue<Map.Entry<String, Long>> identifyingLastWords(
            Map<String, Long> dictionaryWords
    ) {
        return leaveFirstNWordsInQueueByMap(dictionaryWords, 10,
                (a, b) -> Long.compare(b.getValue(), a.getValue()));
    }

    private Queue<Map.Entry<String, Long>> leaveFirstNWordsInQueueByMap(
            Map<String, Long> dictionaryWords,
            Integer countWords,
            Comparator<? super Map.Entry<String, Long>> comparator
    ) {
        Queue<Map.Entry<String, Long>> queueWhitTopWords = new PriorityQueue<>(comparator);

        for (Map.Entry<String, Long> entry : dictionaryWords.entrySet()) {
            queueWhitTopWords.offer(entry);
            if (queueWhitTopWords.size() > countWords) queueWhitTopWords.poll();
        }

        return queueWhitTopWords;
    }
}
