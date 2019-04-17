package com.javarush.task.task39.task3913;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Record {
    String ip;
    String user;
    Date date;
    Event event;
    int task;
    Status status;

    public String getIp() {
        return ip;
    }

    public String getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }

    public Event getEvent() {
        return event;
    }

    public int getTask() {
        return task;
    }

    public Status getStatus() {
        return status;
    }

    public static Record parseRecord(String string) {
        Record record = new Record();
        String[] tmp = string.split("\t");
        record.ip = tmp[0];
        record.user = tmp[1];
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
}
