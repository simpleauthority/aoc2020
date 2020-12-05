package dayfive;

public final class BoardingPass {
    private final String seatPath;

    public BoardingPass(final String seatPath) {
        this.seatPath = seatPath;
    }

    public final Seat getSeat() {
        return new Seat(
                getRow(seatPath.substring(0, 7)),
                getColumn(seatPath.substring(7, 10))
        );
    }

    private int getRow(String rowPath) {
        return followPath(rowPath, 0, 127, 'F', 'B');
    }

    private int getColumn(String columnPath) {
        return followPath(columnPath, 0, 7, 'L', 'R');
    }

    private int followPath(String path, int lowerBound, int upperBound, char lowerInstruction, char upperInstruction) {
        SpacePartition partition = new SpacePartition(lowerBound, upperBound);

        char[] chars = path.toCharArray();
        for (char c : chars) {
            if (c == lowerInstruction) {
                partition = partition.takeLowerHalf();
            } else if (c == upperInstruction) {
                partition = partition.takeUpperHalf();
            }
        }

        return partition.middle();
    }
}
