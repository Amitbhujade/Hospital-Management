package com.Hospital.hospitalManagementProject.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Hospital.hospitalManagementProject.model.Admin;
import com.Hospital.hospitalManagementProject.model.Doctor;
import com.Hospital.hospitalManagementProject.model.Patient;
import com.Hospital.hospitalManagementProject.service.HospitalService;

@RestController
public class HospitalController {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(HospitalController.class);
	
	@Autowired
	HospitalService hospitalService;
	
	//Patient controller 
	@GetMapping(value="/patient")
	public List<Patient> getPatientList()
	{
		return hospitalService.getPatientList();
	}
	
	 @GetMapping("/patient/{patientName}")
	    public ResponseEntity<List<Patient>> getPatientsByName(@PathVariable String patientName) {
	        List<Patient> patients = hospitalService.getPatientsByName(patientName);
	        if (patients.isEmpty()) {
	            return ResponseEntity.notFound().build();
	        } else {
	            return ResponseEntity.ok(patients);
	        }
	    }
	 
	 @PostMapping(value="/patient")
		public String addPatient(@RequestBody Patient newPatient)
		{
			hospitalService.addPatient(newPatient);
			return "Patient added Successfully";
		}
		
	 @DeleteMapping("/patient/{patientName}")
	    public ResponseEntity<String> deletePatientsByPatientName(@PathVariable String patientName) {
	        return hospitalService.deletePatientByPatientName(patientName);
	    }
	 
	 @PutMapping("/patient/{patientId}")
	    public ResponseEntity<String> updatePatientDetails(
	            @PathVariable Long patientId,
	            @RequestBody Patient updatedPatient) {
	        ResponseEntity<String> response = hospitalService.updatePatientDetails(patientId, updatedPatient);
	        return response;
	    }
	
	 @PostMapping("/patientDischarge")
	    public ResponseEntity<String> dischargePatient(@RequestParam Long patientId) {
	        hospitalService.dischargePatient(patientId);
	        return ResponseEntity.ok("Patient discharged successfully.");
	    }
	
	//Doctor controller
	@GetMapping(value="/doctor")
	public List<Doctor> getDoctorList()
	{
		return hospitalService.getDoctorList();
	}
	
	  @PostMapping("/doctor")
	    public ResponseEntity<String> addDoctor(@RequestBody Doctor newDoctor) {
	        Doctor savedDoctor = hospitalService.addDoctor(newDoctor);
	        return ResponseEntity.ok("Doctor added successfully with ID: " + savedDoctor.getDoctorId());
	    }
	
	  @DeleteMapping("/doctor/{doctorId}")
	    public ResponseEntity<String> deleteDoctorByDoctorId(@PathVariable Long doctorId) {
	        return hospitalService.deleteDoctorById(doctorId);
	    }
	  
	  @PutMapping("/doctor/{doctorId}")
	    public ResponseEntity<String> updateDoctor(
	            @PathVariable Long doctorId,
	            @RequestBody Doctor updatedDoctor) {
	        return hospitalService.updateDoctor(doctorId, updatedDoctor);
	    }
	 
	 
	//Admin controller 
		 @GetMapping("/admin/{patientName}")
		 public ResponseEntity<List<Patient>> getPatientsByPatientName(@PathVariable String patientName) {
		     log.info("Searching for patients with name: {}", patientName);
		     
		     List<Patient> patients = hospitalService.getPatientsByPatientName(patientName);

		     if (patients.isEmpty()) {
		         log.info("No patients found with name: {}", patientName);
		         return ResponseEntity.notFound().build();
		     } else {
		         log.info("Patients found: {}", patients);
		         return ResponseEntity.ok(patients);
		     }
		 }
		
		 @GetMapping("/{adminId}/admin")
		    public ResponseEntity<Admin> getAdminById(@PathVariable Long adminId) {
		        Admin admin = hospitalService.getAdminById(adminId);
		        if (admin != null) {
		            return ResponseEntity.ok(admin);
		        } else {
		            return ResponseEntity.notFound().build();
		        }
		    }

	 
	 //Operator Controller

		@PostMapping("/doctorAssign")
		public String assignDoctorToPatient(@RequestParam Long patientId, @RequestParam Long doctorId) {
			hospitalService.assignDoctorToPatient(patientId, doctorId);
			return "The doctor with Id " + doctorId + " assigned to patient with id " + patientId + " successfully";
		}

	
		@PostMapping("/operator")
		public ResponseEntity<String> generateBill(@RequestParam Long patientId, @RequestParam Long doctorId,
				@RequestParam double totalAmount,
				@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

			hospitalService.generateBill(patientId, doctorId, totalAmount, date);
			return ResponseEntity.ok("Bill generated successfully.");
		}

}
