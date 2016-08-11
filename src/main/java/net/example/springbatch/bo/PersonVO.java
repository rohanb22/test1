package net.example.springbatch.bo;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class PersonVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8931770752498266707L;
	
	private int id;
	private String firstName;
	private String lastName;
	private int age;
	private String phoneNumber;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "PersonVO [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", age=" + age + ", phoneNumber="
				+ phoneNumber + "]";
	}
	
	
	
}
