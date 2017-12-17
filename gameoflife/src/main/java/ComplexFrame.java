/**
 * Created by Stepan on 17.12.2017.
 */
public class ComplexFrame {

    final int height;
    final int width;
    final int cycles;
    private final int y;
    private final int x;
    private final int n;
    private Thread threadNode;
    static volatile boolean isInterrupted = false;
    volatile boolean flag;

    public volatile byte[][] frame;
    public volatile byte[][] smallMap;
    public volatile byte[][] newFrame;

    public ComplexFrame(int height, int width, int cycles, int x, int y, int n) {
        this.height = height;
        this.width = width;
        this.cycles = cycles;
        this.x = x;
        this.y = y;
        this.n = n;
        this.threadNode = new Thread(() -> {
            while (!isInterrupted) {
                if (!flag) {
                    doSmth();
                    flag = true;
                }
            }
        });
    }

    public void start() {
        threadNode.start();
    }

    public void close() {
        isInterrupted = true;
        threadNode.interrupt();
    }

    public byte[][] doSmth() {
        newFrame = new byte[height][width];
        for (int i = x; i < x + height; i++) {
            for (int j = y; j < y + width; j++) {
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
                    newFrame[i-x][j-y] = 1;
                } else if (cell == 1 && (sum == 2 || sum == 3)) {
                    newFrame[i-x][j-y] = 1;
                } else {
                    newFrame[i-x][j-y] = 0;
                }
            }
        }
        setSmallFrame(newFrame);
        return newFrame;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean getFlag() {
        return flag;
    }

    public byte[][] getSmallFrame() {
        return smallMap;
    }

    public void setSmallFrame(byte[][] frame){
        this.smallMap = frame;
    }

    public void setFrame(byte[][] frame){
        this.frame = frame;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
