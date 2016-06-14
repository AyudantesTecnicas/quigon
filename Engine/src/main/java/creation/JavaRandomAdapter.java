package creation;

import java.util.Random;

public class JavaRandomAdapter implements GameRandom {
    private Random random;

    public JavaRandomAdapter() {
        this.random = new Random();
    }

    @Override
    public int nextInt(int exclusiveTop) {
        return random.nextInt(exclusiveTop);
    }
}
