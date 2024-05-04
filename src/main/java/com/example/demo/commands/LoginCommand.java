package com.example.demo.commands;

import com.example.demo.helpers.UserHelper;
import com.example.demo.models.UserModel;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Collections;

@Component
public class LoginCommand implements WorKerCommand{
    @Override
    public SendMessage start(Update update) {
        if(!update.getMessage().getText().equals("Log in")&& !update.getMessage().getText().equals("Оставить свое имя")
                &&!update.getMessage().getText().equals("Остаться анонимным"))
        {
            return null;
        }
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Выберите действие");
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        if(update.getMessage().getText().equals("Log in"))
        {
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(new KeyboardButton("Оставить свое имя"));
            keyboardRow.add(new KeyboardButton("Остаться анонимным"));

            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            replyKeyboardMarkup.setKeyboard(Collections.singletonList(keyboardRow));
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
        }
        UserModel userModel = new UserModel();
        userModel.setUsername(update.getMessage().getFrom().getUserName());
        userModel.setTgId(update.getMessage().getFrom().getId().toString());


        if (update.getMessage().getText().equals("Остаться анонимным"))
        {
            sendMessage.setText("Пользователь сохранен");
            UserHelper.saveUser(userModel);
        }
        if (update.getMessage().getText().equals("Оставить свое имя"))
        {
            sendMessage.setText("Пользователь сохранен");
            userModel.setName(update.getMessage().getFrom().getFirstName());
            UserHelper.saveUser(userModel);
        }
        return  sendMessage;
    }

    @Override
    public SendMessage sendDefaultMessage(Update update) {
        return null;
    }
}