import java.util.Random;

public class MasterMind extends GuessingGame {

    private String secret;
    private static final int CODESIZE = 4;
    private static final String COLORS = "RGBYOP";

    /**
     * This method defines the specific gameplay logic for MasterMind.
     * @return A GameRecord representing the result of the gameplay.
     */
    @Override
    public GameRecord play() {
        // Generate the secret code
        secret = generateSecretCode();
        System.out.println(secret);
        String guess;
        while (playTime < maxTries) {
            guess = getUserInput("Enter your guess (e.g., RGRY):");
            // Check for exact matches and partial matches
            int exacts = checkExacts(new StringBuilder(secret), new StringBuilder(guess));
            int partials = checkPartials(new StringBuilder(secret), new StringBuilder(guess));
            System.out.println(exacts + " exact, " + partials + " partial.");

            if (exacts == CODESIZE) {
                System.out.println("Congratulations! You've guessed the secret code.");
                break;
            }
            playTime++;
        }
        int score = computeScore();
        System.out.println("Your score is: " + score);
        return new GameRecord(score, "User"); // User's name can be gathered via another method if needed
    }

    /**
     * Generates a random secret code.
     * @return The generated secret code.
     */
    private String generateSecretCode() {
        StringBuilder secret = new StringBuilder(CODESIZE);
        Random rand = new Random();
        for (int i = 0; i < CODESIZE; i++) {
            secret.append(COLORS.charAt(rand.nextInt(COLORS.length())));
        }
        return secret.toString();
    }

    /**
     * Checks for exact matches in the guessed code.
     * @param secretSB The secret code as a StringBuilder.
     * @param guessSB The guessed code as a StringBuilder.
     * @return The number of exact matches.
     */
    public int checkExacts(StringBuilder secretSB, StringBuilder guessSB) {
        int exacts = 0;
        for (int i = 0; i < CODESIZE; i++) {
            if (secretSB.charAt(i) == guessSB.charAt(i)) {
                exacts++;
                secretSB.setCharAt(i, '-');
                guessSB.setCharAt(i, '*');
            }
        }
        return exacts;
    }

    /**
     * Asks the user if they want to play another game.
     * @return True if the user wants to play again, false otherwise.
     */
    @Override
    public boolean playNext() {
        System.out.println("Do you want to play again? (yes/no)");
        String response = scanner.nextLine().toLowerCase();
        return response.equals("yes");
    }

    /**
     * Checks for partial matches in the guessed code.
     * @param secretSB The secret code as a StringBuilder.
     * @param guessSB The guessed code as a StringBuilder.
     * @return The number of partial matches.
     */
    public int checkPartials(StringBuilder secretSB, StringBuilder guessSB) {
        int partials = 0;
        for (int i = 0; i < CODESIZE; i++) {
            for (int j = 0; j < CODESIZE; j++) {
                if (secretSB.charAt(i) == guessSB.charAt(j)) {
                    partials++;
                    secretSB.setCharAt(i, '-');
                    guessSB.setCharAt(j, '*');
                    break;
                }
            }
        }
        return partials;
    }

    @Override
    public String toString() {
        return "MasterMind{" +
                "secret='" + secret + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Game game = new MasterMind();
        AllGamesRecord allGamesRecord = game.playAll();
        System.out.println("highGameList:"+allGamesRecord.highGameList(2));
        System.out.println("Average is: "+allGamesRecord.average());
    }
}
