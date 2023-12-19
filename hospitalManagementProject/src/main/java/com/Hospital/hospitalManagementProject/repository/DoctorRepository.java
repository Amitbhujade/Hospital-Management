package com.Hospital.hospitalManagementProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Hospital.hospitalManagementProject.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{

}
