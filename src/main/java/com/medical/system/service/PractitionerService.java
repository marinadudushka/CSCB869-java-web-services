package com.medical.system.service;

import com.medical.system.model.Practitioner;
import com.medical.system.repository.PractitionerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PractitionerService {

    private final PractitionerRepository practitionerRepository;

    @Autowired
    public PractitionerService(PractitionerRepository practitionerRepository) {
        this.practitionerRepository = practitionerRepository;
    }

    public List<Practitioner> getPractitioners() {
        return this.practitionerRepository.findAll();
    }

    public Optional<Practitioner> getPractitionerById(Long id) {
        return this.practitionerRepository.findById(id);
    }

    public void addNewPractitioner(Practitioner practitioner) {
        Optional<Practitioner> practitionerById = practitionerRepository.findById(practitioner.getId());
        if(practitionerById.isPresent()){
            throw new IllegalStateException("Practitioner with Id already exists");
        }
        practitionerRepository.save(practitioner);
    }

    public void deletePractitioner(Long id) {
        boolean exists = practitionerRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Practitioner with ID "+id+" does not exist");
        }
        practitionerRepository.deleteById(id);
    }

    public void updatePractitioner(Long id, String firstName, String lastName) {
        Practitioner practitioner = practitionerRepository.findById(id).orElseThrow(() -> new IllegalStateException("Practitioner with ID "+id+" does not exist"));

        if(firstName != null && firstName.length()>0 && !Objects.equals(practitioner.getFirst_name(),firstName)){
            practitioner.setFirst_name(firstName);
        }

        if(lastName != null && lastName.length()>0 && !Objects.equals(practitioner.getLast_name(),lastName)){
            practitioner.setLast_name(lastName);
        }

    }
}
