/**
 * A concrete implementation of the WheelOfFortunePlayer interface representing an AI player that guesses letters of the alphabet sequentially.
 */
public class AlphabetAIPlayer implements WheelOfFortunePlayer{
    private int i = 0;

    @Override
    public char nextGuess() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        char guessChar = alphabet.charAt(i);
        System.out.println(guessChar);
        i++;
        return guessChar;
    }

    @Override
    public String playerId() {
        return "AlphabetAI";
    }

    @Override
    public void reset() {
        i = 0;
    }

    @Override
    public String toString() {
        return "AlphabetAIPlayer{" +
                "i=" + i +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlphabetAIPlayer that = (AlphabetAIPlayer) o;
        return i == that.i;
    }
}