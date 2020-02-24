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
        this.resetCounters();
        int neighbors = 0;
        int length = 0;
        int[] positionAndLength = new int[3];
        for(int i = 0; i < this.game.length; i++) {
            for(int j = 0; j < this.game[i].length; j++){
                exhaustiveChecks++;
                if(game[i][j]) {
                    length++;

                    //check for head
                    if(i == this.headPosition[0] && j == this.headPosition[1])
                        continue;

                    //check for neighbors
                    //above
                    if(i != 0 && game[i - 1][j])
                        neighbors++;

                    //below
                    if(i != game.length - 1 && game[i + 1][j])
                        neighbors++;

                    //right
                    if(j != game[i].length - 1 && game[i][j+1])
                        neighbors++;

                    //left
                    if(j != 0 && game[i][j - 1])
                        neighbors++;

                    if(neighbors > 1)
                        continue;

                    positionAndLength[0] = i;
                    positionAndLength[1] = j;
                    positionAndLength[2] = length;
                    System.out.println(positionAndLength[0]);
                    System.out.println(positionAndLength[1]);
                    System.out.println(exhaustiveChecks);
                    return positionAndLength;
                }
                neighbors = 0;
            }
        }
        System.out.println(length);
        System.out.println(exhaustiveChecks);
        System.out.println(positionAndLength[0]);
        System.out.println(positionAndLength[1]);
        positionAndLength[2] = length;
        return positionAndLength;
    }

    public int[] findTailRecursive(){
       this.resetCounters();
       int[] position = findTailRecursive(headPosition, findFalse());
       int[] positionAndLength = new int[3];
       positionAndLength[0] = position[0];
       positionAndLength[1] = position[0];
       positionAndLength[2] = findLength();
       System.out.println(positionAndLength[2]);
       return positionAndLength;
    }

    int[] findTailRecursive(int[] currentPosition, int[] previousPosition){
        recursiveChecks++;
        int neighbors = 0;
        int[] tailPosition = new int[2];

        //above
        if((currentPosition[0] != 0) && (this.game[currentPosition[0] - 1][currentPosition[1]]) && (currentPosition[0] - 1 != previousPosition[0])){
            neighbors++;
            previousPosition[0] = currentPosition[0];
            previousPosition[1] = currentPosition[1];
            currentPosition[0]--;
        }

        //below
        else if((currentPosition[0] != game.length-1) && (this.game[currentPosition[0] + 1][currentPosition[1]]) && (currentPosition[0] + 1 != previousPosition[0])){
            neighbors++;
            previousPosition[0] = currentPosition[0];
            previousPosition[1] = currentPosition[1];
            currentPosition[0]++;
        }

        //right
        else if((currentPosition[1] != game.length-1) && (this.game[currentPosition[0]][currentPosition[1] + 1]) && (currentPosition[1] + 1 != previousPosition[1])){
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

        if(neighbors == 0) {
            tailPosition[0] = currentPosition[0];
            tailPosition[1] = currentPosition[1];
            System.out.println(tailPosition[0]);
            System.out.println(tailPosition[1]);
            return tailPosition;
        }
        return findTailRecursive(currentPosition, previousPosition);
    }



    public void resetCounters() {
        exhaustiveChecks = 0;
        recursiveChecks = 0;
    }

    private static int getExhaustiveChecks(){
        return exhaustiveChecks;
    }

    private static int getRecursiveChecks(){
        return recursiveChecks;
    }

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


}
