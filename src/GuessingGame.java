import java.util.Objects;
import java.util.Scanner;

/**
 * This abstract class serves as the base class for various guessing games.
 */
public abstract class GuessingGame extends Game {

    protected Scanner scanner = new Scanner(System.in);
    protected int playTime = 0;
    protected final int maxTries = 12;

    /**
     * This method defines the gameplay logic for the specific guessing game.
     * @return A GameRecord representing the result of the gameplay.
     */
    public abstract GameRecord play();

    /**
     * Gets user input with a provided prompt.
     * @param prompt The prompt to display to the user.
     * @return The user's input (uppercase).
     */
    protected String getUserInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().toUpperCase();
    }

    /**
     * Determines whether the user wants to play another game.
     * @return True if the user wants to play again, false otherwise.
     */
    @Override
    public abstract boolean playNext() ;

    // Compute score based on attempts and maxTries
    protected int computeScore() {
        int score = (int) ((double) (maxTries - playTime) / maxTries * 100);
        playTime = 0;
        return score;
    }

    @Override
    public String toString() {
        return "GuessingGame{" +
                "scanner=" + scanner +
                ", playTime=" + playTime +
                ", maxTries=" + maxTries +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuessingGame that = (GuessingGame) o;
        return playTime == that.playTime && maxTries == that.maxTries && Objects.equals(scanner, that.scanner);
    }
}

