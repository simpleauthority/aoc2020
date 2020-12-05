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
        SpacePartition row = new SpacePartition(0, 127);

        char[] chars = rowPath.toCharArray();
        for (char c : chars) {
            switch (c) {
                case 'F':
                    row = row.takeLowerHalf();
                    break;
                case 'B':
                    row = row.takeUpperHalf();
                    break;
            }
        }

        return row.middle();
    }

    private int getColumn(String columnPath) {
        SpacePartition column = new SpacePartition(0, 7);

        char[] chars = columnPath.toCharArray();
        for (char c : chars) {
            switch (c) {
                case 'L':
                    column = column.takeLowerHalf();
                    break;
                case 'R':
                    column = column.takeUpperHalf();
                    break;
            }
        }

        return column.middle();
    }
}
