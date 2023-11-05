import java.util.ArrayList;
import java.util.List;

/**
 * A concrete implementation of the WheelOfFortune game for AI players. This class allows for playing multiple games
 * with AI players, each with their own strategy.
 */
public class WheelOfFortuneAIGame extends WheelOfFortune {
    private List<WheelOfFortunePlayer> players = new ArrayList<>();
    private int playerIndex =  0;

    /**
     * Constructs a WheelOfFortuneAIGame with a default AI player (AlphabetAIPlayer).
     */
    public WheelOfFortuneAIGame() {
        this.players.add(new AlphabetAIPlayer());
    }

    /**
     * Constructs a WheelOfFortuneAIGame with a specified AI player.
     *
     * @param player The AI player to use in the game.
     */
    public WheelOfFortuneAIGame(WheelOfFortunePlayer player) {
        this.players.add(player);
    }

    /**
     * Constructs a WheelOfFortuneAIGame with a list of AI players.
     *
     * @param players The list of AI players to participate in the game.
     */
    public WheelOfFortuneAIGame(List<WheelOfFortunePlayer> players) {
        this.players = players;
    }

    // Get the next guess from the current player
    @Override
    public char getGuess(String previousGuesses) {
        return players.get(playerIndex).nextGuess();
    }

    /**
     * Plays a game for the current AI player and returns the game record.
     *
     * @return A GameRecord representing the result of the gameplay.
     */
    public GameRecord play() {
        System.out.println("Starting the game for player: " + players.get(playerIndex).playerId());
        GameRecord gameRecord = processGuess(players.get(playerIndex).playerId());
        resetGameState();
        return gameRecord;
    }

    // Reset the game state including phrase, chances, and previous guesses
    private void resetGameState() {
        super.reset();
        players.get(playerIndex).reset();
    }

    @Override
    public boolean playNext() {
        if (!phraseList.isEmpty()) {
            return true;// Continue playing if there are more phrases to guess
        }

        playerIndex++;
        if (playerIndex < players.size()) {
            // Switch to the next AI player and reset the phrase list
            phraseList = super.readPhrases();
            System.out.println(players.get(playerIndex).playerId() + " is playing.");
            return true;
        }
        return false;// No more AI players to play
    }

    @Override
    public String toString() {
        return "WheelOfFortuneAIGame{" +
                "players=" + players +
                ", playerIndex=" + playerIndex +
                '}';
    }

    /**
     * Main method to run the WheelOfFortuneAIGame with multiple AI players.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        List<WheelOfFortunePlayer> players = new ArrayList<>();
        players.add(new RandomAIPlayer());
        players.add(new AlphabetAIPlayer());
        players.add(new FrequencyAIPlayer());

        WheelOfFortuneAIGame game = new WheelOfFortuneAIGame(players);
        AllGamesRecord record = game.playAll();

        printSummary(record);
    }

    /**
     * Print a summary of the game results including top scores and averages.
     *
     * @param record The AllGamesRecord containing game records.
     */
    private static void printSummary(AllGamesRecord record) {
        System.out.println(" ");
        System.out.println("Top 2 scores: " + record.highGameList(2));
        System.out.println("The average score of all the games is: " + record.average());

        printPlayerSummary(record, "FrequencyAI");
        printPlayerSummary(record, "AlphabetAI");
        printPlayerSummary(record, "RandomAI");
    }

    /**
     * Print a summary of a specific player's game results including top scores and averages.
     *
     * @param record   The AllGamesRecord containing game records.
     * @param playerId The ID of the player to summarize.
     */
    private static void printPlayerSummary(AllGamesRecord record, String playerId) {
        System.out.println(" ");
        System.out.println(playerId + "Player's highGameList: " + record.highGameList(playerId, 3));
        System.out.println(playerId + "Player's Average score: " + record.average(playerId));
    }
}