import org.junit.jupiter.api.Test;

public class SnakeGameTester {
    boolean[][] newGame1 = {{true, true, true},
                            {true, false, true},
                            {true, false, false}};
    int[] headPosition = {0,2};
    int[] current = {0,0};
    int[] previous = {0, 1};
    SnakeGame game1 = new SnakeGame(newGame1,0,2, headPosition);

    @Test
    public void findTailExhaustive() {
    game1.findTailExhaustive();
    }

    @Test

    public void findTailRecursive(){
    game1.findTailRecursive(current, previous);

    }

}
