package isdd.practice4.model;

/**
 *
 * @author Pepe
 */
public class Member {
    
    private String m_num,
                m_name,
                m_id,
                m_birthdate,
                m_phone,
                m_emailMember,
                m_startingDateMember,
                m_cathegoryMember;

    public Member(String m_num, String m_name, String m_id, String m_birthdate, String m_phone, String m_emailMember, String m_startingDateMember, String m_cathegoryMember) {
        this.m_num = m_num;
        this.m_name = m_name;
        this.m_id = m_id;
        this.m_birthdate = m_birthdate;
        this.m_phone = m_phone;
        this.m_emailMember = m_emailMember;
        this.m_startingDateMember = m_startingDateMember;
        this.m_cathegoryMember = m_cathegoryMember;
    }

    public String getM_num() {
        return m_num;
    }

    public void setM_num(String m_num) {
        this.m_num = m_num;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }

    public String getM_birthdate() {
        return m_birthdate;
    }

    public void setM_birthdate(String m_birthdate) {
        this.m_birthdate = m_birthdate;
    }

    public String getM_phone() {
        return m_phone;
    }

    public void setM_phone(String m_phone) {
        this.m_phone = m_phone;
    }

    public String getM_emailMember() {
        return m_emailMember;
    }

    public void setM_emailMember(String m_emailMember) {
        this.m_emailMember = m_emailMember;
    }

    public String getM_startingDateMember() {
        return m_startingDateMember;
    }

    public void setM_startingDateMember(String m_startingDateMember) {
        this.m_startingDateMember = m_startingDateMember;
    }

    public String getM_cathegoryMember() {
        return m_cathegoryMember;
    }

    public void setM_cathegoryMember(String m_cathegoryMember) {
        this.m_cathegoryMember = m_cathegoryMember;
    }
}
