package com.Hospital.hospitalManagementProject.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Hospital.hospitalManagementProject.model.Admin;
import com.Hospital.hospitalManagementProject.model.Doctor;
import com.Hospital.hospitalManagementProject.model.Operator;
import com.Hospital.hospitalManagementProject.model.Patient;
import com.Hospital.hospitalManagementProject.repository.AdminRepository;
import com.Hospital.hospitalManagementProject.repository.DoctorRepository;
import com.Hospital.hospitalManagementProject.repository.OperatorRepository;
import com.Hospital.hospitalManagementProject.repository.PatientRepository;

import jakarta.annotation.PostConstruct;

@Service
public class HospitalService {
	
	@Autowired
	PatientRepository patientRepository;
	@Autowired
	DoctorRepository doctorRepository;
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	OperatorRepository operatorRepository;
	
	
	
	@PostConstruct
	public void initDB1() {
		List<Patient> patientList = new ArrayList<>();
		patientList.add(new Patient(1L,"Rohit",23,"B+","Malaria",5, null, null, null));
		patientList.add(new Patient(2L,"Shreya",26,"A+","Jaundice",10, null, null, null));
		patientList.add(new Patient(3L,"Dinesh",29,"O-","Blood Cancer",2, null, null, null));
		patientList.add(new Patient(4L,"Kartik",15,"B+","Kidney Stone",4, null, null, null));
		patientList.add(new Patient(5L,"Rekha",40,"A-","Lung Cancer",2, null, null, null));
		patientRepository.saveAll(patientList);
	}
	
	@PostConstruct
	public void initDB2() {
		
		List<Doctor> doctorList = new ArrayList<>();
		
		doctorList.add(new Doctor(1L, "Dr. Ganesh", "M.B.B.S.", "Oncologists"));// Oncologists are doctors that examine,
																				// diagnose, and treat cancer using a
																				// variety of therapies, as well as
																				// medical and surgical care
		doctorList.add(new Doctor(2L, "Dr. Shweta", "B.D.M.S.", "Cardiologists"));// heart disorders, high blood
																					// pressure, heart failure,
																					// abnormalities with your heart
																					// valves and blood arterie
		doctorList.add(new Doctor(3L, "Dr. Mukesh", "M.B.B.S.", "Audiologist"));// causes of hearing loss and decides
																				// the best course of action and cleans
																				// the external ear canal
		doctorList.add(new Doctor(4L, "Dr. Priti", "B.M.S.", "Dentist"));// Teeth related problems
		doctorList.add(new Doctor(5L, "Dr. Shyam", "B.D.M.S.", "Psychiatrist"));// Doctors who specialize in mental
																				// health, which includes substance use
																				// issues, are known as psychiatrists.
																				// Psychiatrists are trained to evaluate
																				// both the mental and physical
																				// components of psychological problems.
		doctorRepository.saveAll(doctorList);
	}
	
	@PostConstruct
	public void initDB3() {
		
		List<Admin> adminList = new ArrayList<>();
		
		adminList.add(new Admin(1L,null,null));
		adminList.add(new Admin(2L,null,null));
		adminList.add(new Admin(3L,null,null));
		adminList.add(new Admin(4L,null,null));
		
		adminRepository.saveAll(adminList);
		
		
	}
	
	
	//Patient methods
	public List<Patient> getPatientList()
	{
		return patientRepository.findAll();
	}
	
	 public List<Patient> getPatientsByName(String patientName) {
	        return patientRepository.findByPatientName(patientName);
	    }
	
	public Patient addPatient(Patient newPatient)
	{
		return patientRepository.save(newPatient);
		
	}
	
	public ResponseEntity<String> deletePatientByPatientName(String patientName) {
        List<Patient> patients = patientRepository.findByPatientName(patientName);

        if (patients.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            for (Patient patient : patients) {
                patientRepository.deleteById(patient.getPatientId());
            }
            return ResponseEntity.ok("Patients deleted successfully.");
        }
    }
	
	 public ResponseEntity<String> updatePatientDetails(Long patientId, Patient updatedPatient) {
	        Optional<Patient> existingPatient = patientRepository.findById(patientId);

	        if (existingPatient.isPresent()) {
	            Patient patientToUpdate = existingPatient.get();
	            
	            // Update the fields you want to change
	            if (updatedPatient.getPatientName() != null) {
	                patientToUpdate.setPatientName(updatedPatient.getPatientName());
	            }
	            if (updatedPatient.getPatientAge() > 0) {
	                patientToUpdate.setPatientAge(updatedPatient.getPatientAge());
	            }
	            if (updatedPatient.getPatientBloodGroup() != null) {
	                patientToUpdate.setPatientBloodGroup(updatedPatient.getPatientBloodGroup());;
	            }
	            if (updatedPatient.getPatientDisease() != null) {
	                patientToUpdate.setPatientDisease(updatedPatient.getPatientDisease());;
	            }
	            if (updatedPatient.getPatientWardNumber() > 0) {
	                patientToUpdate.setPatientWardNumber(updatedPatient.getPatientWardNumber());;
	            }
	       
	            patientRepository.save(patientToUpdate);
	            return ResponseEntity.ok("Patient details updated successfully.");
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	 

		public void dischargePatient(Long patientId) {
		    Patient patient = patientRepository.findById(patientId).orElse(null);
		    if (patient != null) {
		        patient.setDoctor(null);
		        patientRepository.save(patient);
		    }
		}
	
	//Doctor methods
	public List<Doctor> getDoctorList()
	{
		return doctorRepository.findAll();
	}
	

	public Doctor addDoctor(Doctor newDoctor)
	{
		return doctorRepository.save(newDoctor);
		
	}
	
	 public ResponseEntity<String> deleteDoctorById(Long doctorId) {
	        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);

	        if (optionalDoctor.isPresent()) {
	            Doctor doctor = optionalDoctor.get();
	            
	            // Make sure to handle any additional business logic or checks before deletion

	            doctorRepository.delete(doctor);
	            return ResponseEntity.ok("Doctor with ID " + doctorId + " deleted successfully.");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("Doctor with ID " + doctorId + " not found.");
	        }
	    }
	 
	 public ResponseEntity<String> updateDoctor(Long doctorId, Doctor updatedDoctor) {
	        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);

	        if (optionalDoctor.isPresent()) {
	            Doctor existingDoctor = optionalDoctor.get();

	            // Update the existing doctor with the new details
	            existingDoctor.setDoctorName(updatedDoctor.getDoctorName());
	            existingDoctor.setDoctorQualification(updatedDoctor.getDoctorQualification());
	            existingDoctor.setDoctorSpeacilization(updatedDoctor.getDoctorSpeacilization());

	            // Save the updated doctor
	            doctorRepository.save(existingDoctor);

	            return ResponseEntity.ok("Doctor with ID " + doctorId + " updated successfully.");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("Doctor with ID " + doctorId + " not found.");
	        }
	    }
	

	//Admin methods
		public List<Patient> getPatientsByPatientName(String patientName) {
		    List<Patient> patients = adminRepository.findPatientsByPatientName(patientName);
		    System.out.println("Patients found: " + patients);
		    return patients;
		}
		
		public Admin getAdminById(Long adminId) {
			return adminRepository.findById(adminId).orElse(null);
		}
	
	//Operator methods

	public void assignDoctorToPatient(Long patientId, Long doctorId) {
	    Patient patient = patientRepository.findById(patientId).orElse(null);
	    Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
	    if (patient != null && doctor != null) {
	        patient.setDoctor(doctor);
	        patientRepository.save(patient);
	    }
	}
	
	
	 public void generateBill(Long patientId, Long doctorId, double totalAmount, LocalDate date) {
	        Patient patient = patientRepository.findById(patientId).orElse(null);
	        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);

	        if (patient != null && doctor != null) {
	            Operator bill = new Operator();
	            bill.setPatient(patient);
	            bill.setDoctor(doctor);
	            bill.setTotalAmount(totalAmount);
	            bill.setDate(date);
	            // Save the bill to the repository
	            operatorRepository.save(bill);
	        }
	    }

	
	
}
