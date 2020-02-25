import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SnakeGameTester {

    //This case tests an instance where the tail is at 0,0 and the head is somewhere else
    boolean[][] newGame1 = {{true, true, false,false},
                            {false, true, true,false},
                            {false, false, true, false},
                            {true, true, true, false}};
    int[] headPosition1 = {3,0};
    SnakeGame game1 = new SnakeGame(newGame1,3,0, headPosition1);

    @Test
    public void findTailExhaustive1() {
        assertArrayEquals(game1.findTailExhaustive(), new int[] {0, 0, 8});
    }

    @Test
    public void findTailRecursive1(){
        assertArrayEquals(game1.findTailRecursive(), new int[] {0, 0, 8});
    }

    //In this test case, the head is at 0,0 and the tail is somewhere else
    boolean[][] newGame2 = {{true, false, true,true},
                            {true, false, false,true},
                            {true, false, true, true},
                            {true, true, true, false}};
    int[] headPosition2 = {0,0};
    SnakeGame game2 = new SnakeGame(newGame2,0,0, headPosition2);

    @Test
    public void findTailExhaustive2() {
        assertArrayEquals(game2.findTailExhaustive(), new int[] {0, 2, 11});
    }

    @Test
    public void findTailRecursive2(){
        assertArrayEquals(game2.findTailRecursive(), new int[] {0, 2, 11});
    }


    //This test has a snake that goes around the grid in no ordered manner
    boolean[][] newGame3 = {{false, true, true,true,true},
                            {true, false, false,false,true},
                            {true, false, true, true,true},
                            {true, false, true, false,false},
                            {true, true, true,false,false}};
    int[] headPosition3 = {0,1};
    SnakeGame game3 = new SnakeGame(newGame3,0,1, headPosition3);

    @Test
    public void findTailExhaustive3() {
        assertArrayEquals(game3.findTailExhaustive(), new int[] {1, 0, 15});
    }

    @Test
    public void findTailRecursive3(){
        assertArrayEquals(game3.findTailRecursive(), new int[] {1, 0, 15});
        game3.findTailRecursive();
    }

    //This test has the snake in a spiral
    boolean[][] newGame4 = {{true, true, true,true,true},
                            {true, false, false,false,true},
                            {true, false, true, false,true},
                            {true, false, true, false,true},
                            {true, true, true,false,true}};
    int[] headPosition4 = {4,4};
    SnakeGame game4 = new SnakeGame(newGame4,4,4, headPosition4);

    @Test
    public void findTailExhaustive4() {
        assertArrayEquals(game4.findTailExhaustive(), new int[] {2, 2, 17});
    }

    @Test
    public void findTailRecursive4(){
        assertArrayEquals(game4.findTailRecursive(), new int[] {2, 2, 17});
    }

    //This test has a longer snail going around the grid by the edges and inside the grid to check for all directions for neighbors
    boolean[][] newGame5 = {{true, true, true, true, false, false},
                            {true, false, false, true, false, false},
                            {true, true, false, true, false, false},
                            {false, true, false, true, true, true},
                            {false, true, false, false, false, true},
                            {false, false, true, true, true, true}};

    int[] headPosition5 = {4,1};
    SnakeGame game5 = new SnakeGame(newGame5,4,1, headPosition5);

    @Test
    public void findTailExhaustive5() {
        assertArrayEquals(game5.findTailExhaustive(), new int[] {5, 2, 19});
    }

    @Test
    public void findTailRecursive5(){
        assertArrayEquals(game5.findTailRecursive(), new int[] {5, 2, 19});
    }
}
