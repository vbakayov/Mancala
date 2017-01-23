package game;

/**
 * Created by vikto on 1/22/2017.
 */
public class Mancala {
    Player playerOne;
    Player playerTwo;
    Game game;


    private static Mancala mancala;
    private Mancala(){
    };

    public static synchronized Mancala getInstance( ) {
        if (mancala == null)
            mancala=new Mancala();
        return mancala;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public Game getGame() {
        return game;
    }
    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
