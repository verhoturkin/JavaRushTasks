package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BotClient extends Client {

    public static void main(String[] args) {
        Client bot = new BotClient();
        bot.run();
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return ("date_bot_" + (int) (Math.random() * 100));
    }

    public class BotSocketThread extends Client.SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
//            if(message.matches("^\\w+(: ).+")) {
            if (message.contains(": ")) {
                String name = message.substring(0, message.indexOf(": "));
                String text = message.substring(message.indexOf(": ") + 2);
                String format = null;

                switch (text) {
                    case ("дата"):
                        format = "d.MM.YYYY";
                        break;
                    case ("день"):
                        format = "d";
                        break;
                    case ("месяц"):
                        format = "MMMM";
                        break;
                    case ("год"):
                        format = "YYYY";
                        break;
                    case ("время"):
                        format = "H:mm:ss";
                        break;
                    case ("час"):
                        format = "H";
                        break;
                    case ("минуты"):
                        format = "m";
                        break;
                    case ("секунды"):
                        format = "s";
                        break;
                }
                if (format != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat(format);
                    Date now = Calendar.getInstance().getTime();
                    sendTextMessage("Информация для " + name + ": " + sdf.format(now));
                }

            }
        }
    }
}
