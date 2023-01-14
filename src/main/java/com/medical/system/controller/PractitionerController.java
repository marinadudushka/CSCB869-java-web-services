package com.medical.system.controller;

import com.medical.system.model.Practitioner;
import com.medical.system.repository.PractitionerRepository;
import com.medical.system.service.PractitionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/practitioner")
public class PractitionerController {

    private final PractitionerService practitionerService;

    @Autowired
    public PractitionerController(PractitionerService practitionerService) {
        this.practitionerService = practitionerService;
    }

    @GetMapping
    public List<Practitioner> getPractitioners(){
        return this.practitionerService.getPractitioners();
    }

    @GetMapping(path = "{practitionerId}")
    public Optional<Practitioner> getPractitionerById(@PathVariable("practitionerId") Long id) {
        Optional<Practitioner> practitioner = practitionerService.getPractitionerById(id);
        if (!practitioner.isPresent()) {
            throw new IllegalStateException("No practitioner with id " + id + " exists");
        }
        return practitioner;
    }

    @PostMapping
    public void addPractitioner(@RequestBody Practitioner practitioner){
        practitionerService.addNewPractitioner(practitioner);
    }

    @DeleteMapping(path = "{practitionerId}")
    public void deletePractitioner(@PathVariable("practitionerId") Long id){
        practitionerService.deletePractitioner(id);
    }

    @PutMapping(path = "{practitionerId}")
    public void updatePractitioner(@PathVariable("practitionerId") Long id,
                                   @RequestParam(required = false) String firstName,
                                   @RequestParam(required = false) String lastName){
        practitionerService.updatePractitioner(id,firstName,lastName);
    }
}
