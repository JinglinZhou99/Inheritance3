/**
 * A concrete implementation of the WheelOfFortunePlayer interface representing an AI player that guesses letters based on their frequency in the English language.
 */
public class FrequencyAIPlayer implements WheelOfFortunePlayer{
    int i = 0;
    @Override
    public char nextGuess() {
        // Define the order of letter frequency in the English language
        String frequency = "etaoinshrdlucmwfgypbvkjxqz";
        char guessChar = frequency.charAt(i);
        System.out.println(guessChar);
        i++;
        return guessChar;
    }

    @Override
    public String playerId() {
        return "FrequencyAI";
    }

    @Override
    public void reset() {
        i = 0;
    }

    @Override
    public String toString() {
        return "FrequencyAIPlayer{" +
                "i=" + i +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrequencyAIPlayer that = (FrequencyAIPlayer) o;
        return i == that.i;
    }
}