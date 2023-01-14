package com.medical.system.config;

import com.medical.system.model.Patient;
import com.medical.system.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class PatientConfig {

    private final PatientRepository patientRepository;

    public PatientConfig(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            Patient gosho = new Patient("Gosho", "Peshov","9907183443", LocalDate.of(1999,07,18),LocalDate.of(2022,12,15));
            Patient petya = new Patient("Petya", "Atanasova","9810119977",LocalDate.of(1998,10,11),LocalDate.of(2021,07,9));
            patientRepository.saveAll(List.of(gosho,petya));
        };
    }
}
