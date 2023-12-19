package com.Hospital.hospitalManagementProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Hospital.hospitalManagementProject.model.Admin;
import com.Hospital.hospitalManagementProject.model.Patient;

public interface AdminRepository extends JpaRepository<Admin, Long>{
	 @Query("SELECT p FROM Admin a JOIN a.patients p WHERE LOWER(p.patientName) LIKE LOWER(CONCAT('%', :patientName, '%'))")
	    List<Patient> findPatientsByPatientName(@Param("patientName") String patientName);
}
