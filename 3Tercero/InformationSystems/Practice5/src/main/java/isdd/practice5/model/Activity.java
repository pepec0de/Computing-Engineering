/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdd.practice5.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pepe
 */
@Entity
@Table(name = "ACTIVITY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Activity.findAll", query = "SELECT a FROM Activity a"),
    @NamedQuery(name = "Activity.findByAId", query = "SELECT a FROM Activity a WHERE a.aId = :aId"),
    @NamedQuery(name = "Activity.findByAName", query = "SELECT a FROM Activity a WHERE a.aName = :aName"),
    @NamedQuery(name = "Activity.findByADescription", query = "SELECT a FROM Activity a WHERE a.aDescription = :aDescription"),
    @NamedQuery(name = "Activity.findByAPrice", query = "SELECT a FROM Activity a WHERE a.aPrice = :aPrice")})
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "A_ID")
    private String aId;
    @Basic(optional = false)
    @Column(name = "A_NAME")
    private String aName;
    @Basic(optional = false)
    @Column(name = "A_DESCRIPTION")
    private String aDescription;
    @Basic(optional = false)
    @Column(name = "A_PRICE")
    private BigInteger aPrice;
    @JoinTable(name = "PERFORMS", joinColumns = {
        @JoinColumn(name = "P_ID", referencedColumnName = "A_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "P_NUM", referencedColumnName = "M_NUM")})
    @ManyToMany
    private Set<Member1> member1Set;
    @JoinColumn(name = "A_TRAINERINCHARGE", referencedColumnName = "T_COD")
    @ManyToOne
    private Trainer aTrainerincharge;

    public Activity() {
    }

    public Activity(String aId) {
        this.aId = aId;
    }

    public Activity(String aId, String aName, String aDescription, BigInteger aPrice, Trainer aTrainerincharge) {
        this.aId = aId;
        this.aName = aName;
        this.aDescription = aDescription;
        this.aPrice = aPrice;
        this.aTrainerincharge = aTrainerincharge;
    }

    public String getAId() {
        return aId;
    }

    public void setAId(String aId) {
        this.aId = aId;
    }

    public String getAName() {
        return aName;
    }

    public void setAName(String aName) {
        this.aName = aName;
    }

    public String getADescription() {
        return aDescription;
    }

    public void setADescription(String aDescription) {
        this.aDescription = aDescription;
    }

    public BigInteger getAPrice() {
        return aPrice;
    }

    public void setAPrice(BigInteger aPrice) {
        this.aPrice = aPrice;
    }

    @XmlTransient
    public Set<Member1> getMember1Set() {
        return member1Set;
    }

    public void setMember1Set(Set<Member1> member1Set) {
        this.member1Set = member1Set;
    }

    public Trainer getATrainerincharge() {
        return aTrainerincharge;
    }

    public void setATrainerincharge(Trainer aTrainerincharge) {
        this.aTrainerincharge = aTrainerincharge;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aId != null ? aId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activity)) {
            return false;
        }
        Activity other = (Activity) object;
        if ((this.aId == null && other.aId != null) || (this.aId != null && !this.aId.equals(other.aId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "isdd.practice5.model.Activity[ aId=" + aId + " ]";
    }
    
}
