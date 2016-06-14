package driver;

import creation.GameRandom;

import java.util.ArrayList;
import java.util.Iterator;

public class MockGameRandom implements GameRandom {
    private ArrayList<Integer> integers;
    private Iterator<Integer> iterator;

    public MockGameRandom(ArrayList<Integer> integers) {
        this.integers = integers;
        this.iterator = integers.iterator();
    }

    @Override
    public int nextInt(int integer) {
        if (integers.size() != 0) {
            if (iterator.hasNext()) {
                return iterator.next();
            } else {
                iterator = integers.iterator();
                return iterator.next();
            }
        } else {
            return 0;
        }
    }
}
