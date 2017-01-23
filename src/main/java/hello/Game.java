package hello;



import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vikto on 1/22/2017.
 */
public class Game {
    private PlayerEnum onTurn;
    private Mancala mancala;
    int[] allpits;

    public Game() {
        onTurn = PlayerEnum.PlayerOne;
        mancala= Mancala.getInstance();
    }

    public String doMove(String playerTurn, String positionPlayed) {

        //check with server state for rogue hacker post request
        if( onTurn.toString().equalsIgnoreCase(playerTurn)){

            int[] PitsPlayer1 = mancala.getPlayerOne().getPits();
            int[] PitsPlayer2 = mancala.getPlayerTwo().getPits();
            int store1 = mancala.getPlayerOne().getStoreStones();
            int store2 = mancala.getPlayerTwo().getStoreStones();

           int stones;
            if(onTurn.equals(PlayerEnum.PlayerOne)){
                int pit = Integer.parseInt(positionPlayed)-1;
                stones= PitsPlayer1[pit];
                PitsPlayer1[pit]=0;


                while (stones > 0) {
                    ++pit;
                    // wrap around the board before reaching other player's store
                    if (pit > 12) {
                        pit = 0;
                    }

                    if (pit == 6) {
                        store1+= 1;
                    }else if (pit == 13) {
                        store2 += 1;
                    }
                    else if (pit < 6) {
                        PitsPlayer1[pit] += 1;
                    } else if (pit > 6) {
                        PitsPlayer2[PitsPlayer1.length*2-pit] += 1;

                    }
                    stones--;

                }


            }else{
                int pit = Integer.parseInt(positionPlayed);
                stones= PitsPlayer2[pit-7];
                PitsPlayer2[pit-7]=0;

                while (stones > 0) {
                    --pit;
//                   System.out.print("Pit Numer"+ pit);
                    // wrap around the board before reaching other player's store
                    if (pit < 1) {
                        pit = 12;
                    }

                    if (pit == 6) {
                        store2+= 1;
                    }else if (pit == 12) {
                        store1 += 1;
                    }
                    else if (pit < 6) {

                        PitsPlayer1[PitsPlayer1.length-pit-1] += 1;
                    } else if (pit > 6) {
                        PitsPlayer2[(pit - 7)] += 1;

                    }
                    stones--;

                }


            }

            mancala.getPlayerOne().setPits(PitsPlayer1);
            mancala.getPlayerOne().setStoreStones(store1);
            mancala.getPlayerTwo().setPits(PitsPlayer2);
            mancala.getPlayerTwo().setStoreStones(store2);
            System.out.print("Player ONe stones"+ Arrays.toString(PitsPlayer1));
            System.out.print("Player ONe pit"+ store1);
            System.out.print("Player Two stones"+ Arrays.toString(PitsPlayer2));
            System.out.print("Player ONe pit"+ store2);
            //switch player turn
            onTurn = (PlayerEnum.PlayerTwo.equals(onTurn)) ? PlayerEnum.PlayerOne : PlayerEnum.PlayerTwo;
            return "Move Performed";
        }
        else{
            return  "Error: not valid move";
        }



    }




    private <E extends Enum<E>> boolean isInEnum(String value, Class<E> enumClass) {
        for (E e : enumClass.getEnumConstants()) {
            if (e.name().equals(value)) {
                return true;
            }
        }
        return false;
    }

    public int[] concat(int[] a, int[] b) {
        int aLen = a.length;
        int bLen = b.length;
        int[] c= new int[aLen+bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }

}
