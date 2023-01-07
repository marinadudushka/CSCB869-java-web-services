package com.medical.system.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Where(clause = "deleted = 0")
@Table(name = "practitioner")

public class Practitioner extends BaseEntity {

    private String name;

//    private List<String> specialties;

    @OneToMany( mappedBy = "practitioner")
    @JsonIgnoreProperties("practitioner")
    private List<Patient> patients;

    private int deleted = 0;
}
