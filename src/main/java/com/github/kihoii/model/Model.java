package com.github.kihoii.model;

import com.github.kihoii.model.ghosts.Ghost;
import com.github.kihoii.model.pacman.Pacman;
import com.github.kihoii.utils.enums.States;
import com.github.kihoii.utils.observer.FieldUpdate;
import com.github.kihoii.utils.observer.Observable;

import java.io.IOException;

public class Model implements Observable {


    public final int WEIGHT = 15;
    public final int HIGH = 17;
    public final int BLOCK_SIZE = 24;

    public final int START_X = 7 * BLOCK_SIZE;
    public final int START_Y = 12 * BLOCK_SIZE;

    public int PACMAN_SPEED = 3;

    private final short[] map;

    private FieldUpdate fieldUpdate;

    private int reqDx, reqDy;

    private Pacman pacman;
    private Ghost[] ghosts;
    private final int numberOfGhosts = 4;

    public static short[] screenData;

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
        for(int i = 0; i < numberOfGhosts; i++){
            ghosts[i] = new Ghost();
        }

        screenData = new short[HIGH * WEIGHT];
    }


    private void initGame(){
        int size = WEIGHT * HIGH;
        System.arraycopy(map, 0, screenData, 0, size);
        initGhostsCoords();
    }

    private void initGhostsCoords(){
        ghosts[0].setCoords(7 * BLOCK_SIZE, 6 * BLOCK_SIZE);
        ghosts[1].setCoords(6 * BLOCK_SIZE, 7 * BLOCK_SIZE);
        ghosts[2].setCoords(7 * BLOCK_SIZE, 7 * BLOCK_SIZE);
        ghosts[3].setCoords(8 * BLOCK_SIZE, 7 * BLOCK_SIZE);
    }

    private void initStartCoords(){
        initGhostsCoords();
        pacman.setCoords(START_X, START_Y, Direction.NONE);
    }


    public void moveGhosts(){
        for(int i = 0; i < numberOfGhosts; i++){
            ghosts[i].moveGhost(pacman, screenData);
        }
    }

    public void movePacman() throws IOException {

        Direction d = pacman.getDirection();
        switch(d){
            case UP -> {
                reqDx = 0;
                reqDy = -1;
            }
            case DOWN -> {
                reqDx = 0;
                reqDy = 1;
            }
            case RIGHT -> {
                reqDx = 1;
                reqDy = 0;
            }
            case LEFT -> {
                reqDx = -1;
                reqDy = 0;
            } case NONE -> {
                reqDx = 0;
                reqDy = 0;
            }
        }

        moveGhosts();

        if(pacman.movePacman(reqDx, reqDy)){
            int pacmanX = pacman.getX(), pacmanY = pacman.getY();
            pacmanX += pacman.getDx() * PACMAN_SPEED;
            pacmanY += pacman.getDy() * PACMAN_SPEED;

            pacman.setCoords(pacmanX, pacmanY, d);
        }

        if(!pacman.getAlive()){
            if (pacman.getLives() > 0) {
                pacman.setAlive(true);
                d = Direction.NONE;
                pacman.setDxy(0,0);
                initStartCoords();
            }
            else if (pacman.getLives() <= 0){
                notifyObservers(States.END);
            }
        }

        int n_DOTS = 159;
        int TOTAL_SCORE = n_DOTS * 10;
        if (pacman.getScore() % TOTAL_SCORE == 0){
            initGame();
        }

        pacman.setDirection(d);
        notifyObservers(States.IN_PROC);
    }

    public short[] getScreenData() {
        return screenData;
    }

    public Pacman getPacman(){
        return pacman;
    }

    public Ghost[] getGhosts(){
        return ghosts;
    }

    @Override
    public void addObserver(FieldUpdate fieldUpdate) {
        this.fieldUpdate = fieldUpdate;
    }

    @Override
    public void notifyObservers(States state) throws IOException {
        fieldUpdate.handleEvent(state);
    }

}
