package isdd.practice4.model;

/**
 *
 * @author Pepe
 */
public class Trainer {
    
    private String t_cod,
                t_name,
                t_idNumber,
                t_phoneNumber,
                t_email,
                t_date,
                t_nick;

    public Trainer(String t_cod, String t_name, String t_idNumber, String t_phoneNumber, String t_email, String t_date, String t_nick) {
        this.t_cod = t_cod;
        this.t_name = t_name;
        this.t_idNumber = t_idNumber;
        this.t_phoneNumber = t_phoneNumber;
        this.t_email = t_email;
        this.t_date = t_date;
        this.t_nick = t_nick;
    }

    public String getT_cod() {
        return t_cod;
    }

    public void setT_cod(String t_cod) {
        this.t_cod = t_cod;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getT_idNumber() {
        return t_idNumber;
    }

    public void setT_idNumber(String t_idNumber) {
        this.t_idNumber = t_idNumber;
    }

    public String getT_phoneNumber() {
        return t_phoneNumber;
    }

    public void setT_phoneNumber(String t_phoneNumber) {
        this.t_phoneNumber = t_phoneNumber;
    }

    public String getT_email() {
        return t_email;
    }

    public void setT_email(String t_email) {
        this.t_email = t_email;
    }

    public String getT_date() {
        return t_date;
    }

    public void setT_date(String t_date) {
        this.t_date = t_date;
    }

    public String getT_nick() {
        return t_nick;
    }

    public void setT_nick(String t_nick) {
        this.t_nick = t_nick;
    }
    
    
}
