import com.github.kihoii.GameConfig;
import com.github.kihoii.model.Model;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ModelTests {

    private final File MAP_FILE = new File("src/main/resources/Map");

    private static final int WIDTH = 15;
    private static final int HEIGHT = 17;

    private final short[] map = getMap();

    private short[] getMap(){
        short[] map = new short[WIDTH * HEIGHT];
        try {
            Scanner scan = new Scanner(MAP_FILE);
            for(int y = 0; y < WIDTH * HEIGHT; y++) {
                scan.useDelimiter(", ");
                map[y] = scan.nextShort();
            }
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
        return map;
    }

    @Test
    public void minusLive(){
        GameConfig.Position PACMAN_POS = new GameConfig.Position(7, 6);
        GameConfig.Position[] GHOST_POS = new GameConfig.Position[]{new GameConfig.Position(7, 6), new GameConfig.Position(6, 7), new GameConfig.Position(7, 7), new GameConfig.Position(8, 7)};
        Model model = new Model(map, new GameConfig(GHOST_POS, PACMAN_POS));
        model.initNewModel();

        model.move();

        Assert.assertEquals(2, model.getLives());
    }

    @Test
    public void EndGame(){
        GameConfig.Position PACMAN_POS = new GameConfig.Position(7, 6);
        GameConfig.Position[] GHOST_POS = new GameConfig.Position[]{new GameConfig.Position(7, 6), new GameConfig.Position(6, 7), new GameConfig.Position(7, 7), new GameConfig.Position(8, 7)};
        Model model = new Model(map, new GameConfig(GHOST_POS, PACMAN_POS));
        model.initNewModel();

        boolean alive = true;
        while(model.getLives() != 0) {
            alive = model.move();
        }
        Assert.assertFalse(alive);
    }

}
