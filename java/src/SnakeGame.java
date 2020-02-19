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
                }
                neighbors = 0;
            }
        }
        positionAndLength[2] = length;
        return positionAndLength;
    }

    public int[] findTailRecursive(){





        this.resetCounters();
    }

    public void resetCounters();
        this.exhaustiveChecks = 0;
        this.recursiveChecks = 0;

}
