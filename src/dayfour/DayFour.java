package dayfour;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DayFour {
    public static void main(String[] args) throws FileNotFoundException {
        new DayFour().run(new File("aoc4.txt"));
    }

    public void run(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);

        List<Passport> passports = new ArrayList<>();

        System.out.println("Aggregating data into Passport objects...");

        Passport currentPassport = new Passport();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.isBlank()) {
                System.out.println("Line is blank. Moving to next Passport.");
                passports.add(currentPassport);
                currentPassport = new Passport();
                continue;
            }

            System.out.printf("Reading line: \"%s\"%n", line);

            Passport lockedCurrent = currentPassport;

            String[] components = line.split("\\s+");
            Arrays.asList(components).forEach(component -> {
                System.out.printf("Parsing component: \"%s\"%n", component);

                String[] pieces = component.trim().split(":");
                String key = pieces[0].trim(), value = pieces[1].trim();

                switch (key.toLowerCase()) {
                    case "byr":
                        lockedCurrent.setByr(value);
                        break;
                    case "iyr":
                        lockedCurrent.setIyr(value);
                        break;
                    case "eyr":
                        lockedCurrent.setEyr(value);
                        break;
                    case "hgt":
                        lockedCurrent.setHgt(value);
                        break;
                    case "hcl":
                        lockedCurrent.setHcl(value);
                        break;
                    case "ecl":
                        lockedCurrent.setEcl(value);
                        break;
                    case "pid":
                        lockedCurrent.setPid(value);
                        break;
                    case "cid":
                        lockedCurrent.setCid(value);
                        break;
                }
            });

            currentPassport = lockedCurrent;
            System.out.println(currentPassport);
        }

        System.out.printf("Collected %d Passports...%n", passports.size());
        System.out.println("Validating passports...");

        long valid = passports.stream().filter(Passport::isValid).count();
        System.out.printf("%d Passports are valid.%n", valid);
    }
}
