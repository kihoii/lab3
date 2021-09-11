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
        model.getPacman().setCoords(165, 144, Direction.NONE);
        model.moveGhosts();
        Assert.assertFalse(model.getPacman().getAlive());
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

        model.getPacman().setCoords(11, 14, Direction.NONE);
        model.getGhosts()[1].setCoords(11,14);
        model.moveGhosts();

        model.getPacman().setCoords(11, 14, Direction.NONE);
        model.getGhosts()[1].setCoords(11,14);
        model.moveGhosts();

        model.getPacman().setCoords(11, 14, Direction.NONE);
        model.getGhosts()[1].setCoords(11,14);
        model.moveGhosts();

        Assert.assertEquals(0, model.getPacman().getLives());
    }

}
