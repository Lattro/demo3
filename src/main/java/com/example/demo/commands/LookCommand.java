package com.example.demo.commands;

import com.example.demo.helpers.DoctorHelper;
import com.example.demo.helpers.LookHelper;
import com.example.demo.models.BookModel;
import com.example.demo.repos.BookRepo;
import com.example.demo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;


@Component
public class LookCommand implements WorKerCommand {

    @Autowired
    BookRepo bookRepo;
    @Autowired
    UserRepo userRepo;


    @Override
    public SendMessage start(Update update) {
        if(!update.getMessage().getText().equals("Просмотр записей"))
        {
            return null;
        }
        List<String> list = LookHelper.getLooks(update.getMessage().getFrom().getId().toString());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        if(list.size()==0)
        {
            sendMessage.setText("У вас нет текущих записей");
            return sendMessage;
        }
        else
        {
            for (int i = 0; i < list.size(); i++)
            {
                keyboardRows.add(new KeyboardRow());
                keyboardRows.get(i).add(new KeyboardButton(list.get(i)));
            }
        }
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        sendMessage.setText("Выберите запись");
        return sendMessage;
    }

    @Override
    public SendMessage sendDefaultMessage(Update update) {
        return null;
    }
}