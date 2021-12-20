import bot.MyBot;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import country.Data;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Main {
    public static void main(String[] args)  {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new bot.MyBot());

        }catch (TelegramApiException e){
           e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}