import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stepan on 17.12.2017.
 */
public class ComplexGameOfLife implements GameOfLife {

    private int partitions;
    private int n;
    private int cycles;
    private List<ComplexFrame> listNodes = new ArrayList<>();
    static volatile byte[][] frame;

    public ComplexGameOfLife(int partitions) {
        this.partitions = partitions;
    }

    @Override
    public List<String> play(String inputFile) {
        List<String> str = null;
        try {
            str = Utils.readFile(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] options = (str.get(0)).split(" ");
        n = Integer.parseInt(options[0]);
        cycles = Integer.parseInt(options[1]);

        str.remove(0);

        frame = Utils.init(str, n);
        MainFrame main = new MainFrame(partitions,n,cycles, frame);
        main.createStructure();
        main.start();

        byte[][] frame = main.getFrame();
        return Utils.byteArrayToString(frame);
    }

}
