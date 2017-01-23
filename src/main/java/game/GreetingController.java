package game;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//TODO rename controller name, should define new beams etc.
@Controller
public class GreetingController {


    //use just one model for the play page and whole game logic
    @RequestMapping("/play")
    public String greetings() {

        //instantiate the players and the game
        Player player1 = new Player(PlayerEnum.PlayerOne,6,36);
        Player player2 = new Player(PlayerEnum.PlayerOne,6,36);
        Game game= new Game();


        Mancala mancala = Mancala.getInstance();
        mancala.setGame(game);
        mancala.setPlayerOne(player1);
        mancala.setPlayerTwo(player2);
        return "play";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/makemove")
     @ResponseBody
     public String makeMove(@RequestParam("position") String positionPlayed,@RequestParam("player") String playerTurn, Model model) {

        //execute the move
        Mancala mancala = Mancala.getInstance();
        mancala.getGame().doMove(playerTurn,positionPlayed);

        //collect the pit values and stores for each player
        JSONArray ja = new JSONArray();
        ja.add( mancala.getPlayerOne().getPits());
        ja.add( mancala.getPlayerOne().getStoreStones());
        ja.add( mancala.getPlayerTwo().getPits());
        ja.add( mancala.getPlayerTwo().getStoreStones());

        //boolean indicating if any of the players can play again
        JSONObject jsonPlayerObj = new JSONObject();
        jsonPlayerObj.put("player1Again", String.valueOf(mancala.getPlayerOne().isPlayAgain()));
        jsonPlayerObj.put("player2Again", String.valueOf(mancala.getPlayerTwo().isPlayAgain()));
        ja.add(jsonPlayerObj);

        //boolean to check if the game has fined
        JSONObject jsonEndGameObj = new JSONObject();
        jsonEndGameObj.put("endGame",mancala.getGame().checkEndGame());
        ja.add(jsonEndGameObj);

        //return as string;but Objects will remain objects
        return String.valueOf(ja);
    }

}
