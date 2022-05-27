package Search;

public class Missionaries {
    private static final int MAX_NUM = 3;
    private final int wm;
    private final int wc;
    private final int em;
    private final int ec;
    private final boolean boat;

    public Missionaries(int missionaries, int cannibals, boolean boat) {
        wm = missionaries;
        wc = cannibals;
        em = MAX_NUM - wm;
        ec = MAX_NUM - wc;
        this.boat = boat;
    }

    @Override
    public String toString() {
        return String.format(
                "On the west bank there are %d missionaries and %d cannibals.%n"
                        + "On the east bank there are %d missionaries and %d cannibals.%n"
                        + "The boat is on the %s bank.",
                wm, wc, em, ec, boat ? "west" : "east");
    }

    public boolean goalTest() {
        return isLegal() && em == MAX_NUM && ec == MAX_NUM;
    }

    public boolean isLegal() {
        if (wm < wc && wm > 0) {
            return false;
        }
        if (em < ec && em > 0) {
            return false;
        }
        return true;
    }

}
