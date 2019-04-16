package com.javarush.task.task39.task3913;

/*
Парсер логов (1)
Сегодня мы напишем парсер логов.

Лог файл имеет следующий формат:
ip username date event status

Где:
ip - ip адрес с которого пользователь произвел событие.
user - имя пользователя (одно или несколько слов разделенные пробелами).
date - дата события в формате day.month.year hour:minute:second.
event - одно из событий:
LOGIN - пользователь залогинился,
DOWNLOAD_PLUGIN - пользователь скачал плагин,
WRITE_MESSAGE - пользователь отправил сообщение,
SOLVE_TASK - пользователь попытался решить задачу,
DONE_TASK - пользователь решил задачу.
Для событий SOLVE_TASK и DONE_TASK существует дополнительный параметр,
который указывается через пробел, это номер задачи.
status - статус:
OK - событие выполнилось успешно,
FAILED - событие не выполнилось,
ERROR - произошла ошибка.

Пример строки из лог файла:
"146.34.15.5 Eduard Petrovich Morozko 05.01.2021 20:22:55 DONE_TASK 48 FAILED".
Записи внутри лог файла не обязательно упорядочены по дате, события могли произойти и быть записаны в лог в разной последовательности.

Класс, который будет отвечать за парсинг логов называется LogParser.
1.1. Добавь в класс LogParser конструктор с параметром Path logDir, где logDir - директория с логами (логов может быть несколько, все они должны иметь расширение log).
1.2. Реализуй интерфейс IPQuery у класса LogParser:
1.2.1. Метод getNumberOfUniqueIPs(Date after, Date before) должен возвращать количество уникальных IP адресов за выбранный период. Здесь и далее, если в методе есть параметры Date after и Date before, то нужно возвратить данные касающиеся только данного периода (включая даты after и before).
Если параметр after равен null, то нужно обработать все записи, у которых дата меньше или равна before.
Если параметр before равен null, то нужно обработать все записи, у которых дата больше или равна after.
Если и after, и before равны null, то нужно обработать абсолютно все записи (без фильтрации по дате).
1.2.2. Метод getUniqueIPs() должен возвращать множество, содержащее все не повторяющиеся IP. Тип в котором будем хранить IP будет String.
1.2.3. Метод getIPsForUser() должен возвращать IP, с которых работал переданный пользователь.
1.2.4. Метод getIPsForEvent() должен возвращать IP, с которых было произведено переданное событие.
1.2.5. Метод getIPsForStatus() должен возвращать IP, события с которых закончилось переданным статусом.

Реализацию метода main() можешь менять по своему усмотрению.


Требования:
1. В классе LogParser должен быть создан конструктор public LogParser(Path logDir).
2. Класс LogParser должен поддерживать интерфейс IPQuery.
3. Метод getNumberOfUniqueIPs(Date, Date) должен возвращать количество уникальных IP адресов за выбранный период.
4. Метод getUniqueIPs(Date, Date) класса LogParser должен возвращать множество, содержащее все не повторяющиеся IP адреса за выбранный период.
5. Метод getIPsForUser(String, Date, Date) класса LogParser должен возвращать IP адреса, с которых работал переданный пользователь за выбранный период.
6. Метод getIPsForEvent(Event, Date, Date) класса LogParser должен возвращать IP адреса, с которых было произведено переданное событие за выбранный период.
7. Метод getIPsForStatus(Status, Date, Date) класса LogParser должен возвращать IP адреса, события с которых закончилось переданным статусом за выбранный период.

Парсер логов (2)
Реализуй интерфейс UserQuery у класса LogParser:
2.1. Метод getAllUsers() должен возвращать всех пользователей.
2.2. Метод getNumberOfUsers() должен возвращать количество уникальных пользователей.
2.3. Метод getNumberOfUserEvents() должен возвращать количество событий от определенного пользователя.
2.4. Метод getUsersForIP() должен возвращать пользователей с определенным IP.
Несколько пользователей могут использовать один и тот же IP.
2.5. Метод getLoggedUsers() должен возвращать пользователей, которые были залогинены.
2.6. Метод getDownloadedPluginUsers() должен возвращать пользователей, которые скачали плагин.
2.7. Метод getWroteMessageUsers() должен возвращать пользователей, которые отправили сообщение.
2.8. Метод getSolvedTaskUsers(Date after, Date before) должен возвращать пользователей, которые решали любую задачу.
2.9. Метод getSolvedTaskUsers(Date after, Date before, int task) должен возвращать пользователей, которые решали задачу с номером task.
2.10. Метод getDoneTaskUsers(Date after, Date before) должен возвращать пользователей, которые решали любую задачу.
2.11. Метод getDoneTaskUsers(Date after, Date before, int task) должен возвращать пользователей, которые решали задачу с номером task.


Требования:
1. Класс LogParser должен поддерживать интерфейс UserQuery.
2. Метод getAllUsers() должен возвращать множество содержащее всех пользователей.
3. Метод getNumberOfUsers(Date, Date) должен возвращать количество уникальных пользователей за выбранный период.
4. Метод getNumberOfUserEvents(String, Date, Date) должен возвращать количество событий от переданного пользователя за выбранный период.
5. Метод getUsersForIP(String, Date, Date) должен возвращать множество содержащее пользователей, которые работали с переданного IP адреса за выбранный период.
6. Метод getLoggedUsers(Date, Date) должен возвращать множество содержащее пользователей, которые были залогинены за выбранный период.
7. Метод getDownloadedPluginUsers(Date, Date) должен возвращать множество содержащее пользователей, которые скачали плагин за выбранный период.
8. Метод getWroteMessageUsers(Date, Date) должен возвращать множество содержащее пользователей, которые отправили сообщение за выбранный период.
9. Метод getSolvedTaskUsers(Date, Date) должен возвращать множество содержащее пользователей, которые решали любую задачу за выбранный период.
10. Метод getSolvedTaskUsers(Date, Date, int task) должен возвращать множество содержащее пользователей, которые решали задачу с номером task за выбранный период.
11. Метод getDoneTaskUsers(Date, Date) должен возвращать множество содержащее пользователей, которые решили любую задачу за выбранный период.
12. Метод getDoneTaskUsers(Date, Date, int task) должен возвращать множество содержащее пользователей, которые решили задачу с номером task за выбранный период.
*/

import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("C:\\test\\logs\\"));
//        logParser.parseDir();
        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
        System.out.println(logParser.getUniqueIPs(null, new Date()));
        System.out.println(logParser.getIPsForUser("Amigo", null, new Date()));
        System.out.println(logParser.getIPsForEvent(Event.LOGIN, null, new Date()));
        System.out.println(logParser.getIPsForStatus(Status.FAILED, null, new Date()));
        System.out.println(logParser.getAllUsers());
        System.out.println(logParser.getNumberOfUsers(null, new Date()));
        System.out.println(logParser.getNumberOfUserEvents("Amigo", null, new Date()));

    }
}