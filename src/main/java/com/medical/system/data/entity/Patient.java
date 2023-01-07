package com.medical.system.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "patient")

public class Patient extends BaseEntity{
    private String name;
    private String EGN;
    private LocalDate insurancePaymentDate;
//    @ManyToOne
//    @JoinColumn(name = "practitioner_id")
}
