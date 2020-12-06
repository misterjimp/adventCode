package se.jimp.adventCode.Validators;

public class DefaultValidator extends PassportValidator {

    //Default validator sets all fields must have a value set.
    public DefaultValidator() {
        super();
        setPid(".+");
        this.setByr(".+");
        this.setCid(".+");
        this.setEcl(".+");
        this.setEyr(".+");
        this.setHcl(".+");
        this.setHgt(".+");
        this.setCid(".+");
        this.setIyr(".+");
    }
}
