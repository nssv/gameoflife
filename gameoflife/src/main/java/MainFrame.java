import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by Stepan on 17.12.2017.
 */
public class MainFrame  {
    private final int partitions ;
    private final int n;
    private final int cycles;
    private final byte[][] frame;

    private List<ComplexFrame> listNodes = new ArrayList<>();
    private Thread thread;
    private int[] points ;

    public volatile byte[][] oldFrame;
    public volatile byte[][] newFrame;

    public volatile boolean isContinued = false;

    public MainFrame(int partitions, int n, int cycles, byte[][] frame) {
        this.partitions = partitions;
        this.n = n;
        this.cycles = cycles;
        this.frame = frame;
    }

    public void createStructure() {
        points = new int[partitions];
        ArrayList<IntPair> pair = new ArrayList<>();

        for (int i = 0; i < n; i += points.length) {
//            for (int j = 0; j < n; j+= points.length) {
                pair.add(new IntPair(i,0));
//            }
        }

        for (int i = 0; i < partitions; i++) {
            listNodes.add(new ComplexFrame(n/partitions , n, cycles,
                    pair.get(i).getX(), pair.get(i).getY(), n));
        }
    }

    public void start(){
        boolean mainFlag = false;
        List<Boolean> bool = new ArrayList<>();
        int k = 0;

        for (ComplexFrame listNode : listNodes) {
            listNode.setFrame(frame);
            listNode.setFlag(false);
            listNode.start();
        }

        while(!isContinued){
            bool.clear();
            for (ComplexFrame listNode : listNodes) {
                bool.add(listNode.getFlag());
            }

            if(check(bool)){
                System.out.println("______");
                System.out.println("K - " + k);
                System.out.println(")_____");
                if(k == cycles){
                    for (ComplexFrame listNode : listNodes) {
                        byte[][] partOfFrame = listNode.getSmallFrame();
                        int x = listNode.getX();
                        int y= listNode.getY();
                        addPartOfFrame(partOfFrame, x, y, cycles);
                        listNode.close();
                    }
                    isContinued = true;
                }
                for (ComplexFrame listNode : listNodes) {
                    byte[][] partOfFrame = listNode.getSmallFrame();
                    int x = listNode.getX();
                    int y= listNode.getY();
                    addPartOfFrame(partOfFrame, x, y, cycles);
                }

                for (ComplexFrame listNode : listNodes) {
                    listNode.setFrame(frame);
                    listNode.setFlag(false);
                }
                k++;
            }
        }
        System.out.println("all threads are stopped");
    }

    private void addPartOfFrame(byte[][] newFrame, int x, int y, int cycles){
        for (int i = 0; i < newFrame.length; i++) {
            for (int j = 0; j < newFrame[i].length; j++) {
                frame[x+i][j] = newFrame[i][j];
            }
        }
    }

    public byte[][] getFrame() {
        return frame;
    }

    public static boolean check(List<Boolean> list)
    {
        for(boolean b : list) {if(!b) return false;}
        return true;
    }


    class IntPair {
        final int x;
        final int y;

        IntPair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

}
