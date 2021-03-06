package MazeRunner;

import java.util.Arrays;

public class MazeSolver {
    static int[][] nodeAdjacencies;

    public static char[][] solver(char[][] map, int[] start, int[] end, char[][] mapWSpecs){ // this code can easily be remodeled for finding least distance but that will be left for later

        //int[][] nodesN;
        //nodesN = nodeProcessor(map, start, end, nodeProcessor(map,start,end, nodeSort(map)));
        int[][] nodes;
        nodes = nodeProcessor(map, start, end, nodeProcessor(map,start,end, nodeSort(map)));
        int[][] path;
        path = new int[nodes.length+2][2];
        path[0] = start;
        path[path.length-1] = end;



        for (int x = 0; x < path.length-1; x++){
            for (int i = 0; i < nodes.length; i++){
                if (((nodes[i][0] != -1) && (nodes[i][1] != -1))){
                    if (connected(map, path[x], nodes[i])){
                        System.out.println(Arrays.toString(nodes[i]));
                        path[x+1] = nodes[i].clone(); 
                        nodes[i][0] = -1;
                        nodes[i][1] = -1;
                        System.out.println(Arrays.toString(path[x+1]));
                        System.out.println(Arrays.toString(nodes[i]));
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(path));
        for (int i = 0; i < path.length-1; i++){

            if (path[i][0] < path[i+1][0]){
                for (int x = path[i][0]; x <= path[i+1][0]; x++){
                    mapWSpecs[x][path[i][1]] = '#';
                }
            } else if (path[i][0] > path[i+1][0]) {
                for (int x = path[i+1][0]; x <= path[i][0]; x++){
                    mapWSpecs[x][path[i][1]] = '#';
                }
            } else if (path[i][1] < path[i+1][1]) {
                for (int x = path[i][1]; x <= path[i+1][1]; x++){
                    mapWSpecs[path[i][0]][x] = '#';
                }
            } else if (path[i][1] > path[i+1][1]) {
                for (int x = path[i+1][1]; x <= path[i][1]; x++){
                    mapWSpecs[path[i][0]][x] = '#';
                }
            }
        }

        return mapWSpecs;
        /*for (int i = 0; i < nodesN.length; i++){
            nodes[i] = nodesN[i];
        }*/
        /*nodeAdjacencies = new int[nodes.length][3];
        nodes[nodes.length-1] = end;
        nodes[nodes.length-2] = start;
        int[] nodeDistances;
        nodeDistances = new int[nodes.length];
        Arrays.fill(nodeDistances, Integer.MAX_VALUE);*/
        

        /*int temporaryUseIter = 0;

        for (int i = 0; i < nodes.length; i++){
            temporaryUseIter = 0;
            for (int x = 0; x < nodes.length; x++){
                if (i != x){
                    if (connected(map, nodes[i], nodes[x])){
                        nodeAdjacencies[i][temporaryUseIter] = x;
                        temporaryUseIter++;
                    }
                }
            }
        }
        Boolean reached = false;
        //while (reached = false){
        
        //}
        */
    }

    //public static int[] 

    public static int[][] nodeProcessor(char[][] map, int[] start, int[] end, int[][] nodes){
        Boolean[][] validateVH;   // 1 is vertical 0 is horizontal
        validateVH = new Boolean[nodes.length][2];
        
        for (int i = 0; i < nodes.length; i++){
            validateVH[i][0] = false;
            validateVH[i][1] = false;
            for (int x = 0; x < nodes.length; x++){
                if (x != i){
// --------- VERTICAL SECTION ----------
                    if ((nodes[i][1] == nodes[x][1]) && (connected(map, nodes[x], nodes[i]) == true)){
                        validateVH[i][1] = true;
// -------- HORIZONTAL SECTION ---------
                    } else if ((nodes[i][0] == nodes[x][0]) && (connected(map, nodes[x], nodes[i]) == true)){
                        validateVH[i][0] = true;
                    }
                }
            }
// -------- SPECIAL NODES SECTION -------
            if (connected(map, nodes[i], start) == true){
                validateVH[i][0] = true;
            } else if (connected(map, nodes[i], end) == true){
                validateVH[i][0] = true;
            }
        }
        int iterator = 0;
        int[][] newNodes;
        newNodes = new int[nodes.length][2];
        
        for (int i = 0; i < nodes.length; i++){
            
            if (validateVH[i][0] && validateVH[i][1]){
                newNodes[iterator] = nodes[i];
                iterator++;
            }
        }


        return arrayResizer(newNodes);
    }
    


    private static Boolean nxtDegreeConnectHoriz(int[] greaterNode, int[] lesserNode, char[][] map){
        
        for(int i = lesserNode[1]; i < greaterNode[1]; i++){
            if (map[greaterNode[0]][i] != '*'){
                return false;
            } 
        }
        
        return true;
    }
    private static Boolean nxtDegreeConnectVert(int[] greaterNode, int[] lesserNode, char[][] map){
        
        for(int i = lesserNode[0]; i < greaterNode[0]; i++){
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

                if (map[x][y] == '*'){   // EVERYTIME I WRITE 19 or 20 IS BECAUSE IT IS HARDCODED, YOU CAN PASS ANOTHER ARGUMENT FOR SIZE BUT ITS JUST UNECESSARY HERE
                    if ((y==0) || (y==19)){// IN REALITY THIS OUTER LAYER OF IF STATEMENT IS COMPLETELY UNNESSECCARY, with a bit of rewording it could be lost, but it makes logic much clearer
                        if (y==0){
                            if (x == 0){
                                if ((map[x][y+1] == '*')){
                                    horizontal = true;
                                }
                                if ((map[x+1][y] == '*')){
                                    vertical = true;
                                }
                            } else if (x == 19) {
                                if ((map[x][y+1] == '*')){
                                    horizontal = true;
                                }
                                if ((map[x-1][y] == '*')){
                                    vertical = true;
                                }
                            } else {
                                if ((map[x][y+1] == '*')){
                                    horizontal = true;
                                }
                                if ((map[x-1][y] == '*') || (map[x+1][y] == '*')){
                                    vertical = true;
                                }
                            }
                        } else if (y == 19){
                            if (x == 0){
                                if ((map[x][y-1] == '*')){
                                    horizontal = true;
                                }
                                if ((map[x+1][y] == '*')){
                                    vertical = true;
                                }
                            } else if (x == 19) {
                                if ((map[x][y-1] == '*')){
                                    horizontal = true;
                                }
                                if ((map[x-1][y] == '*')){
                                    vertical = true;
                                }
                            } else {
                                if ((map[x][y-1] == '*')){
                                    horizontal = true;
                                }
                                if ((map[x-1][y] == '*') || (map[x+1][y] == '*')){
                                    vertical = true;
                                }
                            }
                        }
                        
                    } else if ((x==0) || (x==19)){ 
                        if (x == 0){
                            if ((map[x][y-1] == '*') || (map[x][y+1] == '*')){
                                horizontal = true;
                            }
                            if ((map[x+1][y] == '*')){
                                vertical = true;
                            }
                        } else if (x == 19){
                            if ((map[x][y-1] == '*') || (map[x][y+1] == '*')){
                                horizontal = true;
                            }
                            if ((map[x-1][y] == '*')){
                                vertical = true;
                            }
                        } 
                    } else {
                        if ((map[x][y-1] == '*') || (map[x][y+1] == '*')){
                            horizontal = true;
                        }
                        if ((map[x-1][y] == '*') || (map[x+1][y] == '*')){
                            vertical = true;
                        }
                    }
                    
                    
                }
                if (vertical && horizontal){
                    nodes[iteration][0] = x;
                    nodes[iteration][1] = y; 
                    iteration++;
                }
                
            }
        }
        return arrayResizer(nodes);
    }

    private static int[][] arrayResizer(int[][] toBeResized){
        int permi = 0;
        for (int i = 0; i < toBeResized.length; i++){
            if ((toBeResized[i][0] == 0) && (toBeResized[i][1] == 0)){
                permi = i;
                break;
            }
        } 
        int[][] newNodes;
        newNodes = new int[permi][2];
        for (int i = 0; i < permi; i++){
            newNodes[i] = toBeResized[i];
        }
        return newNodes;
    }

}




/* THE SIZE THINGIE I USED FOR THE array resizer TO PROVE IT WORKS, just in case bugs come up: (USE IN ONLINE IDE)

import java.util.Arrays;
public class HelloWorld{

     public static void main(String []args){
        int[][] news;
        news = new int[4][2];
        news[0][0] = 1;
        news[0][1] = 1;
        news[1][0] = 1;
        news[1][1] = 1;
     //   news[2][0] = 1;
       // news[2][1] = 1;
        int permi = 0;
        int iterator = 0;
        for (int i = 0; i < news.length; i++){
            iterator = 0;
            for (int x : news[i]){
                if (x == 0){
                    iterator++;
                }
            }
            if (iterator == 2){
                permi = i;
                break;
            }
        }
        
        System.out.println(permi);
        int[][] newNodes;
        newNodes = new int[permi][2];
        for (int i = 0; i < permi; i++){
            newNodes[i] = news[i];
        }
        System.out.println(Arrays.deepToString(newNodes));
        System.out.println(newNodes.length);
        System.out.println(news.length);
    }
}

*/