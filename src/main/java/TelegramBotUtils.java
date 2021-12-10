public interface TelegramBotUtils {

    String USER_NAME = "http://t.me/hvsjgdjsqbot";
    String BOT_TOKEN = "5041226220:AAGv_zv_tBteOOL_3TI9lQPjLNh0z5Y_T3w";

    default boolean isStart(String text) {
        return text.equals("/start");


    }

    default boolean isBooks(String  text){
        return text.equals("Java");
    }

}
