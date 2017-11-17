package oscarmat.kth.id1212.hangman.common;

public enum MessageType {

    // From Client

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

    // From Server

    /**
     * Current state of the game.
     */
    GAMESTATE,

    /**
     * State after the end of a game.
     */
    GAMEOVER,

    /**
     * Current state of the word.
     */
    WORDSTATE,

    /**
     * Amount of failed attempts so far.
     */
    FAILEDATTEMPTS,

    /**
     * Max amount of allowed attempts.
     */
    MAXATTEMPTS,

    /**
     * Whether or not the game is won (lost if not won).
     */
    WON
}
