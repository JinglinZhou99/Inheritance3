import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class AllGamesRecord {
    private List<GameRecord> gameRecords;

    public AllGamesRecord() {
        gameRecords = new ArrayList<>();
    }

    public void add(GameRecord gameRecord) {
        gameRecords.add(gameRecord);
    }

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

    public List<GameRecord> highGameList(int n) {
        List<GameRecord> sortedRecords = new ArrayList<>(gameRecords);
        sortedRecords.sort(Collections.reverseOrder());
        return sortedRecords.subList(0, Math.min(n,sortedRecords.size()));
    }

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

    @Override
    public String toString() {
        return "AllGamesRecord{" +
                "gamesRecords=" + gameRecords +
                '}';
    }
}




