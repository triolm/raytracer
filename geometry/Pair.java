package geometry;

import java.util.ArrayList;

public class Pair<T> {
    private T first, second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    //splits resolutions into sections for multi threading
    public static ArrayList<Pair<Integer>> split(int cores, int xRes) {
        ArrayList<Pair<Integer>> l = new ArrayList<Pair<Integer>>();
        for (int i = 0; i < cores; i++) {
            l.add(new Pair<Integer>(xRes / cores * i, xRes / cores * (i + 1)));
        }
        return l;
    }

}
