package dayfive;

public final class Seat {
    private final int row;
    private final int column;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public final int getRow() {
        return row;
    }

    public final int getColumn() {
        return column;
    }

    public final int getId() {
        return (getRow() * 8) + getColumn();
    }

    @Override
    public String toString() {
        return "Seat{" +
                "row=" + row +
                ", column=" + column +
                ", id=" + getId() +
                '}';
    }
}
