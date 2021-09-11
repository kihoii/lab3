//import com.github.kihoii.controller.Controller;
//import com.github.kihoii.model.Model;
//import com.github.kihoii.model.Direction;
//import com.github.kihoii.utils.enums.States;
//import com.github.kihoii.view.View;
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.util.List;
//
//public class ModelTests {
//
//    @Test
//    public void PacmanDies(){
//        Model model = new Model();
//        model.initNewModel();
//        model.setCurState(States.IN_PROC);
//        model.getPacman().setCoords(165, 144, Direction.NONE);
//        model.moveGhosts();
//        Assert.assertFalse(model.getPacman().getAlive());
//    }
//
//    @Test
//    public void EndGame(){
//        Model model = new Model();
//        model.initNewModel();
//        model.setCurState(States.IN_PROC);
//
//        model.getPacman().setCoords(11, 14, Direction.NONE);
//        model.getGhosts()[1].setCoords(11,14);
//        model.moveGhosts();
//
//        model.getPacman().setCoords(11, 14, Direction.NONE);
//        model.getGhosts()[1].setCoords(11,14);
//        model.moveGhosts();
//
//        model.getPacman().setCoords(11, 14, Direction.NONE);
//        model.getGhosts()[1].setCoords(11,14);
//        model.moveGhosts();
//
//        Assert.assertEquals(0, model.getPacman().getLives());
//    }
//
//}
