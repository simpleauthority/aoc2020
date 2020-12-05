package dayfour;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class Passport {
    private String byr;
    private String iyr;
    private String eyr;
    private String hgt;
    private String hcl;
    private String ecl;
    private String pid;
    private String cid;

    public final String getByr() {
        return byr;
    }

    public final void setByr(String byr) {
        if (byr.length() != 4) {
            this.byr = null;
            return;
        }

        int byrInt = Integer.parseInt(byr);
        this.byr = (byrInt >= 1920 && byrInt <= 2002) ? byr : null;
    }

    public final String getIyr() {
        return iyr;
    }

    public final void setIyr(String iyr) {
        if (iyr.length() != 4) {
            this.iyr = null;
            return;
        }

        int iyrInt = Integer.parseInt(iyr);
        this.iyr = (iyrInt >= 2010 && iyrInt <= 2020) ? iyr : null;
    }

    public final String getEyr() {
        return eyr;
    }

    public final void setEyr(String eyr) {
        if (eyr.length() != 4) {
            this.eyr = null;
            return;
        }

        int eyrInt = Integer.parseInt(eyr);
        this.eyr = (eyrInt >= 2020 && eyrInt <= 2030) ? eyr : null;
    }

    public final String getHgt() {
        return hgt;
    }

    public final void setHgt(String hgt) {
        int mag = Integer.parseInt(hgt.split("cm|in")[0].trim());
        if (hgt.contains("cm")) {
            this.hgt = (mag >= 150 && mag <= 193) ? hgt : null;
        } else if (hgt.contains("in")) {
            this.hgt = (mag >= 59 && mag <= 76) ? hgt : null;
        }
    }

    public final String getHcl() {
        return hcl;
    }

    public final void setHcl(String hcl) {
        final String pattern = "0123456789abcdef";

        char[] chars = hcl.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i == 0) {
                if (chars[i] != '#') {
                    this.hcl = null;
                    return;
                }
            } else {
                if (pattern.indexOf(chars[i]) == -1) {
                    this.hcl = null;
                    return;
                }
            }
        }

        this.hcl = hcl;
    }

    public final String getEcl() {
        return ecl;
    }

    public final void setEcl(String ecl) {
        List<String> possibilities = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
        if (possibilities.stream().anyMatch(ecl::equals)) {
            this.ecl = ecl;
        } else {
            this.ecl = null;
        }
    }

    public final String getPid() {
        return pid;
    }

    public final void setPid(String pid) {
        String pattern = "0123456789";

        if (pid.length() != 9) {
            this.pid = null;
            return;
        }

        char[] chars = pid.toCharArray();
        for (int i = 0; i < 9; i++) {
            if (pattern.indexOf(chars[i]) == -1) {
                this.pid = null;
                return;
            }
        }

        this.pid = pid;
    }

    public final String getCid() {
        return cid;
    }

    public final void setCid(String cid) {
        this.cid = cid;
    }

    public final boolean isValid() {
        return noneNull(getByr(), getIyr(), getEyr(), getHgt(), getHcl(), getEcl(), getPid()); // Don't care if cid is missing.
    }

    private boolean noneNull(Object... objects) {
        return Arrays.stream(objects).allMatch(Objects::nonNull);
    }

    @Override
    public String toString() {
        return "Passport{" +
                "byr='" + byr + '\'' +
                ", iyr='" + iyr + '\'' +
                ", eyr='" + eyr + '\'' +
                ", hgt='" + hgt + '\'' +
                ", hcl='" + hcl + '\'' +
                ", ecl='" + ecl + '\'' +
                ", pid='" + pid + '\'' +
                ", cid='" + cid + '\'' +
                '}';
    }
}
