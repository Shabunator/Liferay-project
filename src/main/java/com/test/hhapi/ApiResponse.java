package com.test.hhapi;

import com.test.entity.Vacancy;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {
    @SerializedName("items")
    private List<Vacancy> vacancyList;

    @SerializedName("found")
    private int vacanciesFound;

    @SerializedName("pages")
    private int pagesFound;

    public List<Vacancy> getVacancyList() {
        return vacancyList;
    }

    public int getCountOfVacancies() {
        return vacanciesFound;
    }

    public int getPagesFound() {
        return pagesFound;
    }

    public void setVacancyList(List<Vacancy> vacancyList) {
        this.vacancyList = vacancyList;
    }

    public void setVacanciesFound(int vacanciesFound) {
        this.vacanciesFound = vacanciesFound;
    }

    public void setPagesFound(int pagesFound) {
        this.pagesFound = pagesFound;
    }
}
