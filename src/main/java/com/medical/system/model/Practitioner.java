package com.medical.system.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "practitioner")
public class Practitioner {

    @Id
    @SequenceGenerator(name = "practitioner_sequence", sequenceName = "practitioner_sequence",allocationSize = 1, initialValue = 100000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "practitioner_sequence")
    private Long id;

    private String first_name;
    private String last_name;

//    private Set<Specialty> specialty; //TODO: add list of specialties


    public Practitioner(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

}
