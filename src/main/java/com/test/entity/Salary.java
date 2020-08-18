package com.test.entity;

import javax.persistence.*;

import static com.test.constants.SchemaNames.TABLE_SALARY;

@Entity
@Table(name = TABLE_SALARY)
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "MIN")
    private Integer from;

    @Column(name = "MAX")
    private Integer to;

    @Column(name = "CURRENCY")
    private String currency;


    @Override
    public String toString() {
        String salaryString;
        if (to != null) {
            salaryString = "От " + from + " до " + to + " " + currency;
        } else {
            salaryString = "От " + from + " " + currency;
        }
        return salaryString;
    }
}