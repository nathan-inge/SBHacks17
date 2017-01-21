package hacks.sb.sbhacks17;

/**
 * Created by Nathan on 1/21/2017.
 */

public final class range {
    private final int first;
    private final int second;

    public range(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFloor() {
        return first;
    }

    public int getCeiling() {
        return second;
    }

}
