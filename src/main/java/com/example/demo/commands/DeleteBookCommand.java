package com.example.demo.commands;

import com.example.demo.helpers.DeleteHelper;
import com.example.demo.helpers.DoctorEnum;
import com.example.demo.helpers.LookHelper;
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
import java.util.Collections;
import java.util.List;

@Component
public class DeleteBookCommand implements WorKerCommand
{

    @Autowired
    BookRepo  bookRepo;
    @Override
    public SendMessage start(Update update) {

        List<String> list = LookHelper.getLooks(update.getMessage().getFrom().getId().toString());
        boolean checkBook = false;
        for (String str: list)
        {
            if(update.getMessage().getText().equals(str))
            {checkBook=true;}
        }
        if (checkBook == false)
        {
            return null;
        }
        else
        {
            String[] parts = update.getMessage().getText().split(" - ");
            String doctor = parts[0];
            String time = parts[1];
            DoctorEnum anEnum = DoctorEnum.valueOf(doctor);
            String tgID = update.getMessage().getFrom().getId().toString();
            DeleteHelper.deleteBook(anEnum,tgID,time);
            return null;
        }
    }

    @Override
    public SendMessage sendDefaultMessage(Update update) {
        return null;
    }
}
