package hello;

import java.lang.reflect.Array;

/**
 * Created by vikto on 1/22/2017.
 */
public class Player {
    private PlayerEnum player;
    private  int[] pits;
    private int current_stones;
    private int storeStones ;

    public Player(PlayerEnum player, int stones, int current_stones) {
        this.player = player;
        this.pits =new int[]{stones,stones,stones,stones,stones,stones};
        this.current_stones = current_stones;
        this. storeStones = 0;
    }

    public PlayerEnum getPlayer() {
        return player;
    }

    public int getStoreStones() {
        return storeStones;
    }


    public void setPlayer(PlayerEnum player) {
        this.player = player;
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
}
