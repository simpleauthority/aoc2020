package daythree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayThree {
    public static void main(String[] args) throws FileNotFoundException {
        new DayThree().run(new File("aoc3.txt"));
    }

    private void run(File file) throws FileNotFoundException {
        List<List<Boolean>> data = new ArrayList<>();

        System.out.println("Reading data...");
        Scanner scanner = new Scanner(file);
        for (int i = 0; scanner.hasNextLine(); i++) {
            Character[] chars = scanner
                    .nextLine()
                    .chars()
                    .mapToObj(piece -> (char) piece)
                    .toArray(Character[]::new);

            data.add(i, new ArrayList<>());
            for (int j = 0; j < chars.length; j++) {
                data.get(i).add(j, chars[j] == '#');
            }
        }
        scanner.close();

        System.out.println("Counting trees with slope (3, -1)...");
        long trees31 = countTrees(data, 3, 1);
        System.out.printf("Counted trees: %d%n", trees31);
        System.out.println("Counting trees with slopes (1, -1), (5, -1), (7, -1), (1, -2)...");
        long trees11 = countTrees(data, 1, 1);
        System.out.printf("Counted trees: %d%n", trees11);
        long trees51 = countTrees(data, 5, 1);
        System.out.printf("Counted trees: %d%n", trees51);
        long trees71 = countTrees(data, 7, 1);
        System.out.printf("Counted trees: %d%n", trees71);
        long trees12 = countTrees(data, 1, 2);
        System.out.printf("Counted trees: %d%n", trees12);

        System.out.printf("Product of all encountered trees: %d%n", trees11 * trees31 * trees51 * trees71 * trees12);
    }

    private long countTrees(List<List<Boolean>> data, int slopeX, int slopeY) {
        int currentX = 0, realX = 0, currentY = 0, maxX = data.get(0).size();
        long trees = 0;

        for (; currentY < data.size(); currentY += slopeY) {
            boolean tree = data.get(currentY).get(currentX);

           if (tree) {
               trees++;
           }

           if (currentX + slopeX >= maxX) {
               if (maxX - currentX == 0) {
                   currentX += slopeX;
               } else {
                   currentX += slopeX - maxX;
               }
           } else {
               currentX += slopeX;
           }
        }

        return trees;
    }
}
