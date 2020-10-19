import java.util.*;

public class WordFrequencyGame {

    private static final String WHITE_SPACES = "\\s+";

    public String getResult(String inputWords) {

        final String ONE_WORD_ONLY = inputWords + " 1";

        if (hasManyWords(inputWords)) {
            try {
                List<WordInfo> wordInfoList = calculateFrequency(inputWords);
                sortWordList(wordInfoList);
                return joinWordAndFrequency(wordInfoList);
            } catch (Exception e) {
                return "Calculate Error";
            }

        } else {
            return ONE_WORD_ONLY;
        }
    }

    private List<WordInfo> calculateFrequency(String inputWords) {
        List<String> words = Arrays.asList(inputWords.split(WHITE_SPACES));
        List<WordInfo> wordInfoList = new ArrayList<>();
        for (String word : new HashSet<>(words)) {
            int frequencyCount = Collections.frequency(words, word);
            wordInfoList.add(new WordInfo(word, frequencyCount));
        }
        return wordInfoList;
    }

    private String joinWordAndFrequency(List<WordInfo> wordInfoList) {
        StringJoiner joiner = new StringJoiner("\n");
        wordInfoList.stream().map(wordInfo -> String.format("%s %d", wordInfo.getWord(), wordInfo.getWordCount())).forEach(joiner::add);
        return joiner.toString();
    }

    private void sortWordList(List<WordInfo> wordInfoList) {
        wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());
    }

    private boolean hasManyWords(String inputWords) {
        return inputWords.split(WHITE_SPACES).length != 1;
    }
}
