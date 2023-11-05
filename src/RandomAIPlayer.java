import java.util.Random;

/**
 * A concrete implementation of the WheelOfFortunePlayer interface representing a random AI player.
 */
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

    // Reset any necessary state or data for the player if needed
    @Override
    public void reset() {
    }

    @Override
    public String toString() {
        return "RandomAIPlayer{}";
    }
}