import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static org.junit.Assert.assertEquals;

/**
 * Created by Mintas on 12/10/2017.
 */
public class GameOfLifeTest {
    private GameOfLife gameOfLife;

    @Before
    public void before() {
        gameOfLife = new SimpleGameOfLife();
    }

    @Test
    public void testGame1() throws Exception {
        testOneGame("src\\test\\resources\\input.txt", "src\\test\\resources\\output.txt");
    }
    @Test
    public void testGame2() throws Exception {
        testOneGame("src\\test\\resources\\input100.txt", "src\\test\\resources\\output100.txt");
    }
    @Test
    public void testGame3() throws Exception {
        testOneGame("src\\test\\resources\\input1000.txt", "src\\test\\resources\\output1000.txt");
    }

    private void testOneGame(String inputFile, String expectedOutputFile) throws FileNotFoundException {

        List<String> result = gameOfLife.play(inputFile);
        assertEquals(Utils.readFile(expectedOutputFile), result);
    }

}
