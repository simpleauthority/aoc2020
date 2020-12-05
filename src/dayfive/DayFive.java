package dayfive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class DayFive {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("aoc5.txt"));

        List<BoardingPass> boardingPasses = new ArrayList<>();

        while (scanner.hasNextLine()) {
            boardingPasses.add(new BoardingPass(scanner.nextLine()));
        }

        scanner.close();

        DayFive dayFive = new DayFive();
        dayFive.findHighestSeatId(boardingPasses);
        dayFive.findMySeatId(boardingPasses);
    }

    private void findMySeatId(List<BoardingPass> boardingPasses) {
        List<Seat> orderedSeats = boardingPasses
                .stream()
                .map(BoardingPass::getSeat)
                .sorted(Comparator.comparingInt(Seat::getId))
                .collect(Collectors.toList());

        for (int i = 0; i + 3 < orderedSeats.size(); i += 3) {
            Seat first = orderedSeats.get(i), middle = orderedSeats.get(i + 1), last = orderedSeats.get(i + 2);

            boolean lower = middle.getId() - first.getId() > 1, upper = last.getId() - middle.getId() > 1;
            if (lower || upper) {
                String msg = "My Seat ID is %d.%n";

                if (lower) {
                    System.out.printf(msg, first.getId() + 1);
                } else if (upper) {
                    System.out.printf(msg, middle.getId() + 1);
                }

                return;
            }
        }

        System.out.println("I can't find my seat!");
    }

    private void findHighestSeatId(List<BoardingPass> boardingPasses) {
        int highestSeatId = boardingPasses
                .stream()
                .map(BoardingPass::getSeat)
                .max(Comparator.comparingInt(Seat::getId))
                .map(Seat::getId)
                .orElse(-1);

        if (highestSeatId == -1) {
            System.out.println("Could not find highest Seat ID");
            return;
        }

        System.out.printf("Highest Seat ID is %d.%n", highestSeatId);
    }
}
