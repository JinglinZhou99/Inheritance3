public class FrequencyAIPlayer implements WheelOfFortunePlayer{
    int i = 0;
    @Override
    public char nextGuess() {
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
}