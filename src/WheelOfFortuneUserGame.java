import java.util.Scanner;

public class WheelOfFortuneUserGame extends WheelOfFortune {

    @Override
    public char getGuess(String previousGuesses) {
        Scanner scanner = new Scanner(System.in);
        return scanner.next().charAt(0);
    }

    @Override
    public GameRecord play() {
        System.out.println("Enter your ID");
        Scanner scanner = new Scanner(System.in);
        String userId = scanner.next();
        GameRecord gameRecord = this.processGuess(userId);
        this.reset();
        return gameRecord;
    }

    @Override
    public boolean playNext() {
        if (!phraseList.isEmpty()) {
            int answer = -1;
            while (answer != 1 && answer != 0) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Do you want to play? Yes = '1' or No = '0'");
                answer = scanner.nextInt();
            }
            return answer == 1;
        } else return false;
    }

    public static void main(String[] args) {
        Game userGame = new WheelOfFortuneUserGame();
        AllGamesRecord record = userGame.playAll();
        System.out.println(record.highGameList(3));
        System.out.println("Average score: "+record.average());
    }
}



