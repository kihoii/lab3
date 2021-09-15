package com.github.kihoii;

public class GameConfig {

    private static final Position PACMAN_POS = new Position(7, 12);
    private static final Position[] GHOST_POS = new Position[]{new Position(7, 6), new Position(6, 7), new Position(7, 7), new Position(8, 7)};
    private final Position[] ghosts;
    private final Position pacman;

    public GameConfig(Position[] ghosts, Position pacman) {
        this.ghosts = ghosts;
        this.pacman = pacman;
    }

    public static GameConfig defaultConfig() {
        return new GameConfig(GHOST_POS, PACMAN_POS);
    }

    public Position[] ghosts() {
        return ghosts;
    }
    
    public Position pacman() {
        return pacman;
    }

    public static class Position {
        private final int x;
        private final int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int x() {
            return x;
        }

        public int y() {
            return y;
        }
    }

}
