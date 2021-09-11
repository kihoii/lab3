package com.github.kihoii.model;

import com.github.kihoii.model.ghosts.Ghost;
import com.github.kihoii.model.ghosts.MoveGhosts;
import com.github.kihoii.model.pacman.MovePacman;
import com.github.kihoii.model.pacman.Pacman;
import com.github.kihoii.utils.constants.Context;
import com.github.kihoii.utils.enums.Direction;
import com.github.kihoii.utils.enums.States;
import com.github.kihoii.utils.observer.FieldUpdate;
import com.github.kihoii.utils.observer.Observable;

public class Model implements Observable {

    // CR: please move it to Pacman
    public static int score;

    // CR: model shouldn't be bounded to possible states of view
    // CR: probably the only thing that it might know is the state of the game (stopped / running)
    // CR: and even that is probably redundant
    private static States curState;
    private FieldUpdate fieldUpdate;

    // CR: naming
    private int req_dx, req_dy;

    private Pacman pacman;
    private Ghost[] ghosts;

    public Model(){
        curState = States.MENU;
    }

    public static short[] screenData;

    public void initNewModel(){
        curState = States.START;
        initData();
        initGame();
        //curState = States.IN_PROC;
        //setCurState(States.IN_PROC);
        notifyObservers();
        curState = States.IN_PROC;
        //Game.timer.start();
    }

    private void initData(){
        pacman = new Pacman();
        ghosts = new Ghost[4];
        for(int i = 0; i < 4; i++){
            ghosts[i] = new Ghost();
        }

        screenData = new short[Context.HIGH * Context.WEIGHT];
    }


    private void initGame(){
        score = 0;
        initLevel();
    }

    private void initLevel(){
        int size = Context.WEIGHT * Context.HIGH;
        System.arraycopy(Context.map, 0, screenData, 0, size);
        initGhostsCoords();
    }

    private void initGhostsCoords(){
        ghosts[0].setCoords(7 * Context.BLOCK_SIZE, 6 * Context.BLOCK_SIZE);
        ghosts[1].setCoords(6 * Context.BLOCK_SIZE, 7 * Context.BLOCK_SIZE);
        ghosts[2].setCoords(7 * Context.BLOCK_SIZE, 7 * Context.BLOCK_SIZE);
        ghosts[3].setCoords(8 * Context.BLOCK_SIZE, 7 * Context.BLOCK_SIZE);
    }

    private void initStartCoords(){
        initGhostsCoords();
        pacman.setCoords(Context.START_X, Context.START_Y, Direction.NONE);
    }


    public void moveGhosts(){
        MoveGhosts.move(pacman, ghosts, screenData);
    }

    public void movePacman(Direction d){

        switch(d){
            case UP -> {
                req_dx = 0;
                req_dy = -1;
            }
            case DOWN -> {
                req_dx = 0;
                req_dy = 1;
            }
            case RIGHT -> {
                req_dx = 1;
                req_dy = 0;
            }
            case LEFT -> {
                req_dx = -1;
                req_dy = 0;
            } case NONE -> {
                req_dx = 0;
                req_dy = 0;
            }
        }

        moveGhosts();

        if(MovePacman.move(pacman, req_dx, req_dy)){
            int pacmanX = pacman.getX(), pacmanY = pacman.getY();
            pacmanX += pacman.getDx() * Context.PACMAN_SPEED;
            pacmanY += pacman.getDy() * Context.PACMAN_SPEED;

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
                //System.out.println("end");
                curState = States.END;
                if(score >= Context.scores[9]) {
                    Context.scores[9] = score;
                }
            }
        }

        if (score % Context.TOTAL_SCORE == 0){
            initLevel();
        }

        pacman.setDirection(d);
        notifyObservers();
    }


    public int getScore(){
        return score;
    }

    public static States getCurState(){
        return curState;
    }

    public void setCurState(States s){
        curState = s;
        notifyObservers();
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
    public void notifyObservers() {
        fieldUpdate.handleEvent(curState);
    }

}
