package isdd.practice5.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Pepe
 */
public class Verifier {
    
    private SimpleDateFormat dateF;
    public Date currentDate;
    
    public Verifier() {
        dateF = new SimpleDateFormat("dd/MM/yyyy");
        currentDate = new Date();
    }
    
    public String getDateString(Date date) {
        return dateF.format(date);
    }
    
    public Date getStringDate(String str) throws ParseException {
        return dateF.parse(str);
    }
    
    // Returns error message
    public String verify(Member1 m) {
        String r = "Not valid ";
        if (!verifyDate(m.getMBirhtdate())) {
            return r + "birthdate";
        }
        if (!verifyPhone(m.getMPhone())) {
            return r + "phone";
        }
        if (!verifyEmail(m.getMEmailmember())) {
            return r + "E-Mail";
        }
        if (!verifyDate(m.getMStartingdatemember())) {
            return r + "starting date";
        }
        return null;
    }
    
    public String verify(Trainer t) {
        String r = "Not valid ";
        if (!verifyPhone(t.getTPhonenumber())) {
            return r + "phone";
        }
        if (!verifyEmail(t.getTEmail())) {
            return r + "E-Mail";
        }
        if (!verifyDate(t.getTDate())) {
            return r + "date";
        }
        return null;
    }
    
    public String verify(Activity a) {
        String r = "Not valid ";
        if (!verifyPhone(a.getAPrice().toString()))
            return r + "price";
        return null;
    }
    
    private boolean verifyDate(String dateStr) {
        if (dateStr.equals(""))
            return true;
        
        boolean r;
        try {
            r = currentDate.after(getStringDate(dateStr));
        } catch (ParseException ex) {
            r = false;
        }
        return r;
    }
    
    private boolean verifyPhone(String phoneStr) {
        return phoneStr.equals("") ? true : phoneStr.matches("^[0-9]*$");
    }

    private boolean verifyEmail(String emailStr) {
        return emailStr.equals("") ? true 
                : emailStr.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
                        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    }
}
