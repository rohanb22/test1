package net.example.springbatch.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(schema="ipl",name="student")
public class Student implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8345346810218267560L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
    private Integer id;
	
	@Column(name="email_address")
	private String emailAddress;
	@Column(name="name")
    private String name;
    @Column(name="purchased_package")
    private String purchasedPackage;
    
    public Student() {}

    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
    
    public String getEmailAddress() {
        return emailAddress;
    }

    public String getName() {
        return name;
    }

    public String getPurchasedPackage() {
        return purchasedPackage;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPurchasedPackage(String purchasedPackage) {
        this.purchasedPackage = purchasedPackage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("emailAddress", this.emailAddress)
                .append("name", this.name)
                .append("purchasedPackage", this.purchasedPackage)
                .toString();
    }
}
