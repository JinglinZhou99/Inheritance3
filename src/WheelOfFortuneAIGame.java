import java.util.ArrayList;
import java.util.List;

public class WheelOfFortuneAIGame extends WheelOfFortune {
    private List<WheelOfFortunePlayer> players = new ArrayList<>();
    private int playerIndex =  0;

    public WheelOfFortuneAIGame() {
        this.players.add(new AlphabetAIPlayer());
    }

    public WheelOfFortuneAIGame(WheelOfFortunePlayer player) {
        this.players.add(player);
    }

    public WheelOfFortuneAIGame(List<WheelOfFortunePlayer> players) {
        this.players = players;
    }

    @Override
    public char getGuess(String previousGuesses) {
        return players.get(playerIndex).nextGuess();
    }

    public GameRecord play() {
        System.out.println("Starting the game for player: " + players.get(playerIndex).playerId());
        GameRecord gameRecord = processGuess(players.get(playerIndex).playerId());
        resetGameState();
        return gameRecord;
    }

    private void resetGameState() {
        super.reset();
        players.get(playerIndex).reset();
    }

    @Override
    public boolean playNext() {
        if (!phraseList.isEmpty()) {
            return true;
        }

        playerIndex++;
        if (playerIndex < players.size()) {
            phraseList = super.readPhrases();
            System.out.println(players.get(playerIndex).playerId() + " is playing.");
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        List<WheelOfFortunePlayer> players = new ArrayList<>();
        players.add(new RandomAIPlayer());
        players.add(new AlphabetAIPlayer());
        players.add(new FrequencyAIPlayer());

        WheelOfFortuneAIGame game = new WheelOfFortuneAIGame(players);
        AllGamesRecord record = game.playAll();

        printSummary(record);
    }

    private static void printSummary(AllGamesRecord record) {
        System.out.println(" ");
        System.out.println("Top 4 scores: " + record.highGameList(4));
        System.out.println("The average score of all the games is: " + record.average());

        printPlayerSummary(record, "FrequencyAI");
        printPlayerSummary(record, "AlphabetAI");
        printPlayerSummary(record, "RandomAI");
    }

    private static void printPlayerSummary(AllGamesRecord record, String playerId) {
        System.out.println(" ");
        System.out.println(playerId + "Player's highGameList: " + record.highGameList(playerId, 3));
        System.out.println(playerId + "Player's Average score: " + record.average(playerId));
    }
}