import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class MyBot extends TelegramLongPollingBot implements TelegramBotUtils {
    private String chat_id;
    private String message;

    @Override
    public String getBotUsername() {
        return TelegramBotUtils.USER_NAME;
    }

    @Override
    public String getBotToken() {
        return TelegramBotUtils.BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        this.chat_id = update.getMessage().getChatId().toString();
        if (update.hasMessage()) {
            String text = update.getMessage().getText();
            if (isStart(text)) {
                this.message = "salom ";
                execute(true);
            } else if (isBooks(text)) {
                this.message = "  🧐  Kerakli bo'limni tanlang  🥲 !!";
                execute(false);
            }
            else  if (update.hasCallbackQuery()){

                this.chat_id = update.getCallbackQuery().getMessage().getChatId().toString();
                String data = update.getCallbackQuery().getData();

                if(data.equals("Java Core")){
                }

            }

        }
    }

    private void execute(boolean isMenu) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(this.chat_id);
        sendMessage.setText(this.message);
        //sendMessage.setReplyMarkup(languages());
        sendMessage.setReplyMarkup(isMenu ? languages() : getButton());
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }

    private ReplyKeyboardMarkup languages() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setResizeKeyboard(true);

        KeyboardRow keyboardRow0 = new KeyboardRow();
        keyboardRow0.add("Dasturlash tillari ");
        keyboardRows.add(keyboardRow0);


        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("Java");
        keyboardRow.add("🙃C++");

        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add("🥳Python");
        keyboardRow1.add("😥C#");

        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add("Rust");
        keyboardRow2.add("Java script");


        keyboardRows.add(keyboardRow);
        keyboardRows.add(keyboardRow1);
        keyboardRows.add(keyboardRow2);


        return replyKeyboardMarkup;
    }

    private InlineKeyboardMarkup getButton() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> list = new ArrayList<>();
        inlineKeyboardMarkup.setKeyboard(list);

        List<InlineKeyboardButton> inlines = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText("Java Core");
        inlineKeyboardButton.setCallbackData(String.valueOf(1));
        inlines.add(inlineKeyboardButton);


        inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText((2) + ".Java OOP");
        inlineKeyboardButton.setCallbackData(String.valueOf(2));
        inlines.add(inlineKeyboardButton);


        inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText((3) + ".Java \nAdvanced");
        inlineKeyboardButton.setCallbackData(String.valueOf(3));
        inlines.add(inlineKeyboardButton);

        list.add(inlines);

        inlineKeyboardMarkup.setKeyboard(list);

        return inlineKeyboardMarkup;
    }
}
