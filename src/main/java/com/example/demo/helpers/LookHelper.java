package com.example.demo.helpers;

import com.example.demo.models.BookModel;
import com.example.demo.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LookHelper
{
    @Autowired
    BookRepo bookRepo;
    private static LookHelper lookHelper = null;

    public LookHelper() {
        lookHelper = this;
    }
    public static List<String> getLooks(String tgId)
    {
        List<BookModel> models =
                lookHelper.bookRepo.findBookModelByTgId(tgId);
        List<String> listLooks  = new ArrayList<>();
        for (BookModel b: models)
        {
            listLooks.add(b.getDoctorEnum().toString()+" - "+b.getTime().toString()+" - Снять бронь");
        }
        return listLooks;
    }
}
