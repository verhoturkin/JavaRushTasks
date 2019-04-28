package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlView implements View {
    private final String filePath = "./4.JavaCollections/src/" + this.getClass().getPackage().getName().replace('.', '/') + "/vacancies.html";
    Controller controller;

    @Override
    public void update(List<Vacancy> vacancies) {
        updateFile(getUpdatedFileContent(vacancies));
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        Document doc = null;
        try {
            doc = getDocument();

            Element template = doc.getElementsByClass("template").first();
            Element copy = template.clone();

            copy.removeClass("template").removeAttr("style");
            doc.select("tr[class=vacancy]").remove().not("tr[class=vacancy template]");

            for (Vacancy vacancy : vacancies) {
                Element element = copy.clone();
                element.getElementsByClass("city").first().text(vacancy.getCity());
                element.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
                element.getElementsByClass("salary").first().text(vacancy.getSalary());
                element.getElementsByTag("a").first().text(vacancy.getTitle()).attr("href", vacancy.getUrl());

                template.before(element.outerHtml());

            }

        } catch (IOException e) {
            e.printStackTrace();
            return "Some exception occurred";
        }

        return doc.html();
    }

    private void updateFile(String content) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }
}
