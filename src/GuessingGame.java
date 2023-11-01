import java.util.Scanner;

public abstract class GuessingGame {

    protected Scanner scanner = new Scanner(System.in);
    protected int playTime = 0;
    protected final int maxTries = 12;

    public abstract GameRecord play();

    protected String getUserInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().toUpperCase();
    }

    public boolean playNext() {
        System.out.println("Do you want to play again? (yes/no)");
        String response = scanner.nextLine().toLowerCase();
        return response.equals("yes");
    }

    // Compute score based on attempts and maxTries
    protected int computeScore() {
        return (int) ((double) (maxTries - playTime) / maxTries * 100);
    }
}

