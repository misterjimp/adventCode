package se.jimp.adventCode.Validators;

import se.jimp.adventCode.Passport;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassportValidator {
    //Basic validator, , all fields can contain anything and nothing. ALl passports are valid.

    //Fields are string regex of what must match in order for the validator to validate the passport to true.

    private String ecl = ".?+";
    private String pid = ".?+";
    private String eyr = ".?+";
    private String hcl = ".?+";
    private String byr = ".?+";
    private String iyr = ".?+";
    private String cid = ".?+";
    private String hgt = ".?+";

    List<String> errorList;
    List<String> recentErrorList;

    public PassportValidator() {
        errorList = new ArrayList<>();
        recentErrorList = new ArrayList<>();
    }

    public String closeRegex(String regex) {
        //bug fix, add ^ in front and $ in the end of all the regexps. They are supposed to be closed strings.
        regex = "^" + regex + "$";
        regex = regex.replace("|", "$|^");

        return regex;
    }

    public boolean matchRegex(String input, String regex) {

        regex = closeRegex(regex);

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);


        boolean matchFound = matcher.find();

        return matchFound;
    }

    public boolean validate(Passport passport) {
        boolean ret = true;

        clearRecentErrorList();

        ret = matchAndReportError(passport.getByr(), this.byr, "passport: " + passport.getPid() + " invalid input on byr field: " + passport.getByr()) && ret;

        ret = matchAndReportError(passport.getCid(), this.cid, "passport: " + passport.getPid() + " invalid input on cid field: " + passport.getCid()) && ret;

        ret = matchAndReportError(passport.getEcl(), this.ecl, "passport: " + passport.getPid() + " invalid input on ecl field: " + passport.getEcl()) && ret;

        ret = matchAndReportError(passport.getHcl(), this.hcl, "passport: " + passport.getPid() + " invalid input on hcl field: " + passport.getHcl()) && ret;

        ret = matchAndReportError(passport.getHgt(), this.hgt, "passport: " + passport.getPid() + " invalid input on hgt field: " + passport.getHgt()) && ret;

        ret = matchAndReportError(passport.getIyr(), this.iyr, "passport: " + passport.getPid() + " invalid input on iyr field: " + passport.getIyr()) && ret;

        ret = matchAndReportError(passport.getEyr(), this.eyr, "passport: " + passport.getPid() + " invalid input on eyr field: " + passport.getEyr()) && ret;

        ret = matchAndReportError(passport.getPid(), this.pid, "passport: " + passport.getPid() + " invalid input on pid field: " + passport.getPid()) && ret;

        return ret;
    }

    private boolean matchAndReportError(String input, String regex, String error) {
        boolean ret = true;

        ret = matchRegex(input, regex);

        if(!ret) {
            addError(error);
            addRecentError(error);
        }

        return ret;
    }

    private void addError(String error) {
        this.errorList.add(error);
    }
    private void addRecentError(String error) {
        this.recentErrorList.add(error);
    }

    private void clearRecentErrorList() {
        recentErrorList.clear();
    }

    public void printErrors() {
        for(String error:errorList) {
            System.out.println(error);
        }
    }

    public void printRecentErrors() {
        for(String error:recentErrorList) {
            System.out.println(error);
        }
    }

    public List<String> getErrors() {
        return this.errorList;
    }

    public String getEcl() {
        return ecl;
    }

    public void setEcl(String ecl) {
        this.ecl = ecl;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getEyr() {
        return eyr;
    }

    public void setEyr(String eyr) {
        this.eyr = eyr;
    }

    public String getHcl() {
        return hcl;
    }

    public void setHcl(String hcl) {
        this.hcl = hcl;
    }

    public String getByr() {
        return byr;
    }

    public void setByr(String byr) {
        this.byr = byr;
    }

    public String getIyr() {
        return iyr;
    }

    public void setIyr(String iyr) {
        this.iyr = iyr;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getHgt() {
        return hgt;
    }

    public void setHgt(String hgt) {
        this.hgt = hgt;
    }
}
