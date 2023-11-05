/**
 * An abstract class representing a generic game.
 */
public abstract class Game {

    /**
     * Play a sequence of games and record their results.
     * @return An AllGamesRecord containing the results of all played games.
     */
    public AllGamesRecord playAll(){
        AllGamesRecord allGamesRecord = new AllGamesRecord();
        while (playNext()){
            GameRecord gameRecord = play();
            allGamesRecord.add(gameRecord);
        }
        return allGamesRecord;
    }

    /**
     * Abstract method to define the specific gameplay logic for a game.
     * @return A GameRecord representing the result of the gameplay.
     */
    public abstract GameRecord play();

    /**
     * Abstract method to determine if the game should continue to the next round.
     * @return true if the game should continue, false otherwise.
     */
    public abstract boolean playNext();
}