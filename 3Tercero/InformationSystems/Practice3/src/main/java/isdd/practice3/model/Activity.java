package isdd.practice3.model;

/**
 *
 * @author Pepe
 */
public class Activity {
    private String a_id,
                a_name,
                a_description,
                a_price,
                a_trainerInCharge;

    public Activity(String a_id, String a_name, String a_description, String a_price, String a_trainerInCharge) {
        this.a_id = a_id;
        this.a_name = a_name;
        this.a_description = a_description;
        this.a_price = a_price;
        this.a_trainerInCharge = a_trainerInCharge;
    }

    public String getA_id() {
        return a_id;
    }

    public void setA_id(String a_id) {
        this.a_id = a_id;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    public String getA_description() {
        return a_description;
    }

    public void setA_description(String a_description) {
        this.a_description = a_description;
    }

    public String getA_price() {
        return a_price;
    }

    public void setA_price(String a_price) {
        this.a_price = a_price;
    }

    public String getA_trainerInCharge() {
        return a_trainerInCharge;
    }

    public void setA_trainerInCharge(String a_trainerInCharge) {
        this.a_trainerInCharge = a_trainerInCharge;
    }
    
    
}
