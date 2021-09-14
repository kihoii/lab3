package com.github.kihoii.model;

import com.github.kihoii.utils.MapBlock;
import com.github.kihoii.view.GameObject;

import java.util.ArrayList;
import java.util.List;

public class Model {

    // CR: make fields static and private
    public final int WIDTH = 15;
    public final int HEIGHT = 17;
    public final int BLOCK_SIZE = 24;
    public final int SCREEN_X = WIDTH * BLOCK_SIZE;
    public final int SCREEN_Y = HEIGHT * BLOCK_SIZE;

    // CR: make field static
    private final int n_DOTS = 159;
    // CR: make field static
    public final int TOTAL_SCORE = n_DOTS * 10;

    private final short[] map;

    private Pacman pacman;
    private Ghost[] ghosts;
    // CR: make field static
    private final int numberOfGhosts = 4;

    public short[] screenData;

    public Model(short[] map){
        this.map = map;
    }

    List<GameObject> objectField;

    public void initNewModel()  {
        initData();
        initGame();
        makeField();
    }

    private void initData(){
        // CR: pass coords as constructor parameters
        pacman = new Pacman();
        ghosts = new Ghost[numberOfGhosts];
        for(int i = 0; i < numberOfGhosts; i++){
            ghosts[i] = new Ghost();
        }

        screenData = new short[HEIGHT * WIDTH];
    }

    private void initGame(){
        int size = WIDTH * HEIGHT;
        System.arraycopy(map, 0, screenData, 0, size);
        initGhostsCoords();
    }

    private void initGhostsCoords(){
        // CR: just pass this as constructor parameters
        ghosts[0].setCoords(7 * BLOCK_SIZE, 6 * BLOCK_SIZE);
        ghosts[1].setCoords(6 * BLOCK_SIZE, 7 * BLOCK_SIZE);
        ghosts[2].setCoords(7 * BLOCK_SIZE, 7 * BLOCK_SIZE);
        ghosts[3].setCoords(8 * BLOCK_SIZE, 7 * BLOCK_SIZE);
    }

    public void moveGhosts(){
        for(int i = 0; i < numberOfGhosts; i++){
            ghosts[i].move(screenData);
        }
    }

    public void movePacman(){
        screenData = pacman.move(screenData, ghosts);
    }

    public boolean move() {

        moveGhosts();
        movePacman();

        if(!pacman.getAlive()){
            return false;
        }

        if (pacman.getScore() % TOTAL_SCORE == 0){
            initGame();
        }
        makeField();
        return true;
    }

    private void makeField(){
        objectField = new ArrayList<>();

        int i = 0;
        for(int y = 0; y < SCREEN_Y; y += BLOCK_SIZE) {
            for (int x = 0; x < SCREEN_X; x += BLOCK_SIZE) {
                if (!(MapBlock.DOT.is(screenData[i]))) {
                    objectField.add(new GameObject.Point(x, y));
                }
                i++;
            }
        }

        objectField.add(new GameObject.Pacman(pacman.getDirection(), pacman.getX(), pacman.getY()));

        for(int k = 0; k < 4; k++){
            objectField.add(new GameObject.Ghost(ghosts[k].getX(), ghosts[k].getY()));
        }
    }

    
    // CR: move logic from make field here. also use local variable instead of 'objectField' field
    public List<GameObject> getField(){
        return objectField;
    }

    public int getLives(){
        return pacman.getLives();
    }

    public int getScore(){
        return pacman.getScore();
    }

    public void setDirection(Direction d){
        pacman.setDirection(d);
    }

    // CR: there should be no methods that are needed for tests only
    public void setPacmanCoords(int x, int y, Direction d){
        pacman.setCoords(x, y, d);
    }

    // CR: there should be no methods that are needed for tests only
    public void setGhostsCoords(int x, int y, int i){
        ghosts[i].setCoords(x, y);
    }

}
