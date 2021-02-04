package MazeRunner;

import java.util.Arrays;

public class MazeSolver {
    
    public static void solver(int[][] map){



    }

    public static int[][] nodeProcessor(char[][] map, int[] start, int[] end){
        int[][] nodes;
        nodes = nodeSort(map);
        int[] nodeWeight;
        nodeWeight = new int[nodes.length];
        for (int x = 0; x < nodes.length)


        return nodes;
    }

    private static Boolean nxtDegreeConnectHoriz(int[] greaterNode, int[] lesserNode, char[][] map){
        
        for(int i = lesserNode[1]; i <= greaterNode[1]; i++){
            if (map[greaterNode[0]][i] != '*'){
                return false;
            } 
        }
        
        return true;
    }
    private static Boolean nxtDegreeConnectVert(int[] greaterNode, int[] lesserNode, char[][] map){
        
        for(int i = lesserNode[0]; i <= greaterNode[0]; i++){
            if (map[i][greaterNode[1]] != '*'){
                return false;
            }
        }
        
        return true;
    }

    public static Boolean connected(char[][] map, int[] node1, int[]node2){
        if (node1[0] == node2[0]){
            if (node1[1] > node2[1]){
                return nxtDegreeConnectHoriz(node1, node2, map);
            } else {
                return nxtDegreeConnectHoriz(node2, node1, map);
            }
            
        } else if (node1[1] == node2[1]) {
            if (node1[0] > node2[0]){
                return nxtDegreeConnectVert(node1, node2, map);
            } else {
                return nxtDegreeConnectVert(node2, node1, map);
            }
        } else {
            return false;
        }
    }

    public static int[][] nodeSort(char[][] map){
        int[][] nodes;
        Boolean vertical;
        Boolean horizontal;

        int iteration = 0;
        nodes = new int[200][2];    //temporarily hardcoded size of node array. this will be resized, you can also make this take arguements according to the size of the array
                                            //mathematically speaking the amount of nodes/intersections could not concievably be larger than half the total tile count of the maze
        for (int x = 0; x < 20; x++){
            for (int y = 0; y < 20; y++){
                vertical = false;
                horizontal = false;

                if (map[x][y] == '*'){
                    if ((y==0) || (y==19)){// IN REALITY THIS OUTER LAYER OF IF STATEMENT IS COMPLETELY UNNESSECCARY, with a bit of rewording it could be lost, but it makes logic much clearer
                        if (y==0){
                            if (x == 0){
                                if ((map[x][y+1] == '*')){
                                    horizontal = true;
                                } else if ((map[x+1][y] == '*')){
                                    vertical = true;
                                }
                            } else if (x == 19) {
                                if ((map[x][y+1] == '*')){
                                    horizontal = true;
                                } else if ((map[x-1][y] == '*')){
                                    vertical = true;
                                }
                            } else {
                                if ((map[x][y+1] == '*')){
                                    horizontal = true;
                                } else if ((map[x-1][y] == '*') || (map[x+1][y] == '*')){
                                    vertical = true;
                                }
                            }
                        } else if (y == 19){
                            if (x == 0){
                                if ((map[x][y-1] == '*')){
                                    horizontal = true;
                                } else if ((map[x+1][y] == '*')){
                                    vertical = true;
                                }
                            } else if (x == 19) {
                                if ((map[x][y-1] == '*')){
                                    horizontal = true;
                                } else if ((map[x-1][y] == '*')){
                                    vertical = true;
                                }
                            } else {
                                if ((map[x][y-1] == '*')){
                                    horizontal = true;
                                } else if ((map[x-1][y] == '*') || (map[x+1][y] == '*')){
                                    vertical = true;
                                }
                            }
                        }
                        
                    } else if ((x==0) || (x==19)){ 
                        if (x == 0){
                            if ((map[x][y-1] == '*') || (map[x][y+1] == '*')){
                                horizontal = true;
                            } else if ((map[x+1][y] == '*')){
                                vertical = true;
                            }
                        } else if (x == 19){
                            if ((map[x][y-1] == '*') || (map[x][y+1] == '*')){
                                horizontal = true;
                            } else if ((map[x-1][y] == '*')){
                                vertical = true;
                            }
                        } 
                    } else {
                        if ((map[x][y-1] == '*') || (map[x][y+1] == '*')){
                            horizontal = true;
                        } else if ((map[x-1][y] == '*') || (map[x+1][y] == '*')){
                            vertical = true;
                        }
                    }
                    
                    
                }
                if (vertical && horizontal){
                    nodes[iteration][0] = x;
                    nodes[iteration][1] = y; 
                }
                iteration++;
            }
        }
        // I HOPE THE FOLLOWING CODE WORKS BECAUSE I REALLY DONT WANT THIS TO BE A CAUSE OF FAILURE
        int permi = 0;
        for (int i = 0; i < nodes.length; i++){
            if (nodes[i] == null){
                permi = i;
            }
        } // IS IT POSSIBLE FOR PERMI TO NEVER INITIALIZE AND THIS FORLOOP TO NOT ASSIGN THE LENGTH OF THE ACTUAL VALUE LIST OF NODES TO PERMI!? I HOPE THIS OVERRIDES PERMI INTO I
        int[][] newNodes;
        newNodes = new int[permi][2];
        for (int i = 0; i < permi; i++){
            newNodes[i] = nodes[i];
        }
        return newNodes;
    }
}
