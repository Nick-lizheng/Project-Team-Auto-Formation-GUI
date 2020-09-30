package model;

import java.io.Serializable;

public class ProjectOwner implements Serializable{
	private static final long serialVersionUID = -2123029360695258121L;
	private String firstName;
	private String surname;
	private String ownerID;
	private String role;
	private String email;
	private String companyID;
	public ProjectOwner(String firstName, String surname, String ownerID, String role, String email, String companyID) {
		super();
		this.firstName = firstName;
		this.surname = surname;
		this.ownerID = ownerID;
		this.role = role;
		this.email = email;
		this.companyID = companyID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getOwnerID() {
		return ownerID;
	}
	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompanyID() {
		return companyID;
	}
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}
	@Override
	public String toString() {
		return "FirstName = " + firstName + ", Surname = " + surname + ", OwnerID = " + ownerID + ", Role = "
				+ role + ", Email = " + email + ", CompanyID = " + companyID;
	}
	
	

}
