import java.util.Random;

public class MasterMind extends GuessingGame {

    private String secret;
    private static final int CODESIZE = 4;
    private static final String COLORS = "RGBYOP";

    @Override
    public GameRecord play() {
        secret = generateSecretCode();
        System.out.println(secret);
        String guess;
        while (playTime < maxTries) {
            guess = getUserInput("Enter your guess (e.g., RGRY):");
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

    private String generateSecretCode() {
        StringBuilder secret = new StringBuilder(CODESIZE);
        Random rand = new Random();
        for (int i = 0; i < CODESIZE; i++) {
            secret.append(COLORS.charAt(rand.nextInt(COLORS.length())));
        }
        return secret.toString();
    }

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

    public static void main(String[] args) {
        MasterMind game = new MasterMind();
        game.play();
        while (game.playNext()) {
            game.play();
        }
    }
}
