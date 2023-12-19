package com.Hospital.hospitalManagementProject.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class Doctor {
	
	@Id
	@GeneratedValue
	Long doctorId;
	String doctorName;
	String doctorQualification;
	String doctorSpeacilization;
	
	 @OneToMany(mappedBy = "doctor")
	    private List<Operator> bills;
	
	 @ManyToOne
	    @JoinColumn(name = "admin_id")
	    private Admin admin;
	 
	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDoctorQualification() {
		return doctorQualification;
	}
	public void setDoctorQualification(String doctorQualification) {
		this.doctorQualification = doctorQualification;
	}
	public String getDoctorSpeacilization() {
		return doctorSpeacilization;
	}
	public void setDoctorSpeacilization(String doctorSpeacilization) {
		this.doctorSpeacilization = doctorSpeacilization;
	}
	
//	
//	public List<Operator> getBills() {
//		return bills;
//	}
//	public void setBills(List<Operator> bills) {
//		this.bills = bills;
//	}
	
	
	public Doctor(Long doctorId, String doctorName, String doctorQualification, String doctorSpeacilization
			) {
		super();
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.doctorQualification = doctorQualification;
		this.doctorSpeacilization = doctorSpeacilization;
	}
	
	public Doctor() {
		super();
	}
	
	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", doctorName=" + doctorName + ", doctorQualification="
				+ doctorQualification + ", doctorSpeacilization=" + doctorSpeacilization + "]";
	}
	
	
	
}
