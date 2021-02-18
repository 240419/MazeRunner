package MazeRunner;
import java.util.Arrays;

/**
 * This is the Maze class. It has all of the methods and information needed
 * to build a specific maze and have a MazeRunner get from the start to the end.
 * There are pits and walls that block the path of the user. If the user attempts to
 * move through a wall or a pit Maze throws an exception and terminates the program.
 * The MazeRunner class should never throw an exception.
 */

public class Maze {
    private char[][] myMap, solution;
    private char[][] solutionWOPitfalls;
    private Player player;
    /**
     * Instantiate a new Maze object.
     */
    public Maze() {        
        myMap = new char[20][20];
        solution = new char[20][20];
        
        player = new Player(1, 0);
        fillMap(myMap);
        fillMap(solution);
        fillSolution();
        
        // System.out.println((MazeSolver.connected(this.solutionWOPitfalls, starte, ende)));
        // System.out.println(Arrays.deepToString(MazeSolver.nodeProcessor(solutionWOPitfalls,starte,ende,MazeSolver.nodeProcessor(solutionWOPitfalls,starte,ende, MazeSolver.nodeSort(solutionWOPitfalls)))));
    }

    private void fillMap(char[][] map) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                map[i][j] = '.';
            }
        }
        myMap[player.row][player.col] = 'x';
    }

    /**
     * Display the maze. Dots represent unexplored spaces, x is your current position,
     * - and | are walls, 0 are pits, and * are explored spaces.
     */

    private void fillSolution() {
        for (int i = 0; i < 6; i++) {
            solution[0][i] = '-';
        }
        for (int i = 0; i < 5; i++) {
            solution[1][i] = '*';
        }
        for (int i = 2; i < 20; i++) {
            solution[i][4] = '*';
        }
        solution[19][4] = '-';
        solution[1][5] = '|';
        solution[2][5] = '|';
        for (int i = 0; i < 14; i++) {
            solution[2][i] = '-';
        }
        solution[2][4] = '*';
        for (int i = 3; i < 20; i++) {
            solution[i][3] = '|';
        }
        for (int i = 4; i < 15; i++) {
            solution[3][i] = '*';
        }
        solution[2][14] = '|';
        solution[1][14] = '|';
        solution[0][14] = '-';
        solution[0][15] = '-';
        solution[0][16] = '-';
        for (int i = 1; i < 7; i++) {
            solution[i][16] = '|';
        }
        solution[7][14] = '-';
        solution[7][15] = '-';
        solution[7][16] = '-';
        for (int i = 5; i < 15; i++) {
            solution[4][i] = '-';
        }
        solution[5][14] = '|';
        solution[6][14] = '|';
        for (int i = 1; i < 7; i++) {
            solution[i][15] = '*';
        }
        for (int i = 5; i < 20; i++) {
            solution[i][5] = '|';
        }
        for (int i = 5; i < 17; i++) {
            solution[12][i] = '-';
            solution[13][i] = '*';
            solution[14][i] = '-';
        }
        solution[11][16] = '|';
        solution[10][16] = '|';
        solution[14][18] = '|';
        solution[13][18] = '|';
        solution[12][18] = '|';
        solution[11][18] = '-';
        solution[11][19] = '-';
        solution[9][16] = '-';
        solution[9][17] = '-';
        solution[9][18] = '-';
        solution[9][19] = '-';
        solution[14][17] = '-';
        for (int i = 10; i < 14; i++) {
            solution[i][17] = '*';
        }
        solution[10][18] = '*';
        solution[10][19] = '*';
        for(int i = 5; i < 13; i++) {
            solution[8][i] = '-';
            solution[10][i] = '-';
            solution[9][i] = '*';
        }
        for (int i = 8; i < 11; i++) {
            solution[i][13] = '|';
        }
        for(int i = 5; i < 19; i++) {
            solution[17][i] = '-';
            solution[19][i] = '-';
            solution[18][i] = '*';
        }
        for (int i = 17; i < 20; i++) {
            solution[i][19] = '|';
        }
        for(int i = 3; i >= 0; i--) {
            solution[14][i] = '-';
            solution[16][i] = '-';
            solution[15][i] = '*';
        }
        for (int i = 14; i < 17; i++) {
            solution[i][0] = '|';
        }
        solutionWOPitfalls = new char[20][20];
        for (int x = 0; x < 20; x++){
            for (int y = 0; y < 20; y++){
                solutionWOPitfalls[x][y] = solution[x][y];
            }
        }
        addPits();
        printMap(solution);
    }
    
    private void addPits() {
        solution[1][2] = '0';
        solution[3][7] = '0';
        solution[3][12] = '0';
        solution[6][4] = '0';
        solution[15][4] = '0';
        solution[9][10] = '0';
        solution[13][17] = '0';
        solution[13][15] = '0';
        solution[18][10] = '0';
    }

    
    /**
     * Determines if the user reached the end of the maze.
     * @return true if the user is at the end, false otherwise.
     */

    public void printMap() {
        printMap(myMap);
    }

    private void printMap(char[][] map) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public char[][] getMap() {
        return this.myMap;
    }

    public class Player {
        public static final int MAX_MOVES = 100;
        private int row, col, moves;
        
        Player(int row, int col) {
            this.row = row;
            this.col = col;
            this.moves = 0;
        }

        private boolean isThereAPit(int rowMove, int colMove) {
            if (this.col + colMove > 20 || this.col + colMove < 0 || this.row + rowMove > 20 || this.row + rowMove < 0  ) {
                return false;
            } else if (solution[this.row + rowMove][this.col + colMove] == '0') {
                myMap[this.row + rowMove][this.col + colMove] = '0';
                return true;
            } else {
                return false;
            }
        }
    
        /**
         * Determines if there is a pit in the direction given.
         * @param dir the direction given ("R", "L", "U", or "D").
         * @return true if there is a pit, false otherwise.
         */
        public boolean isThereAPit(String dir) {
            if(dir.equals("R")) {
                return isThereAPit(0, 1);
            } else if (dir.equals("L")) {
                  return isThereAPit(0,-1);
            } else if (dir.equals("U")) {
                return isThereAPit(-1, 0);
            } else if(dir.equals("D")) {
                return isThereAPit(1, 0);
            } else {
                return false;
            }
        }
    
        private boolean canMove(int rowMove, int colMove) {
            if (this.col + colMove >= 20 || this.col + colMove < 0 || this.row + rowMove >= 20 || this.row + rowMove < 0) {
                return false;
            } else if (solution[this.row + rowMove][this.col + colMove] == '*') {
                myMap[this.row + rowMove][this.col + colMove] = '*';
                return true;
            } else if (solution[this.row + rowMove][this.col + colMove] == '0') {
                myMap[this.row + rowMove][this.col + colMove] = '*';
                return false;
            } else {
                myMap[this.row + rowMove][this.col + colMove] = solution[this.row + rowMove][this.col + colMove];
                return false;
            }
        }

        /**
         * Determines if your character can move right.
         * @return true if there are no obstacles to the right.
         */
        public boolean canIMoveRight() {
            return canMove(0,1);
        }
    
        /**
         * Determines if your character can move left.
         * @return true if there are no obstacles to the left.
         */
        public boolean canIMoveLeft() {
            return canMove(0,-1);
        }
    
        /**
         * Determines if your character can move up.
         * @return true if there are no obstacles above.
         */
        public boolean canIMoveUp() {
            return canMove(-1,0);
        }
    
        /**
         * Determines if your character can move down.
         * @return true if there are no obstacles below.
         */
        public boolean canIMoveDown() {
            return canMove(1,0);
        }
    
        private boolean move(int rowMove, int colMove) {
            if(canMove(rowMove, colMove)) {
                myMap[this.row][this.col] = '*';                
                this.row += rowMove;
                this.col += colMove;
                this.moves++;
                myMap[this.row][this.col] = 'x';
                return true;
            } else {
                return false;
            }
        }
    
        /**
         * Moves your character one space right.
         */
        public boolean moveRight() {
            return move(0, 1);
        }
    
        /**
         * Moves your character one space left.
         */
        public boolean moveLeft() {
            return move(0, -1);
        }
    
        /**
         * Moves your character one space down.
         */
        public boolean moveUp() {
            return move(-1, 0);
        }
    
        /**
         * Moves your character one space up.
         */
        public boolean moveDown() {
            return move(1, 0);
        }
        /**
         * Jumps over a pit in the direction given. Moves your character two spaces.
         * Does nothing if there is no pit in that direction.
         * @param dir the directions given ("R", "L", "U", or "D").
         */
        public void jumpOverPit(String dir) {
            if(isThereAPit(dir)) {
                if(dir.equals("R")) {
                    if (!move(0, 2))
                        if (!move(-1, 1))
                            move(1, 1);
                } else if (dir.equals("L")) {
                    if (!move(0, -2))
                        if (!move(-1, -1))
                            move(1, -1);
                } else if (dir.equals("U")) {
                    if (!move(-2, 0))
                        if (!move(-1, 1))
                            move(-1, -1);
                } else if(dir.equals("D")) {
                    if (!move(2, 0))
                        if (!move(1, 1))
                            move(1, -1);
                }
            }
        } 
        
        public boolean didIWin() {
            if (this.row == 10 && this.col == 19) {
                return true;
            } else {
                return false;
            }
        }

        public int getMoves() {
            return this.moves;
        }
    }

    public Player getPlayer() {
        return this.player;
    }

	public boolean isThereAPit(String dir) {
		return false;
	}
}