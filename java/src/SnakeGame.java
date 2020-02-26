public class SnakeGame {
    private boolean[][] game;
    private int[] headPosition;
    private static int exhaustiveChecks;
    private static int recursiveChecks;

    SnakeGame(){
        this.game = new boolean[1][1];
    }

    SnakeGame(boolean[][] board, int xHeadPosition, int yHeadPosition, int[] headPosition){
        this.headPosition = new int[2];
        this.game = new boolean[board.length][board.length];
        this.headPosition[0] = xHeadPosition;
        this.headPosition[1] = yHeadPosition;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++){
                this.game[i][j] = board[i][j];
            }
        }
    }

    public int[] findTailExhaustive(){
        //Reset counters from previous method call
        this.resetCounters();
        int neighbors;
        int length = 0;
        boolean keepCounting = true;
        int[] positionAndLength = new int[3];
        //check each value in the 2D array
        for(int i = 0; i < this.game.length; i++) {
            for(int j = 0; j < this.game[i].length; j++){
                if(keepCounting) {
                    exhaustiveChecks++;
                }
                if(game[i][j]) {
                    length++;

                    neighbors = this.findNeighbors(i,j);

                    //check for head and if the snake's length is only 1
                    if(i == this.headPosition[0] && j == this.headPosition[1] && neighbors == 0) {
                        positionAndLength[0] = i;
                        positionAndLength[1] = j;
                        keepCounting = false;
                    }

                    if(neighbors > 1)
                        continue;

                    if(i != this.headPosition[0] || j != this.headPosition[1] && neighbors == 1) {
                        positionAndLength[0] = i;
                        positionAndLength[1] = j;
                        keepCounting = false;
                    }
                }
                neighbors = 0;
            }
        }
        positionAndLength[2] = length;

        //I know I don't have to print position and length
        //I did it to make it easier to visualize what the method returns
        System.out.println("X position: " + positionAndLength[0]);
        System.out.println("Y position: " + positionAndLength[1]);
        System.out.println("Snake Length: " + length);
        System.out.println("Exhaustive Checks: "+ getExhaustiveChecks());
        return positionAndLength;
    }

    public int[] findTailRecursive(){
       //Reset counters from previous method call
       this.resetCounters();
       //I used a false position as my previous position as using the head or null gave me a stack overflow error
       int[] position = findTailRecursive(headPosition, findFalse());
       int[] positionAndLength = new int[3];
       positionAndLength[0] = position[0];
       positionAndLength[1] = position[1];
       //Here I find the length with the helper method I created
       positionAndLength[2] = this.findLength();
       //Again, just printed to visualize
       System.out.println("Snake Length: " + positionAndLength[2]);
       System.out.println("Recurisve Checks: " + getRecursiveChecks());
       return positionAndLength;
    }

    private int[] findTailRecursive(int[] currentPosition, int[] previousPosition){
        //Increase recursive checks at the start of every iteration
        recursiveChecks++;
        int neighbors = 0;
        int[] tailPosition = new int[2];

        //I know I have the findNeighbors method but here I also care that the
        //position I am going to check is not the previous position,
        //that is why I did not use the findNeighbors method here

        //above
        if((currentPosition[0] != 0) && (this.game[currentPosition[0] - 1][currentPosition[1]]) && (currentPosition[0] - 1 != previousPosition[0])){
            neighbors++;
            previousPosition[0] = currentPosition[0];
            previousPosition[1] = currentPosition[1];
            currentPosition[0]--;
        }

        //below
        else if((currentPosition[0] != game[0].length-1) && (this.game[currentPosition[0] + 1][currentPosition[1]]) && (currentPosition[0] + 1 != previousPosition[0])){
            neighbors++;
            previousPosition[0] = currentPosition[0];
            previousPosition[1] = currentPosition[1];
            currentPosition[0]++;
        }

        //right
        else if((currentPosition[1] != game[0].length-1) && (this.game[currentPosition[0]][currentPosition[1] + 1]) && (currentPosition[1] + 1 != previousPosition[1])){
            neighbors++;
            previousPosition[0] = currentPosition[0];
            previousPosition[1] = currentPosition[1];
            currentPosition[1]++;
        }

        //left
        else if((currentPosition[1] != 0) && (this.game[currentPosition[0]][currentPosition[1] - 1]) && (currentPosition[1] - 1 != previousPosition[1])) {
            neighbors++;
            previousPosition[0] = currentPosition[0];
            previousPosition[1] = currentPosition[1];
            currentPosition[1]--;
        }

        //Since each I'm not counting the previous state,
        //each part of the body will have one neighbor at most

        //Base case:
        if(neighbors == 0) {
            tailPosition[0] = currentPosition[0];
            tailPosition[1] = currentPosition[1];
            System.out.println("X position: " + tailPosition[0]);
            System.out.println("Y position: " + tailPosition[1]);
            return tailPosition;
        }
        return findTailRecursive(currentPosition, previousPosition);
    }

    public void resetCounters() {
        exhaustiveChecks = 0;
        recursiveChecks = 0;
    }

    public static int getExhaustiveChecks(){
        return exhaustiveChecks;
    }

    public static int getRecursiveChecks(){
        return recursiveChecks;
    }

    //Created a helper method to find snake's length
    public int findLength(){
        int length = 0;
        for(int i = 0; i < this.game.length; i++) {
            for (int j = 0; j < this.game[i].length; j++) {
                if (game[i][j]) {
                    length++;
                }
            }
        }
        return length;
    }

    //Created a helper method to find any false value
    //I use this method in the recursive call and use it as the previous state
    public int[] findFalse(){
        int[] valueFalse = new int[2];
        for(int i = 0; i < this.game.length; i++) {
            for (int j = 0; j < this.game[i].length; j++) {
                if (!game[i][j]) {
                    valueFalse[0] = i;
                    valueFalse[1] = j;
                    return valueFalse;
                }
            }
        }
        return valueFalse;
    }

    //Created a helped method to find neighbors
    public int findNeighbors(int i, int j){

        int neighbors = 0;

        //above
        if (i != 0 && game[i - 1][j])
            neighbors++;

        //below
        if (i != game.length - 1 && game[i + 1][j])
            neighbors++;

        //right
        if (j != game[i].length - 1 && game[i][j + 1])
            neighbors++;

        //left
        if (j != 0 && game[i][j - 1])
            neighbors++;

        return neighbors;
    }
}
