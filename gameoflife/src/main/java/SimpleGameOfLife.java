import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by Stepan on 16.12.2017.
 */
public class SimpleGameOfLife implements GameOfLife {
    int n;
    int cycles;
    private byte frame[][];
    private byte newFrame[][];
    List<String> str;

    @Override
    public List<String> play(String inputFile) {
        try {

            List<String> str = Utils.readFile(inputFile);
            String[] options = (str.get(0)).split(" ");
            n = Integer.parseInt(options[0]);
            cycles = Integer.parseInt(options[1]);
            str.remove(0);

            frame = Utils.init(str, n);
            int l = 0;
            long startTime = System.nanoTime();

            while (l < cycles) {
                frame = evaluate(n, cycles, frame);
                l++;
            }
            long endTime = System.nanoTime();
            System.out.println("Time: " + String.valueOf((endTime-startTime)/1000));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        str = Utils.byteArrayToString(frame);
        return str;
    }

    public byte[][] evaluate(int n, int cycles, byte[][] frame) {
        newFrame = new byte[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = 0;
                byte cell = frame[i][j];

                for (int k1 = 0; k1 < 3; k1++) {
                    for (int k2 = 0; k2 < 3; k2++) {
                        if ((k1 != 1 || k2 != 1)) {
                            sum = sum + frame[((i + k1 - 1) % n + n) % n][((j + k2 - 1) % n + n) % n];
                        }
                    }
                }
                if (cell == 0 && sum == 3) {
                    newFrame[i][j] = 1;
                } else if (cell == 1 && (sum == 2 || sum == 3)) {
                    newFrame[i][j] = 1;
                } else {
                    newFrame[i][j] = 0;
                }

            }
        }
        return newFrame;
    }
}
