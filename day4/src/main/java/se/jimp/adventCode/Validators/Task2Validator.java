package se.jimp.adventCode.Validators;

import se.jimp.adventCode.Validators.DefaultValidator;

public class Task2Validator extends DefaultValidator {

    /*
        byr (Birth Year) - four digits; at least 1920 and at most 2002.
        iyr (Issue Year) - four digits; at least 2010 and at most 2020.
        eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
        hgt (Height) - a number followed by either cm or in:
        If cm, the number must be at least 150 and at most 193.
        If in, the number must be at least 59 and at most 76.
        hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
        ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
        pid (Passport ID) - a nine-digit number, including leading zeroes.
     */

    public Task2Validator() {
        super();
        this.setByr("19[2-9]\\d|200[0-2]");
        this.setIyr("201\\d|2020");
        this.setEyr("202\\d|2030");
        this.setHgt("1[5-8]\\dcm|19[0-3]cm|59in|6\\din|7[0-6]in");
        this.setHcl("#[0-9a-f]{6}");
        this.setEcl("amb|blu|brn|gry|grn|hzl|oth");
        this.setPid("[0-9]{9}");
        this.setCid(".*"); //optional cid
    }
}
