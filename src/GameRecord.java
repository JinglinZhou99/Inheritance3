/**
 * A class representing a record of a game, including the player's score and ID.
 */
public class GameRecord implements Comparable<GameRecord>{
    private int score;
    private String playerId;

    /**
     * Constructs a GameRecord with a score and player ID.
     *
     * @param score    The score achieved in the game.
     * @param playerId The unique identifier of the player.
     */
    public GameRecord(int score, String playerId){
        this.score = score;
        this.playerId = playerId;
    }

    /**
     * Gets the score of the game record.
     *
     * @return The score of the game record.
     */
    public int getScore() {
        return score;
    }

    /**
     * Gets the player ID associated with the game record.
     *
     * @return The player ID.
     */
    public String getPlayerId() {
        return playerId;
    }

    /**
     * Compares this GameRecord to another based on their scores.
     *
     * @param other The other GameRecord to compare to.
     * @return A negative integer, zero, or a positive integer if this GameRecord
     *         is less than, equal to, or greater than the other GameRecord.
     */
    @Override
    public int compareTo(GameRecord other) {
        return Integer.compare(this.score, other.score);
    }

    /**
     * Returns a string representation of the GameRecord.
     *
     * @return A string containing the player ID and score.
     */
    @Override
    public String toString() {
        return "{Id: " + playerId + ", score = " + score + '}';
    }
}