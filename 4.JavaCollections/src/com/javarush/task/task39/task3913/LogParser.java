package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private Path logDir;
    private ArrayList<Record> records = new ArrayList<>();
    private boolean dirIsParsed = false;

    public LogParser(Path logDir) {
        this.logDir = logDir;
    }

    private boolean isDateInRange(Date date, Date after, Date before) {
        return ((after == null || date.after(after)) && (before == null || date.before(before)));
    }

    public int getNumberOfUniqueIPs(Date after, Date before) {

        return getUniqueIPs(after, before).size();
    }

    public Set<Object> execute(String query) {
        parseDir();

        switch (query) {
            case "get ip":
                return records.stream()
                        .map(Record::getIp)
                        .collect(Collectors.toSet());
            case "get user":
                return records.stream()
                        .map(Record::getName)
                        .collect(Collectors.toSet());
            case "get date":
                return records.stream()
                        .map(Record::getDate)
                        .collect(Collectors.toSet());
            case "get event":
                return records.stream()
                        .map(Record::getEvent)
                        .collect(Collectors.toSet());
            case "get status":
                return records.stream()
                        .map(Record::getStatus)
                        .collect(Collectors.toSet());
        }
        return null;
    }

    public Set<String> getUniqueIPs(Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before))
                .map(Record::getIp)
                .collect(Collectors.toSet());
    }

    public Set<String> getIPsForUser(String user, Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && Objects.equals(record.name, user))
                .map(Record::getIp)
                .collect(Collectors.toSet());
    }

    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && record.event == event)
                .map(Record::getIp)
                .collect(Collectors.toSet());
    }

    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && record.status == status)
                .map(Record::getIp)
                .collect(Collectors.toSet());
    }


    public Set<String> getAllUsers() {
        parseDir();

        return records.stream()
                .map(Record::getName)
                .collect(Collectors.toSet());
    }

    public int getNumberOfUsers(Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before))
                .map(Record::getName)
                .collect(Collectors.toSet())
                .size();
    }

    public int getNumberOfUserEvents(String user, Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && Objects.equals(record.name, user))
                .map(Record::getEvent)
                .collect(Collectors.toSet())
                .size();
    }

    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && Objects.equals(record.ip, ip))
                .map(Record::getName)
                .collect(Collectors.toSet());
    }

    public Set<String> getLoggedUsers(Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && record.event == Event.LOGIN)
                .map(Record::getName)
                .collect(Collectors.toSet());
    }

    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && record.event == Event.DOWNLOAD_PLUGIN)
                .map(Record::getName)
                .collect(Collectors.toSet());
    }

    public Set<String> getWroteMessageUsers(Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && record.event == Event.WRITE_MESSAGE)
                .map(Record::getName)
                .collect(Collectors.toSet());
    }

    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && record.event == Event.SOLVE_TASK)
                .map(Record::getName)
                .collect(Collectors.toSet());
    }

    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && record.event == Event.SOLVE_TASK && record.task == task)
                .map(Record::getName)
                .collect(Collectors.toSet());
    }

    public Set<String> getDoneTaskUsers(Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && record.event == Event.DONE_TASK)
                .map(Record::getName)
                .collect(Collectors.toSet());
    }

    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && record.event == Event.DONE_TASK && record.task == task)
                .map(Record::getName)
                .collect(Collectors.toSet());
    }

    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && Objects.equals(record.name, user) && record.event == event)
                .map(Record::getDate)
                .collect(Collectors.toSet());
    }

    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && record.status == Status.FAILED)
                .map(Record::getDate)
                .collect(Collectors.toSet());
    }

    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && record.status == Status.ERROR)
                .map(Record::getDate)
                .collect(Collectors.toSet());
    }

    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && Objects.equals(record.name, user) && record.event == Event.LOGIN)
                .map(Record::getDate)
                .sorted()
                .findFirst()
                .orElse(null);
    }

    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && Objects.equals(record.name, user) && record.event == Event.SOLVE_TASK && record.task == task)
                .map(Record::getDate)
                .sorted()
                .findFirst()
                .orElse(null);
    }

    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && Objects.equals(record.name, user) && record.event == Event.DONE_TASK && record.task == task)
                .map(Record::getDate)
                .sorted()
                .findFirst()
                .orElse(null);
    }

    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && Objects.equals(record.name, user) && record.event == Event.WRITE_MESSAGE)
                .map(Record::getDate)
                .collect(Collectors.toSet());
    }

    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && Objects.equals(record.name, user) && record.event == Event.DOWNLOAD_PLUGIN)
                .map(Record::getDate)
                .collect(Collectors.toSet());
    }

    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    public Set<Event> getAllEvents(Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before))
                .map(Record::getEvent)
                .collect(Collectors.toSet());
    }

    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && Objects.equals(record.ip, ip))
                .map(Record::getEvent)
                .collect(Collectors.toSet());
    }

    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && Objects.equals(record.name, user))
                .map(Record::getEvent)
                .collect(Collectors.toSet());
    }

    public Set<Event> getFailedEvents(Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && record.status == Status.FAILED)
                .map(Record::getEvent)
                .collect(Collectors.toSet());
    }

    public Set<Event> getErrorEvents(Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && record.status == Status.ERROR)
                .map(Record::getEvent)
                .collect(Collectors.toSet());
    }

    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && record.event == Event.SOLVE_TASK && record.task == task)
                .map(Record::getEvent)
                .map(event -> 1)
                .reduce(0, Integer::sum);
    }

    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && record.event == Event.DONE_TASK && record.task == task)
                .map(Record::getEvent)
                .map(event -> 1)
                .reduce(0, Integer::sum);
    }

    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && record.event == Event.SOLVE_TASK)
                .map(Record::getTask)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.reducing(0, e -> 1, Integer::sum)));
    }

    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && record.event == Event.DONE_TASK)
                .map(Record::getTask)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.reducing(0, e -> 1, Integer::sum)));
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
                Files.list(logDir)
                        .filter(path -> path.toString().endsWith(".log"))
                        .forEach(this::parseLog);
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

        public String getIp() {
            return ip;
        }

        public String getName() {
            return name;
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
    }


}