package Search;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import Search.GenericSearch.Node;

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

    public static List<Missionaries> successors(Missionaries mcs) {
        List<Missionaries> sucs = new ArrayList<>();

        if (mcs.boat) { // boat on west bank

            if (mcs.wm > 1) {
                sucs.add(new Missionaries(mcs.wm - 2, mcs.wc, !mcs.boat));
            }

            if (mcs.wm > 0) {
                sucs.add(new Missionaries(mcs.wm - 1, mcs.wc, !mcs.boat));
            }

            if (mcs.wc > 1) {
                sucs.add(new Missionaries(mcs.wm, mcs.wc - 2, !mcs.boat));
            }

            if (mcs.wc > 0) {
                sucs.add(new Missionaries(mcs.wm, mcs.wc - 1, !mcs.boat));
            }

            if (mcs.wc > 0 && mcs.wm > 0) {
                sucs.add(new Missionaries(mcs.wm - 1, mcs.wc - 1, !mcs.boat));
            }
        } else { // boat on east bank

            if (mcs.em > 1) {
                sucs.add(new Missionaries(mcs.wm + 2, mcs.wc, !mcs.boat));
            }

            if (mcs.em > 0) {
                sucs.add(new Missionaries(mcs.wm + 1, mcs.wc, !mcs.boat));
            }

            if (mcs.ec > 1) {
                sucs.add(new Missionaries(mcs.wm, mcs.wc + 2, !mcs.boat));
            }

            if (mcs.ec > 0) {
                sucs.add(new Missionaries(mcs.wm, mcs.wc + 1, !mcs.boat));
            }

            if (mcs.ec > 0 && mcs.em > 0) {
                sucs.add(new Missionaries(mcs.wm + 1, mcs.wc + 1, !mcs.boat));
            }
        }
        sucs.removeIf(Predicate.not(Missionaries::isLegal));
        return sucs;
    }

    public static void displaySolution(List<Missionaries> path) {
        if (path.size() == 0) { // sanity check
            return;
        }
        Missionaries oldState = path.get(0);
        System.out.println(oldState);
        for (Missionaries currentState : path.subList(1, path.size())) {
            if (currentState.boat) {
                System.out.printf("%d missionaries and %d cannibals moved from the east bank to the west bank.%n",
                        oldState.em - currentState.em, oldState.ec - currentState.ec);
            } else {
                System.out.printf("%d missionaries and %d cannibals moved from the west bank to the east bank.%n",
                        oldState.wm - currentState.wm,
                        oldState.wc - currentState.wc);
            }
            System.out.println(currentState);
            oldState = currentState;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(boat, ec, em, wc, wm);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Missionaries other = (Missionaries) obj;
        return boat == other.boat && ec == other.ec && em == other.em && wc == other.wc && wm == other.wm;
    }

    public static void main(String... strings) {
        Missionaries start = new Missionaries(MAX_NUM, MAX_NUM, true);
        Node<Missionaries> solution = GenericSearch.bfs(start, Missionaries::goalTest, Missionaries::successors);

        if (solution == null) {
            System.out.println("No solution found");
        } else {
            List<Missionaries> path = GenericSearch.nodeToPath(solution);
            displaySolution(path);
        }
    }

}
