package daytwo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AocDayTwo {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("aoc2.txt"));
        List<Password> passwords = new ArrayList<>();
        while (scanner.hasNextLine()) {
            passwords.add(Password.create(scanner.nextLine()));
        }
        scanner.close();
        System.out.println(new AocDayTwo().getNumValidPasswordsOne(passwords));
        System.out.println(new AocDayTwo().getNumValidPasswordsTwo(passwords));
    }

    public long getNumValidPasswordsOne(List<Password> passwords) {
        return passwords.stream().filter(Password::isValidOne).count();
    }

    public long getNumValidPasswordsTwo(List<Password> passwords) {
        return passwords.stream().filter(Password::isValidTwo).count();
    }

    static class Password {
        private final char inspected;
        private final int minInspected;
        private final int maxInspected;
        private final String pass;

        public static Password create(String input) {
            String[] splitOne = input.split(":");

            String[] inspectedSplit = splitOne[0].split(" ");
            char inspected = inspectedSplit[1].trim().charAt(0);

            String[] minMaxSplit = inspectedSplit[0].split("-");
            int min = Integer.parseInt(minMaxSplit[0].trim());
            int max = Integer.parseInt(minMaxSplit[1].trim());

            String pass = splitOne[1].trim();

            return new Password(inspected, min, max, pass);
        }

        private Password(char inspected, int minInspected, int maxInspected, String pass) {
            this.inspected = inspected;
            this.minInspected = minInspected;
            this.maxInspected = maxInspected;
            this.pass = pass;
        }

        public boolean isValidOne() {
            long numInspected = pass.chars().mapToObj(c -> (char) c).filter(c -> c.equals(inspected)).count();
            return numInspected >= minInspected && numInspected <= maxInspected;
        }

        public boolean isValidTwo() {
            char posMin = pass.charAt(minInspected - 1);
            char posMax = pass.charAt(maxInspected - 1);
            return (posMin == inspected && posMax != inspected) || (posMax == inspected && posMin != inspected);
        }
    }
}
