package com.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static com.test.constants.SchemaNames.TABLE_EMPLOYERS;

@Entity
@Table(name = TABLE_EMPLOYERS)
public class Employer {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME", nullable = false)
    private String name;

    public Employer() {
    }

    public Employer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getURLString() {
        return "https://hh.ru/employer/" + id;
    }

    @Override
    public String toString() {
        return name;
    }
}