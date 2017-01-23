package hello;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class GreetingController {


    //use just one model for the index page
    @RequestMapping("/play")
    public String greetings() {
        return "greetings";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/makemove")
     @ResponseBody
     public String getTime(@RequestParam("position") String positionPlayed,@RequestParam("player") String playerTurn, Model model) {

        Mancala mancala = Mancala.getInstance();
        mancala.getGame().doMove(playerTurn,positionPlayed);

        JSONArray ja = new JSONArray();
        ja.add( mancala.getPlayerOne().getPits());
        ja.add( mancala.getPlayerOne().getStoreStones());
        ja.add( mancala.getPlayerTwo().getPits());
        ja.add( mancala.getPlayerTwo().getStoreStones());

        JSONObject json = new JSONObject();
        json.put("player1Again", String.valueOf(mancala.getPlayerOne().isPlayAgain()));
        json.put("player2Again", String.valueOf(mancala.getPlayerTwo().isPlayAgain()));
        ja.add(json);

        JSONObject jsonEndGameObj = new JSONObject();
        jsonEndGameObj.put("endGame",mancala.getGame().checkEndGame());
        ja.add(jsonEndGameObj);
        return String.valueOf(ja);
    }

}
