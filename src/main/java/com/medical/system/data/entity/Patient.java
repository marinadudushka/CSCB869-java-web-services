package com.medical.system.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Where(clause = "deleted = 0")
@Table(name = "patient")

public class Patient extends BaseEntity{
    private String name;

    @Column(unique = true)
    private String egn;

    private LocalDate insurancePaymentDate;

    @ManyToOne
    @JoinColumn(name = "practitioner_id")
    private Practitioner practitioner;

    private int deleted = 0;
}
