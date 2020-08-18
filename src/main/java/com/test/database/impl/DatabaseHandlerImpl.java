package com.test.database.impl;

import com.test.database.DatabaseHandler;
import com.test.entity.Employer;
import com.test.entity.EmployerDAO;
import com.test.entity.Vacancy;
import com.test.entity.VacancyDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.service.ServiceRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseHandlerImpl implements DatabaseHandler {
    private SessionFactory sessionFactory;

    public DatabaseHandlerImpl() {
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = createSessionFactory(configuration);
    }

    private SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    /*-----------------------------------------------------------------------------------------------------*/

    @Override
    public void insertVacancy(Vacancy vacancy) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            VacancyDAO dao = new VacancyDAO(session);
            dao.insert(vacancy);
            transaction.commit();
            session.close();
        }
        catch (ConstraintViolationException e) {
            throw new DBException(e);
        }
        catch (HibernateException e) {
            throw new DBException(e);
        }

    }

    @Override
    public void saveVacancies(List<Vacancy> vacancyList) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            VacancyDAO vacancyDAO = new VacancyDAO(session);
            EmployerDAO employerDAO = new EmployerDAO(session);
            Transaction transaction = session.beginTransaction();

            for (Vacancy vacancy : vacancyList) {
                employerDAO.insert(vacancy.getEmployer());
                vacancyDAO.insert(vacancy);
                if (vacancy.getSalary() != null) {
                    session.save(vacancy.getSalary());
                }
            }

            transaction.commit();
            session.close();
        }
        catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    @Override
    public List<Vacancy> getVacancies() throws DBException {
        Map<Integer, Employer> employers = new HashMap<>();
        List<Vacancy> vacancies = new ArrayList<>();
        return vacancies;
    }
}
