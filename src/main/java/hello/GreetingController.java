package hello;

import net.minidev.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.Random;

@Controller
public class GreetingController {
    //use just one model, quick fix (consider refactor)

    @RequestMapping("/play")
    public String greetings(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {

        model.addAttribute("name", "Viktor");
        model.addAttribute("dataNum", "3");
        return "greetings";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/makemove")
     @ResponseBody
     public String getTime(@RequestParam("position") String positionPlayed,@RequestParam("player") String playerTurn, Model model) {
        Mancala mancala = Mancala.getInstance();

        mancala.getGame().doMove(playerTurn,positionPlayed);
        System.out.print(playerTurn);
        System.out.print(positionPlayed);
        Random rand = new Random();
        float r = rand.nextFloat() * 100;
        String result = "<br>Next Random # is <b>" + r + "</b>. Generated on <b>" + new Date().toString() + "</b>";
        System.out.println("Debug Message from CrunchifySpringAjaxJQuery Controller.." + new Date().toString());
//        return result;
        JSONArray ja = new JSONArray();
        ja.add( mancala.getPlayerOne().getPits());
        ja.add( mancala.getPlayerOne().getStoreStones());
        ja.add( mancala.getPlayerTwo().getPits());
        ja.add( mancala.getPlayerTwo().getStoreStones());
        return String.valueOf(ja);
    }

}
