package com.medical.system.config;

import com.medical.system.model.Practitioner;
import com.medical.system.repository.PatientRepository;
import com.medical.system.repository.PractitionerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PractitionerConfig {
    private final PractitionerRepository practitionerRepository;

    public PractitionerConfig(PractitionerRepository practitionerRepository) {
        this.practitionerRepository = practitionerRepository;
    }

    @Bean
    CommandLineRunner commandLineRunner2(PatientRepository patientRepository){
        return args -> {
            Practitioner practitioner = new Practitioner("Kiro","Petrov");
            Practitioner practitioner1 = new Practitioner("Lina", "Kirova");
            practitionerRepository.saveAll(List.of(practitioner1,practitioner));
        };
    }

}
