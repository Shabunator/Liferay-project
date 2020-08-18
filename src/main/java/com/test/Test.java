package com.test;

import com.test.database.impl.DBException;
import com.test.database.DatabaseHandler;
import com.test.database.impl.DatabaseHandlerImpl;
import com.test.entity.Employer;
import com.test.entity.Vacancy;
import com.test.hhapi.ApiHandler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Test {
    public static void main(String[] args) {
        ApiHandler handler = new ApiHandler();
        List<Vacancy> vacancies = handler.getVacancies();

        try {
            DatabaseHandler dbHandler = new DatabaseHandlerImpl();
            dbHandler.saveVacancies(vacancies);
            printVacanciesInfo(vacancies);
            vacancies = dbHandler.getVacancies();
        }

        catch (DBException e) {
            e.printStackTrace();
        }
    }

    static void printVacanciesInfo(List<Vacancy> vacancies) {
        if (vacancies != null) {
            System.out.println("Найдено вакансий:" + vacancies.size());
            for (int i = 0; i < vacancies.size(); i++) {
                Vacancy vacancy = vacancies.get(i);
                System.out.printf("№%4d " + vacancy + "%n", (i + 1));
            }
        } else {
            System.out.println("Список вакансий пуст.");
        }
    }

    private static void printVacanciesInfoBriefly(List<Vacancy> vacancies) {
        if (vacancies != null) {
            System.out.println("Найдено вакансий:" + vacancies.size());
        } else {
            System.out.println("Список вакансий пуст.");
        }
    }

    private static void printEmployersInfoBriefly(List<Vacancy> vacancies) {
        if (vacancies != null) {
            Set<Integer> set = new HashSet<>();
            for (Vacancy vacancy : vacancies) {
                Employer empl = vacancy.getEmployer();
                set.add(empl.hashCode());
            }
            System.out.println("Найдено вакансий:" + vacancies.size());
            System.out.println("Работодателей: " + set.size());

        } else {
            System.out.println("Список вакансий пуст.");
        }
    }
}