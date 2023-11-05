import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class representing a collection of GameRecords and providing various
 * statistical operations on them.
 */
public class AllGamesRecord {
    private List<GameRecord> gameRecords;

    /**
     * Constructs an empty AllGamesRecord.
     */
    public AllGamesRecord() {
        gameRecords = new ArrayList<>();
    }

    /**
     * Adds a GameRecord to the collection.
     *
     * @param gameRecord The GameRecord to be added.
     */
    public void add(GameRecord gameRecord) {
        gameRecords.add(gameRecord);
    }

    /**
     * Calculates the average score of all GameRecords in the collection.
     *
     * @return The average score as a floating-point value.
     */
    public float average() {
        if (gameRecords.isEmpty()) {
            return 0; // Return 0 if there are no game records
        }
        int sumRecords = 0;
        for (GameRecord gameRecord : gameRecords) {
            sumRecords += gameRecord.getScore();
        }
        return (float) sumRecords / gameRecords.size();
    }

    /**
     * Calculates the average score of GameRecords for a specific player.
     *
     * @param playerId The player ID for which to calculate the average score.
     * @return The average score as a floating-point value.
     */
    public float average(String playerId) {
        if (gameRecords.isEmpty()) {
            return 0; // Return 0 if there are no game records
        }
        int sumRecords = 0;
        int count = 0;

        for (GameRecord gameRecord : gameRecords) {
            if (gameRecord.getPlayerId().equals(playerId)) {
                sumRecords += gameRecord.getScore();
                count++;
            }
        }
        if (count == 0) {
            return 0; // Return 0 if the player has no game records
        }
        return (float) sumRecords / count;
    }

    /**
     * Retrieves a list of the top n highest-scoring GameRecords in the collection.
     *
     * @param n The number of top records to retrieve.
     * @return A list of the top GameRecords.
     */
    public List<GameRecord> highGameList(int n) {
        List<GameRecord> sortedRecords = new ArrayList<>(gameRecords);
        sortedRecords.sort(Collections.reverseOrder());
        return sortedRecords.subList(0, Math.min(n,sortedRecords.size()));
    }

    /**
     * Retrieves a list of the top n highest-scoring GameRecords for a specific player.
     *
     * @param playerId The player ID for which to retrieve the top records.
     * @param n        The number of top records to retrieve.
     * @return A list of the top GameRecords for the specified player.
     */
    public List<GameRecord> highGameList(String playerId, int n) {
        List<GameRecord> sortedRecords = new ArrayList<>();
        for (GameRecord gameRecord : gameRecords) {
            if (gameRecord.getPlayerId().equals(playerId)) {
                sortedRecords.add(gameRecord);
            }
        }
        sortedRecords.sort(Collections.reverseOrder());
        return sortedRecords.subList(0, Math.min(n,sortedRecords.size()));
    }

    /**
     * Returns a string representation of the AllGamesRecord.
     *
     * @return A string containing the list of GameRecords.
     */
    @Override
    public String toString() {
        return "AllGamesRecord{" +
                "gamesRecords=" + gameRecords +
                '}';
    }
}
