package com.test.database;

import com.test.database.impl.DBException;
import com.test.entity.Vacancy;

import java.util.List;

public interface DatabaseHandler {

    void insertVacancy(Vacancy vacancy) throws DBException;
    void saveVacancies(List<Vacancy> vacancyList) throws DBException;

    List<Vacancy> getVacancies() throws DBException;

}
