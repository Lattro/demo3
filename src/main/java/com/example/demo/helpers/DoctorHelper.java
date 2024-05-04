package com.example.demo.helpers;

import com.example.demo.models.BookModel;
import com.example.demo.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DoctorHelper
{
    @Autowired
    BookRepo bookRepo;
    private static DoctorHelper doctorHelper = null;

    public DoctorHelper() {
        doctorHelper = this;
    }
    public static void save(BookModel bootModel)
    {
        doctorHelper.bookRepo.save(bootModel);
    }
    public static List<String> getFreeTimes(DoctorEnum anEnum)
    {
        TimeControl  timeControl= new TimeControl();
        List<BookModel> models =
                doctorHelper.bookRepo.findBootModelByDoctorEnum(anEnum);
        List<String> listFreeTimes  = new ArrayList<>();
        listFreeTimes = timeControl.getTimes();
        List<String> list = new ArrayList<>();
        for (BookModel b: models)
        {
            for (String str:  listFreeTimes)
            {
                if(b.getTime().equals(str))
                {
                    list.add(b.getTime());
                }
            }
        }
        listFreeTimes.remove(list);
        return listFreeTimes;
    }
}