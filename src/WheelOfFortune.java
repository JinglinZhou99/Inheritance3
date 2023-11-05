import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * This abstract class serves as the base class for the Wheel of Fortune game.
 */
public abstract class WheelOfFortune extends Game {
    protected List<String> phraseList ;
    private StringBuilder hidden;
    protected String previousGuesses;
    protected int chance;
    protected int totalChances = 26;
    private String currentPhrase;

    /**
     * Constructor for the WheelOfFortune class.
     * Initializes the phrase list and other game-related variables.
     */
    public WheelOfFortune() {
        phraseList = readPhrases();
        previousGuesses = "";
        chance = 0;
    }

    /**
     * Reads a list of phrases from a file.
     * @return A list of phrases.
     */
    public List<String> readPhrases() {
        try {
            return Files.readAllLines(Paths.get("phrases.txt"));
        } catch (IOException e) {
            System.out.println("Error reading phrases: " + e);
            return null;
        }
    }

    /**
     * Selects a random phrase from the phrase list.
     * @return The selected phrase in lowercase.
     */
    public String randomPhrase() {
        Random rand = new Random();
        int index = rand.nextInt(phraseList.size());
        currentPhrase = phraseList.remove(index);
        return currentPhrase.toLowerCase();
    }

    /**
     * Initializes the hidden phrase with asterisks.
     */
    public void getHiddenPhrase() {
        hidden = new StringBuilder(currentPhrase);
        for (int index = 0; index < currentPhrase.length(); index++) {
            if (Character.isLetter(currentPhrase.charAt(index))) {
                hidden.setCharAt(index, '*');
            }
        }
    }

    /**
     * Process a player's guess.
     * @param playId The ID of the player.
     * @return A GameRecord representing the result of the gameplay.
     */
    public GameRecord processGuess(String playId) {
        currentPhrase = randomPhrase();
        getHiddenPhrase();
        while (chance < totalChances) {
            System.out.println("Enter a letter to guess:");
            char guessLetter = validGuess(); // decide whether the guess is valid
            updateGuesses(guessLetter); // update previousGuesses and hiddenPhrase

            if (currentPhrase.indexOf(guessLetter) >= 0) {
                revealGuessedLetter(guessLetter);
                if (currentPhrase.contentEquals(hidden)) {
                    return createGameRecord(playId);
                }
            } else {
                deductChance();
            }
        }
        return createGameRecord(playId);
    }

    private char validGuess() {
        char guessedLetter;
        do {
            guessedLetter = getGuess(previousGuesses);
            if (!Character.isLetter(guessedLetter)) {
                System.out.println("The input is not a letter!");
            } else if (previousGuesses.indexOf(guessedLetter) >= 0) {
                System.out.println("You have already guessed this letter: " + previousGuesses);
            }
        } while (!Character.isLetter(guessedLetter) || previousGuesses.indexOf(guessedLetter) >= 0);

        return guessedLetter;
    }

    private void updateGuesses(char guess) {
        previousGuesses += guess;
    }

    private void revealGuessedLetter(char guessLetter) {
        for (int i = 0; i < currentPhrase.length(); i++) {
            if (guessLetter == currentPhrase.charAt(i)) {
                hidden.setCharAt(i, guessLetter);
            }
        }
        System.out.println("Guess right! " + hidden);
    }

    private void deductChance() {
        chance++;
        System.out.println("Guess Wrong! Left only " + (totalChances-chance) + " chances!");
        System.out.println("Previous guesses: " + this.previousGuesses);
    }

    private GameRecord createGameRecord(String playId) {
        int score = (26 - chance) * 100 / totalChances;
        System.out.println("score:" + score);
        return new GameRecord(score, playId);
    }

    @Override
    public abstract GameRecord play() ;

    @Override
    public abstract boolean playNext() ;

    /**
     * Abstract method to get a player's guess.
     * @param previousGuesses A string containing the player's previous guesses.
     * @return The player's guess.
     */
    public abstract char getGuess(String previousGuesses);

    /**
     * Reset the game state.
     */
    public void reset(){
        currentPhrase = "";
        chance = 0;
        previousGuesses = "";
        hidden = new StringBuilder();
    }

    @Override
    public String toString() {
        return "WheelOfFortune{" +
                "phraseList=" + phraseList +
                ", hidden=" + hidden +
                ", previousGuesses='" + previousGuesses + '\'' +
                ", chance=" + chance +
                ", totalChances=" + totalChances +
                ", currentPhrase='" + currentPhrase + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WheelOfFortune that = (WheelOfFortune) o;
        return chance == that.chance && totalChances == that.totalChances && Objects.equals(phraseList, that.phraseList) && Objects.equals(hidden, that.hidden) && Objects.equals(previousGuesses, that.previousGuesses) && Objects.equals(currentPhrase, that.currentPhrase);
    }
}