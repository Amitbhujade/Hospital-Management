package com.Hospital.hospitalManagementProject.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity	
public class Operator {

	@Id
	@GeneratedValue
	Long operatorId;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    
    Double totalAmount;
    LocalDate date;
    

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	public Operator(Long operatorId, Patient patient, Doctor doctor, Double totalAmount, LocalDate date) {
		super();
		this.operatorId = operatorId;
		this.patient = patient;
		this.doctor = doctor;
		this.totalAmount = totalAmount;
		this.date = date;
	}

	public Operator() {
		super();
	}

	@Override
	public String toString() {
		return "Operator [operatorId=" + operatorId + ", patient=" + patient + ", doctor=" + doctor + ", totalAmount="
				+ totalAmount + ", date=" + date + "]";
	}

	
	
}
