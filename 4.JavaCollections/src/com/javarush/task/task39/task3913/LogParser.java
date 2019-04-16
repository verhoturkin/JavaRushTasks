package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.IPQuery;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery {
    private Path logDir;
    private ArrayList<Record> records = new ArrayList<>();
    private boolean dirIsParsed = false;

    public LogParser(Path logDir) {
        this.logDir = logDir;
    }


    public int getNumberOfUniqueIPs(Date after, Date before) {

        return getUniqueIPs(after, before).size();
    }

    public Set<String> getUniqueIPs(Date after, Date before) {
        parseDir();

        Set<String> ips = new HashSet<>();
        for (Record record : records) {
            if ((after == null || !record.date.before(after)) && (before == null || !record.date.after(before)))
                ips.add(record.ip);
        }
        return ips;
    }

    public Set<String> getIPsForUser(String user, Date after, Date before) {
        parseDir();

        Set<String> ips = new HashSet<>();
        for (Record record : records) {
            if ((after == null || !record.date.before(after))
                    && (before == null || !record.date.after(before))
                    && Objects.equals(record.name, user))
                ips.add(record.ip);
        }
        return ips;
    }

    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        parseDir();

        Set<String> ips = new HashSet<>();
        for (Record record : records) {
            if ((after == null || !record.date.before(after))
                    && (before == null || !record.date.after(before))
                    && record.event == event)

                ips.add(record.ip);
        }
        return ips;
    }

    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        parseDir();

        Set<String> ips = new HashSet<>();
        for (Record record : records) {
            if ((after == null || !record.date.before(after))
                    && (before == null || !record.date.after(before))
                    && record.status == status)

                ips.add(record.ip);
        }
        return ips;
    }

    private Record getRecord(String string) {
        Record record = new Record();
        String[] tmp = string.split("\t");
        record.ip = tmp[0];
        record.name = tmp[1];
        try {
            record.date = new SimpleDateFormat("dd.MM.yyyy H:m:s").parse(tmp[2]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String[] event = tmp[3].split("\\s");
        record.event = Event.valueOf(event[0]);
        if (record.event == Event.SOLVE_TASK || record.event == Event.DONE_TASK) {
            record.task = Integer.parseInt(event[1]);
        }
        record.status = Status.valueOf(tmp[4]);

        return record;
    }

    private void parseLog(Path log) {
        try (BufferedReader reader = Files.newBufferedReader(log)) {
            while (reader.ready()) {
                records.add(getRecord(reader.readLine()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void parseDir() {
        if (!dirIsParsed) {
            try {
                Files.list(logDir).filter(path -> path.toString().endsWith(".log")).forEach(this::parseLog);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        dirIsParsed = true;
    }

    private class Record {
        String ip;
        String name;
        Date date;
        Event event;
        int task;
        Status status;

    }


}