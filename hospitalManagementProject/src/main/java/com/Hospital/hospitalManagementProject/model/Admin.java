package com.Hospital.hospitalManagementProject.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Admin {
	@Id
	@GeneratedValue
	Long adminId;

	@OneToMany(mappedBy = "admin")
    private List<Patient> patients;
    
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;
	

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
//
//	public Patient getPatient() {
//		return patient;
//	}
//
//	public void setPatient(Patient patient) {
//		this.patient = patient;
//	}
	

	public Doctor getDoctor() {
		return doctor;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Admin() {
		super();
	}
	 public Admin(Long adminId, List<Patient> patients, Doctor doctor) {
	        super();
	        this.adminId = adminId;
	        this.patients = patients;
	        this.doctor = doctor;
	    }

	 @Override
	    public String toString() {
	        return "Admin [adminId=" + adminId + ", patients=" + patients + ", doctor=" + doctor + "]";
	    }

    
    
}
