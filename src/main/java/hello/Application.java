package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        Player player1 = new Player(PlayerEnum.PlayerOne,6,36);
        Player player2 = new Player(PlayerEnum.PlayerOne,6,36);
        Game game= new Game();
        Mancala mancala = Mancala.getInstance();

        mancala.setGame(game);
        mancala.setPlayerOne(player1);
        mancala.setPlayerTwo(player2);
    }

}
