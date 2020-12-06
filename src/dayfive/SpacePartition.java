package dayfive;

public final class SpacePartition {
    private final int min;
    private final int max;

    public SpacePartition(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public final SpacePartition takeLowerHalf() {
        if (max - min == 1) {
            return new SpacePartition(min, min);
        }

        return new SpacePartition(min, middle(false));
    }

    public final SpacePartition takeUpperHalf() {
        if (max - min == 1) {
            return new SpacePartition(max, max);
        }

        return new SpacePartition(middle(true), max);
    }

    public final int middle() {
        return middle(false);
    }

    public final int middle(boolean roundUp) {
        if (roundUp) {
            return ((min + max) + 1) / 2;
        } else {
            return (min + max) / 2;
        }
    }

    @Override
    public String toString() {
        return "SpacePartition{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}
