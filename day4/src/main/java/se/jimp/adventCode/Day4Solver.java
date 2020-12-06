package se.jimp.adventCode;

import se.jimp.adventCode.Validators.NorthPoleValidator;
import se.jimp.adventCode.Validators.PassportValidator;
import se.jimp.adventCode.Validators.Task2Validator;

import java.util.ArrayList;
import java.util.List;

public class Day4Solver {

    static final boolean printValidations = false;
    static final boolean printInvalids = true;
    static final boolean printValids = false;

    List<String> passportInput;
    List<Passport> passports = new ArrayList<>();

    public Day4Solver(String filename) {
        passportInput = PuzzleReader.readPuzzleFile(filename);
        createPassports(passportInput);
    }

    private void createPassports(List<String> passportInput) {
        Passport tmp = new Passport("");

        for(String passLine:passportInput) {

            if("".equals(passLine)) {
                addPassport(tmp);
                tmp = new Passport("");
            }
            else {
                tmp.addValues(passLine);
            }
        }
        addPassport(tmp);
    }

    private void addPassport(Passport passport) {
        this.passports.add(passport);
    }

    private void clearPassports() {
        this.passports.clear();
    }

    public int validatePassports(PassportValidator validator) {
        int ret = 0;
        int invalidated = 0;
        int validated = 0;
        int total = 0;

        for(Passport pp:passports) {
            total++;
            if("610596568".equals(pp.getPid())) {
                System.out.println("stop");
            }
            if(validator.validate(pp)) {
                ret++;
                validated++;

                if(printValidations && printValids) {
                    validator.printRecentErrors();
                    pp.print();
                    System.out.println("validated correctly\n---");
                }
            }
            else {
                invalidated++;
                if(printValidations && printInvalids) {
                    validator.printRecentErrors();
                    pp.print();
                    System.out.println("validate failed.\n---");
                }
            }
        }

        System.out.println("Invalidated: "+ invalidated);
        System.out.println("Validated: " + validated);
        System.out.println("Total: " + total);
        //validator.printErrors();

        return ret;
    }

    public int getNumberOfPassports() {
        return this.passports.size();
    }

    public static void main(String[] args) throws Exception {
        Day4Solver solver = new Day4Solver("day4.puzzle");
        PassportValidator task1Validator = new NorthPoleValidator();
        PassportValidator task2Validator = new Task2Validator();

        System.out.println("--- Task 1 -----");
        int northpoleValid = solver.validatePassports(task1Validator);
        System.out.println("Solver has " + solver.getNumberOfPassports() + " passports");
        System.out.println("Task1 validates " + northpoleValid + " passorts");


        System.out.println("\n--- Task 2 -----");
        int task2Valid = solver.validatePassports(task2Validator);
        System.out.println("Solver has " + solver.getNumberOfPassports() + " passports");
        System.out.println("Task2 validates " + task2Valid + " passorts");


    }
}
