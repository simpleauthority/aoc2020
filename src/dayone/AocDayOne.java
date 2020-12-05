package dayone;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AocDayOne {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("aoc1.txt"));
        List<Integer> ints = new ArrayList<>();
        scanner.forEachRemaining(entry -> ints.add(Integer.parseInt(entry)));
        scanner.close();

        System.out.println(new AocDayOne().get2020(ints));
    }

    private int get2020(List<Integer> ints) {
        for (int i = 0; i < ints.size(); i++) {
            int first = ints.get(i);
            for (int j = i + 1; j < ints.size(); j++) {
                int second = ints.get(j);
                for (int k = j + 1; k < ints.size(); k++) {
                    int third = ints.get(k);
                    if (first + second + third == 2020) {
                        int product = first * second * third;
                        System.out.printf("%d + %d + %d = 2020; %d * %d * %d = %d%n", first, second, third, first, second, third, product);
                        return product;
                    }
                }
            }
        }
        return -1;
    }
}
