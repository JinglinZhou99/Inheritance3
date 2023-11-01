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
}