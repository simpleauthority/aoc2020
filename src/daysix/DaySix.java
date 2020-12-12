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

        partOne();
        partTwo();
    }

    private static void partOne() {
        List<Set<Character>> groups = answers
                .stream()
                .map(group -> {
                    Set<Character> out = new HashSet<>();
                    group.forEach(out::addAll);
                    return out;
                })
                .collect(Collectors.toList());

        System.out.printf(
                "The sum of total yes answers is %d.%n",
                groups.stream().map(Set::size).reduce(0, Integer::sum)
        );
    }

    private static void partTwo() {
        List<Set<Character>> groups = answers
                .stream()
                .map(group -> {
                    Set<Character> out = new HashSet<>(group.get(0));
                    for (int i = 1; i < group.size(); i++) {
                        out.retainAll(group.get(i));
                    }
                    return out;
                })
                .collect(Collectors.toList());

        System.out.printf(
                "The sum of each group with everyone answering yes is %d.%n",
                groups.stream().map(Set::size).reduce(0, Integer::sum)
        );
    }
}
