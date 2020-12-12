package daysix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class DaySix {
    private static final List<List<Set<Character>>> answers = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("aoc6.txt"));

        List<Set<Character>> currentGroup = new ArrayList<>();
        Set<Character> currentIndividual = new HashSet<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.isBlank()) {
                if (!currentGroup.isEmpty()) {
                    answers.add(currentGroup);
                    currentGroup = new ArrayList<>();
                }

                continue;
            }

            char[] chars = line.toCharArray();
            for (char c : chars) {
                currentIndividual.add(c);
            }

            currentGroup.add(currentIndividual);
            currentIndividual = new HashSet<>();
        }

        List<Set<Character>> groups = answers
                .stream()
                .map(group -> {
                    Set<Character> out = new HashSet<>();
                    group.forEach(out::addAll);
                    return out;
                })
                .collect(Collectors.toList());

        // Part 1
        System.out.printf(
                "The sum of total yes answers is %d.%n",
                groups.stream().map(Set::size).reduce(0, Integer::sum)
        );
    }
}
