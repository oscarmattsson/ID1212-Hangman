package oscarmat.kth.id1212.hangman.common;

public enum MessageType {

    /**
     * Start a new game.
     */
    NEWGAME,

    /**
     * Play a whole word.
     */
    WORD,

    /**
     * Play a single letter.
     */
    LETTER,

    /**
     * Exit the session.
     */
    EXIT,

    /**
     * Get the total score.
     */
    SCORE

}
