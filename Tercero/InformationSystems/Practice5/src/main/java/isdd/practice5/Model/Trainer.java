/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdd.practice5.Model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pepe
 */
@Entity
@Table(name = "TRAINER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trainer.findAll", query = "SELECT t FROM Trainer t"),
    @NamedQuery(name = "Trainer.findByTCod", query = "SELECT t FROM Trainer t WHERE t.tCod = :tCod"),
    @NamedQuery(name = "Trainer.findByTName", query = "SELECT t FROM Trainer t WHERE t.tName = :tName"),
    @NamedQuery(name = "Trainer.findByTIdnumber", query = "SELECT t FROM Trainer t WHERE t.tIdnumber = :tIdnumber"),
    @NamedQuery(name = "Trainer.findByTPhonenumber", query = "SELECT t FROM Trainer t WHERE t.tPhonenumber = :tPhonenumber"),
    @NamedQuery(name = "Trainer.findByTEmail", query = "SELECT t FROM Trainer t WHERE t.tEmail = :tEmail"),
    @NamedQuery(name = "Trainer.findByTDate", query = "SELECT t FROM Trainer t WHERE t.tDate = :tDate"),
    @NamedQuery(name = "Trainer.findByTNick", query = "SELECT t FROM Trainer t WHERE t.tNick = :tNick")})
public class Trainer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "T_COD")
    private String tCod;
    @Basic(optional = false)
    @Column(name = "T_NAME")
    private String tName;
    @Basic(optional = false)
    @Column(name = "T_IDNUMBER")
    private String tIdnumber;
    @Column(name = "T_PHONENUMBER")
    private String tPhonenumber;
    @Column(name = "T_EMAIL")
    private String tEmail;
    @Basic(optional = false)
    @Column(name = "T_DATE")
    private String tDate;
    @Column(name = "T_NICK")
    private String tNick;
    @OneToMany(mappedBy = "aTrainerincharge")
    private Set<Activity> activitySet;

    public Trainer() {
    }

    public Trainer(String tCod) {
        this.tCod = tCod;
    }

    public Trainer(String tCod, String tName, String tIdnumber, String tDate) {
        this.tCod = tCod;
        this.tName = tName;
        this.tIdnumber = tIdnumber;
        this.tDate = tDate;
    }

    public String getTCod() {
        return tCod;
    }

    public void setTCod(String tCod) {
        this.tCod = tCod;
    }

    public String getTName() {
        return tName;
    }

    public void setTName(String tName) {
        this.tName = tName;
    }

    public String getTIdnumber() {
        return tIdnumber;
    }

    public void setTIdnumber(String tIdnumber) {
        this.tIdnumber = tIdnumber;
    }

    public String getTPhonenumber() {
        return tPhonenumber;
    }

    public void setTPhonenumber(String tPhonenumber) {
        this.tPhonenumber = tPhonenumber;
    }

    public String getTEmail() {
        return tEmail;
    }

    public void setTEmail(String tEmail) {
        this.tEmail = tEmail;
    }

    public String getTDate() {
        return tDate;
    }

    public void setTDate(String tDate) {
        this.tDate = tDate;
    }

    public String getTNick() {
        return tNick;
    }

    public void setTNick(String tNick) {
        this.tNick = tNick;
    }

    @XmlTransient
    public Set<Activity> getActivitySet() {
        return activitySet;
    }

    public void setActivitySet(Set<Activity> activitySet) {
        this.activitySet = activitySet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tCod != null ? tCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trainer)) {
            return false;
        }
        Trainer other = (Trainer) object;
        if ((this.tCod == null && other.tCod != null) || (this.tCod != null && !this.tCod.equals(other.tCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "isdd.practice5.Model.Trainer[ tCod=" + tCod + " ]";
    }
    
}
