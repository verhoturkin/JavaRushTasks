package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public Set<Object> execute(String query) {
        parseDir();

        Query q = Query.parseQuery(query);

        if (q.field1 != null) {
            try {
                Method method = Record.class.getMethod("get" + q.field1.substring(0, 1).toUpperCase() + q.field1.substring(1));
                Method method2;
                if (q.field2 != null)
                    method2 = Record.class.getMethod("get" + q.field2.substring(0, 1).toUpperCase() + q.field2.substring(1));
                else method2 = null;

                Set<Object> set = new HashSet<>();
                for (Record record : records) {
                    if (isDateInRange(record.date, q.date1, q.date2) && method2 != null && Objects.equals(method2.invoke(record), q.value1) || method2 == null) {
                        Object invoke = method.invoke(record);
                        set.add(invoke);
                    }
                }
                return set;
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public int getNumberOfUniqueIPs(Date after, Date before) {

        return getUniqueIPs(after, before).size();
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
                .filter(record -> isDateInRange(record.date, after, before) && Objects.equals(record.user, user))
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
                .map(Record::getUser)
                .collect(Collectors.toSet());
    }

    public int getNumberOfUsers(Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before))
                .map(Record::getUser)
                .collect(Collectors.toSet())
                .size();
    }

    public int getNumberOfUserEvents(String user, Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && Objects.equals(record.user, user))
                .map(Record::getEvent)
                .collect(Collectors.toSet())
                .size();
    }

    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && Objects.equals(record.ip, ip))
                .map(Record::getUser)
                .collect(Collectors.toSet());
    }

    public Set<String> getLoggedUsers(Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && record.event == Event.LOGIN)
                .map(Record::getUser)
                .collect(Collectors.toSet());
    }

    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && record.event == Event.DOWNLOAD_PLUGIN)
                .map(Record::getUser)
                .collect(Collectors.toSet());
    }

    public Set<String> getWroteMessageUsers(Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && record.event == Event.WRITE_MESSAGE)
                .map(Record::getUser)
                .collect(Collectors.toSet());
    }

    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && record.event == Event.SOLVE_TASK)
                .map(Record::getUser)
                .collect(Collectors.toSet());
    }

    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && record.event == Event.SOLVE_TASK && record.task == task)
                .map(Record::getUser)
                .collect(Collectors.toSet());
    }

    public Set<String> getDoneTaskUsers(Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && record.event == Event.DONE_TASK)
                .map(Record::getUser)
                .collect(Collectors.toSet());
    }

    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && record.event == Event.DONE_TASK && record.task == task)
                .map(Record::getUser)
                .collect(Collectors.toSet());
    }

    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && Objects.equals(record.user, user) && record.event == event)
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
                .filter(record -> isDateInRange(record.date, after, before) && Objects.equals(record.user, user) && record.event == Event.LOGIN)
                .map(Record::getDate)
                .sorted()
                .findFirst()
                .orElse(null);
    }

    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && Objects.equals(record.user, user) && record.event == Event.SOLVE_TASK && record.task == task)
                .map(Record::getDate)
                .sorted()
                .findFirst()
                .orElse(null);
    }

    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && Objects.equals(record.user, user) && record.event == Event.DONE_TASK && record.task == task)
                .map(Record::getDate)
                .sorted()
                .findFirst()
                .orElse(null);
    }

    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && Objects.equals(record.user, user) && record.event == Event.WRITE_MESSAGE)
                .map(Record::getDate)
                .collect(Collectors.toSet());
    }

    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        parseDir();

        return records.stream()
                .filter(record -> isDateInRange(record.date, after, before) && Objects.equals(record.user, user) && record.event == Event.DOWNLOAD_PLUGIN)
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
                .filter(record -> isDateInRange(record.date, after, before) && Objects.equals(record.user, user))
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


    private void parseLog(Path log) {
        try (BufferedReader reader = Files.newBufferedReader(log)) {
            while (reader.ready()) {
                records.add(Record.parseRecord(reader.readLine()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void parseDir() {
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


}