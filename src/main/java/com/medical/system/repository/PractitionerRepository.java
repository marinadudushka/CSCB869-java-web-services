package com.medical.system.repository;

import com.medical.system.model.Practitioner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PractitionerRepository extends JpaRepository<Practitioner, Long> {
    Optional<Practitioner> findById(Long id);
}
