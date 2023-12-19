package com.Hospital.hospitalManagementProject.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Patient {
	
	@Id
	@GeneratedValue
	Long patientId;
	String patientName;
	int patientAge;
	String patientBloodGroup;
	String patientDisease;
	int patientWardNumber;
	
	@ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
    
    @OneToMany(mappedBy = "patient")
    private Set<Operator> operators;
	 
	 public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
	        this.doctor = doctor;
	    }
	
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public int getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}
	public String getPatientBloodGroup() {
		return patientBloodGroup;
	}
	public void setPatientBloodGroup(String patientBloodGroup) {
		this.patientBloodGroup = patientBloodGroup;
	}
	public String getPatientDisease() {
		return patientDisease;
	}
	public void setPatientDisease(String patientDisease) {
		this.patientDisease = patientDisease;
	}
	public int getPatientWardNumber() {
		return patientWardNumber;
	}
	public void setPatientWardNumber(int patientWardNumber) {
		this.patientWardNumber = patientWardNumber;
	}
	
	public Set<Operator> getOperators() {
		return operators;
	}

	public void setOperators(Set<Operator> operators) {
		this.operators = operators;
	}
	
	public Patient() {
		super();
	}
	
	public Patient(Long patientId, String patientName, int patientAge, String patientBloodGroup, String patientDisease,
			int patientWardNumber, Doctor doctor, Admin admin, Set<Operator> operators) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.patientAge = patientAge;
		this.patientBloodGroup = patientBloodGroup;
		this.patientDisease = patientDisease;
		this.patientWardNumber = patientWardNumber;
		this.doctor = doctor;
		this.admin = admin;
		this.operators = operators;
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", patientName=" + patientName + ", patientAge=" + patientAge
				+ ", patientBloodGroup=" + patientBloodGroup + ", patientDisease=" + patientDisease
				+ ", patientWardNumber=" + patientWardNumber  + "]";
	}

}
