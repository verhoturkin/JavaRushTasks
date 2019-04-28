package com.javarush.task.task28.task2810.model;

import org.jsoup.Jsoup;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ru/search/vacancy?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        try {
            Document document = Jsoup.connect(String.format(Locale.ENGLISH, URL_FORMAT, "Киев", 0)).get();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


}
