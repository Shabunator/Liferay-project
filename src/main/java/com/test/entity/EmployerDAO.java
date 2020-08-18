package com.test.entity;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public class EmployerDAO {
    private Session session;

    public EmployerDAO(Session session) {
        this.session = session;
    }

    public void insert(Employer employer) {
        session.save(employer);
    }

    public Employer get(int id) throws HibernateException {
        return (Employer) session.get(Employer.class, id);
    }
}
