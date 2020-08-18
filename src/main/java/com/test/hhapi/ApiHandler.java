package com.test.hhapi;

import com.test.entity.Employer;
import com.test.entity.Vacancy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApiHandler {
    private static final String JSON_REQUEST_URL = "https://api.hh.ru/vacancies?area=4&specialization=1&per_page=100";

    private GsonBuilder gsb = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    private Gson gson = gsb.create();

    private ApiResponse getResponse(String url) throws IOException {
        ApiResponse response = null;
        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                BufferedReader bufferedJsonRequest = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                response = gson.fromJson(bufferedJsonRequest, ApiResponse.class);
            }
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return response;
    }

    public List<Vacancy> getVacancies() {
        List<Vacancy> vacancyList = null;
        try {
            // Парсим запрос в объект
            ApiResponse response = getResponse(JSON_REQUEST_URL);

            // Если есть вакансии в запросе, начинаем заполнять коллекцию
            if (response != null && response.getCountOfVacancies() > 0) {
                vacancyList = new ArrayList<>();

                //Карта с айдишниками, чтобы избежать повторения одинаковых employers
                HashMap<Integer, Employer> employersMap = new HashMap<>();

                // Парсим запросы и сшиваем все в один список (де-пагинация)
                for (int i = 1; i <= response.getPagesFound(); i++) {
                    List<Vacancy> listFromResp = response.getVacancyList();

                    //Оптимизация списка (удаление повторяющихся employers)
                    for (Vacancy vacancy : listFromResp) {
                        Employer employer = vacancy.getEmployer();
                        int employerId = employer.getID();
                        if (!employersMap.containsKey(employerId)) {
                            employersMap.put(employerId, employer);
                        } else {
                            vacancy.setEmployer(employersMap.get(employerId));
                        }
                        vacancyList.add(vacancy);
                    }
//                    response = getResponse(JSON_REQUEST_URL + "&page=" + i);
                }
            }
        }
        catch (IOException e) {
            vacancyList = null;
        }
        return vacancyList;
    }
}