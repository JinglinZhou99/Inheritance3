public abstract class Game {

    public AllGamesRecord playAll(){
        AllGamesRecord allGamesRecord = new AllGamesRecord();
        while (playNext()){
            GameRecord gameRecord = play();
            allGamesRecord.add(gameRecord);
        }
        return allGamesRecord;
    }

    public abstract GameRecord play();

    public abstract boolean playNext();
}