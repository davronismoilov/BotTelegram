package bot;


import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class CountryBot {
    static public InlineKeyboardMarkup getButtonList(int index) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();


        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
        for (int i = 0; i < index; i++) {
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText((i + 1) + "");
            inlineKeyboardButton.setCallbackData(String.valueOf(i));
            rowInline.add(inlineKeyboardButton);

            if ((i + 1) % 5 == 0) {
                rowsInline.add(rowInline);
                rowInline = new ArrayList<>();
            }
        }
        rowsInline.add(rowInline);

        rowInline = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(("⬅️"));
        inlineKeyboardButton.setCallbackData("prev");
        rowInline.add(inlineKeyboardButton);

        inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(("❌"));
        inlineKeyboardButton.setCallbackData(String.valueOf("delete"));
        rowInline.add(inlineKeyboardButton);

        inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText("➡️");
        inlineKeyboardButton.setCallbackData("next");
        rowInline.add(inlineKeyboardButton);

        rowsInline.add(rowInline);

        markupInline.setKeyboard(rowsInline);
        return markupInline;

    }




}
