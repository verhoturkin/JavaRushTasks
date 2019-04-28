package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements Strategy {
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?page=%d&q=java+%s";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancies = new ArrayList<>();
        int page = 1;
        while (true) {
            try {
                Document doc = getDocument(searchString, page);
                Elements elements = doc.getElementsByClass("job");
                if (elements.isEmpty())
                    break;

                for (Element element : elements) {
                    Vacancy vacancy = new Vacancy();

                    Element title = element.getElementsByClass("title").first();
                    vacancy.setTitle(title.text());

                    Element salary = element.getElementsByClass("salary").first();
                    vacancy.setSalary(salary != null ? salary.text() : "");

                    Element city = element.getElementsByClass("location").first();
                    vacancy.setCity(city != null ? city.text() : "");

                    Element companyName = element.getElementsByClass("company_name").first().getElementsByTag("a").first();
                    vacancy.setCompanyName(companyName != null ? companyName.text() : "");

                    vacancy.setUrl("https://moikrug.ru" + element.getElementsByClass("title").first().getElementsByTag("a").attr("href"));

                    vacancy.setSiteName("moikrug.ru");

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
                .connect(String.format(URL_FORMAT, page, searchString))
//                .connect("http://javarush.ru/testdata/big28data.html")
//                .connect("http://javarush.ru/testdata/big28data2.html")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36 OPR/58.0.3135.132")
                .referrer("no-referrer-when-downgrade")
                .get();
        document.html();

        return document;
    }
}
