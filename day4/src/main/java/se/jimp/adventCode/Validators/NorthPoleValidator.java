package se.jimp.adventCode.Validators;

public class NorthPoleValidator extends DefaultValidator {

    //North pole validator is the same as the Default validator except the cid field is optional and can contain empty string

    public NorthPoleValidator() {
        super();
        this.setCid(".*");
    }
}
