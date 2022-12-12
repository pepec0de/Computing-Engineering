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
import javax.persistence.ManyToMany;
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
@Table(name = "MEMBER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Member1.findAll", query = "SELECT m FROM Member1 m"),
    @NamedQuery(name = "Member1.findByMNum", query = "SELECT m FROM Member1 m WHERE m.mNum = :mNum"),
    @NamedQuery(name = "Member1.findByMName", query = "SELECT m FROM Member1 m WHERE m.mName = :mName"),
    @NamedQuery(name = "Member1.findByMId", query = "SELECT m FROM Member1 m WHERE m.mId = :mId"),
    @NamedQuery(name = "Member1.findByMBirhtdate", query = "SELECT m FROM Member1 m WHERE m.mBirhtdate = :mBirhtdate"),
    @NamedQuery(name = "Member1.findByMPhone", query = "SELECT m FROM Member1 m WHERE m.mPhone = :mPhone"),
    @NamedQuery(name = "Member1.findByMEmailmember", query = "SELECT m FROM Member1 m WHERE m.mEmailmember = :mEmailmember"),
    @NamedQuery(name = "Member1.findByMStartingdatemember", query = "SELECT m FROM Member1 m WHERE m.mStartingdatemember = :mStartingdatemember"),
    @NamedQuery(name = "Member1.findByMCathegorymember", query = "SELECT m FROM Member1 m WHERE m.mCathegorymember = :mCathegorymember")})
public class Member1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "M_NUM")
    private String mNum;
    @Basic(optional = false)
    @Column(name = "M_NAME")
    private String mName;
    @Basic(optional = false)
    @Column(name = "M_ID")
    private String mId;
    @Column(name = "M_BIRHTDATE")
    private String mBirhtdate;
    @Column(name = "M_PHONE")
    private String mPhone;
    @Column(name = "M_EMAILMEMBER")
    private String mEmailmember;
    @Basic(optional = false)
    @Column(name = "M_STARTINGDATEMEMBER")
    private String mStartingdatemember;
    @Basic(optional = false)
    @Column(name = "M_CATHEGORYMEMBER")
    private Character mCathegorymember;
    @ManyToMany(mappedBy = "member1Set")
    private Set<Activity> activitySet;

    public Member1() {
    }

    public Member1(String mNum) {
        this.mNum = mNum;
    }

    public Member1(String mNum, String mName, String mId, String mStartingdatemember, Character mCathegorymember) {
        this.mNum = mNum;
        this.mName = mName;
        this.mId = mId;
        this.mStartingdatemember = mStartingdatemember;
        this.mCathegorymember = mCathegorymember;
    }

    public String getMNum() {
        return mNum;
    }

    public void setMNum(String mNum) {
        this.mNum = mNum;
    }

    public String getMName() {
        return mName;
    }

    public void setMName(String mName) {
        this.mName = mName;
    }

    public String getMId() {
        return mId;
    }

    public void setMId(String mId) {
        this.mId = mId;
    }

    public String getMBirhtdate() {
        return mBirhtdate;
    }

    public void setMBirhtdate(String mBirhtdate) {
        this.mBirhtdate = mBirhtdate;
    }

    public String getMPhone() {
        return mPhone;
    }

    public void setMPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getMEmailmember() {
        return mEmailmember;
    }

    public void setMEmailmember(String mEmailmember) {
        this.mEmailmember = mEmailmember;
    }

    public String getMStartingdatemember() {
        return mStartingdatemember;
    }

    public void setMStartingdatemember(String mStartingdatemember) {
        this.mStartingdatemember = mStartingdatemember;
    }

    public Character getMCathegorymember() {
        return mCathegorymember;
    }

    public void setMCathegorymember(Character mCathegorymember) {
        this.mCathegorymember = mCathegorymember;
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
        hash += (mNum != null ? mNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Member1)) {
            return false;
        }
        Member1 other = (Member1) object;
        if ((this.mNum == null && other.mNum != null) || (this.mNum != null && !this.mNum.equals(other.mNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "isdd.practice5.Model.Member1[ mNum=" + mNum + " ]";
    }
    
}
