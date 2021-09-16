package com.github.kihoii.model;

import com.github.kihoii.*;
import com.github.kihoii.utils.MapBlock;
import com.github.kihoii.view.GameObject;

import java.util.*;

public class Model {

    static final int BLOCK_SIZE = 24;
    private static final int WIDTH = 15;
    private static final int HEIGHT = 17;
    private static final int SCREEN_X = WIDTH * BLOCK_SIZE;
    private static final int SCREEN_Y = HEIGHT * BLOCK_SIZE;
    private static final int N_DOTS = 159;
    private static final int TOTAL_SCORE = N_DOTS * 10;
    private static final int NUMBER_OF_GHOSTS = 4;

    private final short[] map;
    private final GameConfig gameConfig;

    private Pacman pacman;
    private Ghost[] ghosts;

    private short[] screenData;

    public Model(short[] map, GameConfig gameConfig){
        this.map = map;
        this.gameConfig = gameConfig;
    }

    public void initNewModel()  {
        initData();
        initGame();
    }

    private void initData(){
        GameConfig.Position pacmanPosition = gameConfig.pacman();
        pacman = new Pacman(pacmanPosition.x() * BLOCK_SIZE, pacmanPosition.y() * BLOCK_SIZE);
        GameConfig.Position[] ghostsPosition = gameConfig.ghosts();
        ghosts = Arrays.stream(ghostsPosition)
                .map(p -> new Ghost(p.x() * BLOCK_SIZE, p.y() * BLOCK_SIZE))
                .toArray(Ghost[]::new);

        screenData = new short[HEIGHT * WIDTH];
    }

    private void initGame(){
        System.arraycopy(map, 0, screenData, 0, WIDTH * HEIGHT);
    }

    private void moveGhosts(){
        for(int i = 0; i < NUMBER_OF_GHOSTS; i++){
            ghosts[i].move(screenData);
        }
    }

    private void movePacman(){
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
                if ((MapBlock.DOT.is(screenData[i]))) {
                    objectField.add(new GameObject.Point(x, y));
                }
                i++;
            }
        }


        objectField.add(new GameObject.Pacman(pacman.getDirection(), pacman.getX(), pacman.getY()));

        for(int k = 0; k < 4; k++){
            objectField.add(new GameObject.Ghost(ghosts[k].getX(), ghosts[k].getY()));
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

}
