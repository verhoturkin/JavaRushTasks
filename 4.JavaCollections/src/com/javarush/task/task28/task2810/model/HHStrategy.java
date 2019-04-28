package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancies = new ArrayList<>();
        int page = 0;
        while (true) {
            try {
                Document doc = getDocument(searchString, page);
                Elements elements = doc.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
                if(elements.isEmpty())
                    break;

                for (Element element : elements) {
                    Vacancy vacancy = new Vacancy();

                    Element title = element.getElementsByAttributeValue("data-qa","vacancy-serp__vacancy-title").first();
                    vacancy.setTitle(title.text());

                    Element salary = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").first();
                    vacancy.setSalary(salary!=null?salary.text():"");

                    Element city = element.getElementsByAttributeValue("data-qa","vacancy-serp__vacancy-address").first();
                    vacancy.setCity(city.text());

                    Element companyName = element.getElementsByAttributeValue("data-qa","vacancy-serp__vacancy-employer").first();
                    vacancy.setCompanyName(companyName!=null?companyName.text():"");

                    vacancy.setUrl(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").attr("href"));

                    vacancy.setSiteName("HeadHunter");

                    vacancies.add(vacancy);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            page++;
        }
        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        Document document = Jsoup
                .connect(String.format(URL_FORMAT, searchString, page))
//                .connect("http://javarush.ru/testdata/big28data.html")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36 OPR/58.0.3135.132")
                .referrer("no-referrer-when-downgrade")
                .get();

        return document;
    }


}
