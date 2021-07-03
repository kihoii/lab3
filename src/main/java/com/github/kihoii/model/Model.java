package com.github.kihoii.model;

import com.github.kihoii.Pacman;
import com.github.kihoii.model.ghosts.RandomGhosts;
import com.github.kihoii.model.pacman.MovePacman;
import com.github.kihoii.utils.constants.Context;
import com.github.kihoii.utils.enums.Direction;
import com.github.kihoii.utils.enums.States;
import com.github.kihoii.utils.observer.FieldUpdate;
import com.github.kihoii.utils.observer.Observable;

public class Model implements Observable {

    public static int score;
    private int lives;
    public static boolean alive;

    private static States curState;
    private FieldUpdate fieldUpdate;

    public Model(){
        curState = States.MENU;
    }

    public static short[] screenData;

    public void initNewModel(){
        curState = States.START;
        initData();
        initGame();
        notifyObservers();
        Pacman.timer.start();
    }

    private void initData(){
        Context.ghosts_x = new int[Context.N_GHOSTS];
        Context.ghosts_y = new int[Context.N_GHOSTS];
        Context.ghosts_dx = new int[Context.N_GHOSTS];
        Context.ghosts_dy = new int[Context.N_GHOSTS];
        Context.dx = new int[Context.N_GHOSTS];
        Context.dy = new int[Context.N_GHOSTS];

        screenData = new short[Context.HIGH * Context.WEIGHT];
    }


    private void initGame(){
        alive = true;
        lives = 3;
        score = 0;
        initLevel();
    }

    private void initLevel(){
        int size = Context.WEIGHT * Context.HIGH;
        System.arraycopy(Context.map, 0, screenData, 0, size);
        initStartCoords();
    }

    private void initStartCoords(){
        Context.ghosts_x[0] = 7 * Context.BLOCK_SIZE;
        Context.ghosts_y[0] = 6 * Context.BLOCK_SIZE;

        Context.ghosts_x[1] = 6 * Context.BLOCK_SIZE;
        Context.ghosts_y[1] = 7 * Context.BLOCK_SIZE;

        Context.ghosts_x[2] = 7 * Context.BLOCK_SIZE;
        Context.ghosts_y[2] = 7 * Context.BLOCK_SIZE;

        Context.ghosts_x[3] = 8 * Context.BLOCK_SIZE;
        Context.ghosts_y[3] = 7 * Context.BLOCK_SIZE;

        Context.pacman_x = Context.START_X;
        Context.pacman_y = Context.START_Y;

        Context.pacman_dx = 0;
        Context.pacman_dy = 0;
        Context.req_dx = 0;
        Context.req_dy = 0;
    }


    public void moveGhosts(){
        RandomGhosts.move();
    }

    public void movePacman(Direction d){

        switch(d){
            case UP -> {
                Context.req_dx = 0;
                Context.req_dy = -1;
            }
            case DOWN -> {
                Context.req_dx = 0;
                Context.req_dy = 1;
            }
            case RIGHT -> {
                Context.req_dx = 1;
                Context.req_dy = 0;
            }
            case LEFT -> {
                Context.req_dx = -1;
                Context.req_dy = 0;
            }
        }

        moveGhosts();

        if(MovePacman.move()){
            Context.pacman_x += Context.pacman_dx * Context.PACMAN_SPEED;
            Context.pacman_y += Context.pacman_dy * Context.PACMAN_SPEED;
            if(!alive){
                lives--;
                if (lives > 0) {
                    alive = true;
                    initStartCoords();
                }
                else if (lives == 0){
                    curState = States.END;
                    if(score >= Context.scores[9]) {
                        Context.scores[9] = score;
                    }
                }
            }
            if (score % Context.TOTAL_SCORE == 0){
                initLevel();
            }

        }
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
    }

    public short[] getScreenData() {
        return screenData;
    }

    public int getLives(){
        return lives;
    }

    @Override
    public void addObserver(FieldUpdate fieldUpdate) {
        this.fieldUpdate = fieldUpdate;
    }

    @Override
    public void notifyObservers() {
        fieldUpdate.handleEvent();
    }

}
