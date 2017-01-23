package game;


/**
 * Created by vikto on 1/22/2017.
 */
public class Game {
    private PlayerEnum onTurn;
    private Mancala mancala;
    private int[] PitsPlayer1;
    private int[] PitsPlayer2;
    private int store1;
    private int store2;
    int[] allpits;

    public Game() {
        onTurn = PlayerEnum.PlayerOne;
        mancala= Mancala.getInstance();
    }

    public String doMove(String playerTurn, String positionPlayed) {

        //check with server state for rogue hacker post request
        if( onTurn.toString().equalsIgnoreCase(playerTurn)){

            PitsPlayer1 = mancala.getPlayerOne().getPits();
            PitsPlayer2 = mancala.getPlayerTwo().getPits();
            store1 = mancala.getPlayerOne().getStoreStones();
            store2 = mancala.getPlayerTwo().getStoreStones();

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
                    }
                    else if (pit < 6) {
                        PitsPlayer1[pit] += 1;
                    } else if (pit > 6) {
                        PitsPlayer2[PitsPlayer1.length*2-pit] += 1;

                    }
                    stones--;
                }
                 CheckOpponentCapture(pit);
                 playerTurn(pit);

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
                    }
                    else if (pit < 6) {

                        PitsPlayer1[PitsPlayer1.length-pit-1] += 1;
                    } else if (pit > 6) {
                        PitsPlayer2[(pit - 7)] += 1;

                    }
                    stones--;

                }
                CheckOpponentCapture(pit);
                playerTurn(pit);




            }

            mancala.getPlayerOne().setPits(PitsPlayer1);
            mancala.getPlayerOne().setStoreStones(store1);
            mancala.getPlayerTwo().setPits(PitsPlayer2);
            mancala.getPlayerTwo().setStoreStones(store2);
//            System.out.print("Player ONe stones"+ Arrays.toString(PitsPlayer1));
//            System.out.print("Player ONe pit"+ store1);
//            System.out.print("Player Two stones"+ Arrays.toString(PitsPlayer2));
//            System.out.print("Player ONe pit"+ store2);

            return "Move Performed";
        }
        else{
            return  "Error: not valid move";
        }



    }



//If the last sown seed lands in an empty house owned by the player,
// and the opposite house contains seeds, both the last seed and the
// opposite seeds are captured and placed into the player's store
    //>>>check if equal 1 as the pit was already filled with the new value
    //but was privoiously zero
 private void CheckOpponentCapture(int pit){

        if(onTurn.equals(PlayerEnum.PlayerOne )&& pit<6 && PitsPlayer1[pit]==1 ){
            PitsPlayer1[pit]=0;
            store1+= PitsPlayer2[pit]+1;
            PitsPlayer2[pit]=0;
        } if(onTurn.equals(PlayerEnum.PlayerTwo )&&pit > 6 && PitsPlayer2[(pit - 7)]==1){
            PitsPlayer2[(pit - 7)]=0;
            store2+= PitsPlayer1[pit-7]+1;
            PitsPlayer1[pit-7]=0;
     }


 }

    public boolean checkEndGame() {
        return mancala.getPlayerOne().checkGameOver() || mancala.getPlayerTwo().checkGameOver();
    }

    private void playerTurn(int pit){
        if(pit !=6){
        //switch player turn
        onTurn = (PlayerEnum.PlayerTwo.equals(onTurn)) ? PlayerEnum.PlayerOne : PlayerEnum.PlayerTwo;
        Mancala.getInstance().getPlayerOne().setPlayAgain(false);
        Mancala.getInstance().getPlayerTwo().setPlayAgain(false);
        }
        else{
            //If the last sown seed lands in the player's store, the player gets an additional move
           if (PlayerEnum.PlayerTwo.equals(onTurn)){
               Mancala.getInstance().getPlayerTwo().setPlayAgain(true);
            }
            else{
                Mancala.getInstance().getPlayerOne().setPlayAgain(true);
            }
        }
    }



}
