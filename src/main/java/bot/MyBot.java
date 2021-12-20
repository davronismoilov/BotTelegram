package bot;

import country.CountryService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import weather.Weather;
import weather.WeatherService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;


public class MyBot extends TelegramLongPollingBot implements TelegramBotUtils {
    static String chatId;
    static ChatObjectService chatObjectService = new ChatObjectService();

    public MyBot() {
//        ArrayList<ChatObject> userList = ChatObjectService.getList();
//        if (userList != null)
//            for (int i = 0; i < userList.size(); i = i + 5) {
//
//                int finalI = i;
//                Thread thread = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        sendNotification(finalI);
//                    }
//                });
//                thread.start();
//            }
        chatId = "314452076";
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("XCGVHJBKGCsdgvjfvdgsjgfuew");
        sendMessage.setChatId(chatId);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message msg = update.hasMessage() ? update.getMessage() : update.getCallbackQuery().getMessage();
        this.chatId = msg.getChatId().toString();
        System.out.println(chatId);

        ChatObject chatObject = ChatObjectService.getModel(chatId);
        System.out.println(msg.getChat().getUserName());
        if (chatObject == null) {

            chatObjectService.add(new ChatObject(chatId, null, msg.getChat().getUserName(), 0, 0));
            chatObject = chatObjectService.getModel(chatId);
        } else {
            chatObject = chatObjectService.getModel(chatId);
        }


        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            this.chatId = update.getMessage().getChatId().toString();


            if (message_text.equals("/start")) {
                try {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setText("Kerakli bo'limni tanglang yoki Shunchaki shaharni kiriting : ");
                    sendMessage.setChatId(chatId);
                    sendMessage.setReplyMarkup(mainManu());
                    execute(sendMessage);
                } catch (Exception e) {

                }

            } else if (message_text.equals("MAMLAKATLAR")) {
                try {


                    sendMessageCountry(true, 0);
                    chatObject.setState(State.country);
                    ChatObjectService.update(chatObject);
                    System.out.println(chatObject.getState());
                } catch (Exception e) {

                }
            } else if (isNumeric(message_text) && chatObject.getState() == State.country) {
                System.out.println("city");
                try {
                    sendMessageCountry(false, Integer.valueOf(message_text) - 1);
                    chatObject.setState(State.city);
                    chatObject.setCountryId(Integer.valueOf(message_text));
                    ChatObjectService.update(chatObject);
                } catch (Exception e) {

                }
            } else if (isNumeric(message_text) && chatObject.getState() == (State.city)) {
                System.out.println("obhavo");
                chatObject.setState(State.country);
                chatObject.setCityId(Integer.valueOf(message_text));
                ChatObjectService.update(chatObject);
                try {
                    String cityName = CountryService.getCityName(chatObject.getCountryId(), chatObject.getCityId());
                    if (cityName.indexOf(" Viloyati") != -1) {
                        cityName = cityName.replace(" Viloyati", "");
                    }
                    Weather w = WeatherService.getWeather(cityName);
                    System.out.println(w);
                    SendMessage sendMessage = new SendMessage();
                    String res = "Sana : " + date() +
                            "\n \nShahar  : " + w.getName() + "  ✅" +
                            "\n \nNamlik : " + w.getMain().getHumidity() + "💦" +
                            "\n \n Havo harorati : " + (int) (w.getMain().getTemp() - 273) + " C° 🌡" +
                            "";
                    sendMessage.setText(res);
                    sendMessage.setChatId(chatId);
                    execute(sendMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                SendMessage sendMessage = new SendMessage();
                try {
                    Weather w = WeatherService.getWeather(message_text);
                    System.out.println(w);
                    String res = "Sana : " + date() +
                            "\n \nShahar  : " + w.getName() + "  ✅" +
                            "\n \nNamlik : " + w.getMain().getHumidity() + "💦" +
                            "\n \n Havo harorati : " + (int) (w.getMain().getTemp() - 273) + "  C° 🌡" +
                            "";
                    sendMessage.setText(res);
                } catch (Exception e) {
                    sendMessage.setText("❌ Shahar kiritishda xatolik bo'ldi  ");
                }

                sendMessage.setChatId(chatId);
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {

                }

            }


        } else if (update.hasCallbackQuery()) {
        }
    }


    @Override
    public String getBotUsername() {

        return TelegramBotUtils.USER_NAME;
    }

    @Override
    public String getBotToken() {

        return TelegramBotUtils.BOT_TOKEN;
    }

    void sendMessageCountry(Boolean isCountry, int index) throws Exception {
        SendMessage sendMessage = new SendMessage();
        if (isCountry) {
            String res = CountryService.getCountiries();
            sendMessage.setText(res);
        } else {
            String res = CountryService.getCity(index);
            sendMessage.setText(res);
        }
        sendMessage.setChatId(chatId);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {

        }

    }


    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private String date() {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter time1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return time.format(time1);
    }


    static public ReplyKeyboardMarkup mainManu() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRowList = new ArrayList<KeyboardRow>();
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        replyKeyboardMarkup.setResizeKeyboard(true);

        KeyboardRow mark = new KeyboardRow();
        mark.add("MAMLAKATLAR");
        keyboardRowList.add(mark);


        return replyKeyboardMarkup;
    }


    private void sendNotification(int index) {
        ArrayList<ChatObject> userList = ChatObjectService.getList();
        System.out.println(userList);

        for (int i = index; i < index + 5; i++) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(userList.get(i).getChatId());
            Weather w = null;
            try {
                w = WeatherService.getWeather("Toshkent");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(w);

            String res = "Sana : " + date() +
                    "\n \nShahar  : " + w.getName() + "  ✅" +
                    "\n \nNamlik : " + w.getMain().getHumidity() + "💦" +
                    "\n \n Havo harorati : " + (int) (w.getMain().getTemp() - 273) + " C° 🌡" +
                    "";
            sendMessage.setText(res);

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

}
