package com.test.entity;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class VacancyDAO {
    private Session session;

    public VacancyDAO(Session session) {
        this.session = session;
    }

    public void insert(Vacancy vacancy) {
        session.save(vacancy);
    }

    public Vacancy get(int id) throws HibernateException {
        return (Vacancy) session.get(Vacancy.class, id);
    }
}
