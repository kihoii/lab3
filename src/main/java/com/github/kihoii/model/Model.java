package com.github.kihoii.model;

public class Model {

    public final int WIDTH = 15;
    public final int HEIGHT = 17;
    public final int BLOCK_SIZE = 24;

    private final int n_DOTS = 159;
    public final int TOTAL_SCORE = n_DOTS * 10;

    private final short[] map;

    private Pacman pacman;
    private Ghost[] ghosts;
    private final int numberOfGhosts = 4;

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

    public boolean movePacman() {
        moveGhosts();
        screenData = pacman.move(screenData, ghosts);

        if(!pacman.getAlive()){
            return false;
        }

        if (pacman.getScore() % TOTAL_SCORE == 0){
            initGame();
        }

        return true;
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


}
