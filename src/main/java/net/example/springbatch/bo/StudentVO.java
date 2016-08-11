package net.example.springbatch.bo;

public class StudentVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8345346810218267560L;

    private Integer id;
	
	private String emailAddress;
    private String firstName;
    private String lastName;
    private String purchasedPackage;
    
    public StudentVO() {}

    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
    
    public String getEmailAddress() {
        return emailAddress;
    }


    public String getPurchasedPackage() {
        return purchasedPackage;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPurchasedPackage(String purchasedPackage) {
        this.purchasedPackage = purchasedPackage;
    }


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String secondName) {
		this.lastName = secondName;
	}


	@Override
	public String toString() {
		return "StudentVO [id=" + id + ", emailAddress=" + emailAddress
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", purchasedPackage=" + purchasedPackage + "]";
	}
    
    

}
