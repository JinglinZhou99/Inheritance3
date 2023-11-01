import java.util.Random;
public class RandomAIPlayer implements WheelOfFortunePlayer{
    @Override
    public char nextGuess() {
        Random random = new Random();
        char guessChar = (char) ('a' + random.nextInt(26));
        System.out.println(guessChar);
        return guessChar;
    }

    @Override
    public String playerId() {
        return "RandomAI";
    }

    @Override
    public void reset() {
    }
}