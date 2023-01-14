package com.medical.system.controller;

import com.medical.system.service.PatientService;
import com.medical.system.model.Patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> getPatients(){
        return patientService.getPatients();
    }

    @GetMapping(path = "{patientEgn}")
    public Optional<Patient> getPatientByEgn(@PathVariable("patientEgn") String patientEgn){
        Optional<Patient> patient = patientService.getPatientByEgn(patientEgn);
        if(!patient.isPresent()){
            throw new IllegalStateException("No patient exists with egn "+patientEgn+".");
        }
        return patient;
    }
    @PostMapping
    public void registerPatient(@RequestBody Patient patient){
        patientService.addNewPatient(patient);
    }

    @DeleteMapping(path = "{patientId}")
    public void deletePatient(@PathVariable("patientId") Long id){
        patientService.deletePatient(id);
    }

    @PutMapping(path = "{patientId}")
    public void updatePatient(@PathVariable("patientId") Long id,
                              @RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName,
                              @RequestParam(required = false) LocalDate lastPaidInsurance){
        patientService.updatePatient(id, firstName, lastName,lastPaidInsurance);
    }

    @GetMapping(path = "insurance/{patientEgn}")
    public boolean hasPaidInsuranceInLastSixMonths(@PathVariable("patientEgn") String egn){
        return patientService.hasPaidInsuranceInLastSixMonths(egn);
    }
}
