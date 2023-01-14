package com.medical.system.service;

import com.medical.system.model.Patient;
import com.medical.system.repository.PatientRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getPatients(){
        return this.patientRepository.findAll();
    }

    public void addNewPatient(Patient patient){
        Optional<Patient> patientByEgn = patientRepository.findPatientByEgn(patient.getEgn());
        if(patientByEgn.isPresent()){
            throw new IllegalStateException("A patient with EGN already exists");
        }
        patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        boolean exists = patientRepository.existsById(id);
        if(! exists){
            throw new IllegalStateException("Patient with id "+id+" does not exist");
        }
        patientRepository.deleteById(id);
    }

    @Transactional
    public void updatePatient(Long id, String firstName, String lastName, LocalDate lastPaidInsurance) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new IllegalStateException("Patient with id "+id+" does not exist"));

        if(firstName != null && firstName.length()>0 && !Objects.equals(patient.getFirst_name(),firstName)){
            patient.setFirst_name(firstName);
        }

        if(lastName != null && lastName.length()>0 && !Objects.equals(patient.getLast_name(),lastName)){
            patient.setLast_name(lastName);
        }

        if(lastPaidInsurance != null && lastPaidInsurance.isBefore(LocalDate.now()) && lastPaidInsurance.isAfter(patient.getLast_paid_insurance())){
            patient.setLast_paid_insurance(lastPaidInsurance);
        }
    }

    public Optional<Patient> getPatientByEgn(String egn) {
        return this.patientRepository.findPatientByEgn(egn);
    }

    public boolean hasPaidInsuranceInLastSixMonths(String eng) {
        LocalDate lastPaidInsurance =  patientRepository.findPatientLastPaidInsuranceDate(eng);
        if(Period.between(lastPaidInsurance, LocalDate.now()).toTotalMonths() > 7){
            return false;
        }
        return true;
    }
}
