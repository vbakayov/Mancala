package game;

/**
 * Created by vikto on 1/22/2017.
 */
public class Player {
    private PlayerEnum player;
    private  int[] pits;
    private int current_stones;
    private int storeStones ;
    private boolean playAgain;


    public Player(PlayerEnum player, int stones, int current_stones) {
        this.player = player;
        this.pits =new int[]{stones,stones,stones,stones,stones,stones};
        this.current_stones = current_stones;
        this. storeStones = 0;
        this.playAgain= false;
    }

    public PlayerEnum getPlayer() {
        return player;
    }

    public int getStoreStones() {
        return storeStones;
    }

    public boolean checkGameOver(){
        int sum=0;
        for(int i:pits)
            sum+=i;
        return  sum==0;
    }

    public int[] getPits() {
        return pits;
    }

    public void setPits(int[] pits) {
        this.pits = pits;
    }

    public int getCurrent_stones() {
        return current_stones;
    }

    public void setCurrent_stones(int current_stones) {
        this.current_stones = current_stones;
    }

    public void setStoreStones(int storeStones) {
        this.storeStones = storeStones;
    }

    public boolean isPlayAgain() {
        return playAgain;
    }

    public void setPlayAgain(boolean playAgain) {
        this.playAgain = playAgain;
    }
}
