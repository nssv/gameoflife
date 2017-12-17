import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Stepan on 17.12.2017.
 */
public class Utils {

    public static List<String> readFile(String fileName) throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<>();

        Scanner scan = new Scanner(new File(fileName));
        while (scan.hasNextLine()) {
            lines.add(scan.nextLine());
        }
        scan.close();

        return lines;
    }

    public static byte[][] init(List<String> inputFile, int n) {
        byte[][] frame = new byte[n][n];
        for (int i = 0; i < inputFile.size(); i++) {
            String[] states = inputFile.get(i).split("");
            for (int j = 0; j < states.length; j++) {
                String s1 = states[j];
                frame[i][j] = Byte.parseByte(s1);
            }
        }
        return frame;
    }

    public static List<String> byteArrayToString(byte[][] array) {
        List<String> list = new ArrayList<>(array.length);
        for (int i = 0; i < array.length; i++) {
            String str = "";
            for (int j = 0; j < array.length; j++) {
                str = str + (String.valueOf(array[i][j]));
            }
            list.add(str);
        }
        return list;
    }
}
