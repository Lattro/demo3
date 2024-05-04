package com.example.demo.commands;

import com.example.demo.helpers.DoctorHelper;
import com.example.demo.helpers.TimeControl;
import com.example.demo.helpers.UserHelper;
import com.example.demo.models.BookModel;
import com.example.demo.models.UserModel;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class ChooseTime  implements WorKerCommand{
    @Override
    public SendMessage start(Update update) {
        TimeControl timeControl = new TimeControl();
        List<String> list  = timeControl.getTimes();
        boolean ifThisCommand = false;
        for (String str:list )
        {
            if (update.getMessage().getText().equals(str))
            {
                ifThisCommand = true;
            }
        }
        if (!ifThisCommand)
        {
            return null;
        }
        BookModel bootModel = new BookModel();
        bootModel.setTime(update.getMessage().getText().toString());
        UserModel userModel = new UserModel();
        userModel = UserHelper.findUser(update.getMessage().getFrom().getId().toString());
        bootModel.setTgId(update.getMessage().getFrom().getId().toString());
        bootModel.setDoctorEnum(userModel.getDoctorEnum());
        DoctorHelper.save(bootModel);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText("ВЫ успешно записались к врачу");
        return sendMessage;
    }

    @Override
    public SendMessage sendDefaultMessage(Update update) {
        return null;
    }
}