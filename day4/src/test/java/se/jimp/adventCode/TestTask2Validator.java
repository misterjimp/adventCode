package se.jimp.adventCode;

import org.junit.jupiter.api.Test;
import se.jimp.adventCode.Validators.PassportValidator;
import se.jimp.adventCode.Validators.Task2Validator;

import java.math.BigInteger;

public class TestTask2Validator {


    @Test
    public void testTask2ValidatorInvalids() {

        String invalid1 = "eyr:1972 cid:100 " +
                "hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926";

        String invalid2 = "iyr:2019 " +
                "hcl:#602927 eyr:1967 hgt:170cm " +
                "ecl:grn pid:012533040 byr:1946";

        String invalid3 = "hcl:dab227 iyr:2012 " +
                "ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277";

        String invalid4 = "hgt:59cm ecl:zzz " +
                "eyr:2038 hcl:74454a iyr:2023 " +
                "pid:3556412378 byr:2007";

        String invalid6 = "ecl:oth " +
                "pid:575539099 " +
                "eyr:2031 " +
                "hcl:#cfa07d " +
                "byr:1987 " +
                "iyr:2017 " +
                "hgt:69cm";


        Passport tmp = new Passport(invalid1);
        PassportValidator val = new Task2Validator();

        assert(!val.validate(tmp));

        tmp = new Passport(invalid2);
        assert(!val.validate(tmp));

        tmp = new Passport(invalid3);
        assert(!val.validate(tmp));

        tmp = new Passport(invalid4);
        assert(!val.validate(tmp));

        tmp = new Passport(invalid6);
        assert(!val.validate(tmp));

    }

    @Test
    public void testTask2ValidatorValids() {
        String valid1 = "pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980 " +
                "hcl:#623a2f";
        String valid2 = "eyr:2029 ecl:blu cid:129 byr:1989 " +
                "iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm";
        String valid3 = "hcl:#888785 " +
                "hgt:164cm byr:2001 iyr:2015 cid:88 " +
                "pid:545766238 ecl:hzl " +
                "eyr:2022";
        String valid4 = "iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719";

        Passport tmp = new Passport(valid1);
        PassportValidator val = new Task2Validator();

        assert(val.validate(tmp));

        tmp = new Passport(valid2);
        assert(val.validate(tmp));

        tmp = new Passport(valid3);
        assert(val.validate(tmp));

        tmp = new Passport(valid4);
        assert(val.validate(tmp));

    }

    @Test
    public void testRegexpByr() {
        Task2Validator val = new Task2Validator();
        for(int i = 0;i < 1920;i++) {
            assert(!val.matchRegex(i + "", val.getByr()));
        }

        //byr ranges from 1920 to 2002
        for(int i = 1920;i < 2003;i++) {
            assert(val.matchRegex(i + "", val.getByr()));
        }

        for(int i = 2003;i < 99999;i++) {
            assert(!val.matchRegex(i + "", val.getByr()));
        }
    }

    @Test
    public void testRegexpIyr() {
        Task2Validator val = new Task2Validator();

        for(int i = 0;i < 2010;i++) {
            assert(!val.matchRegex(i + "", val.getIyr()));
        }

        //byr ranges from 2010 to 2020
        for(int i = 2010;i <= 2020;i++) {
            assert(val.matchRegex(i + "", val.getIyr()));
        }

        for(int i = 2021;i < 99999;i++) {
            assert(!val.matchRegex(i + "", val.getIyr()));
        }
    }

    @Test
    public void testRegexpEyr() {
        Task2Validator val = new Task2Validator();

        for(int i = 0;i < 2020;i++) {
            assert(!val.matchRegex(i + "", val.getEyr()));
        }

        //byr ranges from 2010 to 2020
        for(int i = 2020;i <= 2030;i++) {
            assert(val.matchRegex(i + "", val.getEyr()));
        }

        for(int i = 2031;i < 99999;i++) {
            assert(!val.matchRegex(i + "", val.getEyr()));
        }
    }

    @Test
    public void testRegexpHgtCm() {
        Task2Validator val = new Task2Validator();

        for(int i = 0;i < 150;i++) {
            assert(!val.matchRegex(i + "cm", val.getHgt()));
        }

        //byr ranges from 2010 to 2020
        for(int i = 150;i <= 193;i++) {
            assert(val.matchRegex(i + "cm", val.getHgt()));
        }

        for(int i = 194;i < 9999;i++) {
            assert(!val.matchRegex(i + "cm", val.getHgt()));
        }
    }

    @Test
    public void testRegexpHgtIn() {
        Task2Validator val = new Task2Validator();

        for(int i = 0;i < 59;i++) {
            assert(!val.matchRegex(i + "in", val.getHgt()));
        }

        //byr ranges from 2010 to 2020
        for(int i = 60;i <= 76;i++) {
            assert(val.matchRegex(i + "in", val.getHgt()));
        }

        for(int i = 77;i < 999;i++) {
            assert(!val.matchRegex(i + "in", val.getHgt()));
        }
    }

    //Test taakes 3.5 minutes to run. skipped for now
    /*@Test
    public void testRegexpHcl() {
        Task2Validator val = new Task2Validator();

        //hcl ranges from
        for(int i = 0x0;i <= 0xffffff;i++) {
            //System.out.println("#" + String.format("%1$06x",i));
            assert(val.matchRegex("#" + String.format("%1$06x",i), val.getHcl()));
        }

        for(int i = 0x1000000;i < 0xfffffff;i++) {
            boolean test = val.matchRegex("#" + String.format("%1$06x",i), val.getHcl());

            assert(!test);
        }
    }*/

    //Skipped for now takes too long to run
    /*@Test
    public void testRegexpPid() {
        Task2Validator val = new Task2Validator();

        //hcl ranges from
        for (int i = 0; i <= 999999999; i++) {
            //System.out.println(String.format("%1$09d", i));
            assert (val.matchRegex(String.format("%1$09d", i), val.getPid()));
        }
    }*/
}
