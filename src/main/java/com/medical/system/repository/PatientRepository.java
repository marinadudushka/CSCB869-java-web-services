package com.medical.system.repository;

import com.medical.system.model.Patient;
import net.bytebuddy.asm.Advice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findPatientByEgn(String egn);

    @Query("SELECT p.last_paid_insurance FROM Patient p WHERE p.egn=?1")
    LocalDate findPatientLastPaidInsuranceDate(String eng);

}
