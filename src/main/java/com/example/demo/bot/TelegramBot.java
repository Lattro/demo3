package com.example.demo.bot;

import com.example.demo.commands.*;
import com.example.demo.commands.bookcommand.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot
{
    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public void onUpdateReceived(Update update)
    {
        KeyboardRow k = new KeyboardRow();
        k.add(new KeyboardButton("Log in"));
        k.add(new KeyboardButton("Записаться к врачу"));
        k.add(new KeyboardButton("Просмотр записей"));
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText("Выберите действие");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(Collections.singletonList(k));
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        List<WorKerCommand> list = new ArrayList<>();
        list.add(new LoginCommand());
        list.add(new BookCommand());
        list.add(new TerapevtBookCommand());
        list.add(new AlergologBookCommand());
        list.add(new GinekologBookCommand());
        list.add(new HirurgBookCommand());
        list.add(new LorBookCommand());
        list.add(new OkulistBookCommand());
        list.add(new ChooseTime());
        list.add(new LookCommand());
        list.add(new DeleteBookCommand());

        for (WorKerCommand w : list )
        {
            if(w.start(update)!=null)
            {
                sendMessage = w.start(update);
                break;
            }
        }
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

//     if(update.hasMessage())
//     {
//         Message incomeMessage =  update.getMessage();
//         Long chatId = incomeMessage.getChatId();
//         if(incomeMessage.hasText())
//         {
//             String text = incomeMessage.getText();
//             if(text.equals("/start"))
//             {
//                 sendMessage(chatId, "1111");
//             }
//             else
//             {
//                 sendMessage(chatId, "fffff");
//             }
//         }
//         else
//         {
//             sendMessage(chatId,"bbbbbbbbb");
//         }
//     }

    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    private void sendMessage(Long chatId, String messageToSend)
    {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(messageToSend);
        try
        {
            execute(sendMessage);
        }
        catch (TelegramApiException e)
        {
            e.printStackTrace();
        }
    }
}
