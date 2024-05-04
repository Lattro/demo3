package com.example.demo.helpers;

import com.example.demo.models.BookModel;
import com.example.demo.repos.BookRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteHelper
{
    @Autowired
    BookRepo bookRepo;
    private static DeleteHelper deleteHelper = null;

    public DeleteHelper() {
        deleteHelper = this;
    }
   @Transactional
    public static void deleteBook( DoctorEnum anEnum,String tgId, String time)
    {
        deleteHelper.bookRepo.deleteBookModelByDoctorEnumAndAndTgIdAndTime(anEnum,tgId,time);
    }
}
