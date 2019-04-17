package com.javarush.task.task39.task3913;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Query {
    String field1;
    String field2;
    Object value1;
    Date date1;
    Date date2;


    public static Query parseQuery(String query) {
        String datePattern = "^get (?<field1>\\w+) for (?<field2>\\w+) = \"(?<value1>.*?)\" and date between \"(?<date1>.*?)\" and \"(?<date2>.*?)\"";
        String fullPattern = "^get (?<field1>\\w+) for (?<field2>\\w+) = \"(?<value1>.*?)\"";
        String shortPattern = "^get (?<field1>\\w+)";

        Query q = new Query();

        if (query.matches(datePattern)){
            q = parseDateQuery(query, datePattern);
        }else if (query.matches(fullPattern)) {
            q = parseFullQuery(query, fullPattern);
        } else if (query.matches(shortPattern)) {
            q = parseSimpleQuery(query, shortPattern);
        }

        return q;
    }

    private static Query parseDateQuery(String query, String pattern) {
        Query q = new Query();
        Matcher matcher = Pattern.compile(pattern).matcher(query);
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy H:m:s");

        if (matcher.find()) {
            q.field1 = matcher.group("field1");
            q.field2 = matcher.group("field2");
            try {
                if (q.field2.equals("date"))
                    q.value1 = df.parse(matcher.group("value1"));
                else if (q.field2.equals("event"))
                    q.value1 = Event.valueOf(matcher.group("value1"));
                else if (q.field2.equals("status"))
                    q.value1 = Status.valueOf(matcher.group("value1"));
                else q.value1 = matcher.group("value1");
                q.date1 = df.parse(matcher.group("date1"));
                q.date2 = df.parse(matcher.group("date2"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return q;
    }

    public static Query parseFullQuery(String query, String pattern) {
        Query q = new Query();
        Matcher matcher = Pattern.compile(pattern).matcher(query);
        if (matcher.find()) {
            q.field1 = matcher.group("field1");
            q.field2 = matcher.group("field2");
            try {
                if (q.field2.equals("date"))
                    q.value1 = new SimpleDateFormat("dd.MM.yyyy H:m:s").parse(matcher.group("value1"));
                else if (q.field2.equals("event"))
                    q.value1 = Event.valueOf(matcher.group("value1"));
                else if (q.field2.equals("status"))
                    q.value1 = Status.valueOf(matcher.group("value1"));
                else q.value1 = matcher.group("value1");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return q;
    }

    private static Query parseSimpleQuery(String query, String pattern) {
        Query q = new Query();
        Matcher matcher = Pattern.compile(pattern).matcher(query);
        if (matcher.find()) {
            q.field1 = matcher.group("field1");
        }
        return q;
    }


    public Date getDate1() {
        return date1;
    }

    public Date getDate2() {
        return date2;
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public Object getValue1() {
        return value1;
    }
}
