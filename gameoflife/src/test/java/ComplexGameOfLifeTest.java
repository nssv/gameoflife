import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Stepan on 17.12.2017.
 */
public class ComplexGameOfLifeTest {


    @org.junit.Test
    public void testGame1() throws Exception {
        testOneGame("src\\test\\resources\\input.txt", "src\\test\\resources\\output.txt", 9);
    }

    @Test
    public void testGame2() throws Exception {
        testOneGame("src\\test\\resources\\input100.txt", "src\\test\\resources\\output100.txt", 4);
    }

    @Test
    public void testGame3() throws Exception {
        testOneGame("src\\test\\resources\\input1000.txt", "src\\test\\resources\\output1000.txt", 1000);
    }

    private void testOneGame(String inputFile, String expectedOutputFile, int partitions) throws FileNotFoundException {
        ComplexGameOfLife complexGameOfLife = new ComplexGameOfLife(partitions);

        List<String> result = complexGameOfLife.play(inputFile);
        assertEquals(Utils.readFile(expectedOutputFile), result);
    }
}
