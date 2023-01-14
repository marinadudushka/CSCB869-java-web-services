package com.medical.system.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name="patient")
public class Patient{

    @Id
    @SequenceGenerator(name = "patient_sequence", sequenceName = "patient_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_sequence")
    private Long id;

    private String first_name;
    private String last_name;
    private String egn;
    private LocalDate dob; //date of birth
    private LocalDate last_paid_insurance;
    @Transient
    private Integer age;

    public Patient(Long id, String first_name, String last_name, String egn, LocalDate dob, LocalDate last_paid_insurance) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.egn = egn;
        this.dob = dob;
        this.last_paid_insurance = last_paid_insurance;
    }

    public Patient(String first_name, String last_name, String egn, LocalDate dob, LocalDate last_paid_insurance) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.egn = egn;
        this.dob = dob;
        this.last_paid_insurance = last_paid_insurance;
    }

    public Integer getAge() {
        return Period.between(this.dob,LocalDate.now()).getYears();
    }

//    public boolean hadPaidInsuranceInLastSixMonths(){
//        if(Period.between(this.last_paid_insurance, LocalDate.now()).getMonths() > 6){
//            return false;
//        }
//        return true;
//    }
}
