//package com.example.demo.commands.bookcommand;
//
//import com.example.demo.commands.WorKerCommand;
//import com.example.demo.helpers.DoctorEnum;
//import com.example.demo.repos.BookRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class Delete implements WorKerCommand
//{
//    @Autowired
//    BookRepo bookRepo;
//    @Override
//    public SendMessage start(Update update) {
//        if(!update.getMessage().getText().equals("Снять бронь"))
//        {
//            return null;
//        }
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(update.getMessage().getChatId().toString());
//
//        String[] parts = update.getMessage().getText().split(" : ");
//        String doctor = parts[0];
//        String time = parts[1];
//        DoctorEnum anEnum = DoctorEnum.valueOf(doctor);
//        String tgID = update.getMessage().getFrom().getId().toString();
//        bookRepo.deleteBookModelByDoctorEnumAndAndTgIdAndTime(anEnum,tgID,time);
//        sendMessage.setText("Бронирование снято");
//        return sendMessage;
//    }
//
//    @Override
//    public SendMessage sendDefaultMessage(Update update) {
//        return null;
//    }
//}
//}
