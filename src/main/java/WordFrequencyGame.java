import java.util.*;

public class WordFrequencyGame {
    private static final String WHITE_SPACES = "\\s+";

    public String getResult(String inputWords) {

        if (inputWords.split(WHITE_SPACES).length == 1) {
            return inputWords + " 1";
        } else {
            try {
                List<WordInfo> wordInfoList = calculateFrequency(inputWords);

                wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

                return joinWordAndFrequency(wordInfoList);
            } catch (Exception e) {
                return "Calculate Error";
            }
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
        for (WordInfo wordInfo : wordInfoList) {
            String wordInfoLine = String.format("%s %d", wordInfo.getValue(), wordInfo.getWordCount());
            joiner.add(wordInfoLine);
        }
        return joiner.toString();
    }
}
