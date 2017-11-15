package oscarmat.kth.id1212.hangman.server.net;

/**
 * Parses output data from the server to build a message for the client,
 * following the protocol as described in the ClientHandler class.
 */
class MessageBuilder {

    private static final String GAMESTATE = "gamestate";
    private static final String WORDSTATE = "wordstate";
    private static final String FAILEDATTEMPTS = "failedattempts";
    private static final String MAXATTEMPTS = "maxattempts";
    private static final String GAMEOVER = "gameover";
    private static final String WON = "won";
    private static final String LOST = "lost";

    String gameState(String wordState, int failedAttempts, int maxAttempts) {
        StringBuilder msg = new StringBuilder();
        msg.append(GAMESTATE);
        msg.append("[");
        msg.append(WORDSTATE);
        msg.append(":");
        msg.append(wordState);
        msg.append(",");
        msg.append(FAILEDATTEMPTS);
        msg.append(":");
        return msg.toString();
    }
}
