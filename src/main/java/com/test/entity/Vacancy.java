package com.test.entity;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.test.constants.SchemaNames.TABLE_VACANCIES;

@Entity
@Table(name = TABLE_VACANCIES)
public class Vacancy {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @SerializedName("published_at")
    @Column(name = "PUBLISHED", nullable = false)
    private Date published;


    @ManyToOne
    @JoinColumn(name = "EMPLOYER_ID", referencedColumnName = "ID", nullable = false)
    private Employer employer;

    @OneToOne
    @JoinColumn(name = "SALARY_ID", referencedColumnName = "ID")
    private Salary salary;


    public Vacancy() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }


    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public String getURLString() {
        return "https://hh.ru/vacancy/" + id;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
        StringBuilder shortVacancyInfo = new StringBuilder();

        shortVacancyInfo
                .append("; Название: ").append(name)
                .append("; Опубликована: ").append(format.format(getPublished()))
                .append("; Организация: ").append(employer)
                .append("; Оклад: ").append(salary);
        if (salary == null) {
            shortVacancyInfo.append(" оклад не указан");
        }

        return String.valueOf(shortVacancyInfo);
    }
}
