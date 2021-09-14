import com.github.kihoii.model.Model;
import com.github.kihoii.model.Direction;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ModelTests {

    @Test
    public void PacmanDies() throws IOException {
        short[] map = new short[15*17];
        File file = new File("src/main/resources/Map");
        Scanner scan = new Scanner(file);

        for(int y = 0; y < 15*17; y++) {
            scan.useDelimiter(", ");
            map[y] = scan.nextShort();
        }
        Model model = new Model(map);
        model.initNewModel();
        model.setPacmanCoords(7*24, 6*24, Direction.NONE);

        model.moveGhosts();
        Assert.assertTrue(model.getLives() > 0);
    }

    @Test
    public void EndGame() throws IOException {
        short[] map = new short[15*17];
        File file = new File("src/main/resources/Map");
        Scanner scan = new Scanner(file);

        for(int y = 0; y < 15*17; y++) {
            scan.useDelimiter(", ");
            map[y] = scan.nextShort();
        }
        Model model = new Model(map);
        model.initNewModel();

        model.setPacmanCoords(11, 14, Direction.NONE);
        model.setGhostsCoords(11, 14, 1);
        model.movePacman();

        model.setPacmanCoords(11, 14, Direction.NONE);
        model.setGhostsCoords(11, 14, 1);
        model.movePacman();

        model.setPacmanCoords(11, 14, Direction.NONE);
        model.setGhostsCoords(11, 14, 1);
        model.movePacman();

        Assert.assertEquals(0, model.getLives());
    }

}
