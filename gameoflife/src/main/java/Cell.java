/**
 * Created by Stepan on 16.12.2017.
 */
public enum Cell {
    ALIVE(1),
    DEAD(0);

    private int state;
    private int newState;
    Cell(){}

    Cell(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void updateState(int state) {
        this.state = state;
    }
}
