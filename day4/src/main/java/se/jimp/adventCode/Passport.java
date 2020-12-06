package se.jimp.adventCode;

import java.util.List;

public class Passport {

    private String ecl = "";
    private String pid = "";
    private String eyr = "";
    private String hcl = "";
    private String byr = "";
    private String iyr = "";
    private String cid = "";
    private String hgt = "";

    public Passport(String input) {
        this.setFields(input);
    }

    public void addValues(String input) {
        this.setFields(input);
    }

    private void setFields(String input) {
        if(!"".equals(input)) {
            String[] fields = input.split(" ");

            for (int i = 0; i < fields.length; i++) {
                setField(fields[i]);
            }
        }
    }

    private void setField(String input) {
        String[] split = input.split(":");

        this.setField(split[0], split[1]);
    }

    private void setField(String fieldName, String value) {
        switch(fieldName) {
            case "ecl": this.setEcl(value);
                break;
            case "pid": this.setPid(value);
                break;
            case "eyr": this.setEyr(value);
                break;
            case "hcl": this.setHcl(value);
                break;
            case "byr": this.setByr(value);
                break;
            case "iyr": this.setIyr(value);
                break;
            case "cid": this.setCid(value);
                break;
            case "hgt": this.setHgt(value);
                break;
            default:
                System.out.println("Unknown field: " + fieldName + ", data ignored");
        }

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

    public void print() {
        System.out.println("\n -------Passport: -----");
        System.out.println("ecl: " + this.getEcl());
        System.out.println("pid: " + this.getPid());
        System.out.println("eyr: " + this.getEyr());
        System.out.println("hcl: " + this.getHcl());
        System.out.println("byr: " + this.getByr());
        System.out.println("iyr: " + this.getIyr());
        System.out.println("cid: " + this.getCid());
        System.out.println("hgt: " + this.getHgt());
    }
}
