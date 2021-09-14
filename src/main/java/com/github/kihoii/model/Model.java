package com.github.kihoii.model;

import com.github.kihoii.utils.MapBlock;
import com.github.kihoii.view.GameObject;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private final static int WIDTH = 15;
    private final static int HEIGHT = 17;
    private final static int BLOCK_SIZE = 24;
    private final static int SCREEN_X = WIDTH * BLOCK_SIZE;
    private final static int SCREEN_Y = HEIGHT * BLOCK_SIZE;

    private final static int n_DOTS = 159;
    private final static int TOTAL_SCORE = n_DOTS * 10;

    private final short[] map;

    private Pacman pacman;
    private Ghost[] ghosts;
    private final static int numberOfGhosts = 4;

    public short[] screenData;

    public Model(short[] map){
        this.map = map;
    }

    public void initNewModel()  {
        initData();
        initGame();
    }

    private void initData(){
        pacman = new Pacman();
        ghosts = new Ghost[numberOfGhosts];

        screenData = new short[HEIGHT * WIDTH];
    }

    private void initGhosts(){
        ghosts[0] = new Ghost(7 * BLOCK_SIZE, 6 * BLOCK_SIZE);
        ghosts[1] = new Ghost(6 * BLOCK_SIZE, 6 * BLOCK_SIZE);
        ghosts[2] = new Ghost(7 * BLOCK_SIZE, 7 * BLOCK_SIZE);
        ghosts[3] = new Ghost(8 * BLOCK_SIZE, 7 * BLOCK_SIZE);
    }

    private void initGame(){
        int size = WIDTH * HEIGHT;
        System.arraycopy(map, 0, screenData, 0, size);
        initGhosts();
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
        return true;
    }

    public List<GameObject> getField(){
        List<GameObject> objectField = new ArrayList<>();

        int i = 0;
        for(int y = 0; y < SCREEN_Y; y += BLOCK_SIZE) {
            for (int x = 0; x < SCREEN_X; x += BLOCK_SIZE) {
                if (!(MapBlock.DOT.is(screenData[i]))) {
                    GameObject point = new GameObject.Point();
                    point.setCoords(x, y);
                    objectField.add(point);
                }
                i++;
            }
        }

        GameObject pcmn = new GameObject.Pacman(pacman.getDirection());
        pcmn.setCoords(pacman.getX(), pacman.getY());
        objectField.add(pcmn);

        for(int k = 0; k < 4; k++){
            GameObject ghst = new GameObject.Ghost();
            ghst.setCoords(ghosts[k].getX(), ghosts[k].getY());
            objectField.add(ghst);
        }

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

//    // CR: there should be no methods that are needed for tests only
//    public void setPacmanCoords(int x, int y, Direction d){
//        pacman.setCoords(x, y, d);
//    }
//
//    // CR: there should be no methods that are needed for tests only
//    public void setGhostsCoords(int x, int y, int i){
//        ghosts[i].setCoords(x, y);
//    }

}
