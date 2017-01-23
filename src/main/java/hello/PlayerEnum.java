package hello;

/**
 * Created by vikto on 1/22/2017.
 */
public enum PlayerEnum {
    PlayerOne(1),
    PlayerTwo(2);
    private int value;

    PlayerEnum(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }


}