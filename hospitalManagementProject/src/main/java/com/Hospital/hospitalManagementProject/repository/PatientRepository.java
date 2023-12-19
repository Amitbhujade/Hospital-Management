package com.Hospital.hospitalManagementProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Hospital.hospitalManagementProject.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{
	 List<Patient> findByPatientName(String patientName);
}
